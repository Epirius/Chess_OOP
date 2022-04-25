package View;

import Controller.Controller;
import Controller.GameState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Felix Kaasa
 */
public class testButton {
    private boolean clicked = false;

    // Instructions for manual testing of buttons

    /* Testing buttons
        -make sure the buttons  show up, and have the right color when drawButton is called.
        -make sure the buttons position change when updateButtonPosition is called
        -make sure "Button is clicked" gets printed in the console when testMouseOverButton() is run
        -
     */

    @Test
    public void testMouseOverButton(){
        View view = new View();
        Controller controller = new Controller(view);
        view.installController(controller);
        controller.setGameState(GameState.ACTIVE_GAME);

        TextButton button = new TextButton(10, 20, 50, 50, "Test", view, () -> System.out.println("Button is clicked"));
        Assertions.assertTrue(button.mouseIsOverButton(30,30));
        Assertions.assertFalse(button.mouseIsOverButton(300,300));

        button.action.executeAction();
    }
}
