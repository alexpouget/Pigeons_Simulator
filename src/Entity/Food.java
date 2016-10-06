package Entity;

import java.awt.*;

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

    public Food(int x, int y,Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
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
