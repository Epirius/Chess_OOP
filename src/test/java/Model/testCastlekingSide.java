package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Felix Kaasa
 */
public class testCastlekingSide {
    Model model;

    @BeforeEach
    public void setup(){
        model = new Model();

        // setting up the board to be able to castle
        model.doMove(new Move(12,28));
        model.doMove(new Move(52,36));
        model.doMove(new Move(5,12));
        model.doMove(new Move(61,52));
        model.doMove(new Move(6,21));
        model.doMove(new Move(62,45));
        // this is what the board looks like: https://lichess.org/editor/rnbqk2r/ppppbppp/5n2/4p3/4P3/5N2/PPPPBPPP/RNBQK2R_w_KQkq_-_4_4
    }

    @Test
    public void testCastleKingSide(){
        // checking that both teams can castle
        assertTrue(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(4,6, 7));
        assertTrue(model.getLegalMoves().contains(new Move(60,62, 63)));
        }

    @Test
    public void testCantCastleIfRookMoved(){
        // checking that you can't castle if the rook moved
        model.doMove(new Move(7,6));
        model.doMove(new Move(63,62));
        model.doMove(new Move(6,7));
        model.doMove(new Move(62,63));
        assertFalse(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(8,16));
        assertFalse(model.getLegalMoves().contains(new Move(60,62, 63)));
    }

    @Test
    public void testCantCastleIfKingMoved(){
        // checking that you can't castle if the king moved
        model.doMove(new Move(4,5));
        model.doMove(new Move(60,61));
        model.doMove(new Move(5,4));
        model.doMove(new Move(61,60));
        assertFalse(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(9,16));
        assertFalse(model.getLegalMoves().contains(new Move(60,62, 63)));
    }

    @Test
    public void testCantCastleIfSquareBetweenIsAttacked(){
        // checking that you can't castle if an enemy attacks a square between.
        model.doMove(new Move(21,38));
        model.doMove(new Move(45,30));
        model.doMove(new Move(38,44));
        model.doMove(new Move(30,20));

        assertFalse(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(8,16));
        assertFalse(model.getLegalMoves().contains(new Move(60,62, 63)));
        model.doMove(new Move(20,35));
        assertTrue(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(44,27));
        assertTrue(model.getLegalMoves().contains(new Move(60,62, 63)));
    }

    @Test
    public void testCantCastleIfKingIsAttacked(){
        model.doMove(new Move(11,27));
        model.doMove(new Move(51,35));
        model.doMove(new Move(12,33));
        assertFalse(model.getLegalMoves().contains(new Move(60,62, 63)));
        model.doMove(new Move(50,42));
        model.doMove(new Move(8,16));
        assertTrue(model.getLegalMoves().contains(new Move(60,62, 63)));
        model.doMove(new Move(52,25));
        assertFalse(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(16,25));
        model.doMove(new Move(48,40));
        assertTrue(model.getLegalMoves().contains(new Move(4,6, 7)));


    }

    @Test
    public void testCanCastleIfRookIsAttacked(){
        model.doMove(new Move(21,36));
        model.doMove(new Move(45,28));
        model.doMove(new Move(9,17));
        model.doMove(new Move(49,41));
        model.doMove(new Move(2,9));
        model.doMove(new Move(58,49));
        model.doMove(new Move(36,26));
        model.doMove(new Move(28,34));
        model.doMove(new Move(14,22));
        model.doMove(new Move(54,46));
        assertTrue(model.getLegalMoves().contains(new Move(4,6, 7)));
        model.doMove(new Move(4,6, 7));
        assertTrue(model.getLegalMoves().contains(new Move(60,62, 63)));

    }
}
