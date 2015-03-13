package com.pipogame.util;

import com.pipogame.Debug;

/**
 * Lớp tiện ích để tính giá trị toán học.
 * @author HaiNH
 * @company B-Gate
 */
public final class MathExt {
    private static final float[] sinValues;
    private static final float[] cosValues;

    static {
        sinValues = new float[91];
        cosValues = new float[91];
        for (int i = 0; i < 90; i++) {
            double x = Math.toRadians(i);
            
            sinValues[i] = (float) (x - pow(x, 3)/6 + pow(x, 5)/120
                    - pow(x, 7)/5040 + pow(x, 9)/362880);
            cosValues[90 - i] = sinValues[i];
        }
        
        sinValues[90] = cosValues[0] = 1;
    }
    
    /**
     * Tính sin(i), với <code>i</code> tính theo độ.
     * @param i góc (độ)
     * @return giá trị của <code>sin(i)</code>
     */
    public static float sin(int i) {
        if (i >= 0 && i <= 90) {
            return sinValues[i];
        } else if (i > 90 && i <= 180) {
            return sinValues[180 - i];
        } else if (i > 180 && i <= 270) {
            return -sinValues[i - 180];
        } else if (i > 270 && i <= 360) {
            return -sinValues[360 - i];
        } else if (i > 360) {
            return sin(i % 360);
        } else {
            return -sin(-i);
        }
    }
    
    /**
     * Tính cos(i), với <code>i</code> tính theo độ.
     * @param i góc (độ)
     * @return giá trị của <code>cos(i)</code>
     */
    public static float cos(int i) {
        if (i >= 0 && i <= 90) {
            return cosValues[i];
        } else if (i > 90 && i <= 180) {
            return -cosValues[180 - i];
        } else if (i > 180 && i <= 270) {
            return -cosValues[i - 180];
        } else if (i > 270 && i <= 360) {
            return cosValues[360 - i];
        } else if (i > 360) {
            return cos(i % 360);
        } else {
            return cos(-i);
        }
    }
    
    
    /**
     * Get tan of angle i.
     * @param i angle in degree.
     */
    public static float tan(int i) {
        i %= 360;
        if (Debug.ENABLE) {
            if (i == 90 || i == 270) {
                throw new ArithmeticException("Loi tinh tan(" + i + ")");
            }
        }
        return sin(i)/cos(i);
    }
    
    /**
     * Get cotan of angle i.
     * @param i angle in degree.
     */
    public static float cot(int i) {
        i %= 360;
        if (Debug.ENABLE) {
            if (i == 90 || i == 270) {
                throw new ArithmeticException("Loi tinh tan(" + i + ")");
            }
        }
        return sin(i)/cos(i);
    }
    
    public int round(float a) {
        int i = (int) a;
        
        if (a >= 0.5f) {
            return i + 1;
        }
        
        return i;
    }
    
    /**
     * d^2
     * @param d
     * @return d*d
     */
    public static double sqr(double d) {
        return d * d;
    }
    
    /**
     * d^2
     * @param d
     * @return d*d
     */
    public static float sqr(float d) {
        return d * d;
    }
    
    /**
     * Tính hàm mũ, độ chính xác gấp đôi.
     * @param   a   the base.
     * @param   b   the exponent.
     * @return  the value {@code a}<sup>{@code b}</sup>.
     */
    public static double pow(double a, int b) {
        if (b < 0) {
            return 1/pow(a, -b);
        } else if (b == 0) {
            return 1;
        } else if (b == 1) {
            return a;
        }
        
        if (a == 0) {
            return 0;
        } else if (a == 1) {
            return 1;
        }
        
        double y = b % 2 == 1 ? a : 1;
        while ((b >>= 1) > 0) {
            a *= a;
            if (b % 2 == 1) {
                y *= a;
            }
        }
        
        return y;
    }
    /**
     * Tính hàm mũ, độ chính xác không cao lắm, dùng cho các phép tính nhanh.
     * @param   a   the base.
     * @param   b   the exponent.
     * @return  the value {@code a}<sup>{@code b}</sup>.
     */
    public static float pow(float a, int b) {
        if (b < 0) {
            return 1/pow(a, -b);
        }
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        
        float y = b % 2 == 1 ? a : 1;
        while ((b >>= 1) > 0) {
            a *= a;
            if (b % 2 == 1) {
                y *= a;
            }
        }
        
        return y;
    }
    
    /**
     * Get fourth root
     * @param d
     * @return fourth root of d
     */
    public static double fourthRoot(double d) {
        return Math.sqrt(Math.sqrt(d));
    }
    
    /**
     * Lấy dấu của số <code>number</code>
     * @param number số cần lấy dấu
     * @return 1 nếu số không âm, -1 nếu là số âm.
     */
    public static int getSign(int number) {
        return number >= 0 ? 1 : -1;
    }
}
