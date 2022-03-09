package Controller;

import Main.Constants;
import Model.Model;
import Model.Move;
import View.View;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class Controller extends MouseAdapter {
    private Integer[] clickHolder = new Integer[2];
    private List<Move> selectedLegalMoves = new ArrayList<>();
    Model model;
    View view;

    public Controller(Model model, View view){
        this.clickHolder[0] = null;
        this.clickHolder[1] = null;
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int rawX = e.getX();
        int rawY = e.getY();

        //checking if the x and y coordinates of the mouse is over the squares of the board.
        boolean xInBounds = rawX > Constants.boardOffset && rawX < Constants.boardOffset + Constants.squareSize * 8;
        boolean yInBounds = rawY > Constants.boardOffset && rawY < Constants.boardOffset + Constants.squareSize * 8;
        if (!xInBounds || !yInBounds) {return;}

        //converting the x and y coordinates of the mouse to a int of the square it is over.
        int square = rawCoordsToSquare(rawX, rawY);
        System.out.println(square); // TODO delete this line
        handleClicks(square);
        view.setLegalSquares(getLegalSquares());
        view.repaint();
    }

    /**
     * A method that handles the clicks and convert them into actions.
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

    /**
     * converts raw x,y position of a mouse into the square the mouse is over.
     * @param rawX x coord of mouse
     * @param rawY y coord of mouse
     * @return int ID of the square the mouse is over.
     */
    private int rawCoordsToSquare(int rawX, int rawY) {
        boolean xInBounds = rawX > Constants.boardOffset && rawX < Constants.boardOffset + Constants.squareSize * 8;
        boolean yInBounds = rawY > Constants.boardOffset && rawY < Constants.boardOffset + Constants.squareSize * 8;
        if (!xInBounds || !yInBounds){throw new IndexOutOfBoundsException();}

        int offset = Constants.boardOffset;
        int squareSize = Constants.squareSize;
        int x = Math.floorDiv((rawX - offset), squareSize);
        int y = Math.floorDiv((rawY - offset), squareSize);
        int square = ((Math.abs(y - 7) + 1) * 8) - Math.abs(x - 7) - 1;
        return square;
    }

    /**
     * a method that checks if the selected square has legal moves from it. if so it updates the list of legal moves.
     * @param selectedSquare the int id of the square that was selected.
     */
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
}
