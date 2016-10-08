package Entity;

import gui.Ground;
import pattern.Observable;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by alex on 07/10/2016.
 */
public class Pigeon implements Observable {

    private Ground ground;
    private int x;
    private int y;
    private Image img;
    private String pigeonName;
    private Food cible;

    public Pigeon() {
        this.img = Toolkit.getDefaultToolkit().getImage("./ressource/sprite/pigeons.png");
    }

    public Pigeon(int x, int y, String pigeonName,Ground ground) {
        this.x = x;
        this.y = y;
        this.img = Toolkit.getDefaultToolkit().getImage("./ressource/sprite/pigeons.png");
        this.pigeonName = pigeonName;
        this.ground = ground;
    }

    public String getPigeonName() {
        return pigeonName;
    }

    public void setPigeonName(String pigeonName) {
        this.pigeonName = pigeonName;
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

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Food getCible() {
        return cible;
    }

    public void setCible(Food cible) {
        this.cible = cible;
    }

    @Override
    public void newFood(List<Food> foodList) {
        //list des plat a dispo go eat
        cible = findCloser(foodList);
        //start();
        //foodList.remove(0);
        //ground.newFood(foodList);

    }

    //move function
    public void move() {
        while (cible != null || (abs(getX() - cible.getX()) < 10) && (abs(getY() - cible.getY()) < 10)){
            if (abs(getX() - cible.getX()) < 10) {

            } else if (getX() < cible.getX()) {
                setX(cible.getX() + 5);
            } else {
                //left
                setX(cible.getX() - 5);
            }
            if (abs(getY() - cible.getY()) < 10) {

            } else if (getY() < cible.getY()) {
                //down
                setY(cible.getY() + 5);
            } else {
                //up
                setY(cible.getY() - 5);
            }
            ground.repaint();
        }
    }

    public Food findCloser(List<Food> foodList) {
        if(cible != null){
            if(foodList.contains(cible)){
                return cible;
            }
        }

        for (Food f:foodList
             ) {
            if(cible==null){
                cible = null;
                continue;
            }
            if(abs(getX() - f.getX()) + (abs(getY() - f.getY())) <
                    abs(getX() - cible.getX()) + (abs(getY() - cible.getY()))){
                cible = f;
            }
        }
        return foodList.get(0);
    }

    public void eat(){

        //ground.repaint();
    }
}
