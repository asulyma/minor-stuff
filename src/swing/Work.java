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

    }



    public static Builder getBuilder() {
        return builder;
    }
    public static void setBuilder(Builder builder) {
        Work.builder = builder;
    }
    public static void setImage(URL url) {
        try {
            ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setImage(File file) {
        try {
            ImageIO.read(file);
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
