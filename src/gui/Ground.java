package gui;

import Entity.Food;
import Entity.FreshFood;
import Entity.Pigeon;
import Entity.RottenFood;
import pattern.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 05/10/2016.
 */
public class Ground extends JPanel implements Observable   {
    List<Food> foodList;
    private List<Pigeon> pigeonList;

    public Ground() {
        pigeonList = new ArrayList<>();
        foodList = new ArrayList<>();
        setBackground(Color.DARK_GRAY);
        addMouseListener(new AddFood());
        Pigeon jeannot = new Pigeon(50,50,"jeannot",this);
        pigeonList.add(jeannot);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!foodList.isEmpty()){
            for (Food f:foodList
                    ) {
                g.drawImage(f.getImg(), f.getX(),f.getY(),32,32,null);
            }

        }
        if(!pigeonList.isEmpty()){
            for (Pigeon p:pigeonList
                    ) {
                g.drawImage(p.getImg(), p.getX(),p.getY(),48,48,null);
            }

        }
    }

    @Override
    public void newFood(List<Food> foodList) {
        this.foodList = foodList;

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
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                ((Pigeon)p).move();
                                ((Pigeon)p).setCible(null);
                            }
                        }.start();
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


}
