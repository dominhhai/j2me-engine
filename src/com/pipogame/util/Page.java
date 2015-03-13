package com.pipogame.util;

import com.pipogame.Input;
import com.pipogame.Store;
import javax.microedition.lcdui.Graphics;

/**
 * Tiện ích để render text cho một kiểu hiển thị giống như các trang.
 * @author HaiNH
 * @company B-Gate
 */
public class Page {
    
    protected byte[][] introText;
    private final float TIME = 400;
    private int time = (int) TIME;
    private float pos, posColorText = 1;
    boolean pageChanged;
    private int totalPages, page;
    private int oldPage;
    public final int LINES_PER_PAGE;
    protected int page_x, page_y, width = 180, height;

    public Page(String filename, int linePrePage) {
        LINES_PER_PAGE = linePrePage;
        introText = PFont.breakString(filename, width, Store.ENC_UTF8);
        totalPages = introText.length / LINES_PER_PAGE + 
                     (introText.length % LINES_PER_PAGE == 0 ? 0:1);
        init();
    }
    
    public Page(byte[] text, int linePrePage) {
        LINES_PER_PAGE = linePrePage;
        introText = PFont.breakString(text, width);
        totalPages = introText.length / LINES_PER_PAGE + 
                     (introText.length % LINES_PER_PAGE == 0 ? 0:1);
        init();
    }

    private void init() {
        height = LINES_PER_PAGE * PFont.HEIGHT;
    }

    public void handleInput(Input input, int frameTime) {
        if (input.isLeftPrd()) {
            page--;
            if (page < 0) {
                page = 0;
            } else {
                oldPage = page + 1;
                pageChanged = true;
                time = (int)TIME;
            }
        } else if (input.isRightPrd()) {
            page++;
            if (page == totalPages) {
                page = totalPages - 1;
            } else {
                pageChanged = true;
                oldPage = page - 1;
                time = (int)TIME;
            }
        }
    }
    
    public void draw(Graphics g, int frameTime) {
        if (pageChanged) {
            time -= frameTime;
            pos = time / TIME;
            posColorText = MathExt.sqr(1 - pos);
            pos *= pos;
            if (time <= 0) {
                pageChanged = false;
//                time = (int)TIME;
                pos = 0;
                posColorText = 1;
            }
        }
        g.setClip(page_x - 1, page_y - 1, width + 2, height + 2);
        int textColor = 0x00ffffff | (int)(posColorText*255) << 24;
        int head = page*LINES_PER_PAGE, 
                tail = (page+1)*LINES_PER_PAGE;
        tail = tail < introText.length ? tail : introText.length;
        int x = (int) (pos * width)*(page - oldPage);
        for (int i = head; i < tail; i++) {
            PFont.drawString(g, page_x + x,
                    page_y + (i - head)*PFont.HEIGHT,
                    introText[i], textColor);
        }
        
        if (pageChanged) {
            textColor = 0x00ffffff | (int)(pos*255) << 24;
            head = oldPage*LINES_PER_PAGE;
            tail = (oldPage + 1)*LINES_PER_PAGE;
            tail = tail < introText.length ? tail : introText.length;
            x = page_x + (oldPage - page) * width;
            x += (int) (pos * width) * (page - oldPage);
            for (int i = head; i < tail; i++) {
                PFont.drawString(g, x, page_y + (i - head)*PFont.HEIGHT,
                        introText[i], textColor);
            }
        }
    }

    public int getPage() {
        return page;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return page_x;
    }

    public int getY() {
        return page_y;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setX(int page_x) {
        this.page_x = page_x;
    }

    public void setY(int page_y) {
        this.page_y = page_y;
    }
    
    public boolean isLastPage() {
        return page == totalPages - 1;
    }
    
    public boolean isfirstPage() {
        return page == 0;
    }
}
