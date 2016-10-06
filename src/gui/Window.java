package gui;

import utils.Constant;

import javax.swing.*;
import java.awt.*;


/**
 * Created by alex on 05/10/2016.
 */
public class Window extends JFrame {

    private JPanel ground;

    public Window(){
        setTitle("Pigeon Simulator");
        setSize(Constant.WIDTH,Constant.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Container container = getContentPane();
        ground = new Ground();
        container.add(ground);

        setVisible(true);
    }


}
