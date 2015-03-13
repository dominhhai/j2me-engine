package com.pipogame;

import com.pipogame.util.*;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Honghai
 * @company B-Gate
 */
public abstract class AbstractLoadingScreen extends GameScreen { 
//    protected boolean loadDone;
//    private int countDraw;
//    private long time;
    protected float percentLoaded;

    public void setPercentLoaded(float percentLoaded) {
        if (percentLoaded > this.percentLoaded) {
            this.percentLoaded = percentLoaded;
        }
    }
    public static void loadScreen(final ScreenManager screenManager,
            final AbstractLoadingScreen loadingScreen) {
        screenManager.removeAllScreens();
        screenManager.addScreen(loadingScreen);
        
        
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    GameScreen loadedScreen = loadingScreen.getScreenToLoad();
                    loadedScreen.screenManager = screenManager;
                    if (!loadedScreen.initialized) {
                        loadedScreen.initialize();
                    }
                    loadedScreen.loadContent();
                    screenManager.removeAllScreens();
                    screenManager.addScreen(loadedScreen);
                } catch (Exception e) {
                    if (Debug.ENABLE) {
                        System.out.println("Debug info in " + 
                                Util.getClassName(AbstractLoadingScreen.class.getName()));
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread loadThread = new Thread(runnable);
        loadThread.setPriority(Thread.MIN_PRIORITY);
        loadThread.start();
    }
    
    public abstract GameScreen getScreenToLoad();

}
