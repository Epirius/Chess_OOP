package Controller;

import Model.*;
import View.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Felix Kaasa
 */
public class testAI {
    AI ai;
    AI aiBlack;
    Model model;
    Controller controller;

    @BeforeEach
    public void setup(){
        Controller controller = new Controller(new View());
        AI aiWhite = new AI(controller, Team.WHITE);
        aiBlack = new AI(new Controller(new View()), Team.BLACK);
        aiWhite.testingMode();
        aiBlack.testingMode();
        Model model = new Model(true);
        controller.installModel(model);
        aiWhite.installModel(model);
        model.installClock(new Clock(true));
        controller.setGameState(GameState.ACTIVE_GAME);

        this.ai = aiWhite;
        this.model = model;
        this.controller = controller;
    }

    @Test
    public void testGetTeam(){
        Assertions.assertEquals(Team.WHITE, ai.getTeam());
        Assertions.assertEquals(Team.BLACK, aiBlack.getTeam());
    }

    @Test
    public void testCreateMove(){
        model.doMove(new Move(13,29));
        model.doMove(new Move(52,44));
        model.doMove(new Move(8,16));
        model.doMove(new Move(59,38));
        ai.createMove();
        Assertions.assertEquals(new Move(29,38), model.getLastMove());

    }

    @Test
    public void testIsAiTurn(){
        Assertions.assertTrue(ai.isAiTurn());
        model.doMove(new Move(8,14));
        Assertions.assertFalse(ai.isAiTurn());
    }

    @Test
    public void testIsEnabled(){
        Assertions.assertTrue(ai.isEnabled());
    }
}
