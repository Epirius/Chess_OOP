package Controller;

import Main.Constants;
import Model.Model;
import Model.Team;
import Model.Move;
import Model.Type;

import javax.swing.*;
import java.util.List;
import java.util.Random;

/**
 * @author Felix Kaasa
 */
public class AI{
    private final Controller controller;
    public boolean enabled;
    private final Team AI_TEAM;
    private Model model;
    private final Random random = new Random();

    public AI(Controller controller){
        this.controller = controller;
        if (Constants.AI_TEAM == null){
            AI_TEAM = null;
            enabled = false;
        } else {
            this.AI_TEAM = Constants.AI_TEAM;
            enabled = true;
        }
    }

    public void installModel(Model model){ this.model = model;}

    public Team getTeam(){ return this.AI_TEAM;}


    public void createMove() {
        if (!enabled){return;}
        if (controller.model.getTeam() != AI_TEAM){
            throw new IllegalStateException("The AI is trying to create a move but it is not the AI's turn!");
        }

        List<Move> moves = model.getLegalMoves();
        Move aiMove = moves.get(random.nextInt(moves.size()));
        model.doMove(aiMove);
        controller.checkPawnUpgrade(aiMove);
        controller.checkIfGameOver();

    }

    public boolean isAiTurn(){
        return (enabled && model.getTeam() == AI_TEAM);
    }

    public void upgradePawn() {
        model.upgradePawn(Type.QUEEN);
        controller.setGameState(GameState.ACTIVE_GAME);
    }
}
