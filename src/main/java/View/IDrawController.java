package View;

import Controller.AI;
import Controller.GameState;
import Model.Model;

import java.util.List;

public interface IDrawController {

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

    /**
     *
     * @param gameState
     */
    //TODO WRITE JDOCS
    void setGameState(GameState gameState);

    void installModel(Model model);

    void installAI(AI ai);
}
