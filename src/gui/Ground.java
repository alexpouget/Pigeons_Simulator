package gui;

import Entity.Food;
import Entity.FreshFood;
import Entity.Pigeon;
import Entity.RottenFood;
import pattern.Observable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 05/10/2016.
 */
public class Ground extends JPanel implements Observable   {

    private List<Food> foodList;
    private List<Observable> pigeonList;
    private Pigeon jeannot;
    private float countTimer = 15;

    public Ground() {
        pigeonList = new ArrayList<>();
        foodList = new ArrayList<>();
        setBackground(Color.DARK_GRAY);
        addMouseListener(new AddFood());

        //init Countdown
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countTimer -= 0.5;

                if (countTimer < 10 && countTimer > 0) {
                    jeannot.moveRandom(-20, 20);
                    repaint();
                } else if (countTimer < 0) {
                    try {
                        jeannot.setImg(ImageIO.read(new File("./ressource/sprite/pigeons-sleep.png")));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    repaint();
                }
            }
        };

        Timer timer = new Timer(500, actionListener);
        timer.start();
    }

    public void startPigeon(){
        jeannot = new Pigeon(100, 100, "jeannot", this);
        addObservableList(jeannot);
        jeannot.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!foodList.isEmpty()){
            for (Food f:foodList) {
                g.drawImage(f.getImg(), f.getX(),f.getY(),32,32,null);
            }
        }

        g.drawImage(jeannot.getImg(), jeannot.getX(),jeannot.getY(),48,48,null);
        g.dispose();
    }

    @Override
    public void newFood(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Observable> getPigeonList() {
        return pigeonList;
    }

    public void addObservableList(Observable observable) {
        pigeonList.add(observable);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void addFood(Food food) {
        this.foodList.add(food);
    }

    public void removeFood(int index) {
        this.foodList.remove(index);
    }

    private class AddFood implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Random rnd = new Random();
                    int rand = rnd.nextInt();
                    initTimer();
                    switch (rand%5){
                        case 0:
                            foodList.add(new RottenFood(e.getX(),e.getY()));
                            break;
                        default:
                            foodList.add(new FreshFood(e.getX(),e.getY()));
                    }
                    System.out.println("new Food : "+e.getX()+ " : "+e.getY());
                    repaint();
                    for (Observable p:pigeonList
                            ) {
                        p.newFood(foodList);
                    }
                }
            }.start();

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void initTimer() {
        countTimer = 15;
        try {
            jeannot.setImg(ImageIO.read(new File("./ressource/sprite/pigeons.png")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
