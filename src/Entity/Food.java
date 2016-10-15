package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by alex on 05/10/2016.
 */
public class Food {
    private int x;
    private int y;
    private Image img;


    public Food() {
    }

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Food(int x, int y,String img) {
        this.x = x;
        this.y = y;
        try {
            this.img = ImageIO.read(new File(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
