package Main;

import Model.*;
import View.View;
import javax.swing.*;
import java.awt.*;


/**
 * @author Felix Kaasa
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        JComponent view = new View();

        JFrame frame = new JFrame("Chess - Felix");
        frame.setContentPane(view);
        frame.getContentPane().setPreferredSize(new Dimension(Constants.displayWidth, Constants.displayHeight));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setVisible(true);


    }
}
