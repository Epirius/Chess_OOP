package Controller;

import Model.Model;
import View.View;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

/**
 * @author Felix Kaasa
 */
public class testController {
    Model model;
    Controller controller;
    JComponent view;

    @Before
    public void init(){
        controller = new Controller();
        model = new Model();
        view = new View(controller);
        controller.setModelAndView(model, view);
    }

    @Test
    public void testHandleClicks(){
        controller.handleClicks(8);
        controller.handleClicks(24);

    }
}
