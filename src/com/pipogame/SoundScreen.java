package com.pipogame;

import com.pipogame.util.Drawer;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Màn hình bật tắt âm thanh. Sau khi người dùng chọn bật hay tắt âm thanh thì
 * screen này sẽ exit, vì vậy nên add thêm MenuScreen vào ngay sau SoundScreen
 * trong khi initGame.<p/>
 * Khi người dùng chọn:
 * <ol>
 * <li>Bật âm: nếu volume = 0 thì sẽ được set lên 50, ngược lại thì giữ 
 * nguyên</li>
 * <li>Tắt âm: volume sẽ được set về 0</li>
 * </ol>
 * @author HaiNH
 * @see Music
 * @company B-Gate
 */
public class SoundScreen extends GameScreen {
    private Image speakerImage;
    private Image sndOnImage;
    private Image sndOffImage;

    public void initialize() throws Exception {
        super.initialize();
        setTimeCancelInput(300);
        transitionOnTime = transitionOffTime = 300;
    }

    public void loadContent() throws Exception {
        super.loadContent();
        speakerImage = Image.createImage("/com/pipogame/speakers.png");
        sndOnImage = Image.createImage("/com/pipogame/sOn.png");;
        sndOffImage = Image.createImage("/com/pipogame/sOff.png");;
    }

    public void handleInput(Input input, int frameTime) {
        if (input.isSoftLeftPrd()) {
            if (screenManager.music.getVolume() <= 0) {
                screenManager.music.setVolume(50);
            }
            exitScreen();
        } else if (input.isSoftRightPrd()) {
            screenManager.music.setVolume(0);
            exitScreen();
        }
    }

    public void update(int frameTime, boolean covered) {
    }
    
    public void draw(Graphics g, int frameTime) {
        fillScreen(g, 0);
        Drawer.drawCenter(g, speakerImage, _width / 2, _height / 2);
        g.drawImage(sndOnImage, 0, _height - sndOnImage.getHeight(), 0);
        g.drawImage(sndOffImage, _width - sndOffImage.getWidth(),
                _height - sndOffImage.getHeight(), 0);
        
        switch (screenState) {
            case ScreenState.TRANSITION_OFF:
            case ScreenState.TRANSITION_ON:
                drawFade(g, 0);
        }
    }
}
