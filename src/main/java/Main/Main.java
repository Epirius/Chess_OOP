package Main;

import Controller.Controller;
import Model.*;
import View.View;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * @author Felix Kaasa
 */
public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        //JComponent view = new View(controller);
        View view = new View();
        Controller controller = new Controller(model, view);
        //controller.setModelAndView(model, view);


        JFrame frame = new JFrame("Chess - Felix");
        frame.setContentPane(view);
        frame.getContentPane().setPreferredSize(new Dimension(Constants.displayWidth, Constants.displayHeight));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        BufferedImage icon = Constants.knightB;
        frame.setIconImage(icon);
    }
}
