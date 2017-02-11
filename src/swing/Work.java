package swing;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Work {
    private static Builder builder;
    private static BufferedImage image;


    public static void main(String[] args) {
       builder = new Builder();
        //https://diakov.net/uploads/posts/2016-10/1476432520_realtek-logo1.png
        //http://poplm-lone.narod.ru/loveplane-57.jpg
    }



    public static Builder getBuilder() {
        return builder;
    }
    public static void setBuilder(Builder builder) {
        Work.builder = builder;
    }
    public static void setImage(URL url) {
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setImage(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveImage(File file, String formatName) {
        try {
            ImageIO.write(image, formatName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage getImage() {
        return image;
    }
    public static void setImage(BufferedImage image) {
        Work.image = image;
    }


}
