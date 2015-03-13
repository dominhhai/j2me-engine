package com.pipogame.util;

import com.pipogame.Input;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Thể hiển văn bản hỗn hợp giữa text và hình ảnh.<p/>
 * Hiển thị văn bản thành các trang, có hiệu ứng chuyển qua lại giữa các trang.
 * @author HaiNH
 * @company B-Gate
 */
public class RichPage {
    
    private Vector element;
    private int[] endIndexOfPage = new int[30];
    private int x, y, width, height;
    private int currentPageHeight;
    private int time;
    private int TIME = 200;
    
    private int totalPages, curPage, oldPage;
    private int transitionStyle = TRAN_LINEAR;
    private float transPos;
    private boolean pageChanged;
    public static final int TRAN_LINEAR = 1;
    public static final int TRAN_SQUARE_ROOT = 2;
    public static final int TRAN_SQUARE = 3;

    /**
     * Khởi tạo một trang mới.
     * @param x toạ độ x
     * @param y toạ độ y
     * @param width chiều rộng
     * @param height chiều dài
     */
    public RichPage(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        element = new Vector(30);
        totalPages = 1;
    }
    
    /**
     * Thêm text vào <code>RichPage</code>.
     * @param text text cần thêm
     * @return reffernce đến <code>RichPage</code> này
     */
    public RichPage append(String text) {
        byte[][] b = PFont.breakString(text, width);
        for (int i = 0; i < b.length; i++) {
            element.addElement(b[i]);
            checkHeight(PFont.HEIGHT);
        }
        endIndexOfPage[totalPages] = element.size();
        return this;
    }
    
    /**
     * Thêm <code>Image</code> vào <code>RichPage</code>.
     * @param image ảnh cần thêm
     * @return reffernce đến <code>RichPage</code> này
     */
    public RichPage append(Image image) {
        element.addElement(image);
        checkHeight(image.getHeight());
        endIndexOfPage[totalPages] = element.size();
        return this;
    }
    
    public RichPage breakPage() {
        currentPageHeight = 0;
        totalPages++;
        endIndexOfPage[totalPages] = element.size();
        return this;
    }
    
    /**
     * Kiểm tra chiều cao của các thành phần mới được thêm vào 
     * <code>RichPage</code>.
     * @param height chiều cao của thành phần mới.
     */
    private void checkHeight(int height) {
        if (currentPageHeight + height + height > this.height) {
            currentPageHeight = height;
            endIndexOfPage[totalPages] = element.size();
            totalPages++;
            if (totalPages == endIndexOfPage.length) {
                int[] array = new int[totalPages + 10];
                System.arraycopy(endIndexOfPage, 0, array, 0, totalPages);
                endIndexOfPage = array;
            }
        } else {
            currentPageHeight += height;
        }
    }
    
    /**
     * Xử lý input.
     * @param input đối tượng chứa input 
     */
    public void handleInput(Input input) {
        if (input.isLeftPrd() || input.isUpPrd()) {
            curPage--;
            if (curPage < 0) {
                curPage = 0;
            } else {
                oldPage = curPage + 1;
                pageChanged = true;
                time = (int)TIME;
            }
        } else if (input.isRightPrd() || input.isDownPrd()) {
            curPage++;
            if (curPage == totalPages) {
                curPage = totalPages - 1;
            } else {
                pageChanged = true;
                oldPage = curPage - 1;
                time = (int)TIME;
            }
        }
    }
    
    /**
     * Vẽ ra màn hình.
     * @param g đối tượng <code>Graphics</code>
     * @param frameTime thời gian của 1 frame khi vẽ ra.
     */
    public void draw(Graphics g, int frameTime) {
        int pos = 0;
        int clIn = 0xffffffff;
        int clOut = 0;
        if (pageChanged) {
            time -= frameTime;
            if (time < 0) {
                time = 0;
                pageChanged = false;
            }
            pos = calPos();
            clIn = 0x00ffffff | ((int)((1 - transPos) * 255) << 24);
            clOut = 0x00ffffff | ((int)(transPos * 255) << 24);
        }
        int curHei = y;
        
        g.setClip(x - 1, y - 1, width + 2, height + 2);
        for (int i = endIndexOfPage[curPage];
                i < endIndexOfPage[curPage + 1]; i++) {
            Object o = element.elementAt(i);
            if (o instanceof Image) {
                Drawer.drawAtCenterX(g, (Image) o, x + width / 2 + pos, curHei);
                curHei += ((Image) o).getHeight();
            } else {
                PFont.drawString(g, x + pos, curHei, (byte[]) o, clIn);
                curHei += PFont.HEIGHT;
            }
        }
        
        if (pageChanged) {
            curHei = y;
            int _x = this.x + (oldPage - curPage) * width;
            for (int i = endIndexOfPage[oldPage];
                    i < endIndexOfPage[oldPage + 1]; i++) {
                Object o = element.elementAt(i);
                if (o instanceof Image) {
                    Drawer.drawAtCenterX(g, (Image) o,
                            _x + width / 2 + pos, curHei);
                    curHei += ((Image) o).getHeight();
                } else {
                    PFont.drawString(g, _x + pos, curHei,
                            (byte[]) o, clOut);
                    curHei += PFont.HEIGHT;
                }
            }
        }
    }
    
    private int calPos() {
        transPos = time / (float) TIME;
        switch (transitionStyle) {
        case TRAN_LINEAR:
            return (int)(width * transPos) * (curPage - oldPage);
        case TRAN_SQUARE:
            transPos *= transPos;
            return (int)(width * transPos) * (curPage - oldPage);
        case TRAN_SQUARE_ROOT:
            transPos = (float)Math.sqrt(transPos);
            return (int)(width * transPos) * (curPage - oldPage);
        }
        return 0;
    }

    /**
     * Đặt kiểu hiệu ứng chuyển trang.<p/>
     * Các kiểu có sẵn:
     * <ol>
     * <li><code>TRAN_LINEAR</code>: Tuyến tính</li>
     * <li><code>TRAN_SQUARE</code>: Hàm bậc 2</li>
     * <li><code>TRAN_SQUARE_ROOT</code>: Hàm căn bậc 2</li>
     * </ol>
     * Mặc định là <code>TRAN_LINEAR</code>.
     * @param transitionStyle 
     */
    public void setTransitionStyle(int transitionStyle) {
        this.transitionStyle = transitionStyle;
    }

    /**
     * Lấy ra trang hiện thời.
     * @return 
     */
    public int getCurPage() {
        return curPage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public boolean isFirstPage() {
        return curPage == 0;
    }
    
    public boolean isLastPage() {
        return curPage == totalPages - 1;
    }
    
    public void setTransitionTime(int time) {
        TIME = time;
    }
}
