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
    View view;

    @Before
    public void init(){
        model = new Model();
        view = new View();
        controller = new Controller(view);
        controller.installModel(model);
    }
}
