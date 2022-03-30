package View;

import Controller.Controller;
import Controller.GameState;
import Main.Constants;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.GraphicHelperMethods.*;


public class View extends JComponent {
    List<Integer> legalSquares = new ArrayList<>();
    Controller controller;

    public View(){

    }

    public void setDrawable(Controller controller){
        this.controller = controller;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardLayer(g);
        pieceLayer(g);
        hudLayer(g);

    }

    /*
    //TODO REMOVE?
    @Override
    public void setLegalSquares(List<Integer> legalSquares){
        this.legalSquares = legalSquares;
    }

     */

    /**
     * converts a square id to coordinates with 0,0 at TOP LEFT.
     * @param square id of the board square.
     * @return coords with 0,0 at TOP LEFT
     */
    private int[] inverseSquareToCoords(int square){
        int[] coords = IBoard.squareToCoordinates(square);
        int x = coords[0];
        int y = Math.abs(coords[1] - 7);
        return new int[]{x, y};

    }


    /**
     * a method that draws the board layer.
     */
    private void boardLayer(Graphics g){
        g.setColor(Constants.colorBackground);
        g.fillRect(0,0,Constants.displayWidth, Constants.displayHeight);
        int squareSize = Constants.squareSize;
        int boardOffset = Constants.boardOffset;


        // Drawing the board.
        for (int width = 0; width < 8; width++) {
            for (int height = 0; height < 8; height++) {
                Color squareColor = ((width + height) % 2 == 0 ? Constants.colorLightSquare : Constants.colorDarkSquare);
                g.setColor(squareColor);
                int x =  boardOffset + squareSize * width;
                int y =  boardOffset + squareSize * height;
                g.fillRect(x, y, squareSize, squareSize);
            }
        }

        // draw legal squares.
        legalSquares = controller.getLegalSquares();
        if (legalSquares.size() > 0) {
            for (Integer i : legalSquares) {
                // convert the square into coordinates. NB!! 0,0 is at bottom left.
                int[] legalSquare = inverseSquareToCoords(i);
                int x = boardOffset + squareSize * legalSquare[0];
                int y = boardOffset + squareSize * legalSquare[1];
                g.setColor(Constants.colorHighlightSquare);
                g.fillRect(x, y, squareSize, squareSize);
            }
        }

    }

    /**
     * A method that draws the piece layer
     */
    private void pieceLayer(Graphics g){
        JLayeredPane piecePane = new JLayeredPane();
        List<ViewPiece> pieces = controller.getPiecesOnTheBoard();

        for (ViewPiece piece : pieces){
            int[] coords = inverseSquareToCoords(piece.position);
            int x = Constants.boardOffset + Constants.squareSize * coords[0];
            int y = Constants.boardOffset + Constants.squareSize * coords[1];

            int xOffset = x + Math.floorDiv(Constants.squareSize, 2) - Math.floorDiv(piece.image.getWidth(), 2);
            int yOffset = y + Math.floorDiv(Constants.squareSize, 2) - Math.floorDiv(piece.image.getHeight(), 2);
            if (piece.type == Type.PAWN){
                yOffset = y + Math.floorDiv(Constants.squareSize, 6);
            }

            g.drawImage(piece.image, xOffset, yOffset, piecePane);
        }
    }

    private void hudLayer(Graphics g){
        //TODO stop normal handeling of clicks if gamestate is upgrade_pawn.
        JLayeredPane pane = new JLayeredPane();

        // If a pawn is upgrading
        if (controller.getGameState() == GameState.UPGRADE_PAWN){
            g.setColor(Constants.colorPawnUpgradeBG);
            g.fillRect(0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), Constants.upgradePawnBoxHeight * 2);
            g.setColor(Constants.colorBackground);

            Team team;
            if (controller.getTeam() == Team.WHITE){team = Team.BLACK;}
            else {team = Team.WHITE;}
            List<Button> upgradeButtons = getUpgradeButtons(team);
            for (Button button : upgradeButtons) {
                button.drawButton(g, pane);
            }
        }

        if (controller.getGameState() == GameState.CHECK_MATE || controller.getGameState() == GameState.DRAW){
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), Constants.upgradePawnBoxHeight * 2);

            Font myfont = new Font("SansSerif", Font.BOLD, 80);
            g.setFont(myfont);
            g.setColor(Color.WHITE);
            String text = "";
            if (controller.gameState == GameState.CHECK_MATE) {
                text = "Check Mate";
            } else if (controller.gameState == GameState.DRAW){
                text = "Draw";
            }
            drawCenteredString(g, text, 0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), Constants.upgradePawnBoxHeight * 2);
        }
    }

    /**
     * a method that creates 4 buttons used for upgrading a pawn.
     * @param team team the currently is playing.
     * @return a list of buttons.
     */
    public List<Button> getUpgradeButtons(Team team){
        List<Button> upgradeButtons = new ArrayList<>();

        int spacing = (getWidth() - Constants.upgradePawnBoxHeight * 2) / (4 - 1) - Constants.upgradePawnBoxWidth;
        int numButtons = 4;

        //creating 4 pieces that will be placed on top of the buttons (position does not matter here)
        List<ViewPiece> upgradePossibilities = new ArrayList<>();
        upgradePossibilities.add(new ViewPiece(Type.QUEEN, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.ROOK, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.BISHOP, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.KNIGHT, team, 0));

        // creating the 4 buttons
        for (int i = 0; i < numButtons; i++) {
            int xPosition = spacing * (i + 1) + Constants.upgradePawnBoxHeight * i;
            int yPosition = (getHeight() - Constants.upgradePawnBoxWidth) / 2;
            upgradeButtons.add(new Button(xPosition, yPosition, Constants.upgradePawnBoxWidth, Constants.upgradePawnBoxHeight, upgradePossibilities.get(i).image, upgradePossibilities.get(i).type));
        }
        return upgradeButtons;
    }

}
