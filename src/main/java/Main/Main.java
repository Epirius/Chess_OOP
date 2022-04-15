package Main;

import Controller.Controller;
import Model.Clock;
import Model.Model;
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
        View view = new View();
        Controller controller = new Controller(view);
        Clock clock = new Clock(view, controller);
        view.installController(controller);
        view.installClock(clock);
        model.installClock(clock);



        JFrame frame = new JFrame("Chess - Felix");
        frame.setContentPane(view);
        frame.getContentPane().setPreferredSize(new Dimension(Constants.displayWidth, Constants.displayHeight));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false); //TODO MAYBE ALLOW RESIZE
        frame.setVisible(true);

        BufferedImage icon = Constants.knightB;
        frame.setIconImage(icon);
    }
}
