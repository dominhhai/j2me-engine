package com.pipogame.util;

import com.pipogame.GameScreen;
import com.pipogame.Input;
import com.pipogame.Store;
import com.pipogame.components.Rect;
import javax.microedition.lcdui.Graphics;
//TODO
/**
 * Message box.<br/>
 * Cách dùng:
 * @author HaiNH
 * @company B-Gate
 */
public class MessageBox {
    private int width;
    private int height;
    private int x;
    private int y;
    private int transitionPos = TIME;
    private int transitionDir = -1;
    private static final int TIME = 500;
    private byte[] ok, cancel;
    private Rect okRect, cancelREct;
    
    public static final int OK = 1;
    public static final int CANCEL = 2;
    public static String OK_STRING = "Đồng ý";
    public static String CANCEL_STRING = "Thôi";
    
    private byte[][] text;
    
    /**
     * Tạo MessageBox bằng dữ liệu ghi trong file.
     * @param filename
     * @param width 
     */
    public MessageBox(String filename, int width) {
        this.text = PFont.breakString(filename, width, Store.ENC_UTF8);
        this.width = width;
        this.height = PFont.HEIGHT * this.text.length;
        ok = PFont.toBytesIndex(OK_STRING, null);
        cancel = PFont.toBytesIndex(CANCEL_STRING, null);
        okRect = new Rect(0, height - PFont.HEIGHT,
                PFont.getStringWidth(ok), PFont.HEIGHT);
        cancelREct = new Rect(0, okRect.y, okRect.width, PFont.HEIGHT);
    }
    
    /**
     * Tạo messgaeBox với dữ liệu trong một store
     * @param assets
     * @param key
     * @param width 
     */
    public MessageBox(Store assets, int key, int width) {
        this.text = PFont.breakString(assets.getString(key), width);
        this.width = width;
        this.height = PFont.HEIGHT * (this.text.length + 1);
        ok = PFont.toBytesIndex(OK_STRING, null);
        cancel = PFont.toBytesIndex(CANCEL_STRING, null);
        okRect = new Rect(0, height - PFont.HEIGHT,
                PFont.getStringWidth(ok), PFont.HEIGHT);
        cancelREct = new Rect(width - okRect.width, okRect.y,
                okRect.width, PFont.HEIGHT);
    }

    public void setY(int y) {
        this.y = y;
        okRect.y = y + height - PFont.HEIGHT;
        cancelREct.y = okRect.y;
    }

    public void setX(int x) {
        this.x = x;
        okRect.x = x;
        cancelREct.x = x + width - cancelREct.width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * Xử lý input.
     * @param input
     * @return <code>OK</code> &ndash; nếu chọn Đồng ý<br/>
     * <code>CANCEL</code> &ndash; nếu chọn Thôi.
     */
    public int handleInput(Input input) {
        if (transitionDir == -1) {
            if (input.isSoftLeftPrd() || input.touched(okRect)) {
                transitionDir = 1;
                return OK;
            } else if (input.isSoftRightPrd() || input.touched(cancelREct)) {
                transitionDir = 1;
                return CANCEL;
            }
        }
        return 0;
    }
    
    public void transOff() {
        transitionDir = 1;
    }
    
    public boolean draw(Graphics g, int frameTime) {
        transitionPos += transitionDir * frameTime;
        if (transitionPos < 0) {
            transitionPos = 0;
        } else if (transitionPos > TIME) {
            transitionPos = TIME;
        }
        float tranpos2 = MathExt.sqr(transitionPos / (float)TIME);
        int xx = x + (int)(width / 2 * tranpos2);
        int yy = y + (int)(height / 2 * tranpos2);
        int ww = width - (xx - x) * 2;
        int hh = height - (yy - y) * 2;
        
        g.setClip(xx, yy, ww, hh);
        Drawer.drawBg(g, xx, yy, ww, hh, 0x88000000);
        for (int i = 0; i < text.length; i++) {
            PFont.drawString(g, x, y + i * PFont.HEIGHT, text[i], 0xffffffff);
        }
        yy = y + height - PFont.HEIGHT;
        g.setColor(0xffff22);
        g.drawLine(x, yy, x + width, yy);
        PFont.drawString(g, x, yy, ok, 0xffff0000);
        PFont.drawStringL(g, x + width, yy, cancel, 0xffff0000);
        g.setClip(0, 0, GameScreen.width(), GameScreen.height());
        if (transitionDir > 0 && transitionPos >= TIME) {
            transitionDir = -1;
            return false;
        }
        return true;
    }
}
