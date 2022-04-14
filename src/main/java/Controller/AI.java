package Controller;

import Main.Constants;
import Model.Model;
import Model.Board;
import Model.Pieces.King;
import Model.Pieces.Piece;
import Model.Team;
import Model.Move;
import Model.Type;
import View.IDrawAi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Felix Kaasa
 */
public class AI implements IDrawAi {
    private final Controller controller;
    public boolean enabled;
    private final Team AI_TEAM;
    private IAiMovable model;
    private final Random random = new Random();
    private int AI_SEARCH_DEPTH = 3;
    private final boolean randomAI = false;


    public AI(Controller controller, Team team){
        this.controller = controller;
        this.AI_TEAM = team;
        this.enabled = (this.AI_TEAM != null);
    }

    public void installModel(IAiMovable model){ this.model = model;}

    @Override
    public Team getTeam(){ return this.AI_TEAM;}

    @Override
    public void createMove() {
        if (!enabled){return;}
        if (model.getTeam() != AI_TEAM){
            throw new IllegalStateException("The AI is trying to create a move but it is not the AI's turn!");
        }

        List<Move> moves = model.getLegalMoves();
        //aiMove = moves.get(random.nextInt(moves.size()));
        Move aiMove = getBestMove(moves, model);
        model.doMove(aiMove);
        controller.checkPawnUpgrade(aiMove);
        if (controller.checkIfGameOver()){
            controller.handleGameOver();
        }

    }

    @Override
    public boolean isAiTurn(){
        return (enabled && model.getTeam() == AI_TEAM);
    }

    @Override
    public boolean isEnabled(){return enabled;}


    @Override
    public void upgradePawn() {
        model.upgradePawn(Type.QUEEN);
        controller.setGameState(GameState.ACTIVE_GAME);
    }

    private  Move getBestMove(List<Move> possibleMoves, IAiMovable model){
        if (randomAI) {
            return possibleMoves.get(random.nextInt(possibleMoves.size()));
        }

        if (model.getAllPieces().size() < 6){
            AI_SEARCH_DEPTH = 4;
        }
        int depth = AI_SEARCH_DEPTH;
        Integer bestMoveIndex = null;
        List<Move> possibleBestMoves = new ArrayList<>();
        possibleMoves = preSortMovesList(possibleMoves);
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

    /**
     * a method that sorts the list of possibleMoves by which moves are most likely to be good. this helps during alpha beta pruning.
     * @param possibleMoves list of possible moves
     * @return sorted list of possible moves
     */
    private List<Move> preSortMovesList(List<Move> possibleMoves) {
        List<Move> captureMoveList = new ArrayList<>();
        List<Move> normalMoveList = new ArrayList<>();

        for (Move move : possibleMoves){
            if (model.getPiece(move.to) != null){
                captureMoveList.add(move);
            } else {
                normalMoveList.add(move);
            }
        }

        if (captureMoveList.addAll(normalMoveList)){
            return captureMoveList;
        }
        return possibleMoves;
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
    private int minimax(int depth, IAiMovable model, int alpha, int beta, boolean maximizingPlayer){
        // I am implementing the pseudocode from: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
        // and getting inspiration from here: https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

        if (depth == 0 || isNodeTerminal(model)){
            return evaluatePosition(model);
        }

        if (maximizingPlayer){
            int value = Integer.MIN_VALUE;
            for (Move move : preSortMovesList(model.getLegalMoves())){
                model.doMove(move);
                value = Math.max(value, minimax(depth - 1, model, alpha, beta, false));
                model.undoMove();
                if (value >= beta){ break;}
                alpha = Math.max(alpha, value);
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move move : preSortMovesList(model.getLegalMoves())){
                model.doMove(move);
                value = Math.min(value, minimax(depth - 1, model, alpha, beta, true));
                model.undoMove();
                if (alpha >= value){break;}
                beta = Math.min(beta, value);
            }
            return value;
        }


    }

    /**
     * method to see if the node is a leaf node
     * @param model node
     * @return true if it is a leaf node
     */
    private boolean isNodeTerminal(IAiMovable model) {
        return (model.getLegalMoves().size() == 0);
    }

    /**
     * method that tries to score a given position
     * @return int score of the position.
     */
    private int evaluatePosition(IAiMovable model){
        int teamMultiplier = (model.getTeam() == Team.WHITE ? -1 : 1);
        int numberOfTurns = model.getCurrentTurn();
        int pieceValue = model.getScore();
        int addedValue = 0;

        if (isNodeTerminal(model)){
            return Integer.MAX_VALUE * teamMultiplier;
        }

        if (numberOfTurns > 8 && numberOfTurns < 14) {
            // TODO add extra points to addedValue for positioning and castling etc..
        }

        return pieceValue + addedValue * teamMultiplier;
    }
}
