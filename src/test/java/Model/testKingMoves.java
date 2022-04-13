package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Felix Kaasa
 */
public class testKingMoves {
    Model model;

    @BeforeEach
    public void setup(){
        model = new Model(true);
        model.installClock(new Clock(true));
    }

    @Test
    public void kingCaptureUndefendedPiece(){
        model.doMove(new Move(12,28));
        model.doMove(new Move(50,42));
        model.doMove(new Move(4,12));
        model.doMove(new Move(59,32));
        model.doMove(new Move(12,19));
        model.doMove(new Move(55,47));
        model.doMove(new Move(19,26));
        model.doMove(new Move(32,25));
        assertTrue(model.getLegalMoves().contains(new Move(26,25)));
    }

    @Test
    public void kingShouldNotMoveFromCheckToCheck(){
        model.doMove(new Move(11,19));
        model.doMove(new Move(52,44));
        model.doMove(new Move(4,11));
        model.doMove(new Move(59,52));
        model.doMove(new Move(11,18));
        model.doMove(new Move(52,43));
        model.doMove(new Move(18,26));
        model.doMove(new Move(43,35));
        assertFalse(model.getLegalMoves().contains(new Move(26,17)));
    }
}
