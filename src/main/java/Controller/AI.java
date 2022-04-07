package Controller;

import Main.Constants;
import Model.Model;
import Model.Board;
import Model.Pieces.King;
import Model.Pieces.Piece;
import Model.Team;
import Model.Move;
import Model.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Felix Kaasa
 */
public class AI{
    private Controller controller;
    public boolean enabled;
    private Team AI_TEAM;
    private Model model;
    private Random random = new Random();
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
    }

    public void installModel(Model model){ this.model = model;}

    public Team getTeam(){ return this.AI_TEAM;}


    public void createMove() {
        if (!enabled){return;}
        if (controller.model.getTeam() != AI_TEAM){
            throw new IllegalStateException("The AI is trying to create a move but it is not the AI's turn!");
        }

        List<Move> moves = model.getLegalMoves();
        //aiMove = moves.get(random.nextInt(moves.size()));
        aiMove = getBestMove(moves, model);


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

    public  Move getBestMove(List<Move> possibleMoves, Model model){
        int depth = 3;
        Integer bestMoveIndex = null;
        List<Move> possibleBestMoves = new ArrayList<>();
        for (Move move : possibleMoves){

            model.doMove(move);
            int value = minimax(depth, model, Integer.MIN_VALUE, Integer.MAX_VALUE, AI_TEAM != Team.WHITE);
            model.undoMove();

            if (bestMoveIndex == null || (AI_TEAM == Team.BLACK && value < bestMoveIndex || AI_TEAM == Team.WHITE && value > bestMoveIndex)){
                bestMoveIndex = value;
                possibleBestMoves.clear();
                possibleBestMoves.add(move);
            }
            if (value == bestMoveIndex){
                possibleBestMoves.add(move);
            }
        }
        if (possibleBestMoves.size() == possibleMoves.size()){ System.out.println("Random move");}
        return possibleBestMoves.get(random.nextInt(possibleBestMoves.size()));
    }

    //TODO for minimax alog: the algo has to know about pawn upgrading, and check each of the 4 upgrade paths as another branch.
    //TODO this algo should also take positioning into account.
    /**
     * method to find the best move
     * @param depth how much further should the algorithm search
     * @param model model
     * @param alpha alpha
     * @param beta bata
     * @param maximizingPlayer boolean: true for white, false for black.
     */
    public int minimax(int depth, Model model, int alpha, int beta, boolean maximizingPlayer){
        // I am implementing the pseudocode from: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
        // and getting inspiration from here: https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

        if (depth == 0 || isNodeTerminal(model)){
            return evaluatePosition(model);
        }

        if (maximizingPlayer){
            int value = Integer.MIN_VALUE;
            for (Move move : model.getLegalMoves()){
                model.doMove(move);
                value = Math.max(value, minimax(depth - 1, model, alpha, beta, false));
                model.undoMove();
                if (value >= beta){ break;}
                alpha = Math.max(alpha, value);
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move move : model.getLegalMoves()){
                model.doMove(move);
                value = Math.min(value, minimax(depth - 1, model, alpha, beta, true));
                model.undoMove();
                if (alpha >= value){break;}
                beta = Math.min(beta, value);
            }
            return value;
        }


    }

    private boolean isNodeTerminal(Model model) {
        //TODO
        return false;
    }

    /**
     * method that tries to score a given position
     * @return int score of the position.
     */
    private int evaluatePosition(Model model){
        int teamMultiplier = (AI_TEAM == Team.WHITE ? 1 : -1);
        int numberOfTurns = model.getCurrentTurn();
        int finalValue = 0;
        int pieceValue = model.getScore();
        int addedValue = 0; //TODO add things like position, not moving to many pawns, not moving backwords etc..

        // adding points if castling
        if (numberOfTurns > 8 && numberOfTurns < 14) {
        }



        finalValue = pieceValue + addedValue * teamMultiplier;
        return finalValue;
    }
}
