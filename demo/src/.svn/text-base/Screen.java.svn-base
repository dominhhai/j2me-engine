
import com.pipogame.Debug;
import com.pipogame.GameScreen;
import com.pipogame.Input;
import com.pipogame.components.GameSprite;
import com.pipogame.util.Util;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author HaiNH
 * @company B-Gate
 */
public class Screen extends GameScreen {

    GameSprite camelGameSprite;

    public void initialize() {
        super.initialize();
        setTimeCancelInput(200);
        transitionOnTime = 500;
        transitionOffTime = 700;
    }
    
    public void loadContent() {
        super.loadContent();
        try {
            camelGameSprite = new GameSprite(Image.createImage("/camel.png"), 6, 1);
        } catch (IOException ex) {
            if (Debug.ENABLE) {
                System.out.println("Debug info in " +
                        Util.getClassName(getClass().getName()));
                ex.printStackTrace();
            }
        }
        camelGameSprite.setPosition(50, 150);
        camelGameSprite.setTimeSequence(110);
    }
    
    int frameSpeed;

    public void handleInput(Input input, int frameTime) {
        if (input.isLeftPrd()) {
            frameSpeed = (frameSpeed + 4) % 5;
        } else if (input.isRightPrd()) {
            frameSpeed = (frameSpeed + 1) % 5;
        } else if (input.isFirePrd()) {
            exitScreen();
        }
    }

    public void update(int frameTime, boolean covered) {
        camelGameSprite.updateFrame(frameTime, true);
        camelGameSprite.move(20, 5, frameTime);
        if (camelGameSprite.getX() > _width) {
            camelGameSprite.setPosition(0, camelGameSprite.getY());
        }
        if (camelGameSprite.getY() > _height) {
            camelGameSprite.setPosition(camelGameSprite.getX(), 0);
        }
    }
    
    public void draw(Graphics g, int frameTime) {
        g.setColor(0);
        g.fillRect(0, 0, _width, _height);
        camelGameSprite.paint(g);
        g.setColor(0xffff00);
        g.drawString("Speed level " + frameSpeed, (int) (tranPos2*200) + 50, 100, 0);
        g.drawString("FrameTime = " + frameTime, (int) (tranPos2*200) + 50, 120, 0);
        try {
            switch (frameSpeed) {
            case 0:
                Thread.sleep(40);
                break;
            case 1:
                Thread.sleep(50);
                break;
            case 2:
                Thread.sleep(60);
                break;
            case 3:
                Thread.sleep(80);
                break;
            case 4:
                Thread.sleep(100);
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
