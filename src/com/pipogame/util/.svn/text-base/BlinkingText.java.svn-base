package com.pipogame.util;

import com.pipogame.GameScreen;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author HaiNH
 * @company B-Gate
 */
public class BlinkingText {
    protected byte[][] texts;
    protected int[] widths;
    protected int timeCounter;
    public final int time;
    public static final int MIN_TIME = 1000;
    protected int dir = 1;
    protected int[] x, y;
    
//    public BlinkingText(String text, int time, int x, int y) {
//        this(PFont.toBytesIndex(text, null), time, x, y);
//    }
    
    public BlinkingText(byte[] text, int time) {
        this.texts = PFont.breakString(text, GameScreen.width() * 2 / 3);
        this.time = time + MIN_TIME;
        widths = new int[this.texts.length];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = PFont.getStringWidth(texts[i]) / 2;
        }
    }
    
//    public BlinkingText(byte[] text, int time, int y) {
//        this(text, time,
//                (GameScreen.width() - PFont.getStringWidth(text)) / 2, y);
//    }
//    
//    public BlinkingText(byte[] text, int time) {
//        this(text, time, (GameScreen.height() - PFont.HEIGHT) / 2);
//    }
    
    public void draw(Graphics g, int frameTime, int colorOut, int colorIn) {
        update(frameTime);
        
        int transparent = 
                (int)(MathExt.sqr(timeCounter / (float) time) * 255) << 24;
        int _colorOut = transparent | colorOut;
        int _colorIn = transparent | colorIn;
        int center = GameScreen.width() / 2;
        int centerY = (GameScreen.height() - texts.length * PFont.HEIGHT) / 2;
        for (int i = 0; i < this.texts.length; i++) {
            PFont.drawString(g, center - widths[i],
                    centerY + i * PFont.HEIGHT, texts[i], _colorOut, _colorIn);
        }
    }
    
    private void update(int frameTime) {
        timeCounter += dir * frameTime;
        if (timeCounter > time) {
            timeCounter = time;
            dir = -1;
        } else if (timeCounter < MIN_TIME) {
            timeCounter = MIN_TIME;
            dir = 1;
        }
    }
    
    public void draw(Graphics g, int frameTime, int color) {
        update(frameTime);
        
        color = (int)(MathExt.sqr(timeCounter / (float) time) * 255) << 24
                | color;
        int center = GameScreen.width() / 2;
        int centerY = (GameScreen.height() - texts.length * PFont.HEIGHT) / 2;
        for (int i = 0; i < this.texts.length; i++) {
            PFont.drawString(g, center - widths[i],
                    centerY + i * PFont.HEIGHT, texts[i], color);
        }
    }
}
