package Entity;

import gui.Ground;
import pattern.Observable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by alex on 07/10/2016.
 */
public class Pigeon extends Thread implements Observable {

    private Ground ground;
    private int x;
    private int y;
    private Image img;
    private String pigeonName;
    private Food cible;
    private List<Food> foodList;
    private final Object lock = new Object();

    public Pigeon(int x, int y, String pigeonName, Ground ground) {
        this.x = x;
        this.y = y;

        try {
            this.img = ImageIO.read(new File("./ressource/sprite/pigeons.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        this.foodList = foodList;
        cible = findFreshFood(this.foodList.size()-1);
    }

    /**
     * Move function
     *
     * @throws InterruptedException
     */
    public void move() throws InterruptedException {
        while ((abs(getX() - cible.getX()) >= 10) || (abs(getY() - cible.getY()) >= 10)){
            if (abs(getX() - cible.getX()) < 10) {

            } else if (getX() < cible.getX()) {
                setX(getX() + 5);
            } else {
                //left
                setX(getX() - 5);
            }
            if (abs(getY() - cible.getY()) < 10) {

            } else if (getY() < cible.getY()) {
                //down
                setY(getY() + 5);
            } else {
                //up
                setY(getY() - 5);
            }

            ground.repaint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eat();
    }

    /**
     * Move randomly
     *
     * @param min
     * @param max
     */
    //moveRandom function
    public void moveRandom(int min, int max) {

        int newPositionX = getNewPostionInsideTheGround(min, max, getX(), getGround().getWidth());
        int newPositionY = getNewPostionInsideTheGround(min, max, getY(), getGround().getHeight());

        setX(newPositionX);
        setY(newPositionY);
    }

    /**
     * Return a new position (always inside the window)
     *
     * @param min
     * @param max
     * @param reference
     * @param size
     * @return
     */
    private int getNewPostionInsideTheGround(int min, int max, int reference, int size) {

        int newPosition = reference + min + (int)(Math.random() * ((max - min) + 1));

        while (newPosition < 10 || newPosition > size - 10) {
            newPosition = reference + min + (int)(Math.random() * ((max - min) + 1));
        }

        return newPosition;
    }


    /**
     * Find some food
     *
     * @param index
     * @return
     */
    public Food findFreshFood(int index) {
        synchronized (lock) {

            if (!this.foodList.isEmpty()) {
                cible = this.foodList.get(index);
            } else {
                cible = null;
            }

            return cible;
        }
    }

    /**
     * Eat function
     */
    public void eat() {

        if (cible instanceof FreshFood) {
            this.foodList.remove(cible);
        }

        synchronized (lock) {
            cible = null;
        }

        for (Food f : foodList) {
            if (f instanceof FreshFood) {
                findFreshFood(foodList.indexOf(f));
            }
        }

        getGround().initTimer();
        ground.repaint();
    }

    @Override
    public void run() {
       // super.run();
        while(x!=0) {
            while (null == cible) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
