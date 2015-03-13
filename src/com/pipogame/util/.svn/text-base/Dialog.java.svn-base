package com.pipogame.util;

import com.pipogame.*;
import javax.microedition.lcdui.Graphics;

/**
 * Tiện ích để render text. Các dòng chữ sẽ được render từ từ cho đến khi hết
 * tất cả các chữ cái. Nếu số dòng vượt quá chiều cao của Dialog thì nó sẽ tự 
 * động được đẩy lên.
 *
 * @author Honghai
 * @company B-Gate
 */
public class Dialog {

    ScreenManager drawer;

    /**
     * x position to draw text.
     */
    private final int x;
    /**
     * y position to draw text.
     */
    private final int y;
    /**
     * Vị trí bắt đầu của text.
     */
    private int y2;
    public static final int TIME_SLIDE = 500;
    private float timeSlide;
    /**
     * Width and height of the text to draw, not the out box
     */
    private int width, height;

    private int[] space_width;
    /**
     * Length of the whole text
     */
    private int length;
    /**
     * Length of text drawn to the screen
     */
    private int lengthDrawn;
    private int timeCounter;

    private byte[][] text;

    public static final int TIME_STOP = 1000;
    int timeStop;

    
    /**
     *
     * @param text nội dung cần render
     * @param x the value of x
     * @param y the value of y
     * @param width the value of width
     * @param height the value of height
     * @param drawer the value of drawer
     * @param justify the value of justify
     */
    public Dialog(byte[][] text, int x, int y,
            int width, int height, ScreenManager drawer, boolean justify) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.y2 = y;
        this.width = width;
        this.height = height;
        this.drawer = drawer;
        this.space_width = new int[text.length];
        if (justify) {
            int numSpace;
            for (int k = 0; k < text.length - 1; k++) {
                length += text[k].length;
                numSpace = 0;
                for (int i = 0; i < text[k].length; i++) {
                    if (text[k][i] == PFont.SPACE) {
                        numSpace++;
                    }
                }
                if (numSpace == 0) {
                    numSpace = 1;
                }
                space_width[k] = 
                        (width - PFont.getStringWidth(text[k])) / numSpace +
                        PFont.SPACE_WIDTH;
            }
        }
        length += text[text.length - 1].length;
        space_width[space_width.length - 1] = PFont.SPACE_WIDTH;
    }
    
    public Dialog(byte[] text, int x, int y,
            int width, int height, ScreenManager drawer, boolean justify) {
        this.text = PFont.breakString(text, width);
        this.x = x;
        this.y = y;
        this.y2 = y;
        this.width = width;
        this.height = height;
        this.drawer = drawer;
        this.space_width = new int[text.length];
        if (justify) {
            int numSpace;
            for (int k = 0; k < text.length - 1; k++) {
                length += this.text[k].length;
                numSpace = 0;
                for (int i = 0; i < this.text[k].length; i++) {
                    if (this.text[k][i] == PFont.SPACE) {
                        numSpace++;
                    }
                }
                if (numSpace == 0) {
                    numSpace = 1;
                }
                space_width[k] = 
                        (width - PFont.getStringWidth(this.text[k])) / numSpace +
                        PFont.SPACE_WIDTH;
            }
        }
        length += this.text[text.length - 1].length;
        space_width[space_width.length - 1] = PFont.SPACE_WIDTH;
    }

    /**
     * Draw the dialog in animation
     * @param g Graphics object
     * @param frameTime Time of each frame
     * @return <code><b>true</b></code> if the animation is done<br/>
     * otherwise return <code><b>false</b></code>
     */
    public boolean drawDialog(Graphics g, int frameTime, boolean cancelOutArea,
                                                         boolean justify) {
        if (lengthDrawn < length) {
            timeCounter += frameTime;
            if (timeCounter >= 66) {
                timeCounter -= 66;
                lengthDrawn++;
            }
        } else {
            lengthDrawn = length;
            timeCounter = 0;
        }
        byte t_lines = 1;
        short t_lenDrawn = 0;

        for (int i = 0; i < text.length; i++) {
            t_lenDrawn += text[i].length;
            if (t_lenDrawn < lengthDrawn) {
                t_lines++;
            } else {
                break;
            }
        }
        t_lines -= height / PFont.HEIGHT;

        if (t_lines == 2) {
            t_lenDrawn += 10;
        }

        if (t_lines > 0) {
            int t_height = y - t_lines * PFont.HEIGHT;
            if (y2 > t_height) {
                y2 = (int)(Util.sqr(1 - timeSlide / TIME_SLIDE) * PFont.HEIGHT)
                        + t_height;
                timeSlide += frameTime;
                if (timeSlide > TIME_SLIDE) {
                    timeSlide = TIME_SLIDE;
                }
            } else {
                timeSlide = 0;
            }
        }

        int drawn = 0;

        if (cancelOutArea) {
            g.setClip(x, y, width, height);
        }

        int spaceWidth;

        int y_draw = y2 - PFont.HEIGHT;
        for (int i = 0; i < text.length; i++) {
            y_draw += PFont.HEIGHT;
            if (justify) {
                spaceWidth = space_width[i];
            } else {
                spaceWidth = PFont.SPACE_WIDTH;
            }
            if (drawn + text[i].length <= lengthDrawn) {
                if (needDraw(y_draw)) {
                    PFont.drawString(g, x, y_draw, text[i],
                                     0, PFont.COLOR_WHITE, 0,
                                     text[i].length, spaceWidth);
                }
                drawn += text[i].length;
            } else if (drawn < lengthDrawn) {
                if (needDraw(y_draw)) {
                    PFont.drawString(g, x, y_draw, text[i],
                                 0, PFont.COLOR_WHITE, 0,
                                 (int)lengthDrawn - drawn, spaceWidth);
                }
                drawn = lengthDrawn;
            } else {
                break;
            }
        }
        
        if (cancelOutArea) {
            drawer.restoreClipping();
        }

        if (lengthDrawn >= length) {
            timeStop += frameTime;
            if (timeStop > TIME_STOP) {
                timeStop = 0;
                return true;
            }
        }
        return false;
    }

    private boolean needDraw(int y) {
        return y >= this.y - PFont.HEIGHT && y <= this.y + height;
    }
    
    /**
     * Báo cho Dialog biết phải draw hết nội dung ngay lập tức.
     */
    public void finishDraw() {
        if (lengthDrawn < length) {
            lengthDrawn = length - 1;
        }
    }

}
