package com.pipogame;


//import game.MainCanvas;

import javax.microedition.lcdui.Canvas;

/**
 * Class redefines codes of mobile phone to our constant values.
 * Class can give to developers following information:
 * <ul>
 * <li/><i>defined platform name</i><br>
 * In case if device vendor not defined we'll recieve <code>PLATFORM_NOT_DEFINED</code> like platform name.
 * Same in this case keyCodes will be setted like for Nokia and SE. It's done for work on emulators,
 * because on some of them it's impossible to define platform name.
 * <li/><i>adopted to our constants key code value</i>
 * <li/><i>for test returns defined real code of left softkey</i>
 * </ul>
 */
public final class KeyCodeAdapter {
    /**
     * canvas used for defining codes
     */
    private final Canvas adaptorCanvas;
    /**
     * constants for platforms  names
     */
    public static String PLATFORM_MOTOROLA = "motorola";
    public static String PLATFORM_NOKIA = "nokia";
    public static String PLATFORM_SONY_ERICSSON = "SE";
    public static String PLATFORM_SIEMENS = "siemens";
    public static String PLATFORM_SAMSUNG = "samsung";
    public static String PLATFORM_LG = "LG";
    public static String PLATFORM_NOT_DEFINED = "NA";
    /**
     * constants for keycodes
     */
    public static final int SOFT_KEY_LEFT = -201;
    public static final int SOFT_KEY_RIGHT = -202;
    public static final int SOFT_KEY_MIDDLE_INTERNET = -203;
    /**
     * this key is present on Nokia s60
     */
    public static final int PENCIL_KEY = -207;
    public static final int DELETE_KEY = -204;
    public static final int BACK_KEY = -205;
    //    public static final int SEND_KEY = -206;
    //constant will be used in future for green key start dialling
    public static final int KEY_1 = 201;
    public static final int KEY_2 = 202;
    public static final int KEY_3 = 203;
    public static final int KEY_4 = 204;
    public static final int KEY_5 = 205;
    public static final int KEY_6 = 206;
    public static final int KEY_7 = 207;
    public static final int KEY_8 = 208;
    public static final int KEY_9 = 209;
    public static final int KEY_0 = 200;
    public static final int KEY__POUND = 211;
    public static final int KEY__STAR = 212;
    /**
     * KEYS on JOISTICK
     */
    public static final int UP_KEY = 221;
    public static final int DOWN_KEY = 222;
    public static final int LEFT_KEY = 223;
    public static final int RIGHT_KEY = 224;
    public static final int CENTER_KEY = 225;

    public static final int NOT_DEFINED_KEY = 254;

    /**
     * current platform name
     */
    private final String PLATFORM_NAME;
    /**
     * current platform codeofSoftkey
     */
    public final int SOFTKEY_LEFT;
    public final int SOFTKEY_RIGHT;
//    public final int SOFTKEY_MIDDLE_INTERNET;
//    public final int SOFTKEY_DELETE;
//    public final int SOFTKEY_BACK;

    /**
     * standart values for softkeys of different platforms
     * used only in predefining
     */
    private static final int SOFT_KEY_LEFT_SE = -6;
    private static final int SOFT_KEY_RIGHT_SE = -7;
    private static final int DELETE_KEY_SE = -8;
    private static final int INTERNET_KEY_SE = -10;
    private static final int BACK_KEY_SE = -11;
    private static final int SOFT_KEY_LEFT_SAMSUNG = -6;
    private static final int SOFT_KEY_RIGHT_SAMSUNG = -7;
    private static final int DELETE_KEY_SAMSUNG = -8;
    private static final int SOFT_KEY_LEFT_SIEMENS = -1;
    private static final int SOFT_KEY_RIGHT_SIEMENS = -4;
    private static final int SOFT_KEY_LEFT_NOKIA = -6;
    private static final int SOFT_KEY_RIGHT_NOKIA = -7;
    private static final int DELETE_KEY_NOKIA = -8;
    private static final int PENCIL_KEY_NOKIA = -50;
    private static final int SOFT_KEY_LEFT_MOTOROLA = -21;
    private static final int SOFT_KEY_RIGHT_MOTOROLA = -22;
    private static final int SOFT_KEY_LEFT_MOTOROLA2 = -20;
    private static final int SOFT_KEY_LEFT_MOTOROLA1 = 21;
    private static final int SOFT_KEY_RIGHT_MOTOROLA1 = 22;
    private static final int SOFT_KEY_MIDLE_MOTOROLA = -23;
    private static final int SOFT_KEY_MIDLE_NOKIA = -5;

    private static final String SOFT_STRING = "SOFT";
    private static final String LEFT_STRING = "LEFT";
    private static final String RIGHT_STRING = "RIGHT";
    private static final String ONE_STRING = "1";
    private static final String TWO_STRING = "2";
    private static final String FOUR_STRING = "4";
    private static final String EMPTY_STRING = "";
    private static final String CLEAR_STRING = "CLEAR";

    /**
     * constructor.
     * here is predefining of spesial keys and platform made
     */
    private KeyCodeAdapter(Canvas adaptorCanvas) {
        this.adaptorCanvas = adaptorCanvas;
        PLATFORM_NAME = getPlatform();

        SOFTKEY_LEFT = getLeftSoftkeyCode();
        SOFTKEY_RIGHT = getRightSoftkeyCode();
//        SOFTKEY_MIDDLE_INTERNET = getMidleORInternetSoftkeyCode();
//        SOFTKEY_DELETE = getDeleteKeyCode();
//        SOFTKEY_BACK = getBackKeyCode();
        
        if (Debug.ENABLE) {
            System.out.println("FlatForm Name:"+PLATFORM_NAME);
            System.out.println("SOFTKEY_LEFT: "+SOFTKEY_LEFT);
        }
    }


    /**
     * Returns mobile phone platform
     *
     * @return name mobile phone platform
     */
    private String getPlatform() {
        // detecting NOKIA or SonyEricsson
        try {
            final String currentPlatform = 
                    System.getProperty("microedition.platform");
            if (currentPlatform.indexOf("Nokia") != -1) {
                return PLATFORM_NOKIA;
            } else if (currentPlatform.indexOf("SonyEricsson") != -1) {
                return PLATFORM_SONY_ERICSSON;
            }
        } catch (Throwable ex) {
        }
        // detecting SAMSUNG
        try {
            Class.forName("com.samsung.util.Vibration");
            return PLATFORM_SAMSUNG;
        } catch (Throwable ex) {
        }
        // detecting MOTOROLA
        try {
            Class.forName("com.motorola.multimedia.Vibrator");
            return PLATFORM_MOTOROLA;
        } catch (Throwable ex) {
            try {
                Class.forName("com.motorola.graphics.j3d.Effect3D");
                return PLATFORM_MOTOROLA;
            } catch (Throwable ex2) {
                try {
                    Class.forName("com.motorola.multimedia.Lighting");
                    return PLATFORM_MOTOROLA;
                } catch (Throwable ex3) {
                    try {
                        Class.forName("com.motorola.multimedia.FunLight");
                        return PLATFORM_MOTOROLA;
                    } catch (Throwable ex4) {
                    }
                }
            }
        }
        
        // detecting SIEMENS
        try {
            Class.forName("com.siemens.mp.io.File");
            return PLATFORM_SIEMENS;
        } catch (Throwable ex) {
        }
        // detecting LG
        try {
            Class.forName("mmpp.media.MediaPlayer");
            return PLATFORM_LG;
        } catch (Throwable ex) {
            try {
                Class.forName("mmpp.phone.Phone");
                return PLATFORM_LG;
            } catch (Throwable ex1) {
                try {
                    Class.forName("mmpp.lang.MathFP");
                    return PLATFORM_LG;
                } catch (Throwable ex2) {
                    try {
                        Class.forName("mmpp.media.BackLight");
                        return PLATFORM_LG;
                    } catch (Throwable ex3) {
                    }
                }
            }
        }
        return PLATFORM_NOT_DEFINED;
    }

    /**
     * define real left soft key code by platform
     *
     * @return code
     */
    private int getLeftSoftkeyCode() {
        int keyCode = 0;
        try {
            if (PLATFORM_NAME.equals(PLATFORM_MOTOROLA)) {
                String softkeyLeftMoto = EMPTY_STRING;
                try {
                    softkeyLeftMoto = adaptorCanvas
                            .getKeyName(SOFT_KEY_LEFT_MOTOROLA).toUpperCase();
                } catch (IllegalArgumentException ilae) {
                   
//                    ilae.printStackTrace();
                }
                String softkeyLeftMoto1 = EMPTY_STRING;
                try {
                    softkeyLeftMoto1 = adaptorCanvas
                            .getKeyName(SOFT_KEY_LEFT_MOTOROLA1).toUpperCase();
                } catch (IllegalArgumentException ilae) {
//                    ilae.printStackTrace();
                }
                String softkeyLeftMoto2 = EMPTY_STRING;
                try {
                    softkeyLeftMoto2 = adaptorCanvas
                            .getKeyName(SOFT_KEY_LEFT_MOTOROLA2).toUpperCase();
                } catch (IllegalArgumentException ilae) {
//                    ilae.printStackTrace();
                }
                if (softkeyLeftMoto.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto.indexOf(ONE_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA;
                } else if (softkeyLeftMoto1.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto1.indexOf(ONE_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA1;
                } else if (softkeyLeftMoto2.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto2.indexOf(ONE_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA2;
                } else if (softkeyLeftMoto.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto.indexOf(LEFT_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA;
                } else if (softkeyLeftMoto1.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto1.indexOf(LEFT_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA1;
                } else if (softkeyLeftMoto2.indexOf(SOFT_STRING) >= 0
                        && softkeyLeftMoto2.indexOf(LEFT_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA2;
                }else{
                    return SOFT_KEY_LEFT_MOTOROLA;
                }

            } else if (PLATFORM_NAME.equals(PLATFORM_NOKIA)) {
                return SOFT_KEY_LEFT_NOKIA;
            } else if (PLATFORM_NAME.equals(PLATFORM_SAMSUNG)) {
                return SOFT_KEY_LEFT_SAMSUNG;
            } else if (PLATFORM_NAME.equals(PLATFORM_SIEMENS)) {
                return SOFT_KEY_LEFT_SIEMENS;
            } else if (PLATFORM_NAME.equals(PLATFORM_SONY_ERICSSON)) {
                return SOFT_KEY_LEFT_SE;
            } else if (PLATFORM_NAME.equals(PLATFORM_NOT_DEFINED)) {
                return SOFT_KEY_LEFT_NOKIA;
            }else
            {
                          return SOFT_KEY_LEFT_NOKIA;
            }
            
        } catch (Throwable iaEx) {
            //#if emulator
//#              return SOFT_KEY_LEFT_NOKIA;
            //#endif
        }finally{
            if (keyCode == 0) {
                //#if emulator
//#                  return SOFT_KEY_LEFT_NOKIA;
                //#endif
            }
        }
        return SOFT_KEY_LEFT_NOKIA;
    }

    /**
     * define real right soft key code for current platform
     *
     * @return code
     */
    private int getRightSoftkeyCode() {
//        int keyCode = 0;
        try {
            if (PLATFORM_NAME.equals(PLATFORM_MOTOROLA)) {

                String rightSoftMoto1 = EMPTY_STRING;
                try {
                    rightSoftMoto1 = adaptorCanvas
                            .getKeyName(SOFT_KEY_LEFT_MOTOROLA1).toUpperCase();
                } catch (IllegalArgumentException ilae) {
//                    ilae.printStackTrace();
                }
                String rightSoftMoto = EMPTY_STRING;
                try {
                    rightSoftMoto = adaptorCanvas
                            .getKeyName(SOFT_KEY_RIGHT_MOTOROLA).toUpperCase();
                } catch (IllegalArgumentException ilae) {
//                    ilae.printStackTrace();
                }
                String rightSoftMoto2 = EMPTY_STRING;
                try {
                    rightSoftMoto2 = adaptorCanvas
                            .getKeyName(SOFT_KEY_RIGHT_MOTOROLA1).toUpperCase();
                } catch (IllegalArgumentException ilae) {
//                    ilae.printStackTrace();
                }
                if (rightSoftMoto.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto.indexOf(TWO_STRING) >= 0) {
                    return SOFT_KEY_RIGHT_MOTOROLA;
                } else if (rightSoftMoto1.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto1.indexOf(TWO_STRING) >= 0) {
                    return SOFT_KEY_RIGHT_MOTOROLA;
                } else if (rightSoftMoto2.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto2.indexOf(TWO_STRING) >= 0) {
                    return SOFT_KEY_RIGHT_MOTOROLA1;
                } else if (rightSoftMoto.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto.indexOf(RIGHT_STRING) >= 0) {
                    return SOFT_KEY_LEFT_MOTOROLA;
                } else if (rightSoftMoto1.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto1.indexOf(RIGHT_STRING) >= 0) {
                    return SOFT_KEY_RIGHT_MOTOROLA1;
                } else if (rightSoftMoto2.indexOf(SOFT_STRING) >= 0
                        && rightSoftMoto2.indexOf(RIGHT_STRING) >= 0) {
                    return SOFT_KEY_RIGHT_MOTOROLA;
                }

            } else if (PLATFORM_NAME.equals(PLATFORM_NOKIA)) {
                return SOFT_KEY_RIGHT_NOKIA;
            } else if (PLATFORM_NAME.equals(PLATFORM_SAMSUNG)) {
                return SOFT_KEY_RIGHT_SAMSUNG;
            } else if (PLATFORM_NAME.equals(PLATFORM_SIEMENS)) {
                String rightSoftSiemens = adaptorCanvas
                        .getKeyName(SOFT_KEY_RIGHT_SIEMENS).toUpperCase();
                if (rightSoftSiemens.indexOf(SOFT_STRING) >= 0) {
                    if (rightSoftSiemens.indexOf(FOUR_STRING) >= 0) {
                        return SOFT_KEY_RIGHT_SIEMENS;
                    } else if (rightSoftSiemens.indexOf(RIGHT_STRING) >= 0) {
                        return SOFT_KEY_RIGHT_SIEMENS;
                    }
                }
            } else if (PLATFORM_NAME.equals(PLATFORM_SONY_ERICSSON)) {
                return SOFT_KEY_RIGHT_SE;
            } else if (PLATFORM_NAME.equals(PLATFORM_NOT_DEFINED)) {
                for (int i = -125; i <= 125; i++) {
                    if (i == 0) {
                        i++;
                    }
                    String keyName = adaptorCanvas.getKeyName(i).toUpperCase();
                    if (keyName.indexOf(SOFT_STRING) >= 0) {
                        if (keyName.indexOf(TWO_STRING) >= 0) {
//                            keyCode = i;
                            break;
                        } else if (keyName.indexOf(FOUR_STRING) >= 0) {
//                            keyCode = i;
                            break;
                        } else if (keyName.indexOf(RIGHT_STRING) >= 0) {
//                            keyCode = i;
                            break;
                        }
                    }
                }
            }
        } catch (Throwable iaEx) {
            //#if emulator
//#             return SOFT_KEY_RIGHT_NOKIA;
            //#endif
        }
        return SOFT_KEY_RIGHT_NOKIA;
    }

    /**
     * define real middle soft key code for current platform
     *
     * @return code
     */
    private int getMidleORInternetSoftkeyCode() {
        try {
            if (PLATFORM_NAME.equals(PLATFORM_MOTOROLA)) {
                if (adaptorCanvas.getKeyName(SOFT_KEY_MIDLE_MOTOROLA)
                        .toUpperCase().indexOf(SOFT_STRING) >= 0) {
                    return SOFT_KEY_MIDLE_MOTOROLA;
                }
            } else if (PLATFORM_NAME.equals(PLATFORM_NOKIA)) {
                if (adaptorCanvas.getKeyName(SOFT_KEY_MIDLE_NOKIA)
                        .toUpperCase().indexOf(SOFT_STRING) >= 0) {
                    return SOFT_KEY_MIDLE_NOKIA;
                }
            } else if (PLATFORM_NAME.equals(PLATFORM_SAMSUNG)) {
            } else if (PLATFORM_NAME.equals(PLATFORM_SIEMENS)) {
            } else if (PLATFORM_NAME.equals(PLATFORM_SONY_ERICSSON)) {
                return INTERNET_KEY_SE;
            }
        } catch (Throwable e) {
        }
        return 0;
    }

    /**
     * define real key's C or DELETE code for current platform
     *
     * @return code
     */
    private int getDeleteKeyCode() {
        try {
            if (PLATFORM_NAME.equals(PLATFORM_MOTOROLA)) {

            } else if (PLATFORM_NAME.equals(PLATFORM_NOKIA)) {
                if (adaptorCanvas.getKeyName(DELETE_KEY_SE)
                        .toUpperCase().indexOf(CLEAR_STRING) >= 0) {
                    return DELETE_KEY_NOKIA;
                } else {
                    return DELETE_KEY_NOKIA;
                }
            } else if (PLATFORM_NAME.equals(PLATFORM_SAMSUNG)) {
                if (adaptorCanvas.getKeyName(DELETE_KEY_SAMSUNG)
                        .toUpperCase().indexOf(CLEAR_STRING) >= 0) {
                    return DELETE_KEY_SAMSUNG;
                }
            } else if (PLATFORM_NAME.equals(PLATFORM_SIEMENS)) {
            } else if (PLATFORM_NAME.equals(PLATFORM_SONY_ERICSSON)) {
                if (adaptorCanvas.getKeyName(DELETE_KEY_SE)
                        .toUpperCase().indexOf(CLEAR_STRING) >= 0) {
                    return DELETE_KEY_SE;
                } else if (adaptorCanvas.getKeyName(DELETE_KEY_SE)
                        .toUpperCase().indexOf("C") >= 0) {
                    return DELETE_KEY_SE;
                } else {
                    return DELETE_KEY_SE;
                }
            }
        } catch (Throwable e) {
            return DELETE_KEY_SE;
        }
        return 0;
    }


    /**
     * define real key's BACK code for current platform
     *
     * @return code
     */
    private int getBackKeyCode() {
        try {
            if (PLATFORM_NAME.equals(PLATFORM_MOTOROLA)) {

            } else if (PLATFORM_NAME.equals(PLATFORM_NOKIA)) {

            } else if (PLATFORM_NAME.equals(PLATFORM_SAMSUNG)) {
            } else if (PLATFORM_NAME.equals(PLATFORM_SIEMENS)) {
            } else if (PLATFORM_NAME.equals(PLATFORM_SONY_ERICSSON)) {
                return BACK_KEY_SE;
            }
        } catch (Throwable e) {
        }
        return 0;
    }

    /**
     * name of curent platform
     *
     * @return PLATFORM_NAME
     */
    public String getPlatformName() {
        return PLATFORM_NAME;
    }

    /**
     * Used to adopt key code to predefined constance, which are platform
     * independent.
     * <p/>
     * You can use this method in any kind of canvas, but better at first
     * time to call
     * <code>getInstance()</code> method at the beginning of midlet work,
     * because initialization takes time.
     * <p/>
     * Best variant for usage is calling <code>adoptKeyCode()</code> to 
     * use <code>keyPressed()</code> method in Canvas:
     * <pre>
     * protected void keyPressed(int keyCode) {
     *     keyCode = KeyCodeAdapter.getInstance().adoptKeyCode(keyCode);
     * }
     * </pre>
     * and then you can use it:
     * <pre>
     * switch (keyCode) {
     *   case KeyCodeAdapter.UP_KEY:
     *     break;
     *   case KeyCodeAdapter.SOFT_KEY_LEFT:
     *     break;
     * }</pre>
     * or send this code to any other classes.
     *
     * @param keycode This code is sent by platform to canvas and
     * redirected here
     * @return this keycode is equal to one of our constants declared
     * in this class
     */
    public static int adoptKeyCode(int keycode) {
        switch (keycode) {
            case Canvas.KEY_NUM0:
                return KEY_0;
            case Canvas.KEY_NUM1:
                return KEY_1;
            case Canvas.KEY_NUM2:
                return KEY_2;
            case Canvas.KEY_NUM3:
                return KEY_3;
            case Canvas.KEY_NUM4:
                return KEY_4;
            case Canvas.KEY_NUM5:
                return KEY_5;
            case Canvas.KEY_NUM6:
                return KEY_6;
            case Canvas.KEY_NUM7:
                return KEY_7;
            case Canvas.KEY_NUM8:
                return KEY_8;
            case Canvas.KEY_NUM9:
                return KEY_9;
            case Canvas.KEY_STAR:
                return KEY__STAR;
            case Canvas.KEY_POUND:
                return KEY__POUND;
            default:
//                if (Debug.ENABLE) {
//                    System.out.print("keycode: " + keycode + " - SoftKey_Left: "
//                            + SOFTKEY_RIGHT);
//                }
//                if (keycode == SOFTKEY_LEFT) {
//                    
//                    return SOFT_KEY_LEFT;
//                } else if (keycode == SOFTKEY_RIGHT) {
//                    return SOFT_KEY_RIGHT;
//                } else if (keycode == SOFTKEY_DELETE) {
//                    return DELETE_KEY;
//                } else if (keycode == SOFTKEY_BACK) {
//                    return BACK_KEY;
//                } else if (keycode == SOFTKEY_MIDDLE_INTERNET) {
//                    return SOFT_KEY_MIDDLE_INTERNET;
//                } else if (keycode == PENCIL_KEY_NOKIA) {
//                    return PENCIL_KEY;
//                }
//                else {
//                    try {
//                        final int gameAction;
//                        gameAction = adaptorCanvas.getGameAction(keycode);
//                        if (gameAction == Canvas.UP) {
//                            return UP_KEY;
//                        } else if (gameAction == Canvas.DOWN) {
//                            return DOWN_KEY;
//                        } else if (gameAction == Canvas.LEFT) {
//                            return LEFT_KEY;
//                        } else if (gameAction == Canvas.RIGHT) {
//                            return RIGHT_KEY;
//                        } else if (gameAction == Canvas.FIRE) {
//                            return CENTER_KEY;
//                }
//                    } catch (IllegalArgumentException e) {
//                        if (Debug.ENABLE) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
                break;
        }
        //#if debug
        //# return keycode;
        //#else
        return NOT_DEFINED_KEY;
        //#endif
    }

    /**
     * return instance of class
     *
     * @return instance
     */
    public static KeyCodeAdapter getInstance(Canvas canvas) {
        return new KeyCodeAdapter(canvas);
    }
}
