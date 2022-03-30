package Model;


import org.junit.Test;
import static org.junit.Assert.*;

public class testGameOver {

    @Test
    public void testDraw(){
        Model model = new Model();

        // moves found here https://www.chess.com/forum/view/fun-with-chess/fastest-stalemate
        model.doMove(new Move(10,26));
        model.doMove(new Move(55,39));
        model.doMove(new Move(15,31));
        model.doMove(new Move(48,32));
        model.doMove(new Move(3,24));
        model.doMove(new Move(56,40));
        model.doMove(new Move(24,32));
        model.doMove(new Move(40,47));
        model.doMove(new Move(32,50));
        model.doMove(new Move(53,45));
        model.doMove(new Move(50,51));
        model.doMove(new Move(60,53));
        model.doMove(new Move(51,49));
        model.doMove(new Move(59,19));
        model.doMove(new Move(49,57));
        model.doMove(new Move(19,55));
        model.doMove(new Move(57,58));
        model.doMove(new Move(53,46));
        model.doMove(new Move(58,44));

        assertEquals(0, model.getLegalMoves().size());
    }

    @Test
    public void testCheckMate(){
        Model model = new Model();

        model.doMove(new Move(12,28));
        model.doMove(new Move(52,36));
        model.doMove(new Move(5,26));
        model.doMove(new Move(57,42));
        model.doMove(new Move(3,39));
        model.doMove(new Move(54,46));
        model.doMove(new Move(39,21));
        model.doMove(new Move(61,54));
        model.doMove(new Move(21,53));

        assertEquals(0, model.getLegalMoves().size());
    }
}
