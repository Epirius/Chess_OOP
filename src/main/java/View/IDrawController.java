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
     * a method to set the current gameState
     * @param gameState the state to set the gameState to
     */
    void setGameState(GameState gameState);

    /**
     * a method to install a model
     * @param model model to install
     */
    void installModel(Model model);

    /**
     * a method to install an ai
     * @param ai ai to be installed
     */
    void installAI(AI ai);
}
