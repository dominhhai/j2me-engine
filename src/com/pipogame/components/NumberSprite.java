package com.pipogame.components;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Image represents the number sprite must have its numbers in a row
 * start from 0 to 9
 * @author Honghai
 */
public class NumberSprite {
    
    private Image numImg;
    private int width;
    private int height;
    
    
    public NumberSprite(Image img) {
        numImg = img;
        this.width = img.getWidth() / 10;
        this.height = img.getHeight();
    }
    
    public void draw(Graphics g, int number, int x, int y) {
        draw(g, String.valueOf(number), x, y);
    }
    
    private void draw(Graphics g, String number, int x, int y) {
        int length = number.length();
        
        for (int i = 0; i < length; i++) {
            int a = number.charAt(i);
            a -= 48;
            g.drawRegion(numImg, a * width, 0, width, height,
                    Sprite.TRANS_NONE, x + i * width, y, 0);
        }
    }
    
    public void drawCenter(Graphics g, int number, int x, int y) {
        String nums = String.valueOf(number);
        int length = nums.length();
        
        x -= (length) * width / 2;
        
        draw(g, nums, x, y);
    }
}
