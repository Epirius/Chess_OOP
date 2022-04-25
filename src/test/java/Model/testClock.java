package Model;

import Controller.Controller;
import View.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Felix Kaasa
 */
public class testClock {
    Clock clock;

    @BeforeEach
    public void setup(){
        View view = new View();
        this.clock = new Clock(view, new Controller(view), 3, 2);
    }

    @Test
    public void testClockSwitchPlayer(){
        Assertions.assertEquals(Team.WHITE, clock.getCurrentPlayer());
        clock.nextPlayer();
        Assertions.assertEquals(Team.BLACK, clock.getCurrentPlayer());
        clock.nextPlayer();
        Assertions.assertEquals(Team.WHITE, clock.getCurrentPlayer());
    }

    @Test
    public void testGetCurrentPlayer_AND_testNextPlayer(){
        Assertions.assertEquals(Team.WHITE, clock.getCurrentPlayer());
        clock.nextPlayer();
        Assertions.assertEquals(Team.BLACK, clock.getCurrentPlayer());
    }

    @Test
    public void testSetAndGetTime(){
        clock.setTime(Team.WHITE,10);
        clock.setTime(Team.BLACK,20);
        Assertions.assertEquals(10, clock.getTime(Team.WHITE));
        Assertions.assertEquals(20, clock.getTime(Team.BLACK));
    }
}
