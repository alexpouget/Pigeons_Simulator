package Entity;

import pattern.Observable;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by alex on 07/10/2016.
 */
public class Pigeon implements Observable{

    private int x;
    private int y;
    private Image img;
    private String name;

    public Pigeon() {
        this.img = Toolkit.getDefaultToolkit().getImage("./ressource/sprite/pigeons.png");
    }

    public Pigeon(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.img = Toolkit.getDefaultToolkit().getImage("./ressource/sprite/pigeons.png");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void newFood(List<Food> foodList) {
        //list des plat a dispo go eat
        System.out.println("recu nouvelle graille dispo");
    }
}
