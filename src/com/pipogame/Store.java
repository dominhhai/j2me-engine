package com.pipogame;

import com.pipogame.components.InitFromString;
import com.pipogame.util.Util;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.rms.RecordStore;

/**
 * Cung cấp phương tiện lưu trữ các setting trong game, bao gồm điểm cao và các
 * nội dung khác. Store chỉ có thể lưu vào bộ nhớ điện thoại, nhưng có thể 
 * load nội dung từ trong file jar hoặc trên bộ nhớ điện thoại. Encode mặc định
 * được sử dụng là UTF-8
 * @author HaiNH
 * @company B-Gate
 */
public class Store {
    protected Hashtable settings;
    String recordName;
    public static String splitor = new String(new char[]{(char) 7}, 0, 1);
    public static String partSplitor = new String(new char[]{(char) 0}, 0, 1);
    public static final int splitorLeng = splitor.length();
    static final String subKey = "__sub";
    StringBuffer buffer = new StringBuffer(200);
    public static String ENC_UTF8 = "UTF-8";
    public static String EMPTY_STRING = "";
    public static String EXTENSION = "_REC_st";
    
    public static final int LOAD_FROM_RECORDSTORE = 1;
    public static final int LOAD_FROM_JAR = 2;
    
    /**
     * Tạo một đốt tượng <code>Store</code> mới
     * @param recordName Tên của bản ghi (tên thật sự sẽ bao gồm 
     * <code>recordName + "_REC_st"</code>)
     * @param loadFrom LOAD_FROM_RECORDSTORE hay LOAD_FROM_JAR
     */
    public Store(String recordName, int loadFrom) {
        settings = new Hashtable(10);
        this.recordName = recordName + EXTENSION;
        
        switch (loadFrom) {
            case LOAD_FROM_JAR:
                loadFromJar();
                break;
            case LOAD_FROM_RECORDSTORE:
                loadSetting();
                break;
            default:
                throw new IllegalArgumentException("Not supported loader"); 
        }
    }
    
    public static Store loadJar(String fullFilePath) {
        return new Store(fullFilePath, LOAD_FROM_JAR);
    }
    
//    public static Store loadRecordStore(String recordName) {
//        return new Store(recordName, LOAD_FROM_RECORDSTORE);
//    }
    
    private void loadSetting() {
        RecordStore store = null;
        try {
            store = RecordStore.openRecordStore(recordName, false);
            if (store == null) {
                return;
            }
            byte[] record = store.getRecord(1);
            getAllSettings(new String(record, ENC_UTF8));
            store.closeRecordStore();
            if (Debug.ENABLE) {
                System.out.println("Debug info in " +
                        Util.getClassName(getClass().getName()));
                System.out.println("Load settings done!" + recordName);
            }
        } catch (Exception ex) {
            if (store != null) {
                try {
                    store.closeRecordStore();
                } catch (Exception ex1) {
                    if (Debug.ENABLE) {
                        System.out.println("Debug info in " +
                                Util.getClassName(getClass().getName()));
                        ex1.printStackTrace();
                    }
                }
            }
            if (Debug.ENABLE) {
                System.out.println("Debug info in "
                        + Util.getClassName(getClass().getName()));
                System.out.println("Load from RecordStore fail! " + recordName);
                ex.printStackTrace();
            }
        }
        privateGetHighScore();
    }
    
    private void loadFromJar() {
        try {
            InputStream inputStream = getClass()
                    .getResourceAsStream(recordName);
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);
            getAllSettings(new String(buf, ENC_UTF8));
            inputStream.close();
            if (Debug.ENABLE) {
                System.out.println("Debug info in " +
                        Util.getClassName(getClass().getName()));
                System.out.println("Load from jar done! " + recordName);
            }
        } catch (Exception ex) {
            if (Debug.ENABLE) {
                System.out.println("Debug info in "
                        + Util.getClassName(getClass().getName()));
                System.out.println("Load from jar fail! " + recordName);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Lưu nội dung Store vào bộ nhớ điện thoại
     */
    public void save() {
        RecordStore store;
        try {
            RecordStore.deleteRecordStore(recordName);
        } catch (Exception ex) {
            if (Debug.ENABLE) {
                System.out.println("Debug info in " + Util.getClassName(getClass().getName()));
                ex.printStackTrace();
            }
        }
        try {
            store = RecordStore.openRecordStore(recordName, true,
                                                RecordStore.AUTHMODE_PRIVATE,
                                                true);
            if (store == null) {
                return;
            }

            byte[] record = prepairSettings();
            store.addRecord(record, 0, record.length);
            store.closeRecordStore();
            if (Debug.ENABLE) {
                System.out.println("Debug info in " +
                        Util.getClassName(getClass().getName()));
                System.out.println("Saving done! " + recordName);
            }
        } catch (Exception e) {
            if (Debug.ENABLE) {
                System.out.println("Debug info in " +
                        Util.getClassName(getClass().getName()));
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Đặt một nội dung vào Store, nếu nó đã có thì nó sẽ bị ghi đè. Cả khoá
     * và giá trị đều phải khác null
     * @param key khoá, xác định duy nhất
     * @param value 
     */
    public void set(String key, String value) {
        settings.put(key, value);
    }
    
    /**
     * Lấy ra một xâu với khoá <code>key</code> khác <code>null</code> 
     * @param key khoá muốn lấy giá trị
     * @return giá trị tương ứng với 
     * <code>key</code>
     */
    public String getString(String key) {
        Object o = settings.get(key);
        if (o != null) {
            return o.toString();
        }
        return null;
    }
    
    /**
     * Lấy <code>setting</code> với khoá là một số nguyên
     * @param keyNumber khoá
     * @return <code>String</code> tương ứng với <code>key</code>
     */
    public String getString(int keyNumber) {
        return getString(EMPTY_STRING + keyNumber);
    }
    
    private void getAllSettings(String s) {
        settings.clear();
        String key, value;
        int nextLineIdx;
        int start = 0;
        while (start < s.length()) {
            nextLineIdx = s.indexOf(splitor, start);
            if (nextLineIdx > 0) {
                key = s.substring(start, nextLineIdx);
                start = nextLineIdx + splitorLeng;
                nextLineIdx = s.indexOf(splitor, start);
                value = s.substring(start, nextLineIdx);
                start = nextLineIdx + splitorLeng;
                settings.put(key, value);
            } else {
                break;
            }
        }
    }

    private byte[] prepairSettings() {
        try {
            clearBuffer();
            Enumeration e = settings.keys();
            Object o;
            while (e.hasMoreElements()) {
                o = e.nextElement();
                buffer.append(o).append(splitor).append(settings.get(o))
                        .append(splitor);
            }
            return buffer.toString().getBytes(ENC_UTF8);
        } catch (Exception ex) {
            if (Debug.ENABLE) {
                System.out.println("Debug info in " + Util.getClassName(getClass().getName()));
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    private void clearBuffer() {
        buffer.delete(0, buffer.length());
    }

    public void set(String key, int value) {
        settings.put(key, EMPTY_STRING + value);
    }

    /**
     * Lấy ra một số nguyên với khoá là <code>key</code>
     * @param key
     * @return số nguyên ứng với khoá <code>key</code> hoặc 0 nếu chưa tồn tại
     * <code>key</code>.
     */
    public int getInt(String key) {
        String value = (String)settings.get(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }
    
    /**
     * Thay đổi một lượng của số chứa trong entry có khoá là <code>key</code>.
     * @param key khoá
     * @param quantity lượng cần thay đổi
     * @return giá trị mới của entry
     */
    public int changeInt(String key, int quantity) {
        int result = getInt(key);
        result += quantity;
        set(key, result);
        return result;
    }
    
    /**
     * Đưa vào một biến <code>boolean</code>
     * @param key khoá
     * @param value giá trị <code>boolean</code>
     */
    public void set(String key, boolean value) {
        settings.put(key, EMPTY_STRING + value);
    }
    
    /**
     * Lấy ra một giá trị <code>boolean</code>
     * @param key khoá
     * @return biến kiểu <code>boolean</code>
     */
    public boolean getBoolean(String key) {
        String value = (String)settings.get(key);
        if (value != null) {
            return value.startsWith("true");
        }
        return false;
    }
    
    /**
     * Kiểm tra xem có khoá <code>key</code> trong <code>Store</code> hay
     * không
     * @param key khoá cần kiểm tra
     * @return <code>true</code> nếu có, <code>false</code> nếu không
     */
    public boolean contains(String key) {
        return settings.containsKey(key);
    }
    
    /**
     * Đưa vào một mảng số nguyên
     * @param key khoá
     * @param array mảng số nguyên cần lưu trữ
     */
    public void set(String key, int[] array) {
        clearBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i]).append(partSplitor);
        }
        settings.put(key, buffer.toString());
    }
    
    /**
     * Lấy ra mảng số nguyên
     * @param key khoá
     * @return mảng số nguyên nếu tồn tại, <code>null</code> nếu setting với khoá 
     * <code>key</code> không tồn tại
     */
    public int[] getIntArray(String key) {
        String arrayInString = (String)settings.get(key);
        int[] array = null;
        if (arrayInString != null) {
            String[] strings = Util.splitString(arrayInString, partSplitor);
            array = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                array[i] = Integer.parseInt(strings[i]);
            }
        }
        return array;
    }
    
    /**
     * Ghi một mảng nguyên 2 chiều vào setting
     * @param key khoá
     * @param value mảng số nguyên 2 chiều
     */
    public void set(String key, int[][] value) {
        clearBuffer();
        int ii = value.length, jj = value[0].length;
        buffer.append(ii).append(partSplitor).append(ii);
        settings.put(key + subKey, buffer.toString());
        
        clearBuffer();
        for (int i = 0; i < ii; i++) {
            for (int j = 0; j < jj; j++) {
                buffer.append(value[i][j]).append(partSplitor);
            }
        }
        settings.put(key, buffer.toString());
    }
    
    /**
     * Lấy ra mảng số nguyên 2 chiều
     * @param key khoá
     * @return mảng số nguyên 2 chiều nếu tồn tại, <code>null</code> nếu khoá 
     * <code>key</code> không tồn tại
     */
    public int[][] get2DArray(String key) {
        String[] array = Util.splitString((String)settings.get(key + subKey),
                                            partSplitor);
        int ii = Integer.parseInt(array[0]);
        int jj = Integer.parseInt(array[1]);
        int[][] result = new int[ii][jj];
        array = Util.splitString((String)settings.get(key), partSplitor);
        int j;
        for (int i = 0; i < ii; i++) {
            for (j = 0; j < jj; j++) {
                result[i][j] = Integer.parseInt(array[i*jj + j]);
            }
        }
        return result;
    }
    
    /**
     * Đưa vào một mảng các đối tượng có thể khởi tạo từ <code>String</code>
     * <br/>
     * Các đối tượng này phải override phương thức <code>toString()</code>
     * và cài đặt <code>InitFromString.init()</code> để khởi tạo lại từ 
     * <code>String</code> trả về của <code>toString()</code>
     * @param key khoá
     * @param array 
     */
    public void set(String key, InitFromString[] array) {
        clearBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i].toString()).append(partSplitor);
        }
        settings.put(key, buffer.toString());
    }
    
    /**
     * Lấy lại các đối tượng và đưa vào trong mảng <code>aray</code>
     * @param key khoá
     * @param array mảng sẽ chứa các đối tượng lưu trong <code>Store</code>
     */
    public void getArray(String key, InitFromString[] array) {
        String value = getString(key);
        if (value != null) {
            String[] values = Util.splitString(value, partSplitor);
            if (Debug.ENABLE && values.length > array.length) {
                throw new IllegalArgumentException(
                        "Check array size of setting:\n"
                        + key + " in Settings.getArray()");
            }
            for (int i = 0; i < values.length; i++) {
                array[i].init(values[i]);
            }
        }
    }
    
    /**
     * Xoá một setting
     * @param key 
     */
    public void delete(String key) {
        settings.remove(key);
    }
    
    //<editor-fold defaultstate="collapsed" desc="HighScore">   
    private static final String hiscoKey = "hisco_XhVhQ";
    public static final int MAX_HIGH_SCORES = 10;
    private static final int[] HIGH_SCORE = new int[MAX_HIGH_SCORES];
    private void privateGetHighScore() {
        String hisco = getString(hiscoKey);
        if (hisco != null) {
            String[] highScores = Util.splitString(hisco, partSplitor);
            for (int i = 0; i < highScores.length; i++) {
                HIGH_SCORE[i] = Integer.parseInt(highScores[i]);
            }
        }
    }
    
    /**
     * Lấy mảng chứa high score, mảng này không cùng tham chiếu đến mảng trong
     * <code>Store</code>
     * @return mảng chứa high score
     */
    public int[] getHighScores() {
        int[] hs = new int[HIGH_SCORE.length];
        System.arraycopy(HIGH_SCORE, 0, hs, 0, hs.length);
        return hs;
    }
    
    /**
     * Parse in a new high score, if newHs &gt; any old high score, it will be 
     * put in the high score array
     * @param newHS new high score
     * @return index of the new high score or -1 if it's not a new high score
     */
    public int newHighScore(int newHS) {
        if (newHS > HIGH_SCORE[0]) {
            HIGH_SCORE[0] = newHS;
            Util.sort(HIGH_SCORE);
        }
        set(hiscoKey, HIGH_SCORE);
        for (int i = 0; i < HIGH_SCORE.length; i++) {
            if (HIGH_SCORE[i] == newHS) {
                return i;
            }
        }
        return -1;
    }
    //</editor-fold>
}
