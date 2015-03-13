package com.pipogame;

import com.pipogame.util.*;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Biểu diễn một screen của game, đây là thành phần nhỏ nhất của một đối tượng
 * screen dùng để cô lập các thành phần của game.
 * Có thể dùng lớp này để tạo một Menu screen,
 * GamePlay screen, HighScore screen... phục vụ các mục đích khác nhau.
 * <p/>
 * Một screen có 3 trạng thái cơ bản là {@linkplain ScreenState#TRANSITION_ON},
 * {@linkplain ScreenState#ACTIVE} và
 * {@linkplain ScreenState#TRANSITION_OFF} chuyển đổi qua lại theo 1 chiều
 * (cũng có thể ép từ ACTIVE về ON)
 * <p/>
 * Quá trình chuyển đổi trạng thái bắt đầu theo đúng thứ tự trên. Thời gian để
 * một screen xuất hiện được set qua trường {@linkplain #transitionOnTime} và
 * thời gian để một screen biến mất đc xác định qua trường
 * {@linkplain #transitionOffTime}. Có một trường gọi là
 * <code>trasitionPos</code>
 * thuộc đoạn [0;1] làm nhiệm vụ lưu trạng thái chuyển đổi của screen. Nếu = 0
 * tức là screen đã đến trạng thái ACTIVE, nếu = 1 tức là nó đang ở trạng thái
 * ON hoặc đã chuyển từ trạng thái ACTIVE sang OFF. Các trạng thái được liệt kê
 * trong lớp {@linkplain ScreenState}.
 * <p/>
 * Mỗi screen có các phương thức đặc trưng để quản lý các thành phần của screen
 * đó.
 *
 * @company B-Gate
 * @see ScreenManager
 */
public abstract class GameScreen {

    /**
     * The <code>ScreenManager</code> of the <code>GameScreen</code>.
     */
    public ScreenManager screenManager;
    /**
     * Indicate that this screen is exiting from the list.
     */
    boolean exiting;
    protected boolean initialized;
    protected boolean loaded;
    /**
     * the time that this screen used to transition on in millisecond.
     */
    protected int transitionOnTime;
    /**
     * the time that this screen is used to transition off in millisecond.
     */
    protected int transitionOffTime;
    /**
     * transition position of a screen,
     * its value ranges from 0 to 1<br/>
     * = 1: transitioned, fully off the display<br/>
     * = 0: fully active, no transition<br/>
     * Transition on: 1 -&gt; 0.
     */
    protected float transitionPos = 1;
    protected float tranPos2;
    protected int screenState = ScreenState.TRANSITION_ON;
    private int timeCancelCount;
    private int timeCancelInput = 300;
    /**
     * Width and _height of Screens.<br/>
     * Please do not modify these two fields.
     */
    protected static int _width, _height;

    protected int translateX;
    protected int translateY;

    protected Store asset;

    /**
     * Load all resource for this <code>GameScreen</code> here.
     */
    public void loadContent() throws Exception {
        loaded = true;
    }

    /**
     * Initialize all game screen state here.
     */
    public void initialize() throws Exception {
        initialized = true;
    }

    /**
     * Handle input for this screen.
     *
     * @param input the Input that method can query input state
     * @see #setTimeCancelInput(int)
     */
    public abstract void handleInput(Input input, int frameTime);
    
    /**
     * Tiền xử lý input. Xem xem trạng thái của input là gì, reset input nếu
     * phím bị giữ trong khoảng thời gian tối thiểu
     * <code>timeCancelInput</code>.
     * 
     * @param input
     * @param frameTime
     * @return  <code>true</code> nếu input ko bị reset và có thể cho phép
     * screen được gọi đến <code>handleInput()</code>.
     * <br/> <code>false</code> nếu ngược lại.
     */
    boolean preHandleInput(Input input, int frameTime) {
        if (input.isSameKeyState() || input.isSameKeyCode()) {
            timeCancelCount += frameTime;
            if (timeCancelCount >= timeCancelInput) {
                timeCancelCount = 0;
            } else {
                input.resetKey();
                return false;
            }
        } else {
            timeCancelCount = 0;
        }
        
        return true;
    }

    /**
     * Update code đặt ở đây.
     * @param frameTime Thời gian chạy của frame trước.
     * @param covered <code>true</code> nếu screen bị che bởi screen khác. 
     * <i><font color="red">Không cần sử dụng <code>covered</code>
     * nữa.</font></i>
     * 
     */
    public abstract void update(int frameTime, boolean covered);
    
    boolean preUpdate(int frameTime, boolean covered) {
        if (exiting) {
            screenState = ScreenState.TRANSITION_OFF;

            if (!updateTransition(frameTime, transitionOffTime, 1)) {
                onScreenExiting();
                screenManager.removeCurrentScreen();
                onScreenExited();
                PFont.free();
                return false;
            }
        } else if (covered) {
            if (updateTransition(frameTime, transitionOffTime, 1)) {
                screenState = ScreenState.TRANSITION_OFF;
            } else {
                screenState = ScreenState.HIDDEN;
            }
        } else {
            if (updateTransition(frameTime, transitionOnTime, -1)) {
                screenState = ScreenState.TRANSITION_ON;
            } else {
                screenState = ScreenState.ACTIVE;
            }
        }
        return true;
    }

    /**
     * Vẽ các hình ảnh lên screen.
     *
     * @param g the graphics buffer to draw in
     * @param frameTime Thời gian chạy của frame trước.
     */
    public abstract void draw(Graphics g, int frameTime);

    /**
     * Giải phóng các tài nguyên mà Screen đã sử dụng (Image, Sprite, ...) hoặc
     * làm một số công việc khác.
     */
    public void finalize() {
        loaded = false;
    }

    /**
     * Update the position of the screen in game.
     *
     * @param frameTime time between each frame
     * @param time total time to make transition
     * @param direction which transition direction to perform, its value
     * is 1 or -1.
     * <li>If direction == 1, screen is going to transition off
     * <li> else it is going to transition on
     */
    private boolean updateTransition(int frameTime, int time, int direction) {
        float transDelta;

        if (time == 0) {
            transDelta = 1;
        } else {
            transDelta = frameTime / (float) time;
        }

        transitionPos += transDelta * direction;
        tranPos2 = transitionPos * transitionPos;

        if (((direction < 0) && (transitionPos < 0)) ||
                 ((direction > 0) && (transitionPos > 1))) {
            if (transitionPos < 0) {
                transitionPos = 0;
                tranPos2 = 0;
            } else {
                transitionPos = 1;
                tranPos2 = 1;
            }
            return false;
        }
        return true;
    }

    /**
     * Load hình ảnh trong file jar, các đường dẫn được cho trong 
     * <code>asset</code>, trước khi gọi đến <code>loadImage</code> phải khởi
     * tạo <code>asset</code> trước.
     * @param key khoá
     * @return
     * @throws IOException 
     */
    public final Image loadImage(int key) throws IOException {
        if (Debug.ENABLE) {
            if (asset == null) {
                throw new NullPointerException("asset null in loadImage(key).");
            } else if (asset.getString(key) == null) {
                throw new NullPointerException(
                        "value null in loadImage(" + key + ").");
            }
        }
        return Image.createImage(asset.getString(key));
    }

    /**
     * Gọi khi cần thoát màn hình để chuyển sang màn hình khác.
     * Nếu <code>transitionOffTime == 0</code> thì màn hình sẽ bị bỏ đi ngay lập
     * tức, nếu không thì nó sẽ transition off trong thời gian lưu trong biến.
     * <code>transitionOffTime</code>
     */
    public final void exitScreen() {
        if (transitionOffTime == 0) {
            onScreenExiting();
            screenManager.removeCurrentScreen();
            onScreenExited();
        } else {
            exiting = true;
        }
    }
    
    /**
     * Được gọi khi thoát game. Cần thực hiện những việc như lưu game... thì 
     * override phương thức này.
     */
    public void onExitGame() {}

    /**
     * Được gọi khi đã remove screen này khỏi queue. Không nên addScreen ở đây.
     * @deprecated 
     */
    protected void onScreenExited() {
    }

    /**
     * Được gọi khi chuẩn bị remove screen này khỏi queue. Không nên
     * addScreen ở đây.
     * @deprecated 
     */
    protected void onScreenExiting() {
    }

    /**
     * Đặt một khoảng thời gian để bỏ qua input khi bấm giữ cùng một phím.
     * Nếu phím đó được bấm hai lần trong 1 khoảng thời gian rất nhanh thì nó sẽ
     * không bị bỏ qua. Giá trị mặc định là 100ms.
     * @param time thời gian để bỏ qua các input tiếp theo khi phím bị giữ,
     * tính bằng millisecond
     */
    public void setTimeCancelInput(int time) {
        if (Debug.ENABLE) {
            if (time < 0) {
                System.out.println("Debug info in " +
                         Util.getClassName(getClass().getName()));
                throw new IllegalArgumentException("Time cannot be negative:" +
                        time);
            }
        }
        timeCancelInput = time;
    }

    public boolean isExiting() {
        return exiting;
    }

    /**
     * Lấy độ rộng của screen.
     * @return độ rộng screen
     */
    public static int width() {
        return _width;
    }

    /**
     * Lấy độ dài của screen.
     * @return độ dài screen
     */
    public static int height() {
        return _height;
    }

    /**
     * Lấy ra đối tượng <code>ScreenManager</code>.
     */
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    
    /**
     * Vẽ làm cho screen mờ dần hoặc rõ dần theo một màu trong khi transition on
     * hoặc off.
     * @param g 
     * @param color màu gốc
     */
    protected final void drawFade(Graphics g, int color) {
        Drawer.drawBg(g, 0, 0, _width, _height,
                (((int)(transitionPos*256)) << 24) | (color & 0x00ffffff));
    }
    
    /**
     * Vẽ làm cho screen mờ dần hoặc rõ dần theo một màu.
     * @param g
     * @param color
     * @param transparent độ trong suốt của màn che, giá trị từ 0 đến 1
     */
    protected final void drawFade(Graphics g, int color, float transparent) {
        Drawer.drawBg(g, 0, 0, _width, _height,
                (((int)(transparent*256)) << 24) | (color & 0x00ffffff));
    }
    
    /**
     * Tô đầy screen bởi một màu
     * @param g đối tượng Graphics
     * @param color màu cần tô
     */
    public static void fillScreen(Graphics g, int color) {
        g.setColor(color);
        g.fillRect(0, 0, _width, _height);
    }
    
    /**
     * Lớp enum các trạng thái của một <code>GameScreen</code>.
     */
    public static class ScreenState {
        /**
         * Trạng thái mới bắt đầu của một screen.
         */
        public static final int TRANSITION_ON = 1;
        /**
         * Sau trạng thái {@link #TRANSITION_ON}. Trạng thái này là trạng thái
         * hoạt động của một screen.
         */
        public static final int ACTIVE = 2;
        /**
         * Trạng thái dự phòng (không đc sử dụng trong engine này).
         */
        public static final int HIDDEN = 3;
        /**
         * Trạng thái sau khi gọi phương thức {@link GameScreen#exitScreen()}
         * của một screen.
         */
        public static final int TRANSITION_OFF = 4;
    }
}
