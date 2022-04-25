package Controller;

import Model.Model;
import View.View;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Felix Kaasa
 */
public class testController {
    Model model;
    Controller controller;
    View view;

    @BeforeEach
    public void init(){
        model = new Model();
        view = new View();
        controller = new Controller(view);
        controller.installModel(model);
    }

    @Test
    public void testSetAndGetGameState(){
        init();
        controller.setGameState(GameState.ACTIVE_GAME);
        Assertions.assertEquals(GameState.ACTIVE_GAME, controller.getGameState());

        controller.setGameState(GameState.CREATE_GAME);
        Assertions.assertEquals(GameState.CREATE_GAME, controller.getGameState());
    }

    @Test
    public void test_HandleClick_And_GetLegalSquares(){
        init();
        controller.testHandleClicks(8);
        Assertions.assertEquals(2, controller.getLegalSquares().size());

        controller.testHandleClicks(8);
        Assertions.assertEquals(0, controller.getLegalSquares().size());
    }
}
