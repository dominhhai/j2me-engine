
package com.pipogame.components;

/**
 * Represent a point in 2D screen
 *
 * @author Honghai
 * @company B-Gate
 */
public class Point implements InitFromString{
    protected short x;
    protected short y;
    /**
     * Status of Point
     */
    protected byte status;

    /**
     * Construct new zero-point
     */
    public Point(){}

    /**
     * Construct new point with zero stt
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = (short) x;
        this.y = (short) y;
        status = 0;
    }

    public Point(int x, int y, int stt) {
        this.x = (short) x;
        this.y = (short) y;
        this.status = (byte) stt;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
        status = p.status;
    }

    /**
     * Get the status of point
     * @return status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * Set Point's status
     * @param stt status
     */
    public void setStatus(int stt) {
        this.status = (byte) stt;
    }

    public void setAllFields(int x, int y, int status) {
        this.x = (short) x;
        this.y = (short) y;
        this.status = (byte) status;
    }

    public void copy(Point p) {
        x = p.x;
        y = p.y;
        status = p.status;
    }

    public short getX() {
        return x;
    }

    public void setX(int x) {
        this.x = (short) x;
    }

    public short getY() {
        return y;
    }

    public void setY(int y) {
        this.y = (short) y;
    }
    
    public void setX_Y(int x, int y) {
        this.x = (short) x;
        this.y = (short) y;
    }

    public String toString() {
        return "(" + x + ';' + y + ')' + status + '.';
    }

    public void init(String params) {
        int a = params.indexOf(';'), b = params.indexOf(')', a);
        x = Short.parseShort(params.substring(1, a));
        y = Short.parseShort(params.substring(a + 1, b));
        status = Byte.parseByte(params.substring(b + 1,
                                                 params.indexOf('.', b)));
    }

}
