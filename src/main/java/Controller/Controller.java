package Controller;

import Model.Model;
import Model.Move;
import Model.Pieces.Piece;
import View.ViewPiece;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class Controller {
    private Integer[] clickHolder = new Integer[2];
    private List<Move> selectedLegalMoves = new ArrayList<>();
    Model model;
    JComponent view;

    public Controller(){
        this.clickHolder[0] = null;
        this.clickHolder[1] = null;
    }

    public void setModelAndView(Model model, JComponent view){
        this.model = model;
        this.view = view;
    }

    /**
     * Handles clicks sendt from View.
     * @param clickedSquare an int corresponding to the ID of the square that was clicked on.
     */
    public void handleClicks(int clickedSquare){
        if (clickHolder[0] == null){

            // reset clickHandler
            clickHolder[1] = null;

            //if the first click is on a friendly piece.
            if (model.isSquareFriendly(clickedSquare)){
                clickHolder[0] = clickedSquare;
                updateSelectedLegalMoves(clickHolder[0]);
            }

        }
        // if the player clicks on the same square two times.
        else if (clickHolder[0] == clickedSquare){
            clickHolder[0] = null;
            clickHolder[1] = null;
            selectedLegalMoves.clear();
        }
        // if the player is clicking on a second square different from the first
        else if (clickHolder[0] != null && clickHolder[0] != clickedSquare){
            clickHolder[1] = clickedSquare;
            selectedLegalMoves.clear();

            // if clickHandler[0] and clickHandler[0] make a valid move
            if (model.getLegalMoves().contains(new Move(clickHolder[0], clickHolder[1]))){
                createMove(new Move(clickHolder[0], clickHolder[1]));
                clickHolder[0] = null;
                clickHolder[1] = null;
            }
            else{
                clickHolder[0] = clickHolder[1];
                clickHolder[1] = null;
                updateSelectedLegalMoves(clickHolder[0]);
            }
        }
    }

    private void updateSelectedLegalMoves(int selectedSquare) {
        for (Move move : model.getLegalMoves()){
            if (move.from == selectedSquare){
                // if the selected square has legal moves, update the list of selected legal moves.
                selectedLegalMoves.add(move);
            }
        }
    }

    private void createMove(Move move){
        //TODO
        System.out.println("Move: " + move.from + ", " + move.to);
    }

    /**
     * get squares that are legal after the user has clicked on a square
     * @return int[] of legal squares
     */
    public List<Integer> getLegalSquares(){
        List<Integer> squares = new ArrayList<>();
        for (Move move : selectedLegalMoves){
            squares.add(move.to);
        }
        return squares;
    }

    public List<ViewPiece> getPiecesOnTheBoard(){
        List<ViewPiece> output = new ArrayList<>();
        for (Piece piece : model.getAllPieces()){
            output.add(new ViewPiece(piece.getPiece(), piece.getTeam(), piece.getPosition()));
        }
        return output;
    }
}
