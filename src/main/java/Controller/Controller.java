package Controller;

import Model.Board;
import Model.Team;
import View.View;

/**
 * @author Felix Kaasa
 */
public class Controller {
    private Integer[] clickHandler = new Integer[2];
    Board board;
    View view;

    public Controller(Board board, View view){
        System.out.println("set up...");
        this.board = board;
        this.view = view;
        this.clickHandler[0] = null;
        this.clickHandler[1] = null;
    }

    /**
     * The view will send user clicks to this method.
     * @param clickedSquare an int corresponding to the ID of the square that was clicked on.
     */
    public void handleClicks(int clickedSquare){
        if (this.clickHandler[0] == null){

            // reset clickHandler
            this.clickHandler[1] = null;

            //if the first click is on a friendly piece.
            if ((board.isCurrentPlayerIsWhite() && board.getPiece(clickedSquare).team == Team.WHITE) || (!board.isCurrentPlayerIsWhite() && board.getPiece(clickedSquare).team == Team.BLACK)){
                this.clickHandler[0] = clickedSquare;
            }

            // if (clickedSquare in moves[0]
            //      find all legal moves and display them. TODO
        }
        // if the player clicks on the same square two times.
        else if (this.clickHandler[0] == clickedSquare){
            this.clickHandler[0] = null;
            this.clickHandler[1] = null;
            // remove display of legal moves TODO
        }
        // if the player is clicking on a second square different then the first
        else if (this.clickHandler[0] != null && this.clickHandler[0] != clickedSquare){
            // remove display of legal moves TODO

            // if ( this.clickHandler[0] and clickedSquare make a valid move)
            //      create() the move and send it to Model TODO
            //      this.clickHandler[0] = null;
            //      this.clickHandler[1] = null;
            // else
            //      this.clickHandler[0] = clickedSquare;
            //      this.clickHandler[1] = null;
            //      update display of legal moves TODO
        }
    }
}
