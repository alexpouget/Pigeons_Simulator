package gui;

import Entity.Food;
import Entity.FreshFood;
import Entity.RottenFood;

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
public class Ground extends JPanel  {
    List<Food> foodList;

    public Ground() {
        foodList = new ArrayList<>();
        setBackground(Color.DARK_GRAY);
        addMouseListener(new AddFood());

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
    }

    private class AddFood implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
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
