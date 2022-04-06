package Model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Felix Kaasa
 */
public class testNumberOfMoves {
    Model model;

    //I DID NOT WRITE THIS TEST, JUST COPYING FROM THE INTERNET //TODO DELETE THIS TEST.
    @Test
    public void testMoveNumbers(){
        this.model = new Model();
        this.model.installClock(new Clock(true));
        // the expected values comes form here: https://www.chessprogramming.org/Perft_Results
        assertEquals(197281, calculateNumberOfMoves(4));


        System.out.println(calculateNumberOfMoves(5));
    }

    public int calculateNumberOfMoves(int depth){
        // THIS CODE IS COPIED FROM https://www.chessprogramming.org/Perft
        if (depth == 0){
            return 1;
        }
        List<Move> moves = model.getLegalMoves();

        int nodes = 0;
        for (Move move : moves){
            model.doMove(move);
            nodes += calculateNumberOfMoves(depth -1);
            model.undoMove();
        }
        return nodes;
    }
}
