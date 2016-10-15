package Entity;

import gui.Ground;
import pattern.Observable;

import javax.imageio.ImageIO;
import javax.swing.*;
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


    public Pigeon() {
        x = 0;
        y = 0;
        try {
            this.img = ImageIO.read(new File("./ressource/sprite/pigeons.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Pigeon(int x, int y, String pigeonName) {
        this.x = x;
        this.y = y;
        try {
            this.img = ImageIO.read(new File("./ressource/sprite/pigeons.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pigeonName = pigeonName;


    }

    public Pigeon(int x, int y, String pigeonName,Ground ground) {
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
        //list des plat a dispo go eat
        System.out.println("notified");
        this.foodList = foodList;
        cible = findCloser();
        //start();
        //foodList.remove(0);
        //ground.newFood(foodList);

    }

    //move function
    public void move() {
        while (cible != null && ((abs(getX() - cible.getX()) >= 10) && (abs(getY() - cible.getY()) >= 10))){
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
            ground.paint(ground.getGraphics());
            //ground.repaint();
        }
        eat();
    }

    public Food findCloser() {
        synchronized (lock) {
            if (cible != null) {
                if (foodList.contains(cible)) {
                    return cible;
                }
            }

            for (Food f : foodList
                    ) {
                if (cible == null) {
                    cible = f;
                    continue;
                }
                if (abs(getX() - f.getX()) + (abs(getY() - f.getY())) <
                        abs(getX() - cible.getX()) + (abs(getY() - cible.getY()))) {
                    cible = f;
                }
            }
            return cible;
        }
    }

    public void eat(){
        this.foodList.remove(0);
        synchronized (lock) {
            cible = null;
        }
        System.out.println("manger !!");
        findCloser();
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
            System.out.println("move");
            move();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
