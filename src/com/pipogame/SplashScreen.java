package com.pipogame;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author HaiNH
 * @company B-Gate
 */
public class SplashScreen extends GameScreen {

    private Sprite flash1102;
    private Sprite flashgameHd;
    private Sprite flashPi;
    private Sprite flashPo;
    private int xFlash;
    private int yFlash;
    private int statusFlash = -1;
    private int screenW = 34;
    private int speed = 1;
    private int xPo;
    private int xPi;
    private int timeNextFrame = 0;
    private int speedActor = 7;
    private int yActor;
    private int[] seqPo1 = new int[]{6, 7};
    private int[] seqPi1 = new int[]{0, 1, 2, 3, 4, 5};
    private int[] seqPo2 = new int[]{0, 1, 2, 3, 4, 5};
    private int frameRate;
    public void initialize() throws Exception {
        super.initialize();
        frameRate = screenManager.frameRate;
    }

    public void loadContent() throws Exception {
        super.loadContent();
        flash1102 = new Sprite(loadImageByte("1102"),
                120, 49);
        flashgameHd = new Sprite(loadImageByte("gamehd"),
                176, 34);
        flashPo = new Sprite(loadImageByte("po"), 31, 53);
        flashPo.setFrameSequence(seqPo1);
        flashPi = new Sprite(loadImageByte("pi"), 37, 64);
        flashPi.setFrameSequence(seqPi1);
        flashgameHd.setVisible(false);
        xFlash = _width / 2 - 60;
        yFlash = -50;
        xPo = _width + flashPo.getWidth() / 2;
        xPi = -flashPi.getWidth() / 2;
        yActor = _height / 2;
    }

    public void handleInput(Input input, int frameTime) {
    }

    public void update(int frameTime, boolean covered) {
        switch (statusFlash) {
            case -1:
                timeNextFrame += frameTime;
                if (timeNextFrame > 300) {
                    timeNextFrame = 0;
                    statusFlash++;
                }
                break;
            case 0:
                if (yFlash >= _height / 2 - 10) {
                    yFlash = _height / 2 - 10;
                    statusFlash++;
                    screenManager.setFrameRate(100);
                    speed = 3;
                } else {
                    if (speed + yFlash > _height / 2 - 10) {
                        speed = _height / 2 - yFlash - 10;
                    }
                    yFlash = yFlash + speed;
                    speed = speed + 5;
                }
                break;
            case 1:
                screenManager.setFrameRate(100);
                yFlash = yFlash - speed;
                speed++;
                timeNextFrame++;
                if (timeNextFrame > 6) {
                    statusFlash++;
                    speed = 1;
                    timeNextFrame = 0;
                }
                break;
            case 2:
                if (yFlash >= _height / 2 - 10) {
                    yFlash = _height / 2 - 10;
                    statusFlash++;
                    screenManager.setFrameRate(10);
                    speed = 1;
                } else {
                    if (speed + yFlash > _height / 2 - 10) {
                        speed = _height / 2 - yFlash - 10;
                    }
                    yFlash = yFlash + speed;
                    speed++;
                }

                break;
            case 3:
                screenManager.setFrameRate(100);
                flash1102.nextFrame();
                if (flash1102.getFrame() == 0) {
                    statusFlash++;
                    screenManager.setFrameRate(33);
                    flash1102.setFrame(8);
                    flashgameHd.setVisible(true);
                    xPo = _width / 2 + 23;
                }
                break;
            case 4:
                timeNextFrame++;
                if (timeNextFrame > 3) {
                    timeNextFrame = 0;
                    flashPo.nextFrame();
                }
                if (screenW > 0) {
                    screenW--;
                } else {
                    statusFlash++;
                }

                break;
            case 5:
                timeNextFrame++;
                if (timeNextFrame > 1) {
                    timeNextFrame = 0;
                    flashPo.nextFrame();
                }
                screenManager.setFrameRate(13);
                flashPi.nextFrame();
                if (xPi < _width / 2 - 11) {
                    xPi = xPi + speedActor;
                } else {
                    flashPi.setFrameSequence(new int[]{6, 7});
                    statusFlash++;
                }

                break;
            case 6:
                screenManager.setFrameRate(7);
                flashPo.nextFrame();
                flashPi.nextFrame();
                timeNextFrame++;
                if (timeNextFrame == 5) {
                    flashPo.setFrameSequence(new int[]{8, 9});
                } else if (timeNextFrame == 10) {
                    statusFlash++;
                    timeNextFrame = 0;
                }
                break;
            case 7:
                timeNextFrame++;
                flashPo.nextFrame();
                flashPi.nextFrame();
                if (timeNextFrame == 1) {
                    flashPi.setFrameSequence(seqPi1);
                    flashPi.setTransform(2);
                    statusFlash++;
                    timeNextFrame = 0;

                }
            case 8:
                timeNextFrame++;
                if (timeNextFrame % 2 == 0) {
                    flashPo.nextFrame();
                } else if (timeNextFrame == 9) {
                    statusFlash++;
                    timeNextFrame = 0;
                    flashPo.setFrameSequence(new int[]{10, 11});
                }
                screenManager.setFrameRate(13);
                flashPi.nextFrame();
                if (xPi > -flashPi.getWidth()) {
                    xPi = xPi - speedActor;
                }
                break;
            case 9:
                timeNextFrame++;
                if (timeNextFrame % 2 == 0) {
                    flashPo.nextFrame();
                } else if (timeNextFrame == 9) {
                    statusFlash++;
                    timeNextFrame = 0;
                    flashPo.setFrameSequence(seqPo2);
                }
                screenManager.setFrameRate(13);
                flashPi.nextFrame();
                if (xPi > -flashPi.getWidth()) {
                    xPi = xPi - speedActor;
                }
                break;
            case 10:
                flashPi.nextFrame();
                flashPo.nextFrame();
                if (xPo > -flashPo.getWidth()) {
                    xPi = xPi - speedActor;
                    xPo = xPo - speedActor;
                } else {
                    statusFlash++;
                }

                break;
            case 11:
                timeNextFrame++;
                if (timeNextFrame > 10) {
                    screenManager.setFrameRate(frameRate);
                    exitScreen();
                }
                break;

        }
    }

    public void draw(Graphics g, int frameTime) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, _width, _height);
        if (flashgameHd.isVisible()) {
            flashgameHd.setPosition(_width / 2 - flashgameHd.getWidth() / 2,
                    _height / 2 - flashgameHd.getHeight() + screenW);
            flashgameHd.paint(g);
        }
        if (flashPo.isVisible()) {
            flashPo.setPosition(xPo - flashPo.getWidth() / 2,
                    yActor - flashPo.getHeight() + screenW);
            flashPo.paint(g);
        }
        if (flashPi.isVisible()) {
            flashPi.setPosition(xPi - flashPi.getWidth() / 2,
                    yActor - flashPi.getHeight());
            flashPi.paint(g);
        }
        if (statusFlash == 4) {
            g.fillRect(0, _height / 2, _width, 50);
        }
        if (flash1102.isVisible()) {
            flash1102.setPosition(xFlash + 5, yFlash);
            flash1102.paint(g);
        }
    }


    public Image loadImageByte(String name) throws IOException {
        InputStream is = getClass().getResourceAsStream("/com/pipogame/"
                + name);
        byte[] byteImage = new byte[is.available()];
        is.read(byteImage);
        for (int i = 0; i < byteImage.length; i = i + 3) {
            byteImage[i] = (byte) ~(byteImage[i]);
            byteImage[i] = (byte) (byteImage[i] - 14);
        }
        return Image.createImage(byteImage, 0, byteImage.length);
    }
}
