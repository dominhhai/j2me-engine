package com.pipogame.components;

/**
 *
 * @author HaiNH
 * @company B-Gate
 */
public class Rect {
    public int x, y, width, height;

    public Rect() {
    }
    
    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int centerX() {
        return x + width / 2;
    }
    
    public int centerY() {
        return y + height / 2;
    }
    
    public boolean contains(int x, int y) {
        if (x >= this.x && x <= this.x + width &&
                y >= this.y && y <= this.y + height) {
            return true;
        }
        return false;
    }
    
    public boolean intersect(Rect r) {
        if (contains(r.x, r.y) || contains(r.x, r.y + r.height) 
                || contains(r.x + r.width, r.y) 
                || contains(r.x + r.width, r.y + r.height)) {
            return true;
        }
        return false;
    }
}
