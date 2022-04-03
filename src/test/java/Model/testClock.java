package Model;

import Controller.Controller;
import View.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Felix Kaasa
 */
public class testClock {

    @Test
    public void testClockSwitchPlayer(){
        View view = new View();
        Clock clock = new Clock(view, new Controller(view));
        Assertions.assertEquals(Team.WHITE, clock.getCurrentPlayer());
        clock.nextPlayer();
        Assertions.assertEquals(Team.BLACK, clock.getCurrentPlayer());
        clock.nextPlayer();
        Assertions.assertEquals(Team.WHITE, clock.getCurrentPlayer());

    }
}
