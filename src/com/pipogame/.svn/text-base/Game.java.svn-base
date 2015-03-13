package com.pipogame;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * Đầu vào của game, các game mới cần kế thừa lớp này và khai báo midlet chính
 * của chương trình.
 * <p/>
 * <b>Chú ý:</b>
 * <ul>
 * <li>Khi release phải đặt {@link Debug#ENABLE} = <code>false</code></li>
 * </ul>
 * @author Honghai
 */
public abstract class Game extends MIDlet implements CommandListener {

    protected boolean midletPaused = false;
    protected ScreenManager screenManager;


    /**
     * Initializes the application.
     * It is called only once when the MIDlet is started. The method is
     * called before the <code>startGame</code> method.
     */
    private void initialize() {
        screenManager = new ScreenManager(this, getGameName());
    }

    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    protected void startGame() {
        if (initGame(screenManager) &&
                checkDevice(getNotSupportedScreenArray())) {
            screenManager.startUp();
            switchDisplayable(null, screenManager);
        }
    }

    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    protected void resumeGame() {
        screenManager.resumeGame();
    }

    /**
     * Switches a current displayable in a display. The <code>display</code>
     * instance is taken from <code>getDisplay</code> method. This method is
     * used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if
     * <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    protected void switchDisplayable(Alert alert, Displayable nextDisplayable) {
        Display display = getDisplay();
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }
    }

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    protected Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    void exitGame() {
        switchDisplayable(null, null);
        destroyApp(true);
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and
     * initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeGame();
        } else {
            initialize();
            startGame();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
        if (screenManager != null) {
            screenManager.pauseGame();
        }
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        if (unconditional) {
            screenManager.exitGameLoop();
            screenManager = null;
            System.gc();
            notifyDestroyed();
        }
    }
    
    /**
     * Khởi tạo các thông số cần thiết cho game và
     * Add một screen bắt đầu cho game, thường là <code>SplashScreen</code>
     * hoặc <code>MenuScreen</code>... hay thêm nhạc cho game.
     * Nếu phương thức này trả về false thì
     * game sẽ không chạy được.
     * @param screenManager <code>ScreenManger</code> quản lý các tiến trình
     * trong game, gọi
     * <code>screenManager.addScreen(new Screen&lt;?extend GameScreen&gt;())</code>
     * để thêm một <code>GameScreen</code> mới
     * @return <code>true</code> nếu add thành công.
     */
    protected abstract boolean initGame(ScreenManager screenManager);
    
    /**
     * Lấy tên game để đặt tên cho {@linkplain Store} lưu trữ game.
     * @return tên game
     */
    protected abstract String getGameName();
    
    /**
     * 
     * @param mesage
     * @return 
     */
    private Displayable getAlertForNonSupportedScreen(String mesage) {
        Form alertForm = new Form("Lỗi");
        alertForm.append(mesage);
        alertForm.addCommand(new Command("Thoát", Command.OK, 1));
        alertForm.setCommandListener(this);
        return alertForm;
    }

    public void commandAction(Command c, Displayable d) {
        if (c.getCommandType() == Command.OK) {
            destroyApp(true);
        }
    }
    
    /**
     * Gọi đến phương thức này khi kiểm tra thấy màn hình thiết bị không được
     * hỗ trợ. Việc kiểm tra nên thực hiện ở phương thức 
     * {@link #initGame(com.pipogame.ScreenManager)}.
     * @return <code>true</code> &ndash; nếu thiết bị phù hợp và sắn sàng vào
     * trò chơi.<br/>
     * <code>false</code> &ndash; nếu ngược lại.
     */
    public boolean checkDevice(int[] notSupportScreens) {
        if (notSupportScreens == null) {
            return true;
        }
        for (int i = 0; i < notSupportScreens.length; i++) {
            if (notSupportScreens[i] == screenManager.screenType) {
                String exceptionDes = "Trò chơi này không hỗ trợ màn hình "
                        + screenManager.getWidth() + 'x' 
                        + screenManager.getHeight()
                        +" của thiết bị này";
                
                switchDisplayable(null, 
                        getAlertForNonSupportedScreen(exceptionDes));
                return false;
            }
        }
        return true;
    }
    
    /**
     * Lấy ra các loại screen mà game không hỗ trợ. Các lớp kế thừa có thể
     * ghi đè phương thức này để xác định game không chạy đc trên loại màn hình
     * nào.
     * @return mảng int[] chứa các loại screen không hỗ trợ, phương thức này của 
     * lớp {@link Game} trả về null, có nghĩa là hỗ trợ mọi loại màn hình.
     */
    protected int[] getNotSupportedScreenArray() {
        return null;
    }
}
