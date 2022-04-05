package Controller;

import Main.Constants;
import Model.Model;
import Model.Team;
import Model.Move;
import Model.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

/**
 * @author Felix Kaasa
 */
public class AI implements ActionListener{
    private Controller controller;
    public boolean enabled;
    private Team AI_TEAM;
    private Model model;
    private Random random = new Random();
    private Timer timer;
    private Move aiMove;

    public AI(Controller controller){
        this.controller = controller;
        if (Constants.AI_TEAM == null){
            AI_TEAM = null;
            enabled = false;
        } else {
            this.AI_TEAM = Constants.AI_TEAM;
            enabled = true;
        }

        timer = new Timer(200, this);
    }

    public void installModel(Model model){ this.model = model;}

    public Team getTeam(){ return this.AI_TEAM;}


    public void createMove() {
        if (!enabled){return;}
        if (controller.model.getTeam() != AI_TEAM){
            throw new IllegalStateException("The AI is trying to create a move but it is not the AI's turn!");
        }

        List<Move> moves = model.getLegalMoves();
        aiMove = moves.get(random.nextInt(moves.size()));
        timer.restart();

    }

    public boolean isAiTurn(){
        return (enabled && model.getTeam() == AI_TEAM);
    }

    public void upgradePawn() {
        model.upgradePawn(Type.QUEEN);
        controller.setGameState(GameState.ACTIVE_GAME);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // EVENT
        model.doMove(aiMove);
        controller.checkPawnUpgrade(aiMove);
        controller.checkIfGameOver();

        // UPDATE TIMER
        timer.stop();
        timer.setDelay(random.nextInt(2000-50) + 50);
    }
}
