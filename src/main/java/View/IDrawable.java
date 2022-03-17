package View;

import Controller.GameState;

import java.util.List;

public interface IDrawable {

    /**
     * get all the pieces on the board
     * @return a list of ViewPiece containing all pieces on the board.
     */
    List<ViewPiece> getPiecesOnTheBoard();

    /**
     * get squares that are legal after the user has clicked on a square
     * @return int[] of legal squares
     */
    List<Integer> getLegalSquares();

    /**
     * used to get the current game state (for example main menu, game over etc..)
     * @return the state of the game
     */
    GameState getGameState();
}
