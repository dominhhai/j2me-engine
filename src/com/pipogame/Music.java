package com.pipogame;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

/**
 * Chơi các đoạn nhạc MIDI. Các đoạn nhạc có thể là nhạc nền của game hoặc là
 * các âm thanh trong game.<p/>
 * &ndash; Các file âm thanh chỉ có thể chơi từng file tại cùng 1 thời điểm,
 * file nào được play sau sẽ ko nghe được (tự ngắt).<p/>
 * &ndash; Nhạc nền của game sẽ được play theo chế độ loop.<p/>
 * &ndash; Nếu <b>VolumeControl</b> đặt volume = 0 thì tự động tất cả các âm 
 * sẽ bị tắt, kể cả khi mới play lần đầu, phương thức play sẽ return ngay lập
 * tức.<p/>
 * &ndash; Nếu volume được đặt lại &gt; 0 thì các âm lại được bật lại.<p/>
 * @author HaiNH
 * @company B-Gate
 */
public class Music implements Runnable{
    private Player musicPlayer;
    private Hashtable musicResources;
    
    private Hashtable soundPlayers;
    private int volume;
    
    public static String VOLUME_CONTROL = "VolumeControl";
    public static String TYPE_MIDI = "audio/midi";
    
    public Music(int volume) {
        musicResources = new Hashtable(3);
        soundPlayers = new Hashtable(10);
        this.volume = volume;
    }

    public void run() {
    }

    public int getVolume() {
        return volume;
    }
    
    public void changeVolume(int quantity) {
        setVolume(volume + quantity);
    }

    /**
     * Đặt mức volume. Nếu volume tăng từ 0 lên thì music nền sẽ được bật lại
     * nếu nó đã từng được bật, nếu chưa từng bật thì setVolume sẽ ko bật music.
     * @param volume mức âm lượng, từ 0 &ndash;&gt; 100
     * @return <code>true</code> nếu volume được tăng từ mức 0 lên &gt; 0, 
     * <code>false</code> trong các trường hợp còn lại. Có thể sử dụng biến trả
     * về để bật lại âm thanh nếu cần.
     */
    public boolean setVolume(int volume) {
        boolean restartMusic = this.volume == 0 && volume > 0;
        
        if (volume > 100) {
            this.volume = 100;
        } else if (volume <= 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
        
        try {
            if (this.volume > 0) {
                if (musicPlayer != null) {
                    VolumeControl vc = (VolumeControl) musicPlayer
                            .getControl(VOLUME_CONTROL);
                    vc.setLevel(this.volume);
                    if (restartMusic) {
                        musicPlayer.stop();
                        musicPlayer.start();
                        restartMusic = false;
                    }
                }
                
                Enumeration players = soundPlayers.elements();
                while (players.hasMoreElements()) {                    
                    Player player = (Player) players.nextElement();
                    if (player.getState() == Player.STARTED) {
                        VolumeControl vc = (VolumeControl) player
                                .getControl(VOLUME_CONTROL);
                        vc.setLevel(this.volume);
                    }
                }
            } else {
                stopAll();
            }
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }
        
        return restartMusic;
    }

    public void addMusic(String musicKey, String resource) {
        musicResources.put(musicKey, resource);
    }
    
    public void removeMusic(String musickey) {
        musicResources.remove(musickey);
    }
    
    public void stopMusic() {
        try {
            musicPlayer.stop();
        } catch (MediaException ex) {
            if (Debug.ENABLE) {
                ex.printStackTrace();
            }
        }
        musicPlayer = null;
    }
    
    public void addSound(String soundKey, String resource) {
        try {
            Player soundPlayer = Manager.createPlayer(
                    getClass().getResourceAsStream(resource), TYPE_MIDI);
            soundPlayer.prefetch();
            VolumeControl volumeControl = (VolumeControl) soundPlayer
                    .getControl(VOLUME_CONTROL);
            volumeControl.setLevel(volume);
            soundPlayers.put(soundKey, soundPlayer);
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }
    }
    
    public void removeSound(String soundKey) {
        soundPlayers.remove(soundKey);
    }
    
    public void playMusic(String musicKey){
        try {
            if (volume <= 0) {
                if (musicPlayer != null) {
                    musicPlayer.stop();
                }
                return;
            }
            
            musicPlayer = Manager.createPlayer(
                    getClass().getResourceAsStream(
                    (String) musicResources.get(musicKey)), TYPE_MIDI);
            musicPlayer.prefetch();
            VolumeControl volumeControl = (VolumeControl) musicPlayer
                    .getControl(VOLUME_CONTROL);
            volumeControl.setLevel(volume);
            musicPlayer.setLoopCount(-1);
            
            musicPlayer.start();
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopMusic(String musicKey) {
        
    }
    
    public void playSound(String soundKey) {
        if (volume <= 0) {
            return;
        }
        Player player = (Player) soundPlayers.get(soundKey);
        try {
            if (player.getState() == Player.STARTED) {
                player.stop();
            }

            player.start();
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopAll() {
        try {
            if (musicPlayer != null
                    && musicPlayer.getState() == Player.STARTED) {
                musicPlayer.stop();
            }
            Enumeration players = soundPlayers.elements();
            while (players.hasMoreElements()) {
                ((Player) players.nextElement()).stop();
            }
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }
    }
    
    public void finalize() {
        if (musicPlayer != null) {
            musicPlayer.close();
        }

        Enumeration players = soundPlayers.elements();
        while (players.hasMoreElements()) {
            ((Player) players.nextElement()).close();
        }
    }
}
