package com.pipogame.components;

import com.pipogame.Debug;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Honghai
 * @company B-Gate
 */
public class GameSprite extends Sprite implements ISprite {
    private int timeSequence = 80;
    private int timePlayedOnFrame;
    private boolean isLastFrameReached;
    private Image image;

    public GameSprite(Image image) {
        super(image);
        this.image = image;
    }
//
//    public GameSprite(Sprite sprite) {
//        super(sprite);
//    }

    public GameSprite(GameSprite gs) {
        super(gs);
        this.timeSequence = gs.timeSequence;
        this.image = gs.image;
    }

    /**
     * Construct a new GameSprite
     * @param image image source
     * @param frameWidth frame width
     * @param frameHeight frame height
     */
    public GameSprite(Image image, short frameWidth, short frameHeight) {
        super(image, frameWidth, frameHeight);
        this.image = image;
    }
    
    /**
     * Construct a new GameSprite by an image and number of
     * frames in Horizon and Vertical
     * @param image Image source of sprite
     * @param numFramesHorizon frames in horizon
     * @param numFramesVertical frames in vertical
     */
    public GameSprite(Image image, int numFramesHorizon, int numFramesVertical) {
        super(image, image.getWidth()/numFramesHorizon,
                     image.getHeight()/numFramesVertical);
        this.image = image;
    }
    
    public void setTimeSequence(int timeSequence) {
        this.timeSequence = timeSequence;
    }
    
    public void setFootPosition(int x, int y) {
        setPosition(x, y - getHeight());
    }

    public void updateFrame(int frameTime, boolean looping) {
        this.timePlayedOnFrame += frameTime;

        if (timePlayedOnFrame >= timeSequence) {
            if (looping || getFrame() != getFrameSequenceLength() - 1) {
                nextFrame();
                timePlayedOnFrame -= timeSequence;
            }
        }
        if (getFrame() == getFrameSequenceLength() - 1) {
            isLastFrameReached = true;
        }
    }
    
    protected float dx, dy;

    public void move(int speedX, int speedY, int frameTime) {
        dx += speedX * frameTime / 1000f;
        dy += speedY * frameTime / 1000f;
        move((int) dx, (int) dy);
        dx -= (int) dx;
        dy -= (int) dy;
    }
    
    public void paintCenterX(Graphics g, int x, int y) {
        setPosition(x - getWidth() / 2, y);
        paint(g);
    }
    
    public void paintCenterY(Graphics g, int x, int y) {
        setPosition(x , y - getHeight() / 2);
        paint(g);
    }
    
    public void paintCenter(Graphics g) {
        setPosition(getX() - getWidth() / 2, getY() - getHeight() / 2);
        paint(g);
        setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    public int getTimeSequence() {
        return timeSequence;
    }

    public int getTimePlayedOnFrame() {
        return timePlayedOnFrame;
    }

    /**
     * Kiểm tra xem đã phải là frame cuối cùng. Chỉ cho kết quả đúng nếu sprite
     * đó <code>updateFrame</code> với <code>looping = false</code>.
     *
     * @return <code>true</code> &ndash; nếu là frame cuối.
     */
    public boolean isLastFrame() {
        return timePlayedOnFrame >= timeSequence 
                && getFrame() == getFrameSequenceLength() - 1;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isLastFrameWhenLooping() {
        return isLastFrameReached;
    }

    /**
     * Reset về trạng thái ban đầu.
     */
    public void reset() {
        timePlayedOnFrame = 0;
        isLastFrameReached = false;
        setFrame(0);
    }
    
    /**
     * Xem {@linkplain #GameSprite(javax.microedition.lcdui.Image, int, int)}.
     * @param i
     * @param numFrameHorizon
     * @param numFrameVertical
     * @return 
     */
    public static Sprite newSprite(Image i, int numFrameHorizon,
            int numFrameVertical) {
        return new Sprite(i, i.getWidth()/numFrameHorizon, 
                i.getHeight()/numFrameVertical);
    }

    /**
     * 
     * @return 
     */
    public Image getImage() {
        return image;
    }

    /**
     * Tương tự {@linkplain Sprite#defineCollisionRectangle(int, int, int, int)}.
     * @param r tương ứng các tham số của
     * defineCollisionRectangle(int, int, int, int)
     */
    public void addCollisionRectangle(Rect r) {
        defineCollisionRectangle(r.x, r.y, r.width, r.height);
    }

    /**
     * Cho sprite không có CollisionRectangle, r có thể null do không sử dụng đến.
     * @param r &ndash; nullable, khác null cũng vẫn thế :))
     */
    public void removeCollisionRectangle(Rect r) {
        defineCollisionRectangle(0, 0, 0, 0);
    }

    /**
     * Lấy toạ độ chân sprite.
     * @return 
     */
    public int getFootY() {
        return getY() + getHeight();
    }
    
    /**
     * Lấy toạ độ bên trái sprite.
     * @return 
     */
    public int getLeftX() {
        return getX() + getWidth();
    }

    /**
     * Đặt toạ độ sprite theo chân của nó.<br/>
     * Đặt chân sprite vào toạ độ (x, y)
     * @param x
     * @param y 
     */
    public void setPositionFoot(int x, int y) {
        setPosition(x, y - getHeight());
    }
    
    public void drawAll(Graphics g) {
        if (Debug.ENABLE) {
            for (int i = 0; i < getFrameSequenceLength(); i++) {
                setPosition(getWidth() + getX(), getY());
                paint(g);
                nextFrame();
            }
        }
    }
}
