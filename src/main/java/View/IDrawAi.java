package View;

import Model.Team;

/**
 * @author Felix Kaasa
 */
public interface IDrawAi {

    /**
     * a method that creates a move and then sends that move to the model.
     */
    void createMove();

    /**
     * a method to check if it is the ai's turn
     * @return true if ai's turn, false if player's turn.
     */
    boolean isAiTurn();

    /**
     * a method that is called if the ai needs to choose which piece to upgrade the pawn to
     */
    void upgradePawn();

    /**
     * a method to get the ai's team
     * @return ai's team
     */
    Team getTeam();

    /**
     * a method to check if the ai is enabled
     * @return true if ai is enabled, else false
     */
    boolean isEnabled();
}
