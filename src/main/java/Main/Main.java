package Main;

import Model.*;
import View.View;
import javax.swing.*;


/**
 * @author Felix Kaasa
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        JComponent view = new View();

        JFrame frame = new JFrame("Chess - Felix");
        frame.setContentPane(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.displayWidth, Constants.displayHeight);

        frame.setVisible(true);


    }
}
