package com.pipogame;

import com.pipogame.util.Util;

/**
 * Một abstract screen phục vụ load một screen nào đó. Screen đó đc khởi tạo 
 * trong phương thức {@link #getGameScreenToLoad()}. Sau khi load xong
 * screen đó sẽ tự động được đưa lên trước để hiển thị, 
 * <code>LoadingScreen</code> sẽ bị loại đi. Thanh loading có thể được vẽ bình
 * thường thông qua các phương thức của lớp {@link GameScreen}.
 * @author Honghai
 * @company B-Gate
 */
public abstract class LoadingScreen extends GameScreen { 
    protected float percentLoaded;
    
    public void setPercentLoaded(float percentLoaded) {
        if (percentLoaded > this.percentLoaded) {
            this.percentLoaded = percentLoaded;
        }
    }
    public static void loadScreen(final ScreenManager screenManager,
            final LoadingScreen loadingScreen) {
        screenManager.removeAllScreens();
        screenManager.addScreen(loadingScreen);
        
        
        Runnable loadJob = new Runnable() {
            public void run() {
                try {
                    GameScreen loadedScreen = loadingScreen.getGameScreenToLoad();
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
                                Util.getClassName(LoadingScreen.class.getName()));
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread loadThread = new Thread(loadJob);
        loadThread.setPriority(Thread.MIN_PRIORITY);
        loadThread.start();
    }
    
    public abstract GameScreen getGameScreenToLoad();
}
