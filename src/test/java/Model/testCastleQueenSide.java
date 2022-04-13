package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Felix Kaasa
 */
public class testCastleQueenSide {
    Model model;

    @BeforeEach
    public void setup(){
        model = new Model(true);
        model.installClock(new Clock(true));

        // setting up the board to be able to castle
        model.doMove(new Move(11,19));
        model.doMove(new Move(51,43));
        model.doMove(new Move(2,29));
        model.doMove(new Move(58,37));
        model.doMove(new Move(3,11));
        model.doMove(new Move(59,51));
        model.doMove(new Move(1,16));
        model.doMove(new Move(57,40));
        // this is what the board looks like: https://lichess.org/editor/r3kbnr/pppqpppp/n2p4/5b2/5B2/N2P4/PPPQPPPP/R3KBNR_w_KQkq_-_6_5
    }

    @Test
    public void testCastleQueenSide(){
        // checking that both teams can castle
        assertTrue(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(4,2, 0));
        assertTrue(model.getLegalMoves().contains(new Move(60,58, 56)));

    }

    @Test
    public void testCantCastleIfRookMoved(){
        // checking that you can't castle if the rook moved
        model.doMove(new Move(0,1));
        model.doMove(new Move(56,57));
        model.doMove(new Move(1,0));
        model.doMove(new Move(57,56));
        assertFalse(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(8,16));
        assertFalse(model.getLegalMoves().contains(new Move(60,58, 56)));
    }

    @Test
    public void testCantCastleIfKingMoved(){
        // checking that you can't castle if the king moved
        model.doMove(new Move(4,3));
        model.doMove(new Move(60,59));
        model.doMove(new Move(3,4));
        model.doMove(new Move(59,60));
        assertFalse(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(8,16));
        assertFalse(model.getLegalMoves().contains(new Move(60,58, 56)));
    }

    @Test
    public void testCantCastleIfSquareBetweenIsAttacked(){
        model.doMove(new Move(11,18));
        model.doMove(new Move(51,33));
        model.doMove(new Move(18,50));
        assertFalse(model.getLegalMoves().contains(new Move(60,58, 56)));
        model.doMove(new Move(33,9));
        assertFalse(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(50,49));
        model.doMove(new Move(9,17));
        assertTrue(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(49,17));
        assertTrue(model.getLegalMoves().contains(new Move(60,58, 56)));
    }

    @Test
    public void testCantCastleIfKingIsAttacked(){
        model.doMove(new Move(11,32));
        model.doMove(new Move(51,24));
        model.doMove(new Move(32,33));
        assertFalse(model.getLegalMoves().contains(new Move(60,58, 56)));
        model.doMove(new Move(50,42));
        model.doMove(new Move(33,34));
        assertTrue(model.getLegalMoves().contains(new Move(60,58, 56)));
        model.doMove(new Move(24,25));
        assertFalse(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(10,18));
        model.doMove(new Move(40,34));
        assertTrue(model.getLegalMoves().contains(new Move(4,2, 0)));
    }

    @Test
    public void testCanCastleIfRookIsAttacked(){
        model.doMove(new Move(14,22));
        model.doMove(new Move(54,46));
        model.doMove(new Move(5,14));
        model.doMove(new Move(61,54));
        model.doMove(new Move(9,17));
        model.doMove(new Move(49,41));
        assertTrue(model.getLegalMoves().contains(new Move(4,2, 0)));
        model.doMove(new Move(17,25));
        assertTrue(model.getLegalMoves().contains(new Move(60,58, 56)));
    }
}
