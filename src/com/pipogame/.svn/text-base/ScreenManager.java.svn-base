/**
 * package com.pipogame infomation
 * @see ScreenManager
 */
package com.pipogame;

import com.pipogame.components.Point;
import com.pipogame.components.Rect;
import com.pipogame.util.Drawer;
import com.pipogame.util.Queue;
import com.pipogame.util.Util;
import java.util.Random;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

/**
 * Đối tượng gốc quản lý các màn hình của game. Đây là một
 * {@linkplain GameCanvas} để quản lý, hiển thị các đối tượng
 * {@linkplain GameScreen}. Các screen được đưa vào một
 * {@linkplain Queue} để quản lý. Screen ở đầu của
 * <code>Queue</code> sẽ là screen hiện thời. Mỗi khi screen hiện thời bị đẩy
 * khỏi queue thì các screen khác mới có cơ hội được hiển thị.<br/>
 * <code>ScreenManager</code> cho phép tối đa 10 <code>GameScreen</code> ở trong
 * queue.
 * <dl>
 * <dt>Các bước hiển xử lý một <code>GameScreen</code>:</dt>
 * <dd>&ndash; handleInput: Gọi đến phương thức
 * xử lý input của screen hiện tại thông qua phương thức
 * {@link GameScreen#handleInput(com.pipogame.Input, int)}.
 * </dd>
 * <dd>&ndash; update: Update trạng thái của screen
 * hiện tại thông qua phương thức {@linkplain GameScreen#update(int frameTime,
 * boolean)}.
 * </dd>
 * <dd>&ndash; draw: Vẽ screen hiện tại lên màn hìn
 * thiết bị thông qua thông qua phương thức
 * {@linkplain GameScreen#draw(Graphics, int)}.
 * </dd>
 * </dl>
 *
 * Các bước xử lý trên (có thể không đầy đủ, xem {@linkplain #cancelOneDraw()})
 * được thực hiện trong một vòng
 * lặp của <code>Thread</code>
 * chính của game engine trong thời gian là {@linkplain #frameTime}. frameTime
 * này sẽ được truyền vào các phương thức handleInput, update, draw của lớp
 * GameScreen để tiện sử dụng sau này.
 * <p/>
 * Game hoạt động ở 2 chế độ
 * <ul>
 * <li><i><b>FixFrameRate</b></i> &ndash; chế độ mà frameTime của mỗi frame
 * sẽ được kéo dài sao cho nó ít nhất là bằng với một mốc nào đó đặt trước.
 * Tức là nếu frameTime của frame đó ngắn hơn định mức thì vòng lặp sẽ sleep
 * trong khoảng thời gian còn lại, nếu dài hơn thì nó chính bằng giá trị đó.
 * </li>
 * <li>
 * <i><b>VariableFrameRate</b></i> &ndash; chế độ mà mỗi khi một vòng lặp của
 * một frame kết thúc, ngay lập tức vọng lặp tiếp theo sẽ được gọi.
 * </li>
 * </ul>
 * Chú ý: Ở chế độ Debug (tức là {@link Debug#ENABLE}<code> = true</code>)  và 
 * <i>VariableFrameRate</i> thì 
 * frameTime luôn được đặt 1 giá trị xác định, 33 default.
 * <p/>
 *       Game có 2 trạng thái là:
 * <ul>
 * <li>
 *  <i><b>pause</b></i> &ndash; đang dừng, ở chế độ này chỉ có phương thức 
 * {@linkplain GameScreen#handleInput(Input, int)} được gọi đến mà thôi,
 * hai phương thức còn lại sẽ ko đc gọi.
 * </li>
 * <li>
 *  <i><b>running</b></i> &ndash; toàn bộ vòng lặp game sẽ đc thực thi.
 * </li>
 * </ul>
 * 
 * <code>ScreenManager</code> cung cấp một đối tượng lưu trữ {@link Store}, 
 * một đối tượng chơi các file MIDI đồng thời quản lý âm lượng {@link Music}.
 * <p/>
 * <b>Chú ý:</b><br/>
 * <ul>
 * <li>Khi release phải đặt {@linkplain #DISPLAY_FRAMETIME} = false</li>
 * <li>Bật hiển thị frame-rate bằng cách giữ phím '#'</li>
 * <li>Bật hiển thị thông tin bộ nhớ bằng cách giữ phím '*'</li>
 * </ul>
 *
 * @author Honghai
 * @company B-Gate
 * @see Input
 */
public class ScreenManager extends GameCanvas implements Runnable {
    /**
     * Lưu trữ setting của game
     */
    public final Store store;
    /**
     * Âm thanh của game. Khi muốn tăng giảm âm lượng chỉ cần sử dụng các phương
     * thức của chính đối tượng <code>music</code> này. Âm lượng của game sẽ
     * được tự động lưu lại.
     */
    public final Music music;
    private boolean appRunning;
    /**
     * The MIDlet which the application belongs
     */
    private final Game game;
    /**
     * Screens of the game.
     */
    private final Queue screens = new Queue(10);
    /**
     * Time to refresh each game frame and state in milliseconds
     */
    private int frameTime = 33;
    private int fixFrameTime = 33;
    /**
     * True if run game at fix frame rate
     */
    private boolean runAtFixedRate = true;
    /**
     * <code>timeCounter</code> used when a <code>GameScreen</code> is first
     * added. <code>GameScreen</code> will handle input when
     * <code>timeCounter</code> out of <code>timeCancel</code>
     */
    private int timeCounterToCancelInput;
    /**
     * Time before the <code>GameScreen</code> can handle input
     */
    private final int timeCancelInputOfEachScreen = 300;
    /**
     * Is the game is running
     */
    private boolean gamePaused = false;
    /**
     * If true, the <code>draw()</code> method will not be called
     */
    private boolean isSuppressDraw = false;
    private boolean reUpdate = false;
    /**
     * Hold input information
     */
    private Input input;
    /**
     * A service random object
     */
    public static final Random RANDOM = new Random();
    private Thread gameThread;

    /**
     * Ứng với loại màn hình 320x320, giá trị = 1
     */
    public static final byte SCR_320_320 = 1;

    /**
     * Ứng với loại màn hình 320x240, giá trị = 2
     */
    public static final byte SCR_320_240 = 2;

    /**
     * Ứng với loại màn hình 240x320, giá trị = 3
     */
    public static final byte SCR_240_320 = 3;

    /**
     * Ứng với loại màn hình 172x200, giá trị = 4
     */
    public static final byte SCR_172_200 = 4;

    /**
     * Ứng với loại màn hình 128x160, giá trị = 5
     */
    public static final byte SCR_128_160 = 5;

    /**
     * Biến áp dụng trong quá trình debug, hiển thị frameTime trên mạn hình
     */
    public static final boolean DISPLAY_FRAMETIME = false;

    /**
     * Loại màn hình máy đang chạy. Game tự động detect loại màn hình máy là
     * <code>SCR_320_320, SCR_240_320</code>...
     */
    public final byte screenType;

    private Graphics g;
    
    public final boolean handleSoftkeyByTouch;
    private Rect softLeftRect;
    private Rect softRightRect;
    private Point translatePoint;
    private Image softKeyPlaceImage;
    public boolean drawSoftkeyPlace;
    
    /**
     * Key để lấy ra âm lượng của âm thanh trong game. Việc khởi tạo và lưu
     * lại âm lượng hoàn toàn do engine quản lý.
     */
    static String VOLUME_KEY = "&#vLmE;+";

    /**
     * Khởi tạo đối tượng <code>ScreenManager</code> mới.
     * @param game {@linkplain MIDlet} đầu vào của chương trình.
     * @param gameName Tên game để tiện cho việc lưu trữ một {@link RecordStore}
     * chứa các {@link Store} cho game
     */
    public ScreenManager(Game game, String gameName) {
        super(false);
        if (Debug.ENABLE) {
            if (gameName.length() > 31 - 13) {
                throw new IllegalArgumentException("Excetion: Game name is " +
                        "too long in constructor Screenmanager(..., ...)");
            }
        }
        this.game = game;

        store = new Store(gameName, Store.LOAD_FROM_RECORDSTORE);
        if (store.contains(VOLUME_KEY)) {
            music = new Music(store.getInt(VOLUME_KEY));
        } else {
            music = new Music(50);
        }
        setFullScreenMode(true);
        screenType = getScreenType(super.getWidth(), super.getHeight());

        if (DISPLAY_FRAMETIME) {
            ggg = new com.pipogame.util.TimeProcessMeasurer();
        }

        g = super.getGraphics();
        g.setColor(0);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        if (Debug.ENABLE || DISPLAY_FRAMETIME) {
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN,
                      Font.SIZE_SMALL));
        }
        handleSoftkeyByTouch = hasPointerEvents();
        
        if (handleSoftkeyByTouch) {
            try {
                softKeyPlaceImage = Image.createImage("/com/pipogame/pt.png");
            } catch (Exception ex) {
                if (Debug.ENABLE) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void startUp() {
        redefineScreenSize(screenType);
        translatePoint = new Point((super.getWidth() - GameScreen._width) / 2,
                (super.getHeight() - GameScreen._height) / 2);
        
        input = new Input(this, hasPointerEvents(), translatePoint);
        
        int width = GameScreen._width / 4;
        int height = GameScreen._height / 8;
        softLeftRect = new Rect(0, GameScreen._height - height, width, height);
        softRightRect = new Rect(GameScreen._width - width, softLeftRect.y,
                width, height);
        g.translate(translatePoint.getX(), translatePoint.getY());
        
        gameThread = new Thread(this);
        gameThread.setPriority(Thread.MAX_PRIORITY);
        gameThread.start();
    }
    
    protected void redefineScreenSize(int screenType) {
        if (screenType == SCR_128_160) {
            GameScreen._width = 128;
            GameScreen._height = 160;
        } else if (screenType == SCR_172_200) {
            GameScreen._width = 172;
            GameScreen._height = 200;
        } else if (screenType == SCR_320_240) {
            GameScreen._width = 320;
            GameScreen._height = 240;
        } else if (screenType == SCR_320_320) {
            GameScreen._width = 320;
            GameScreen._height = 320;
        } else {
            GameScreen._width = 240;
            GameScreen._height = 320;
        }
    }

    private byte getScreenType(int width, int height) {
        if (Debug.ENABLE) {
            System.out.println("Debug info in " +
                    Util.getClassName(getClass().getName()));
            System.out.println("Screen " + width + "x" +height);
        }
        GameScreen._width = width;
        GameScreen._height = height;
        if (width < 140) {
            return SCR_128_160;
        } else if (width < 190) {
            return SCR_172_200;
        } else if (width < 250) {
            return SCR_240_320;
        } else if (height > 300) {
//            return SCR_320_320;
            return SCR_240_320;
        }
        return SCR_320_240;
    }

    private com.pipogame.util.TimeProcessMeasurer ggg;

    private void mainLoop() {
        try {
            loadHeadScreenIfUnloaded();
            // Always handle input even if not update or draw
            // Cancel input some time if new screen added
            if (timeCounterToCancelInput < timeCancelInputOfEachScreen) {
                timeCounterToCancelInput += frameTime;
                input.resetKey();
            } else {
                int keyState = getKeyStates();
                input.setNewKeyState(keyState);
                if (handleSoftkeyByTouch) {
                    if (input.touched(softLeftRect)) {
                        input.setSoftkeyLeftPrd();
                    } else if (input.touched(softRightRect)) {
                        input.setSoftkeyRightPrd();
                    }
                }
                handleInput(input);
            }
            if (gamePaused) {
                Thread.sleep(200);
            } else {
                // Update if the game is running
                update(frameTime);
                // And suppress draw if needed
                if (!isSuppressDraw && !reUpdate) {
                    draw();
                } else {
                    Thread.sleep(10);
                }
                reUpdate = false;
            }

        } catch (Exception e) {
            restoreClipping();
            if (Debug.ENABLE) {
                System.out.println("Debug info in "
                        + Util.getClassName(getClass().getName())
                        + ". Terminating main loop.");
                e.printStackTrace();
            }
            g.setColor(0xff0000);
            String err = Util.getClassName(e.getClass().getName()) + ": " +
                    e.getMessage();
            
            if (err.equals(": ")) {
                err = "Lỗi không xác định";
            }
            g.drawString(err, (getWidth() - g.getFont().stringWidth(err)) / 2,
                    (getHeight() - g.getFont().getHeight()) / 2, 0);
            flushGraphics();
            appRunning = false;
        }
    }

    /**
     * Thread to run the game loop
     */
    public final void run() {
        appRunning = true;
        gamePaused = false;

        long startTime, runTime, sleepTime;
        while (appRunning) {
            if (DISPLAY_FRAMETIME) {
                ggg.begin();
            }
            startTime = System.currentTimeMillis();
            mainLoop();
            runTime = System.currentTimeMillis() - startTime;

            if (DISPLAY_FRAMETIME) {
                ggg.end(frameTime, (int) runTime);
            }
            if (runAtFixedRate) {
                sleepTime = fixFrameTime - runTime;
                if (sleepTime > 0) {
                    frameTime = fixFrameTime;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        if (Debug.ENABLE) {
                            System.out.println("Debug info in " +
                                    Util.getClassName(getClass().getName()));
                            ex.printStackTrace();
                        }
                    }
                } else if (Debug.ENABLE) {
                    if (runTime > 150) {
                        frameTime = fixFrameTime;
                    } else {
                        frameTime = (int) runTime;
                    }
                } else {
                    frameTime = (int) runTime;
                }
            } else if (Debug.ENABLE) {
                if (runTime > 150) {
                    frameTime = fixFrameTime;
                } else {
                    frameTime = (int) runTime;
                }
            } else {
                frameTime = (int) runTime;
            }
        }
    }

    /**
     * Thêm vào trong queue một screen mới
     * @param gameScreen screen mới
     */
    public void addScreen(GameScreen gameScreen) {
        gameScreen.exiting = false;
        
        gameScreen.screenManager = this;
        boolean screenAdded = screens.offer(gameScreen);
        
        restoreClipping();

        if (Debug.ENABLE) {
            if (!screenAdded) {
                throw new RuntimeException("Too much screen in buffer.");
            }
            
            System.out.println("Debug info in "
                    + Util.getClassName(getClass().getName()));
            System.out.println("Added "
                    + Util.getClassName(gameScreen.getClass().getName())
                    + ". Num of screen = " + screens.occupiedSlots());
        }
    }
    
    private void loadHeadScreenIfUnloaded() {
        GameScreen screen = (GameScreen)screens.peek();
        if (screen != null) {
            if (!screen.initialized) {
                try {
                    screen.initialize();
                } catch (Exception ex) {
                    if (Debug.ENABLE) {
                        System.out.println("Debug info in " +
                                Util.getClassName(getClass().getName()));
                        ex.printStackTrace();
                    }
                }
                screen.initialized = true;
            }
            
            if (!screen.loaded) {
                try {
                    screen.loadContent();
                } catch (Exception ex) {
                    if (Debug.ENABLE) {
                        System.out.println("Debug info in " +
                                Util.getClassName(getClass().getName()));
                        ex.printStackTrace();
                    }
                }
                screen.loaded = true;
                if (Debug.ENABLE) {
                    screenName = Util.getClassName(screen.getClass().getName())
                             + "; "+ screens.occupiedSlots();
                }
            }
        }
    }

    /**
     * Gỡ bỏ screen hiện thời, khởi tạo các biến cần thiết cho 
     * <code>GameScreen</code>
     * sau trong queue.
     */
    void removeCurrentScreen() {
        timeCounterToCancelInput = 0;
        GameScreen screen = (GameScreen) screens.poll();
        screen.finalize();
        
        if (Debug.ENABLE) {
            System.out.println("Debug info in "
                    + Util.getClassName(getClass().getName()));
            System.out.println("Remove "
                    + Util.getClassName(screen.getClass().getName())
                    + ". Num of screen = " + screens.occupiedSlots());
        }
        // Mỗi lần thay đổi 1 screen phải update lại chứ ko vẽ luôn vì lúc đó có 
        // thể screen mới chưa update hết vị trí các đối tượng trong game trước
        // khi vẽ.
        reUpdate = true;
        // Sau đó khôi phục clipping cho màn hình tiếp theo.
        restoreClipping();
    }

    /**
     * Gỡ bỏ tất cả các screen trong queue
     */
    public void removeAllScreens() {
        timeCounterToCancelInput = 0;
        while(!screens.isEmpty()) {
            removeCurrentScreen();
        }
    }

    /**
     * Thay đổi trạng thái tạm dừng hay chơi tiếp của game
     * @return <code><b>true</b> - </code>if changed to paused<br/>
     * <code><b>false</b> - </code> if changed to running
     */
    public boolean changePauseResume() {
         return gamePaused = !gamePaused;
    }

    /**
     * Only call this from MIDlet to destroy the game loop thread.
     */
    void exitGameLoop() {
//        gcTimer.cancel();
        synchronized (screens) {
            while (!screens.isEmpty()) {
                ((GameScreen)screens.poll()).onExitGame();
            }
            store.set(VOLUME_KEY, music.getVolume());
            music.finalize();
            store.save();
            appRunning = false;
        }
    }

    /**
     * Gọi từ một <code>GameScreen</code> khi muốn thoát khỏi game.
     */
    public void exitGame() {
        game.exitGame();
    }

    private void handleInput(Input input) {
        if (!screens.isEmpty()) {
            GameScreen screen = (GameScreen) screens.peek();
            if (screen.preHandleInput(input, frameTime) && !screen.exiting) {
                screen.handleInput(input, frameTime);
                input.resetKey();
            }
        }
    }

    private void update(int frameTime) {
        if (!screens.isEmpty()) {
            GameScreen screen = (GameScreen) screens.peek();
            if (!screen.loaded) {
                reUpdate = true;
                return;
            }
            if (screen.preUpdate(frameTime, false)) {
                screen.update(frameTime, false);
            }
        }
    }

    /**
     * Tiện ích khi update trạng thái của game dẫn đến hình ảnh tại frame đó
     * bị giật, ta có thể bỏ qua lượt draw() của frame đó để đến với frame tiếp
     * theo. Nếu Game chạy ở chế độ <i><b>FixFrameRate</b></i> thì vòng lặp của game
     * sẽ sleep để đủ thời gian của frame đó, còn nếu game chạy ở chế độ
     * <i><b>VariableFrameRate</b></i> thì nó sẽ ngay lập tức thực hiện frame tiếp theo.
     * @see ScreenManager
     */
    public void cancelOneDraw() {
        reUpdate = true;
    }

    //<editor-fold defaultstate="collapsed" desc="Debug Variables">
    private long counttimeff = TIME_SIZE_UPDATE;
    public static final int TIME_SIZE_UPDATE = 3000;
    private String freeSize, totalsize, screenName;
    public boolean sizeEnabel, frameEnable;
    //</editor-fold>

    private void draw(){
        restoreClipping();
        if (!screens.isEmpty()) {
            ((GameScreen)screens.peek()).draw(g, frameTime);
        }
        
        if (drawSoftkeyPlace && handleSoftkeyByTouch) {
            Drawer.drawCenter(g, softKeyPlaceImage,
                    softLeftRect.centerX(), softLeftRect.centerY());
            Drawer.drawCenter(g, softKeyPlaceImage,
                    softRightRect.centerX(), softRightRect.centerY());
        }
        
        if (DISPLAY_FRAMETIME) {
            if (frameEnable) {
                g.setClip(0, 0, GameScreen._width, GameScreen._height);
                ggg.printTime(g, 2, 2, 0x0000ff);
                g.drawString(screenName, 0, 17, 0);
            }
        }
        if (Debug.ENABLE) {
            if (sizeEnabel) {
                counttimeff += frameTime;
                if (counttimeff > TIME_SIZE_UPDATE) {
                    counttimeff -= TIME_SIZE_UPDATE;
                    freeSize = "Free:" + Runtime.getRuntime().freeMemory()/1024;
                    totalsize = "Total:" 
                            + Runtime.getRuntime().totalMemory()/1024;
                }
                Font font = g.getFont();
                g.setColor(0x0000ff);
                g.drawString(totalsize,
                        getWidth() - font.stringWidth(totalsize), 0, 0);
                g.drawString(freeSize, getWidth() - font.stringWidth(freeSize),
                        2*font.getHeight(), 0);
            }
        }

        flushGraphics(translatePoint.getX(), translatePoint.getY(),
                GameScreen._width, GameScreen._height);
    }

    /**
     * Tương tự như {@linkplain #cancelOneDraw()} nhưng setSuppressDraw sẽ bỏ
     * qua bước draw nếu đặt <code>true</code> cho đến khi đặt lại thành <code>false</code>
     * @param suppressDraw <code> true</code> nếu muốn các frame tiếp theo sẽ
     * bỏ qua bước render
     */
    public void setSuppressDraw(boolean suppressDraw) {
        isSuppressDraw = suppressDraw;
    }

    /**
     * Kiểm tra xem có đang ở chế độ suppressDraw
     * @return <code>true</code> &ndash; nếu đang ở chế độ suppressDraw
     */
    public boolean isSuppressDraw() {
        return isSuppressDraw;
    }

    /**
     * Kiểm tra xem game có đang chạy.
     * @return
     */
    public boolean isRunning() {
        return !gamePaused;
    }

    /**
     * Khôi phục clipping cho đối tượng {@link Graphics} của game
     */
    public void restoreClipping() {
        g.setClip(0, 0, GameScreen._width, GameScreen._height);
    }

    /**
     * Lấy đối tượng {@link Graphics} của game
     * @return
     */
    public Graphics getGraphics() {
        return g;
    }

    /**
     * Đưa game về trạng thái tạm dừng
     */
    public void pauseGame() {
        gamePaused = true;
    }

    /**
     * Đưa game về trạng thái chạy bình thường
     */
    public void resumeGame() {
        gamePaused = false;
    }
    
    /**
     * Lưu frameRate để tạo lại sau khi chạy SplashScreen vì SplashScreen thay 
     * đổi frameRate liên tục.
     */
    int frameRate;
    
    /**
     * Đặt frame rate cho game.
     * Nếu <code>framePerSecond</code> = 0, game sẽ hoạt động ở chế độ
     * <i>VariableFrameRate</i>, nếu &gt; 0 và &#8804; 100 thì game sẽ hoạt động
     * ở chế độ <i>FixFrameRate</i>.
     *
     * @param framePerSecond Frame trên 1 giây
     * @throws IllegalArgumentException nếu <code>framePerSecond</code> &lt 0
     * hoặc &gt; 100
     */
    public void setFrameRate(int framePerSecond) {
        this.frameRate = framePerSecond;
        if (framePerSecond > 0 && framePerSecond <= 100) {
            this.fixFrameTime = 1000 / framePerSecond;
            runAtFixedRate = true;
        } else {
            if (framePerSecond == 0) {
                runAtFixedRate = false;
                fixFrameTime = 33;
            } else {
                if (Debug.ENABLE) {
                    throw new IllegalArgumentException("Not accept framerate ="+
                            framePerSecond + " frames per second");
                }
            }
        }
    }

    /**
     * Overriden {@link Canvas#keyPressed(int)}.
     * @param keyCode 
     */
    public void keyPressed(int keyCode) {
        input.setKeyCode(keyCode);
    }

    protected void keyRepeated(int keyCode) {
        input.setKeyRepeated(keyCode);
        if (Debug.ENABLE) {
            if (keyCode == KEY_NUM0) {
                sizeEnabel = !sizeEnabel;
            } else if (keyCode == KEY_POUND) {
                frameEnable = !frameEnable;
            }
        }
    }

    protected void keyReleased(int keyCode) {
        input.setKeyReleased(keyCode);
    }

    /**
     * Overriden {@link Canvas#pointerPressed(int, int)}.
     * @param x
     * @param y
     */
    public void pointerPressed(int x, int y) {
        input.setTouchPosition(x, y);
    }

    /**
     * Overriden {@link Canvas#pointerReleased(int, int)}.
     * @param x
     * @param y
     */
    public void pointerReleased(int x, int y) {
        input.setTouchUpPosition(x, y);
    }

//    /**
//     * Check this device support touch handler.
//     * @return true if this device support touching
//     */
//    public final boolean isTouchHandler() {
//        if (hasPointerEvents() && hasPointerMotionEvents()) {
//            return true;
//        }
//        return false;
//    }
    
    public int getNumbersOfScreen() {
        return screens.occupiedSlots();
    }

    protected void hideNotify() {
        gamePaused = true;
    }

    protected void showNotify() {
        gamePaused = false;
    }
}
