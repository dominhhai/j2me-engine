package com.pipogame.components;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Interface for sprites.
 * @author HaiNH
 * @company B-Gate
 */
public interface ISprite {
    public void paint(Graphics g);
    /**
     * Đặt thời gian cho một frame. Sau thời gian này Sprite sẽ next frame 
     * một lần.
     * @param timeSequence Thời gian 1 frame tính bằng milli giây
     */
    public void setTimeSequence(int timeSequence);
    /**
     * Update frame theo thời gian. Chỉ có tác dụng update cho việc next frame.
     * @param frameTime
     * @param looping nếu <code>true</code>, frame sẽ được lặp, còn 
     * <code>false</code> thì sẽ chạy đến frame cuối rồi dừng.
     */
    public void updateFrame(int frameTime, boolean looping);
    /**
     * Khởi đặt lại thông số thời gian của frame (đối với looping = false), 
     * cho đặt frame hiện tại là frame 0.
     * <br/>Xem {@linkplain #updateFrame(int, boolean)}
     */
    public void reset();
    public Image getImage();
    public void nextFrame();
    public void prevFrame();
    /**
     * Di chuyển sprite theo thời gian và vận tốc, tính bằng pixel/s.
     * @param speedX Vận tốc theo phương trục x
     * @param speedY Vận tốc theo phương trục y
     * @param frameTime 
     */
    public void move(int speedX, int speedY, int frameTime);
    /**
     * Lấy vị trí góc trên bên trái theo trục x.
     * @return 
     */
    public int getX();
    /**
     * Lấy vị trí góc trên bên trái theo trục y.
     * @return 
     */
    public int getY();
    
    /**
     * Lấy toạ độ chân sprite theo trục y.
     * @return 
     */
    public int getFootY();
    /**
     * Tương tự {@linkplain Sprite#getWidth()}.
     * @return 
     */
    public int getWidth();
    /**
     * Tương tự {@linkplain Sprite#getHeight()}.
     * @return 
     */
    public int getHeight();
    
    /**
     * Tương tự {@linkplain Sprite#getFrame()}.
     * @return 
     */
    public int getFrame();
    
    /**
     * Tương tự {@linkplain Sprite#setFrame(int) }.
     * @return 
     */
    public void setFrame(int sequenceIndex);
    
    /**
     * Tương tự {@linkplain Sprite#setPosition(int, int)}.
     * @return 
     */
    public void setPosition(int x, int y);
    /**
     * Đặt vị trí góc dưới bên trái của Sprite vào toạ độ (x, y).
     */
    public void setPositionFoot(int x, int y);
    
    /**
     * Tương tự {@linkplain Sprite#setVisible(boolean) }.
     * @return 
     */
    public void setVisible(boolean visible);
    
    /**
     * Tương tự {@linkplain Sprite#isVisible() }.
     * @return 
     */
    public boolean isVisible();
    
    /**
     * Tương tự {@linkplain Sprite#defineCollisionRectangle(int, int, int, int) }.
     * @return 
     */
    public void defineCollisionRectangle(int x, int y, int width, int height);
    public void addCollisionRectangle(Rect r);
    public void removeCollisionRectangle(Rect r);
    public boolean isLastFrame();
}
