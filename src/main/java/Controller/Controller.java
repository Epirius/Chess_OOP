package Controller;

import Main.Constants;
import Model.Model;
import Model.Pieces.Pawn;
import Model.Type;
import Model.Team;
import Model.Move;
import Model.Pieces.Piece;
import View.View;
import View.Button;
import View.ViewPiece;
import View.IDrawable;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Felix Kaasa
 */
public class Controller extends MouseAdapter implements IDrawable {
    private Integer[] clickHolder = new Integer[2];
    private List<Move> selectedLegalMoves = new ArrayList<>();
    Model model;
    View view;
    public GameState gameState = GameState.ACTIVE_GAME; //TODO HANDLE CHANGING OF GAME STATE.

    public Controller(Model model, View view){
        this.clickHolder[0] = null;
        this.clickHolder[1] = null;
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int rawX = e.getX();
            int rawY = e.getY();

            //checking if the x and y coordinates of the mouse is over the squares of the board.
            boolean xInBounds = rawX > Constants.boardOffset && rawX < Constants.boardOffset + Constants.squareSize * 8;
            boolean yInBounds = rawY > Constants.boardOffset && rawY < Constants.boardOffset + Constants.squareSize * 8;
            if (!xInBounds || !yInBounds) {
                return;
            }

            //converting the x and y coordinates of the mouse to a int of the square it is over.
            if (gameState == GameState.ACTIVE_GAME) {
                int square = rawCoordsToSquare(rawX, rawY);
                System.out.println(square); // TODO delete this line
                handleClicks(square);
            }
            else if (gameState == GameState.UPGRADE_PAWN){
                handlePawnUpgradeClicks(rawX, rawY);
            }

            view.repaint();
        }
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

            // if clickHandler[0] and clickHandler[1] make a valid move
            if (model.getLegalMoves().contains(new Move(clickHolder[0], clickHolder[1]))){
                createMove(clickHolder[0], clickHolder[1]);
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
     * this method handles clicks when a pawn upgrade is happening
     * @param rawX raw x position of mouse
     * @param rawY raw y position of mouse
     */
    private void handlePawnUpgradeClicks(int rawX, int rawY) {
        if (gameState != GameState.UPGRADE_PAWN){
            throw new RuntimeException("handlePawnUpgradeClicks should only be called if the Gamestate is UPGRADE_PAWN");
        }

        // Team does not matter here.
        List<Button> upgradeButtons = view.getUpgradeButtons(Team.BLACK);
        for (Button button : upgradeButtons) {
            int xStart = button.getStartPosition()[0];
            int yStart = button.getStartPosition()[1];
            int xEnd = button.getEndPosition()[0];
            int yEnd = button.getEndPosition()[1];

            if (rawX > xStart && rawX < xEnd && rawY > yStart && rawY < yEnd){
                System.out.println("Button hit " + button.type); //TODO HANDLE EVENT
                model.upgradePawn(button.type);
                gameState = GameState.ACTIVE_GAME;
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

    private void createMove(int from, int to){
        for (Move legalMove : model.getLegalMoves()){
            if (legalMove.equals(new Move(from, to))){
                model.doMove(legalMove);
                checkPawnUpgrade(legalMove);
                checkIfGameOver();
            }
        }
    }

    private void checkPawnUpgrade(Move move){
        if (model.getPiece(move.to).type.equals(Type.PAWN) && ((move.to >= 56 && move.to < 64) || (move.to <= 7 && move.to >= 0))){
            this.gameState = GameState.UPGRADE_PAWN;
        }
    }

    private void checkIfGameOver(){
        if (model.getLegalMoves().size() == 0){
            if (model.kingInCheck()){
                gameState = GameState.CHECK_MATE;
            } else {
                gameState = GameState.DRAW;
            }
            view.repaint();
        }
    }

    @Override
    public List<Integer> getLegalSquares(){
        List<Integer> squares = new ArrayList<>();
        for (Move move : selectedLegalMoves){
            squares.add(move.to);
        }
        return squares;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public List<ViewPiece> getDeadViewPieces(Team team) {
        //TODO add to an interface
        List<ViewPiece> output = new ArrayList<>();
        List<Piece> deadPieces = model.getDeadPieces();

        for (Piece piece : deadPieces){
            if (piece.team != team){continue;}
            output.add(new ViewPiece(piece.type, piece.team, 0)); // position does not matter here.
        }
        return output;
    }

    @Override
    public List<ViewPiece> getPiecesOnTheBoard(){
        List<ViewPiece> output = new ArrayList<>();
        for (Piece piece : model.getAllPieces()){
            output.add(new ViewPiece(piece.getPiece(), piece.getTeam(), piece.getPosition()));
        }
        return output;
    }

    //TODO add to some interface
    public Team getTeam(){
        return model.getTeam();
    }
}
