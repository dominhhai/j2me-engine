package com.pipogame.util;

import com.pipogame.Debug;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Hepler for drawing.
 * @author HaiNH
 * @company B-Gate
 */
public class Drawer {

    private static int current_color;

    private static int current_alpha;

    private static final int[] ARGB20 = new int[400];

    /**
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     * @see #drawBg(javax.microedition.lcdui.Graphics, int, int, int, int, int)
     */
    private static void drawBg(Graphics g, int x, int y,
            int width, int height) {
        if ((current_color & -16777216) == 0) {
            return;
        }
        int mWidth = width / 20;
        int mHeight = height / 20;
        for (int i = 0; i < mWidth; i++) {
            for (int j = 0; j < mHeight; j++) {
                g.drawRGB(ARGB20, 0, 20, x + i * 20, y + j * 20, 20, 20, true);
            }
        }
        int rWidth = width % 20;
        int rHeight = height % 20;
        if (rWidth > 0) {
            drawBg20(g, x + width - rWidth, y, rWidth, height);
        }
        if (rHeight > 0 && width > 20) {
            drawBg20(g, x, y + height - rHeight, width - rWidth, rHeight);
        }
    }

    public static void drawBg(Graphics g, int x, int y, int width, int height,
            int color) {
        setBgColor(color);
        drawBg(g, x, y, width, height);
    }

    private static void drawBg20(Graphics g, int x, int y,
            int width, int height) {
        int i;
        int num;
        int remain;
        if (width < 20) {
            int nHeight = 400 / width;
            num = height / nHeight;
            for (i = 0; i < num; i++) {
                g.drawRGB(ARGB20, 0, width, x, y + i * nHeight,
                        width, nHeight, true);
            }
            remain = height % nHeight;
            if (remain > 0) {
                g.drawRGB(ARGB20, 0, width, x, y + num * nHeight,
                        width, remain, true);
            }
        } else {
            int nWidth = 400 / height;
            num = width / nWidth;
            for (i = 0; i < num; i++) {
                g.drawRGB(ARGB20, 0, nWidth, x + i * nWidth, y,
                        nWidth, height, true);
            }
            remain = width % nWidth;
            if (remain > 0) {
                g.drawRGB(ARGB20, 0, remain, x + num * nWidth, y,
                        remain, height, true);
            }
        }
    }

    public static void setBgAlpha(int alpha) {
        if (Debug.ENABLE) {
            if (alpha > 255 || alpha < 0) {
                System.out.println("Debug info DrawDialog");
                throw new IllegalArgumentException(
                        "Alpha value is not valid: #" + alpha);
            }
        }
        if (current_alpha == alpha) {
            return;
        }
        current_alpha = alpha;
        int color = (alpha << 24) | (ARGB20[0] & 16777215);
        for (int k = ARGB20.length - 1; k >= 0; k--) {
            ARGB20[k] = color;
        }
    }

    private static void setBgColor(int color) {
        if (color == current_color) {
            return;
        }
        current_color = color;
        for (int k = ARGB20.length - 1; k >= 0; k--) {
            ARGB20[k] = color;
        }
    }
    
    /**
     * vẽ căn giữa toạ độ x
     * @param g
     * @param image
     * @param x
     * @param y 
     */
    public static void drawAtCenterX(Graphics g, Image image, int x, int y) {
        g.drawImage(image, x - image.getWidth() / 2, y, 0);
    }
    
    /**
     * Vẽ căn giũa hình ảnh vào điểm (x, y)
     * @param g
     * @param image
     * @param x
     * @param y 
     */
    public static void drawCenter(Graphics g, Image image, int x, int y) {
        g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight()/2, 0);
    }

    public static void drawTransform(Graphics g, Image i, int x, int y,
            int transform) {
        g.drawRegion(i, 0, 0, i.getWidth(), i.getHeight(), transform, x, y, 0);
    }
}
