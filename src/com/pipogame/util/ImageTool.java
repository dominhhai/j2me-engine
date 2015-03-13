package com.pipogame.util;

import com.pipogame.*;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Lớp xử lý ảnh bằng các tác vụ cơ bản
 * @author HaiNH
 * @company B-Gate
 */
public final class ImageTool {
    /**
     * Màu thay thế cho pixel trong suốt trong ảnh không có alpha chanel.<br/>
     * Giá trị <code>0xffff00ff</code>, có thể sai khác tuỳ theo thiết bị.
     */
    public static final int TRANSPARENT_COLOR = getTransparentColor();
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    
    /**
     * Lấy màu sẽ được coi là trong suốt, độc lập trên các thiết bị khác nhau.
     * @return Mã màu được coi là trong suốt.
     */
    private static int getTransparentColor() {
        Image image = ImageTool.createImage(2, 2);
        Graphics g = image.getGraphics();
        g.setColor(0xffff00ff);
        g.fillRect(0, 0, 2, 2);
        int [] data = getImageData(image, 0, 0, 2, 2);
        return data[0];
    }
    
    /**
     * Thay đổi các kênh màu của ảnh gốc.
     * @param image ảnh gốc
     * @param red lượng thay đổi của kênh <i>red</i>
     * @param green lượng thay đổi của kênh <i>green</i>
     * @param blue lượng thay đổi của kênh <i>blue</i>
     * @return ảnh đã được thay đổi kênh màu
     */
    public static Image changeImageRGB(Image image, int red, int green, int blue) {
        int[] rgbData = new int[image.getWidth()*image.getHeight()];
        image.getRGB(rgbData, 0, image.getWidth(), 0, 0,
                image.getWidth(), image.getHeight());
        return Image.createRGBImage(changeRGB(rgbData, red, green, blue),
                image.getWidth(), image.getHeight(), true);
    }
    
    /**
     * Thay đổi độ sáng của ảnh gốc
     * @param image ảnh gốc
     * @param brightness lượng sáng cần thay đổi
     * @return 
     */
    public static Image changeImageBrightness(Image image, int brightness) {
        int[] rgbData = new int[image.getWidth() * image.getHeight()];
        image.getRGB(rgbData, 0, image.getWidth(), 0, 0,
                image.getWidth(), image.getHeight());
        return Image.createRGBImage(
                changeRGB(rgbData, brightness, brightness, brightness),
                image.getWidth(),
                image.getHeight(),
                true);
    }

    private static int[] changeRGB(int[] rgbDat, int red, int green, int blue) {
        if (Debug.ENABLE) {
            if (red > 255 | green > 255 | blue > 255 |
                    red < -255| green < -255| blue < -255) {
                throw new IllegalArgumentException("In "
                        + ImageTool.class.getName()
                        + ".changeRGB(int[] rgbDat, int red, int green, int blue)");
            }
        }
        int opac, r, g, b;
        int deep = 256;
        for (int i = 0; i < rgbDat.length; i++) {
            opac = rgbDat[i] & 0xff000000;
            r = Math.max(((rgbDat[i] & 0x00ff0000) >>> 16) + red, 0);
            r = r > 0 ? r : 0;
            g = Math.max(((rgbDat[i] & 0x0000ff00) >>> 8) + green, 0);
            g = g > 0 ? g : 0;
            b = Math.max((rgbDat[i] & 0x000000ff) + blue, 0);
            b = b > 0 ? b : 0;
            
            rgbDat[i] = opac | r << 16 | g << 8 | b;
        }
        return rgbDat;
    }

    /**
     * Tạo ảnh đa mức xám cho ảnh ban đầu.
     *
     * @param image ảnh ban đầu
     * @return
     */
    public static Image getGrayScaleImage(Image image) {
        int     width = image.getWidth(),
                height = image.getHeight(),
                length = width*height;
        
        int[] rgbData = new int[length];
        
        image.getRGB(rgbData, 0, width, 0, 0, width, height);
        
        int opac, red, green, blue;
        int color;
        for (int x = 0; x < length; x++) {
            color = rgbData[x];
            opac = (color & 0xFF000000); // Opacity level
            if (opac == 0) {
                continue;
            }
            red = ((color & 0x00FF0000) >>> 16); // Red level
            green = ((color & 0x0000FF00) >>> 8); // Green level
            blue = (color & 0x000000FF); // Blue level

            int gray = (int)(red*0.299f + green*0.587f + blue*0.114f);
            
            rgbData[x] = opac | gray << 16 | gray << 8 | gray;
        }
        Image grayImage = Image.createRGBImage(rgbData, width, height, true);
        return grayImage;
    }
    
    /**
     * Lấy vùng ảnh từ ảnh gốc &ndash; giữ nguyên kênh <i>alpha</i>.
     * @param image ảnh gốc
     * @param src_x góc trên bên trái vùng cần copy
     * @param src_y góc trên bên trái vùng cần copy
     * @param width chiều rộng vùng cần copy
     * @param height chiều dài vùng cần copy
     * @return Ảnh được copy từ ảnh gốc
     */
    public static Image getImageRegion(Image image, int src_x, int src_y,
            int width, int height) {
        return Image.createRGBImage(
                getImageData(image, src_x, src_y, width, height),
                width, height, true);
    }
    
    private static int[] getImageData(Image image, int src_x, int src_y,
            int width, int height) {
        if (Debug.ENABLE) {
            if (src_x + width > image.getWidth() ||
                    src_y + height > image.getHeight()) {
                throw new IllegalArgumentException("In "
                        + ImageTool.class.getName()
                        + "getRGBRegion(Image image, int src_x, int " +
                        "src_y, int width, int height)");
            }
        }
        
        Image image_temp = createImage(width, height);
        Graphics g = image_temp.getGraphics();
        
        g.setColor(TRANSPARENT_COLOR);
        g.fillRect(0, 0, width, height);
        
        g.drawRegion(image, src_x, src_y, width, height, 0, 0, 0, 0);
        
        return getMutableImageData(image_temp);
    }
    
    public static Image createImage(int width, int height) {
        return Image.createImage(width, height);
    }
    
    /**
     * Chia ảnh <code>src</code> thành các phần có chiều dài và chiều rộng là
     * <code>width</code> và <code>height</code> rồi lấy frame với số thứ tự
     * là <code>frame</code> (bắt đầu từ 0).
     * @param image ảnh gốc
     * @param width chiều dài 1 frame
     * @param height chiều rộng 1 frame
     * @param frame số thứ tự của frame cần lấy ảnh
     * @return ảnh nhỏ ở frame thứ <code>frame</code>.
     */
    public static Image getImageRegion(Image image, 
            short width, short height, int frame) {
        int framesInWidth = image.getWidth() / width;
        return getImageRegion(image, (frame % framesInWidth) * width,
                frame / framesInWidth * height, width, height);
    }
    /**
     * Lấy một phần ảnh gốc tạo bởi các frame.
     * @param image ảnh gốc
     * @param numHorz số frame theo hàng ngang
     * @param numVer số frame theo hàng dọc
     * @param frame số thứ tự của frame cần copy
     * @return Vùng ảnh cần copy
     */
    public static Image getImageByFrame(Image image,
            int numHorz, int numVer, int frame) {
        int width = image.getWidth()/numHorz;
        int height = image.getHeight() / numVer;
        return getImageRegion(image, (frame % numHorz) * width,
                frame / numHorz * height, width, height);
    }
    
    /**
     * Lấy dữ liệu RGB từ một ảnh mutable, màu để tượng trưng cho trong suốt
     * được định nghĩa trong {@link #TRANSPARENT_COLOR}, màu này sẽ được thay
     * thế bằng một pixel trong suốt.
     * @param mutableImage ảnh gốc
     */
    public static int[] getMutableImageData(Image mutableImage) {
        int width = mutableImage.getWidth(), height = mutableImage.getHeight();
        int[] data = new int[width* height];
        mutableImage.getRGB(data, 0, width, 0, 0, width, height);
        for (int j = 0; j < data.length; j++) {
            if (data[j] == TRANSPARENT_COLOR) {
                data[j] = 0;
            }
        }
        return data;
    }
    
    /**
     * Lấy ra mảng ARGB của ảnh.
     * @param image ảnh cần lấy data.
     * @return mảng chứa data của ảnh.
     */
    public static int[] getImutableImageData(Image image) {
        int width = image.getWidth(), height = image.getHeight();
        int[] data = new int[width* height];
        image.getRGB(data, 0, width, 0, 0, width, height);
        return data;
    }

    /**
     * Thay đổi một thành phần màu của <code>color</code>.
     * Các thành phần màu này có thể là <code>RED, GREEN</code> hay 
     * <code>BLUE</code>.
     * @param color Màu ban đầu
     * @param component Thành phần màu
     * @param quantity Lượng thay đổi
     * @return màu đã thay đổi thành phần
     * @throws IllegalArgumentException nếu thành phần màu lớn hơn 2
     */
    public static int changeColor(int color, int component, int quantity) {
        if (Debug.ENABLE) {
            if (component > 2) {
                throw new IllegalArgumentException("Color compoment false: " + component +
                        " ImageProcessing.changeColor(int, int , int)");
            }
        }
        component *= 8;
        int newComponent = (color >>> component) & 0xff;
        color &= ~(0xff<<component);
        
        newComponent = ((newComponent + quantity + 256) % 256) << component;
        return color | newComponent;
    }
    
    /**
     * Thay đổi opacity của một vùng trên ảnh có dữ liệu được chứa trong
     * <code>data</code>.
     * @param data dữ liệu của ảnh gốc
     * @param imWidth chiều rộng ảnh gốc
     * @param imHeight chiều cao ảnh gốc
     * @param transparency độ trong suốt mới
     * @param x toạ độ x góc trên bên trái của vùng
     * @param y toạ độ y góc trên bên trái của vùng
     * @param width chiều rộng của vùng
     * @param height chiều dài của vùng
     * @return <code>image</code> nhưng đã thay đổi opacity của vùng ảnh 
     * được chọn.
     */
    public static int[] changeOpacity(int[] data, int imWidth, int imHeight,
            float transparency, int x, int y, int width, int height) {
        if (Debug.ENABLE) {
            if (transparency < 0 || transparency > 1) {
                throw new IllegalArgumentException(
                        "Transparency cannot greater than 1 or less than 0");
            }
        }
        
        int pixel;
        int location;
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                location = i + j * imWidth;
                pixel = data[location] >>> 24;
                pixel *= transparency;
                data[location] = pixel << 24 | (data[location] & 0x00ffffff);
            }
        }
        
        return data;
    }
    
    /**
     * Thay đổi độ trong suốt của một vùng trong ảnh
     * @param data dữ liệu của ảnh gốc
     * @param imWidth chiều rộng của ảnh gốc
     * @param imHeight chiều cao của ảnh gốc
     * @param transparency độ trong suốt của vùng ảnh
     * @param x toạ độ x của góc trên bên trái của vùng ảnh
     * @param y toạ độ y của góc trên bên trái của vùng ảnh
     * @param width chiều rộng vùng ảnh
     * @param height chiều cao vùng ảnh
     * @return hình ảnh đã được thay đổi độ trong suốt
     */
    public static Image changeImageOpacity(int[] data, int imWidth, int imHeight,
            float transparency, int x, int y, int width, int height) {
        data = changeOpacity(data, imWidth, imHeight, transparency, x, y, width, height);
        return Image.createRGBImage(data, imWidth, imHeight, true);
    }
    
    public static Image changeImageOpacity(int[] data, int imWidth, int imHeight,
            float transparency) {
        return changeImageOpacity(data, imWidth, imHeight, transparency,
                0, 0, imWidth, imHeight);
    }
    
    public static Image changeImageOpacity(Image image, float transparency) {
        int w = image.getWidth(), h = image.getHeight();
        return Image.createRGBImage(
                changeOpacity(
                getImutableImageData(image), w, h, transparency, 0, 0, w, h),
                w, h, true);
    }
    
    /**
     * Tạo một ảnh đệm MutableImage với vùng trong suốt giả được tô màu với mã
     * màu 0xffff00ff
     * @param width chiều rộng ảnh
     * @param height chiều cao ảnh
     * @return ảnh được dùng như ảnh đệm
     */
    public static Image makePsudoTransparentImageBuffer(int width, int height) {
        Image image = createImage(width, height);
        Graphics g = image.getGraphics();
        g.setColor(TRANSPARENT_COLOR);
        g.fillRect(0, 0, width, height);
        return image;
    }
    
    /**
     * Tạo một ảnh đệm MutableImage với vùng trong suốt giả được tô màu với mã
     * màu 0xffff00ff và được vẽ sẵn ảnh <code>image</code>.
     * @param image ảnh được vẽ sẵn vào bộ đệm
     * @return ảnh được dùng như ảnh đệm
     */
    public static Image makePsudoTransparentImageBuffer(Image image) {
        Image buff = makePsudoTransparentImageBuffer(image.getWidth(), 
                image.getHeight());
        Graphics g = buff.getGraphics();
        g.drawImage(image, 0, 0, 0);
        return buff;
    }
}
