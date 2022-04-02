package View;

import Controller.Controller;
import Main.Constants;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Controller.GameState;

import static View.GraphicHelperMethods.*;


public class View extends JComponent {
    List<Integer> legalSquares = new ArrayList<>();
    Controller controller;
    Clock clock;

    public View() {
    }

    public void installController(Controller controller){
        this.controller = controller;
    }

    public void installClock(Clock clock){
        this.clock = clock;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardLayer(g);
        pieceLayer(g);
        hudLayer(g);

    }

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
        JLayeredPane pane = new JLayeredPane();
        Font myfont = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(myfont);

        drawClock(g);

        drawDeadPieces(g, pane);

        // If a pawn is upgrading
        if (controller.getGameState() == GameState.UPGRADE_PAWN){
            g.setColor(Constants.colorPawnUpgradeBG);
            g.fillRect(0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), Constants.upgradePawnBoxHeight * 2);
            g.setColor(Constants.colorBackground);

            Team team;
            if (controller.getTeam() == Team.WHITE){team = Team.BLACK;}
            else {team = Team.WHITE;}
            List<ImageButton> upgradeButtons = getUpgradeButtons(team);
            for (ImageButton button : upgradeButtons) {
                button.drawButton(g, pane);
            }
        }

        // game over screens.
        if (controller.getGameState() == GameState.CHECK_MATE){ endScreen(g, "Check Mate");}
        if (controller.getGameState() == GameState.DRAW){ endScreen(g, "Draw");}
        if (controller.getGameState() == GameState.TIME_OUT){ endScreen(g, "Time ran out");}
    }

    private void endScreen(Graphics g, String text) {
        Font myfont;
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), (int) (Constants.upgradePawnBoxHeight * 2.5f));
        myfont = new Font("SansSerif", Font.BOLD, 80);
        g.setFont(myfont);

        g.setColor(Color.WHITE);
        drawCenteredString(g, text, 0, getHeight() / 2 - Constants.upgradePawnBoxHeight, getWidth(), Constants.upgradePawnBoxHeight * 2);

        int buttonY = (int) (getHeight() / 1.6f);
        int buttonX = getWidth() / 2;
        int buttonWidth = 120;
        int buttonHeight = 30;
        TextButton mainMenu = new TextButton(buttonX - 30 - buttonWidth, buttonY,buttonWidth,buttonHeight,"main menu", this, () -> controller.setGameState(GameState.MAIN_MENU));
        TextButton quitButton = new TextButton(buttonX + 30, buttonY,buttonWidth,buttonHeight, "Quit", this, () -> System.exit(0));
        quitButton.drawButton(g);
        mainMenu.drawButton(g);
    }

    /**
     * draw the pieces that have been killed for both teams
     * @param g Graphics object
     * @param pane pane
     */
    private void drawDeadPieces(Graphics g, JLayeredPane pane) {
        // Dead pieces
        for (Team team : Arrays.asList(Team.WHITE, Team.BLACK)){
            List<ViewPiece> viewPieces = controller.getDeadViewPieces(team);
            int yPos = (team == Team.BLACK ? getHeight() - Constants.boardOffset / 2 : Constants.boardOffset / 2) - 8;
            int xPos = Constants.boardOffset;

            for (ViewPiece piece : viewPieces) {
                g.drawImage(piece.smallImage, xPos, yPos, pane);
                xPos += piece.smallImage.getWidth();
            }

        }
    }

    /**
     * used to draw the clocks for each team.
     * @param g graphics object to draw on.
     */
    private void drawClock(Graphics g) {
        // Clock
        g.setColor(Constants.colorPawnUpgradeBG);
        int whiteTotal = clock.getTime(Team.WHITE);
        int whiteMins = whiteTotal / 60;
        int blackTotal = clock.getTime(Team.BLACK);
        int blackMins = blackTotal / 60;
        int xPos = getWidth() - Constants.boardOffset + 5;

        g.drawString((blackMins > 0 ? blackMins + ":" : "")  + (blackTotal % 60 < 10 ? "0" : "") + blackTotal % 60, xPos, (getHeight() - Constants.squareSize) / 2 + 10);
        g.drawString((whiteMins > 0 ? whiteMins + ":" : "")  + (whiteTotal % 60 < 10 ? "0" : "") + whiteTotal % 60, xPos, (getHeight() + Constants.squareSize) / 2);
    }

    /**
     * a method that creates 4 buttons used for upgrading a pawn.
     * @param team team the currently is playing.
     * @return a list of buttons.
     */
    public List<ImageButton> getUpgradeButtons(Team team){
        List<ImageButton> upgradeButtons = new ArrayList<>();

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
            upgradeButtons.add(new ImageButton(xPosition, yPosition, Constants.upgradePawnBoxWidth, Constants.upgradePawnBoxHeight, this, upgradePossibilities.get(i).image, upgradePossibilities.get(i).type));
        }
        return upgradeButtons;
    }

}
