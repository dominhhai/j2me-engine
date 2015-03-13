
package com.pipogame;

import com.pipogame.components.ISprite;
import com.pipogame.components.Point;
import com.pipogame.components.Rect;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;


/**
 * Chứa trạng thái input của game, bao gồm cả trạng thái của keycode, keystate
 * và touch nếu hỗ trợ của thiết bị di động.
 * Nếu 
 */
public class Input {
    private int keyState;
    private int oldKeyState;
    private int keyCode;
    private int oldKeyCode;
    private int keyCodeRepeated;
    private int keyCodeReleased;
    private int touchX;
    private int touchY;
    private int translateX;
    private int translateY;
    private boolean touched;
    private boolean touchUp;
    private int oldTouchX;
    private int oldTouchY;
    private int distanceDrag;
    
    
    public final boolean touchSupported;
    
    private KeyCodeAdapter adapter;

    /**
     * Khởi tạo một đối tượng lưu trữ và xử lý input.
     * @param gameCanvas Canvas 
     */
    public Input(ScreenManager gameCanvas, boolean touchSupported,
            Point translate) {
        adapter = KeyCodeAdapter.getInstance(gameCanvas);
        this.touchSupported = touchSupported;
        translateX = translate.getX();
        translateY = translate.getY();
    }

    void setNewKeyState(int keyState) {
        this.keyState = keyState;
    }

    void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
    
    void setSoftkeyLeftPrd() {
        this.keyCode = adapter.SOFTKEY_LEFT;
    }

    void setSoftkeyRightPrd() {
        this.keyCode = adapter.SOFTKEY_RIGHT;
    }
    
    void setKeyReleased(int keyCodeReleased) {
        this.keyCodeReleased = keyCodeReleased;
    }

    void setKeyRepeated(int keyCodeRepeated) {
        this.keyCodeRepeated = keyCodeRepeated;
    }
    
    /**
     * Lấy lại keyCode.
     * <strike>Key code được map từ lớp {@link KeyCodeAdapter}.</strike>
     * @return 
     */
    public int getKeyCode() {
//        return KeyCodeAdapter.adoptKeyCode(keyCode);
        return keyCode;
    }

    public int getKeyCodeReleased() {
        return keyCodeReleased;
    }

    public int getKeyCodeRepeated() {
        return keyCodeRepeated;
    }
    
    public boolean isKeyCodePressed(int keyCode) {
        return keyCode == this.keyCode;
    }
    
    public boolean isKeyCodeRepeated(int keyCode) {
        return keyCode == this.keyCodeRepeated;
    }
    
    public boolean isKeyCodeReleased(int keycode) {
        return keycode == this.keyCodeReleased;
    }

    void resetKey() {
        oldKeyState = keyState;
        oldKeyCode = keyCode;
        keyState = 0;
        keyCode = 0;
        keyCodeRepeated = 0;
        keyCodeReleased = 0;
        touchX = Integer.MIN_VALUE;
        touchY = Integer.MIN_VALUE;
        touchUp = false;
    }

    /**
     * Check if Left key pressed
     */
    public boolean isLeftPrd() {
        return (keyState & GameCanvas.LEFT_PRESSED) != 0;
    }

    /**
     * Check if Right key pressed
     */
    public boolean isRightPrd() {
        return (keyState & GameCanvas.RIGHT_PRESSED) != 0;
    }

    /**
     * Check if Up key pressed
     */
    public boolean isUpPrd() {
        return (keyState & GameCanvas.UP_PRESSED) != 0;
    }

    /**
     * Check if Down key pressed
     */
    public boolean isDownPrd() {
        return (keyState & GameCanvas.DOWN_PRESSED) != 0;
    }

    /**
     * Check if Fire key pressed
     */
    public boolean isFirePrd() {
        return (keyState & GameCanvas.FIRE_PRESSED) != 0;
    }

    /**
     * Check if any key pressed
     */
    public boolean isSomeKeyPrd() {
        return keyState != 0 || keyCode != 0;
    }

    /**
     * Kiểm tra có phải phím mềm trái đã đc ấn.
     */
    public boolean isSoftLeftPrd() {
        return keyCode == adapter.SOFTKEY_LEFT;
    }

    /**
     * Kiểm tra có phải phím mềm phải đã đc ấn.
     */
    public boolean isSoftRightPrd() {
        return keyCode == adapter.SOFTKEY_RIGHT;
    }

    /**
     * Kiểm tra có phải cùng một keyState của lần lấy keyState này so với 
     * lần lấy keyState trước đã được ấn.
     * @return <code>true</code> &ndash; nếu cùng keyState ở cả 2 frame
     * này và frame trước
     */
    public boolean isSameKeyState() {
        return keyState != 0 && keyState == oldKeyState;
    }
    
    /**
     * Kiểm tra có phải cùng một keyCode của lần lấy keyCode này so với 
     * lần lấy keyCode trước đã được ấn.
     * @return <code>true</code> &ndash; nếu cùng keyCode ở cả 2 frame
     * này và frame trước
     */
    public boolean isSameKeyCode() {
        return keyCode != 0 && keyCode == oldKeyCode;
    }
    
    /**
     * Update vị trí con trỏ touch
     * @param x
     * @param y 
     */
    void setTouchPosition(int x, int y) {
        touchX = x - translateX;
        touchY = y - translateY;
    }
    
    void setTouchUpPosition(int x, int y) {
        touchUp = true;
        touched = false;
        touchX = x - translateX;
        touchY = y - translateY;
    }

    public int getTouchX() {
        return touchX;
    }

    public int getTouchY() {
        return touchY;
    }

    public boolean isTouchSupported() {
        return touchSupported;
    }

    public boolean isTouched() {
        return touched;
    }
    
    public boolean isTouchUp() {
        return touchUp;
    }
    
    /**
     * Kiểm tra xem có phải điểm vừa chạm trên màn hình có nằm trong hình chữ
     * nhật này không.
     * @param x toạ độ x &ndash; góc trên bên trái hình chữ nhật
     * @param y toạ độ y &ndash; góc trên bên trái hình chữ nhật
     * @param width chiều rộng hình chữ nhật
     * @param height chiều dài hình chữ nhật
     * @return <code>true</code>&ndash;nếu điểm vừa chạm nằm trong hình chữ nhật 
     * được định nghĩa trong danh sách tham số.
     */
    public boolean touched(int x, int y, int width, int height) {
        if (x <= touchX && touchX <= x + width
                && y <= touchY && touchY <= y + height) {
            return true;
        }
        return false;
    }
    
    public boolean touched(Rect r) {
        return r.contains(touchX, touchY);
    }
    
    public boolean touched(Sprite sprite) {
        return touched(sprite.getX(), sprite.getY(),
                sprite.getWidth(), sprite.getHeight());
    }
    
    public boolean touched(ISprite sprite) {
        return touched(sprite.getX(), sprite.getY(),
                sprite.getWidth(), sprite.getHeight());
    }

    public String toString() {
        return "keystate " + keyState + "; key code " + keyCode;
    }

    public int getKeyState() {
        return keyState;
    }
}
