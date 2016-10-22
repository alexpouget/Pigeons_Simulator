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

    private List<Food> foodList;
    private List<Observable> pigeonList;
    private Pigeon jeannot;
    private Image img = Toolkit.getDefaultToolkit().getImage("./ressource/sprite/pigeons.png");

    public Ground() {
        pigeonList = new ArrayList<>();
        foodList = new ArrayList<>();
        setBackground(Color.DARK_GRAY);
        addMouseListener(new AddFood());



    }

    public void startPigeon(){
        jeannot = new Pigeon(50,50,"jeannot",this);
        addObservableList(jeannot);
        jeannot.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!foodList.isEmpty()){
            for (Food f:foodList
                    ) {
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

}
