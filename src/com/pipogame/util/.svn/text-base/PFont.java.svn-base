
package com.pipogame.util;

import com.pipogame.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Chiến Lê
 * @company B-Gate
 *
 */
public class PFont {

    private static byte fontByteArray[] = null;
    private final static char[] arrChar = {
        'a', 'Ấ', 'ể', 'l', 'ơ', 'Ú', '2', ':', 'ữ',
        'á', 'Ầ', 'Ê', 'L', 'ớ', 'Ù', '3', ';', 'Ữ',
        'à', 'Ậ', 'Ế', 'm', 'ờ', 'Ụ', '4', '{', 'õ',
        'ạ', 'Ẩ', 'Ề', 'M', 'ợ', 'Ủ', '5', '}', 'Õ',
        'ả', 'b', 'Ệ', 'n', 'ở', 'ư', '6', '[', 'ỗ',
        'A', 'B', 'Ể', 'N', 'Ơ', 'ứ', '7', ']', 'Ỗ',
        'Á', 'c', 'f', 'o', 'Ớ', 'ừ', '8', '<', 'ỡ',
        'À', 'C', 'F', 'ó', 'Ờ', 'ự', '9', '>', 'Ỡ',
        'Ạ', 'd', 'g', 'ò', 'Ợ', 'ử', '!', ',', 'ĩ',
        'Ả', 'D', 'G', 'ọ', 'Ở', 'Ư', '@', '.', 'Ĩ',
        'ă', 'đ', 'h', 'ỏ', 'p', 'Ứ', '#', '|', 'ỵ',
        'ắ', 'Đ', 'H', 'O', 'P', 'Ừ', '$', '\\','ý',
        'ằ', 'e', 'i', 'Ó', 'q', 'Ự', '%', '`', 'ỳ',
        'ặ', 'é', 'í', 'Ò', 'Q', 'Ử', '^', '~', 'ỷ',
        'ẳ', 'è', 'ì', 'Ọ', 'r', 'v', '&', 'ã', 'ỹ',
        'Ă', 'ẹ', 'ị', 'Ỏ', 'R', 'V', '*', 'Ã', 'Ỵ',
        'Ắ', 'ẻ', 'ỉ', 'ô', 's', 'w', '(', 'ă', 'Ý',
        'Ằ', 'E', 'I', 'ố', 'S', 'W', ')', 'Ẵ', 'Ỳ',
        'Ặ', 'É', 'Í', 'ồ', 't', 'x', '-', 'ẫ', 'Ỷ',
        'Ẳ', 'È', 'Ì', 'ộ', 'T', 'X', '_', 'Ẫ', 'Ỹ',
        'â', 'Ẹ', 'Ị', 'ổ', 'u', 'y', '+', 'ẽ', 999,
        'ấ', 'Ẻ', 'Ỉ', 'Ô', 'ú', 'Y', '=', 'Ẽ', 999,
        'ầ', 'ê', 'j', 'Ố', 'ù', 'z','\'', 'ễ', 999,
        'ậ', 'ế', 'J', 'Ồ', 'ụ', 'Z','\"', 'Ễ', 999,
        'ẩ', 'ề', 'k', 'Ộ', 'ủ', '0', '?', 'ũ', 999,
        'Â', 'ệ', 'K', 'Ổ', 'U', '1', '/', 'Ũ', 999
    };
    /**
     * charHeight
     */
    private final static byte[] charTop  = {
        7, 2, 2, 5, 6, 2, 5, 6, 4,
        4, 2, 2, 5, 4, 2, 5, 6, 2,
        4, 2, 2, 7, 4, 5, 5, 3, 4,
        7, 0, 2, 5, 6, 1, 5, 3, 2,
        3, 5, 2, 7, 3, 6, 5, 4, 2,
        5, 5, 0, 5, 4, 4, 5, 4, 0,
        2, 7, 5, 7, 2, 4, 5, 6, 4,
        2, 5, 5, 4, 2, 6, 5, 6, 2,
        5, 5, 7, 4, 4, 3, 5, 12, 4,
        1, 5, 5, 7, 1, 4, 100, 11, 2,
        4, 4, 5, 3, 7, 2, 3, 3, 7,
        2, 5, 5, 5, 5, 2, 4, 3, 4,
        2, 7, 5, 2, 7, 4, 3, 3, 4,
        4, 4, 4, 2, 4, 1, 7, 9, 3,
        2, 4, 4, 5, 7, 7, 2, 4, 4,
        2, 7, 5, 1, 5, 5, 7, 2, 5,
        0, 3, 3, 4, 7, 7, 5, 2, 2,
        0, 5, 5, 4, 5, 5, 5, 0, 2,
        2, 2, 2, 4, 5, 7, 10, 2, 1,
        0, 2, 2, 4, 5, 5, 12, 0, 2,
        4, 5, 5, 2, 7, 7, 7, 4, 100,
        4, 1, 1, 2, 4, 5, 6, 2, 100,
        4, 4, 5, 2, 4, 7, 0, 2, 100,
        4, 4, 5, 2, 7, 5, 0, 0, 100,
        2, 4, 5, 2, 3, 5, 5, 4, 100,
        2, 4, 5, 0, 5, 5, 3, 2, 100
     };

    /**
     * char_Height
     */
    private final static byte[] charHeight  = {
        8, 13, 13, 10, 9, 13, 10, 10, 11,
        11, 13, 13, 10, 11, 13, 10, 11, 13,
        11, 15, 13, 8, 11, 12, 10, 14, 11,
        10, 15, 13, 10, 11, 14, 10, 14, 13,
        12, 10, 15, 8, 12, 9, 10, 13, 13,
        10, 10, 15, 10, 11, 11, 10, 13, 15,
        13, 8, 10, 8, 13, 11, 10, 7, 11,
        13, 10, 10, 11, 13, 11, 10, 7, 13,
        12, 10, 10, 11, 13, 12, 10, 5, 11,
        14, 10, 10, 10, 14, 11, -99, 4, 13,
        11, 11, 10, 12, 10, 13, 13, 14, 10,
        13, 10, 10, 10, 10, 13, 12, 12, 13,
        13, 8, 10, 13, 10, 13, 12, 4, 13,
        13, 11, 11, 13, 11, 14, 8, 4, 14,
        13, 11, 11, 12, 8, 8, 13, 11, 13,
        13, 10, 12, 14, 10, 10, 7, 13, 12,
        15, 12, 12, 11, 8, 8, 11, 13, 13,
        15, 10, 10, 11, 10, 10, 11, 15, 13,
        15, 13, 13, 11, 10, 8, 3, 13, 14,
        15, 13, 13, 13, 10, 10, 3, 15, 13,
        11, 12, 12, 13, 8, 10, 7, 11, -99,
        11, 14, 14, 13, 11, 10, 7, 13, -99,
        11, 11, 12, 13, 11, 8, 5, 13, -99,
        13, 11, 10, 13, 10, 10, 5, 15, -99,
        13, 11, 10, 15, 12, 10, 10, 11, -99,
        13, 13, 10, 15, 10, 10, 12, 13, -99,
      };

    private static int at_color1 = 1, at_color2 = 2;
    private final static int[] AT_CHAR = {
        0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0,
        0, 0, 0, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 0, 0,
        0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 1, 0,
        0, 1, 2, 1, 0, 1, 1, 2, 2, 2, 1, 2, 1, 2, 2, 1,
        0, 1, 2, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 2, 1,
        1, 2, 1, 0, 1, 2, 1, 0, 0, 0, 1, 2, 1, 1, 2, 1,
        1, 2, 1, 1, 2, 1, 0, 0, 0, 1, 2, 1, 0, 1, 2, 1,
        1, 2, 1, 1, 2, 1, 0, 0, 0, 1, 2, 1, 0, 1, 2, 1,
        1, 2, 1, 1, 2, 1, 0, 0, 0, 1, 2, 1, 1, 2, 1, 0,
        1, 2, 1, 0, 1, 2, 1, 1, 1, 2, 2, 1, 1, 2, 1, 0,
        0, 1, 2, 1, 0, 1, 2, 2, 2, 1, 2, 2, 2, 1, 1, 0,
        0, 1, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 2, 1,
        0, 0, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 0,
        0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0,
        0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0
     };


    public static final byte SPACE_WIDTH = 3;

    private final static byte[] charWidths = {
        6, 7, 7, 3, 7, 7, 6, 2, 8,
        6, 7, 6, 6, 7, 7, 6, 2, 8,
        6, 7, 7, 9, 7, 7, 7, 4, 7,
        6, 7, 6, 10, 7, 7, 6, 4, 7,
        6, 7, 6, 7, 7, 8, 6, 3, 7,
        7, 7, 7, 8, 7, 8, 6, 3, 7,
        7, 5, 5, 7, 7, 8, 6, 7, 7,
        7, 7, 6, 7, 7, 8, 6, 7, 7,
        7, 7, 7, 7, 7, 8, 3, 3, 5,
        7, 7, 7, 7, 7, 8, 16, 3, 5,
        6, 8, 7, 7, 7, 8, 9, 2, 7,
        6, 8, 7, 7, 7, 8, 6, 6, 6,
        6, 6, 3, 7, 7, 8, 10, 3, 6,
        6, 6, 3, 7, 7, 8, 6, 9, 6,
        6, 6, 3, 7, 5, 6, 10, 6, 6,
        7, 6, 3, 7, 7, 7, 6, 7, 7,
        7, 6, 3, 7, 5, 9, 4, 7, 7,
        7, 6, 3, 7, 7, 9, 4, 7, 7,
        7, 6, 3, 7, 4, 6, 6, 6, 7,
        7, 6, 3, 7, 7, 7, 7, 7, 7,
        6, 6, 3, 7, 7, 6, 6, 6, 0,
        7, 6, 3, 7, 7, 7, 7, 6, 0,
        6, 6, 4, 7, 7, 6, 2, 6, 0,
        6, 7, 5, 7, 7, 8, 4, 6, 0,
        7, 6, 6, 7, 7, 6, 5, 7, 0,
        7, 6, 7, 7, 7, 5, 6, 7, 0,
        // Space width
        SPACE_WIDTH
    };
    //color
    public static final int COLOR_BLACK = 0xFF000000;
    public static final int COLOR_YELOW = 0xFFFFFF00;
    public static final int COLOR_PINK = 0xFFFF00FF;
    public static final int COLOR_RED = 0xFFFF0000;
    public static final int COLOR_GREEN = 0xFF00FF00;
    public static final int COLOR_BLUE = 0xFF0000FF;
    public static final int COLOR_WHITE = 0xFFFFFFFF;
    ////////////////////////////////////////////////
    private static final int SUM_CHAR = arrChar.length;
    private static final int WIDTH_SPRITE = 11;
    private static final int HEIGHT_SPRITE = 17;
    private static final int SUM_SPRITES_IN_ROW = 9;
    private static final int LENG_RGBARR = WIDTH_SPRITE * HEIGHT_SPRITE;
    /**
     * Mảng argb chứa ký tự đang cần vẽ.
     */
    private static int charct_rgb[];

    /**
     * Mã ký tự ENTER.
     */
    public static final byte ENTER = Byte.MAX_VALUE;
    /**
     * Mã ký tự SPACE.
     */
    public static final byte SPACE = toBytesIndex(" ", null)[0];
    public static final byte MINUS = (byte) 41;
    /**
     * Not a character.
     */
    public static final byte NOT_A_CHAR = ENTER - 1;
    /**
     * Chiều cao của bộ chữ.
     */
    public static final byte HEIGHT = 17;

    /**
     * Đường dẫn đến font file.
     */
    public static String FONT_PATH = "/com/pipogame/util/fonts.pipo";
    public static String LONG_WORD_WARNING =
            "Has a long word, the word will be cut";
    /**
     * Tính sẵn chỉ số của các chữ số.
     */
    private static final byte[] NUMBER_INDEXES = new byte[] {
        (byte)94, (byte)103, (byte)-121,  (byte)-112, (byte)-103,
        (byte)-94,  (byte)-85,  (byte)-76, (byte)-67, (byte)-58
    };

    /**
     * Mảng lưu số đã được convert.
     */
    private static final byte[] NUMBER = new byte[9];
    static {
        for (int i = 0; i < NUMBER.length; i++) {
            NUMBER[i] = NOT_A_CHAR;
        }
    }

    private static boolean initialized = false;
    
    public static void initFont() {
        if (!initialized) {
            loadFont(FONT_PATH);
            initialized = true;
        }
    }

    /**
     * Lấy chiều dài của string đã mã hoá.
     * @param b string đã mã hoá
     * @param offset vị trí đầu
     * @param length chiều dài string
     * @return chiều dài của string trong chuỗi mã hoá
     */
    public static int getStringWidth(byte[] b, int offset, int length) {
        int leng = 0, maxLen = 0;
        length += offset;
        for (int i = offset; i < length; i++) {
            if (b[i] == NOT_A_CHAR) {
                break;
            }
            if (b[i] == ENTER) {
                if (leng > maxLen) {
                    maxLen = leng;
                }
                leng = 0;
                continue;
            }
            leng += charWidths[b[i] + ENTER];
        }
        return maxLen > leng ? maxLen : leng;
    }

    /**
     * Đưa ra chiều dài toàn bộ string đã mã hoá.
     * @param string
     * @return  
     */
    public static int getStringWidth(byte[] string) {
        return getStringWidth(string, 0, string.length);
    }

    public static final int BASE_10 = 10;
    private static final int MAX_NUMBER = 99999999, MIN_NUMBER = -MAX_NUMBER;
    private static int lastNumber = Integer.MIN_VALUE - 12;
    /**
     * Draw number in range of -99.999.999 to 99.999.999.
     * @param g graphics object that handles draw operation
     * @param number number to be drawn
     * @param x coordinate
     * @param y coordinate
     * @param color1 outer color
     * @param color2 inner color
     * @return width of string drawn
     */
    public static int drawNumber(Graphics g, int number, int x, int y,
                                  int color1, int color2) {
        if (Debug.ENABLE && (number > MAX_NUMBER || number < MIN_NUMBER)) {
                throw new IllegalArgumentException(
                        "The number is out of range: " + number);
        }
        if (lastNumber != number) {
            toBytesIndex(number, BASE_10, NUMBER);
            lastNumber = number;
        }
        return drawNumber(g, NUMBER, x, y, color1, color2);
    }

    /**
     * Draw number in range of -99.999.999 to 99.999.999.
     * @param g
     * @param number
     * @param x
     * @param y
     * @param color
     * @return 
     */
    public static int drawNumber(Graphics g, int number,
            int x, int y, int color) {
        return drawNumber(g, number, x, y, 0, color);
    }
    /**
     * Draw number in little endian<br/>
     * Little-endian: <code>0A0B0C0D</code> is stored in memory as
     * <code>0D0C0B0A</code>
     *
     * @param g 
     * @param number 
     * @param x 
     * @param y 
     * @param outColor 
     * @param inColor 
     * @return 
     * @see drawNumber(Graphics g, int number, int x, int y,
     * int color1, int color2)
     */
    public static int drawNumber(Graphics g, byte[] number,
            int x, int y, int outColor, int inColor) {
        if (!initialized) {
            loadFont(FONT_PATH);
            initialized = true;
        }
//        int width = number[number.length - 1];
//        if (width == MINUS) {
//            width = drawRGB(g, MINUS, x, y - 1, color1, color2, SPACE_WIDTH);
//        } else if (width != NOT_A_CHAR){
//            width = drawRGB(g, width, x, y, color1, color2, SPACE_WIDTH);
//        } else {
//            width = 0;
//        }
        int width = 0;
        for (int i = number.length - 2; i >= 0; i--) {
            if (number[i] != NOT_A_CHAR) {
                if (number[i] == MINUS) {
                    width += drawRGB(g, number[i], x + width, y - 1,
                            outColor, inColor, SPACE_WIDTH);
                } else {
                    width += drawRGB(g, number[i], x + width, y,
                            outColor, inColor, SPACE_WIDTH);
                }
            }
        }
        return width;
    }

    /**
     * Render số nguyên <code>number</code> căn phải theo <code>x, y</code>.
     * @param g
     * @param number
     * @param x
     * @param y
     * @param color màu bên trong.
     * @return width
     */
    public static int drawNumberL(Graphics g, int number,
            int x, int y, int color) {
        return drawNumberL(g, number, x, y, 0, color);
    }

    /**
     * Render số nguyên <code>number</code> căn phải theo <code>x, y</code>.
     * @param g
     * @param number
     * @param x
     * @param y
     * @param colorOut màu bên ngoài
     * @param colorIn màu bên trong
     * @return chiều dài string được vẽ.
     */
    public static int drawNumberL(Graphics g, int number,
            int x, int y, int colorOut, int colorIn) {
        if (Debug.ENABLE && (number > MAX_NUMBER || number < MIN_NUMBER)) {
                throw new IllegalArgumentException(
                        "The number is out of range");
        }
        if (lastNumber != number) {
            toBytesIndex(number, BASE_10, NUMBER);
            lastNumber = number;
        }
        int width = getStringWidth(NUMBER);
        drawNumber(g, NUMBER, x - width, y, colorOut, colorIn);
        return width;
    }
    
    public static int drawNumberCenter(Graphics g, int number,
            int x, int y, int colorOut, int colorIn) {
        if (Debug.ENABLE && (number > MAX_NUMBER || number < MIN_NUMBER)) {
                throw new IllegalArgumentException(
                        "The number is out of range");
        }
        if (lastNumber != number) {
            toBytesIndex(number, BASE_10, NUMBER);
            lastNumber = number;
        }
        int width = getStringWidth(NUMBER);
        drawNumber(g, NUMBER, x - width/2, y, colorOut, colorIn);
        return width;
    }
    
    /**
     * Vẽ số và căn giữa
     * @param g
     * @param number
     * @param x
     * @param y
     * @param color
     * @return 
     */
    public static int drawNumberCenter(Graphics g, int number,
            int x, int y, int color) {
        return drawNumberCenter(g, number, x, y, 0, color);
    }

    /**
     * Draw a string presented in <code>byte[] index</code>
     * @param g
     * @param x
     * @param y
     * @param index
     * @param color1 Outer color
     * @param color2 Inner color
     * @param offset
     * @param length
     * @param space_width độ dài của dấu cách tính bằng pixel
     */
    public static void drawString(Graphics g, int x, int y, byte[] index,
                                  int color1, int color2, int offset,
                                  int length, int space_width) {
        int width = 0;
        length = offset + length;
        if (!initialized) {
            loadFont(FONT_PATH);
            initialized = true;
        }

        for (int i = offset; i < length; i++) {
            if (index[i] == ENTER) {
                width = 0;
                y += HEIGHT_SPRITE;
            } else if (index[i] == NOT_A_CHAR) {
                break;
            } else {
                width += drawRGB(g, index[i], x + width, y,
                                   color1, color2, space_width);
            }
        }
    }
    
    public static void drawStrings(Graphics g, byte[][] indice, int x, int y,
            int colorOut, int colorIn) {
        for (int i = 0; i < indice.length; i++) {
            drawString(g, x, y + i * HEIGHT, indice[i], colorOut, colorIn,
                    0, indice[i].length);
        }
    }

    /**
     * Draw a string presented in <code>byte[] index</code>
     * @param g
     * @param x
     * @param y
     * @param index
     * @param color1 Outer color
     * @param color2 Inner color
     * @param offset
     * @param length
     */
    public static void drawString(Graphics g, int x, int y, byte[] index,
                                  int color1, int color2, int offset,
                                  int length) {
        drawString(g, x, y, index, color1, color2, offset, length, SPACE_WIDTH);
    }

    public static void drawString(Graphics g, int x, int y, byte[] indexes,
                                  int color) {
        drawString(g, x, y, indexes, 0, color, 0, indexes.length, SPACE_WIDTH);
    }
    
    public static void drawString(Graphics g, int x, int y, byte[] indexes,
                                  int colorOut, int colorIn) {
        drawString(g, x, y, indexes, colorOut, colorIn, 0,
                indexes.length, SPACE_WIDTH);
    }

    /**
     * Vẽ xâu căn trái
     * @param g
     * @param x
     * @param y
     * @param indexes
     * @param color 
     */
    public static void drawStringL(Graphics g, int x, int y, byte[] indexes,
                                  int color) {
        drawString(g, x - getStringWidth(indexes), y, indexes,
                0, color, 0, indexes.length, SPACE_WIDTH);
    }

    /**
     * Vẽ xâu căn trái
     * @param g
     * @param x
     * @param y
     * @param indexes
     * @param colorIn
     * @param colorOut 
     */
    public static void drawStringL(Graphics g, int x, int y, byte[] indexes,
                                  int colorIn, int colorOut) {
        drawString(g, x - getStringWidth(indexes), y, indexes,
                colorOut, colorIn, 0, indexes.length, SPACE_WIDTH);
    }

    /**
     * Chuyển từ String sang dạng xâu mã hoá. Xâu đã mã hoá sẽ được lưu trong
     * <code>buf</code> nếu <code>buf</code> chứa đủ chỗ cho <code>str</code>,
     * nếu không đủ hoặc <code>buf == null</code> thì xâu mã hoá sẽ được trả về 
     * trong một mảng <code>byte[]</code> mới.
     * @param str xâu vào
     * @param buf xâu đã mã hoá
     * @return xâu đã mã hoá.
     */
    public static byte[] toBytesIndex(String str, byte[] buf) {
        int length = str.length();
        if (buf == null || buf.length < length) {
            buf = new byte[length];
        }
        int i;
        for (i = 0; i < length; i++) {
            if (str.charAt(i) != '\n') {
                buf[i] = (byte) (getIndex(str.charAt(i)) - ENTER);
            } else {
                buf[i] = ENTER;
            }
        }

        for (i = length; i < buf.length; i++) {
            buf[i] = NOT_A_CHAR;
        }
        return buf;
    }

    /**
     * Chuyển đổi số nguyên sang index theo thứ tự ngược.
     * <br/>Ví dụ 12345 được chuyển thành "54321" theo cơ số 10
     * <br/>và -12345 được chuyển thành "54321-" theo cơ số 10
     * @param number số cần chuyển
     * @param base cơ số
     * @param buf bộ đệm
     * @return &ndash; Mảng chứa index mới nếu <code>buf == null</code> hoặc
     * <code>buf.length &lt; chiều dài số nguyên</code><br/>
     * &ndash; <code>buf</code> nếu <code>buf</code> đủ chứa số nguyên
     */
    public static byte[] toBytesIndex(int number, int base, byte[] buf) {
        if (number == 0) {
            if (buf == null || buf.length == 0) {
                return new byte[] {NUMBER_INDEXES[0]};
            } else {
                buf[0] = NUMBER_INDEXES[0];
                for (int i = 1; i < buf.length; i++) {
                    buf[i] = NOT_A_CHAR;
                }
                return buf;
            }
        }

        int leng = 0;
        boolean neg = false;

        if (number < 0) {
            neg = true;
            number = -number;
            leng++;
        }
        int temp = number;
        while (temp > 0) {
            leng++;
            temp /= base;
        }

        if (buf == null || buf.length < leng) {
            buf = new byte[leng];
        }
        leng = 0;
        while (number > 0) {
            buf[leng] = NUMBER_INDEXES[number % base];
            number /= base;
            leng++;
        }
        if (neg) {
            buf[leng++] = MINUS;
        }
        if (leng < buf.length) {
            for (int i = leng; i < buf.length; i++) {
                buf[i] = NOT_A_CHAR;
            }
        }

        return buf;
    }

    private static void loadFont(String str) {
        InputStream is = PFont.class.getResourceAsStream(str);
        try {
            fontByteArray = new byte[is.available()];
            is.read(fontByteArray);
            charct_rgb = new int[LENG_RGBARR];
            is.close();
        } catch (IOException e) {
            if (Debug.ENABLE) {
                System.out.println("Debug info PFont loadFont()");
                e.printStackTrace();
            }
        }
    }

    /**
     * Vẽ một string và căn giữa theo toạ độ x
     * @param g
     * @param x
     * @param y
     * @param indexes mảng chữa xâu
     * @param color1 màu bên ngoài
     * @param color2 màu bên trong
     * @param offset offset bắt đầu
     * @param length độ dài xâu
     */
    public static void drawCenterString(Graphics g, int x, int y,
            byte[] indexes, int color1, int color2, int offset, int length) {
        drawString(g, x - getStringWidth(indexes, offset, length) / 2, y,
                          indexes, color1, color2, offset, length, SPACE_WIDTH);
    }

    /**
     * Vẽ một string và căn giữa theo toạ độ x
     * @param g
     * @param x
     * @param y
     * @param indexes
     * @param color Màu bên trong của chữ (khác với màu bên ngoài trong font
     * của Chiến)
     */
    public static void drawCenterString(Graphics g, int x, int y,
            byte[] indexes, int color) {
        drawString(g, x - getStringWidth(indexes) / 2, y, indexes,
                   0, color, 0, indexes.length, SPACE_WIDTH);
    }

    private static int i1, j1;
    private static int drawRGB(Graphics g, int index, int x, int y,
                               int color1, int color2, int space_width) {
        index += ENTER;
        int charWidth = charWidths[index];
        /*
         * Kiem tra xem ki tu co nam trong clip cua graphics khong, neu khong 
         * thi return ngay.
         */
        if (x + charWidth < g.getClipX() ||
                x > g.getClipX() + g.getClipWidth() ||
                y + HEIGHT < g.getClipY() ||
                y > g.getClipY() + g.getClipHeight()) {
            return charWidth;
        }
        
        if (index == 87) {
            if (0 != at_color1) {
                for (int i = 0; i < AT_CHAR.length; i++) {
                    if (AT_CHAR[i] == at_color1) {
                        AT_CHAR[i] = 0;
                    }
                }
                at_color1 = 0;
            }
            if (color2 != at_color2) {
                for (int i = 0; i < AT_CHAR.length; i++) {
                    if (AT_CHAR[i] == at_color2) {
                        AT_CHAR[i] = color2;
                    }
                }
                at_color2 = color2;
            }
            g.drawRGB(AT_CHAR, 0, charWidth, x, y, charWidth, charWidth, true);

            return charWidth;
        }

        charWidths[SUM_CHAR] = (byte)space_width;
        if (index < SUM_CHAR) {
            int temp = (index % SUM_SPRITES_IN_ROW) * WIDTH_SPRITE +
                (index / SUM_SPRITES_IN_ROW) * SUM_SPRITES_IN_ROW * LENG_RGBARR
                + charTop[index] * SUM_SPRITES_IN_ROW * WIDTH_SPRITE;
            int bottom = charHeight[index] + charTop[index];
            int row;

            for (i1 = charTop[index]; i1 < bottom; i1++) {
                row = i1 * WIDTH_SPRITE;
                for (j1 = 0; j1 < charWidth; j1++) {
                    switch (fontByteArray[temp + j1]) {
                    case 1:
                        charct_rgb[row + j1] = color1;
                        break;
                    case 2:
                        charct_rgb[row + j1] = color2;
                        break;
                    default:
                        charct_rgb[row + j1] = 0;
                    }
                }
                temp += SUM_SPRITES_IN_ROW * WIDTH_SPRITE;
            }

            g.drawRGB(charct_rgb, charTop[index] * WIDTH_SPRITE, WIDTH_SPRITE,
                          x, y + charTop[index], charWidth,
                          charHeight[index], true);
        }

        return charWidth;
    }

    public static void drawChar(Graphics g, int index, int x, int y,
                                int color1, int color2) {
        drawRGB(g, index, x, y, color1, color2, 0);
    }

    public static int getIndex(char ch) {
        int i;
        for (i = 0; i < SUM_CHAR; i++) {
            if (ch == arrChar[i]) {
                break;
            }
        }
        return i;
    }
    
    public static void free() {
        fontByteArray = null;
        charct_rgb = null;
        initialized = false;
    }

    //<editor-fold defaultstate="collapsed" desc="break string functions">
    public static String[] breakString(String text, Font font, int width) {
        Vector lines = new Vector(5);
        int lastPos = 0;
        int begin = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ' || i == text.length() - 1) {
                // Cắt dòng nếu
                if (font.substringWidth(text, begin, i - begin) > width) {
                    if (begin > lastPos) {
                        // Đây là khi một từ dài quá chiều dài width mong muốn
                        // thì nó sẽ bị cắt ra làm nhiều phần
                        if (Debug.ENABLE) {
                            System.out.println(LONG_WORD_WARNING);
                        }
                        int j;
                        // Chỉ cắt phần vừa với 1 dòng, để lại phần thừa của
                        // từ đó cho vào dòng sau
                        for (j = begin; j < i; j++) {
                            if (font.substringWidth(text, begin, j - begin)
                                    > width) {
                                lines.addElement(text.substring(begin, j));
                                // Đoạn từ begin đến i sẽ đc cho vào dòng sau
                                begin = j;
                            }
                        }
                        lastPos = i;
                    } else {
                        lines.addElement(text.substring(begin, lastPos));

                        i = lastPos + 1;
                        begin = lastPos + 1;
                    }
                } else {
                    lastPos = i;
                }
            }
        }

        if (begin < text.length()) {
            lines.addElement(text.substring(begin));
        }

        String[] lineStrings = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            lineStrings[i] = (String) lines.elementAt(i);
        }

        return lineStrings;
    }

    public static byte[][] breakString(byte[] text, int width) {
        if (Debug.ENABLE) {
            if (width < PFont.WIDTH_SPRITE) {
                throw new IllegalArgumentException(
                        "width of text is too short" + width);
            }
        }
        Vector lines = new Vector(10, 5);
        int lastPos = 0;
        int begin = 0;
        int i;
        for (i = 0; i < text.length; i++) {
            if (text[i] == PFont.ENTER) {
                lines.addElement(Util
                                .getSubArray(text, begin, i - begin));
                begin = i + 1;
                lastPos = begin;
            } else if (text[i] == SPACE || text[i] == NOT_A_CHAR
                    || i == text.length - 1) {
                // Cắt dòng nếu
                if (PFont.getStringWidth(text, begin, i - begin) > width) {
                    if (begin > lastPos) {
                        // Đây là khi một từ dài quá chiều dài width mong muốn
                        // thì nó sẽ bị cắt ra làm nhiều phần
                        if (Debug.ENABLE) {
                            System.out.println(LONG_WORD_WARNING);
                        }
                        // Chỉ cắt phần vừa với 1 dòng, để lại phần thừa của
                        // từ đó cho vào dòng sau
                        for (int j = begin; j < i; j++) {
                            if (PFont.getStringWidth
                                    (text, begin, j - begin) > width) {
                                j--;
                                lines.addElement(Util
                                        .getSubArray(text,begin, j - begin));
                                // Đoạn từ begin đến i sẽ đc cho vào dòng sau
                                begin = j;
                            }
                        }
                        lastPos = i;
                    } else {
                        if (begin == lastPos) {
                            continue;
                        }
                        lines.addElement(Util
                                .getSubArray(text, begin, lastPos - begin));

                        i = lastPos + 1;
                        begin = lastPos + 1;
                    }
                } else {
                    lastPos = i;
                }
            }
        }

        for (i = text.length - 1; i >= 0; i--) {
            if (text[i] != NOT_A_CHAR) {
                i++;
                break;
            }
        }

        if (begin < i) {
            lines.addElement(Util.getSubArray(text, begin, i - begin));
        }

        byte[][] lineStrings = new byte[lines.size()][];
        for (i = 0; i < lines.size(); i++) {
            lineStrings[i] = (byte[]) lines.elementAt(i);
        }

        return lineStrings;
    }

    public static byte[][] breakString(String string, int width) {
        return breakString(PFont.toBytesIndex(string, null), width);
    }

    public static byte[][] breakString(String s, byte[] buf_of_s, int width) {
        return breakString(PFont.toBytesIndex(s, buf_of_s), width);
    }

    /**
     * Break a string from a file
     * @param filename
     * @param width
     * @param enc
     * @return
     */
    public static byte[][] breakString(String filename, int width, String enc) {
        try {
            InputStream inputStream = PFont.class.getResourceAsStream(filename);
            byte[] buf = new byte[inputStream.available() - 3];
            if (enc.equals(Store.ENC_UTF8)) {
                inputStream.skip(3);
            }
            inputStream.read(buf);
            inputStream.close();
            String s = new String(buf, enc);
            return breakString(s, buf, width);
        } catch (IOException e) {
            if (Debug.ENABLE) {
                System.out.println("Debug info breakString(,,)");
                e.printStackTrace();
            }
            return null;
        }
    }
    //</editor-fold>

}
