package com.pipogame;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Lớp <code>Graphics</code> đặc biệt dành riêng cho game, xử lý các tác vụ vẽ
 * thay cho đối tượng {@linkplain Graphics} truyền thống.
 * @author HaiNH
 * @company B-Gate
 */
public class GameGraphics {
    private Graphics g;
    
    public void clipRect(int x, int y, int width, int height) {
            g.clipRect(x, y, width, height);
        }

        public void copyArea(int x_src, int y_src, int width, int height,
                int x_dest, int y_dest, int anchor) {
            g.copyArea(x_src, y_src, width, height, x_dest, y_dest, anchor);
        }

        public void drawArc(int x, int y, int width, int height, int startAngle,
                int arcAngle) {
            g.drawArc(x, y, width, height, startAngle, arcAngle);
        }

        public void drawChar(char character, int x, int y, int anchor) {
            g.drawChar(character, x, y, anchor);
        }

        public void drawChars(char[] data, int offset, int length, int x, int y,
                int anchor) {
            g.drawChars(data, offset, length, x, y, anchor);
        }

        public void drawImage(Image img, int x, int y, int anchor) {
            g.drawImage(img, x, y, anchor);
        }

        public void drawLine(int x1, int y1, int x2, int y2) {
            g.drawLine(x1, y1, x2, y2);
        }

        public void drawRGB(int[] rgbData, int offset, int scanlength, int x,
                int y, int width, int height, boolean processAlpha) {
            g.drawRGB(rgbData, offset, scanlength, x, y, width, height, processAlpha);
        }

        public void drawRect(int x, int y, int width, int height) {
            g.drawRect(x, y, width, height);
        }

        public void drawRegion(Image src, int x_src, int y_src, int width,
                int height, int transform, int x_dest, int y_dest, int anchor) {
            g.drawRegion(src, x_src, y_src, width, height, transform,
                    x_dest, y_dest, anchor);
        }

        public void drawRoundRect(int x, int y, int width, int height,
                int arcWidth, int arcHeight) {
            g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        }

        public void drawString(String str, int x, int y, int anchor) {
            g.drawString(str, x, y, anchor);
        }

        public void drawSubstring(String str, int offset, int len, int x, int y,
                int anchor) {
            g.drawSubstring(str, offset, len, x, y, anchor);
        }

        public void fillArc(int x, int y, int width, int height, int startAngle,
                int arcAngle) {
            g.fillArc(x, y, width, height, startAngle, arcAngle);
        }

        public void fillRect(int x, int y, int width, int height) {
            g.fillRect(x, y, width, height);
        }

        public void fillRoundRect(int x, int y, int width, int height,
                int arcWidth, int arcHeight) {
            g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        }

        public void fillTriangle(int x1, int y1, int x2, int y2,
                int x3, int y3) {
            g.fillTriangle(x1, y1, x2, y2, x3, y3);
        }

        public int getBlueComponent() {
            return g.getBlueComponent();
        }

        public int getClipHeight() {
            return g.getClipHeight();
        }

        public int getClipWidth() {
            return g.getClipWidth();
        }

        public int getClipX() {
            return g.getClipX();
        }

        public int getClipY() {
            return g.getClipY();
        }

        public int getColor() {
            return g.getColor();
        }

        public int getDisplayColor(int color) {
            return g.getDisplayColor(color);
        }

        public Font getFont() {
            return g.getFont();
        }

        public int getGrayScale() {
            return g.getGrayScale();
        }

        public int getGreenComponent() {
            return g.getGreenComponent();
        }

        public int getRedComponent() {
            return g.getRedComponent();
        }

        public int getStrokeStyle() {
            return g.getStrokeStyle();
        }

        public int getTranslateX() {
            return g.getTranslateX();
        }

        public int getTranslateY() {
            return g.getTranslateY();
        }

        public void setClip(int x, int y, int width, int height) {
            g.setClip(x, y, width, height);
        }

        public void setColor(int RGB) {
            g.setColor(RGB);
        }

        public void setColor(int red, int green, int blue) {
            g.setColor(red, green, blue);
        }

        public void setFont(Font font) {
            g.setFont(font);
        }

        public void setGrayScale(int value) {
            g.setGrayScale(value);
        }

        public void setStrokeStyle(int style) {
            g.setStrokeStyle(style);
        }

        public void translate(int x, int y) {
            g.translate(x, y);
        }
}
