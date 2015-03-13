package com.pipogame.util;

import com.pipogame.*;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 * Helper for measuring and displaying frame time
 * @author Honghai
 * @company B-Gate
 * @website pipogame.com
 */
public class TimeProcessMeasurer {
    private long startTime;
    private int totalTime = 400;
    private Vector times;
    private String displayString;

    public TimeProcessMeasurer() {
        if (ScreenManager.DISPLAY_FRAMETIME) {
            times = new Vector(30);
            displayString = "";
        }
    }

    /**
     * Begin to measure time
     */
    public void begin() {
        if (ScreenManager.DISPLAY_FRAMETIME) {
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Put this method to the end of statements where you want to measure
     * the process time
     */
    public void end(int frameTime, int runTime) {
        if (ScreenManager.DISPLAY_FRAMETIME) {
            totalTime += frameTime;
            times.addElement(new Long(runTime));
            if (totalTime > 1000) {
                totalTime -= 1000;
                int size = times.size();
                long longestTime = 0;
                long mediumTime;
                long shortestTime = Long.MAX_VALUE;
                long totalTime = 0;
                for (int i = 0; i < size; i++) {
                    long elementTime = ((Long) times.elementAt(i)).longValue();
                    if (elementTime > longestTime) {
                        longestTime = elementTime;
                    }
                    if (elementTime < shortestTime) {
                        shortestTime = elementTime;
                    }
                    totalTime += elementTime;
                }
                mediumTime = totalTime / size;
                times.removeAllElements();
                StringBuffer buf = new StringBuffer(12);
                buf.delete(0, buf.length());
                displayString = buf.append(shortestTime).append(';')
                        .append(mediumTime).append(';').append(longestTime)
                        .toString();
            }
        }
    }
    
    public void end(int frameTime) {
        if (ScreenManager.DISPLAY_FRAMETIME) {
            long runTime = System.currentTimeMillis() - startTime;
            end(frameTime, (int) runTime);
//            totalTime += runTime;
//            times.addElement(new Long(runTime));
//            if (totalTime > 1000) {
//                totalTime -= 1000;
//                int size = times.size();
//                long longestTime = 0;
//                long mediumTime;
//                long shortestTime = Long.MAX_VALUE;
//                long totalTime = 0;
//                for (int i = 0; i < size; i++) {
//                    long elementTime = ((Long) times.elementAt(i)).longValue();
//                    if (elementTime > longestTime) {
//                        longestTime = elementTime;
//                    }
//                    if (elementTime < shortestTime) {
//                        shortestTime = elementTime;
//                    }
//                    totalTime += elementTime;
//                }
//                mediumTime = totalTime / size;
//                times.removeAllElements();
//                StringBuffer buf = new StringBuffer(12);
//                buf.delete(0, buf.length());
//                displayString = buf.append(shortestTime).append(';')
//                        .append(mediumTime).append(';').append(longestTime)
//                        .toString();
//            }
        }
    }

    /**
     * Print the time to the screen
     * @param g the graphics object to draw in
     * @param x x&ndash;coordinate
     * @param y y&ndash;coordinate
     */
    public void printTime(Graphics g, int x, int y, int color) {
        if (ScreenManager.DISPLAY_FRAMETIME) {
//            Font font = g.getFont();
//            int fW = font.stringWidth(displayString), fH = font.getHeight();
            x += g.getClipX();
            y += g.getClipY();
//            Drawer.drawBg(g, x, y, fW, fH, 0x99dddddd);
            g.setColor(color);
            g.drawString(displayString, x, y, 0);
        }
    }
}
