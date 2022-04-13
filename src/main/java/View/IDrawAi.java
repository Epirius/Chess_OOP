package View;

import Model.Team;

/**
 * @author Felix Kaasa
 */
public interface IDrawAi {

    void createMove();

    boolean isAiTurn();

    void upgradePawn();

    Team getTeam();

    boolean isEnabled();
}
