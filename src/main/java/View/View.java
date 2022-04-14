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
import Controller.AI;

import static View.GraphicHelperMethods.*;


public class View extends JComponent {
    List<Integer> legalSquares = new ArrayList<>();
    private Team selectTeam = null;
    protected IDrawController controller;
    protected IDrawModel model;
    private IDrawAi ai;
    private Clock clock;
    private int minutesPerSide = Constants.TIME_MINUTES;
    private int secondsPerMove = Constants.TIME_ADDED_EACH_MOVE_SECONDS;
    public final int upgradePawnBoxHeight = 100;
    public final int upgradePawnBoxWidth = 100;

    private static final Color colorBackground = new Color(90, 94, 89);
    private static final Color colorDarkSquare = new Color(21, 29, 36);
    private static final Color colorLightSquare = new Color(237, 132, 99);
    private static final Color colorHighlightSquare = new Color(213, 248, 147, 175);
    private static final Color colorPawnUpgradeBG = new Color(95, 205, 228);
    private static final Color colorButton = Color.GRAY;

    private List<Button> createGame_buttonsList;
    private List<Button> upgradeButtonsWhite;
    private List<Button> upgradeButtonsBlack;
    private List<Button> hudButtons;
    private List<Button> endScreenButtons;




    public View() {
        createGame_buttonsList = new ArrayList<>();
        upgradeButtonsWhite = new ArrayList<>();
        upgradeButtonsBlack = new ArrayList<>();
        hudButtons = new ArrayList<>();
        endScreenButtons = new ArrayList<>();
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
        if (controller.getGameState() == GameState.MAIN_MENU){
            mainMenu(g);
        } else if (controller.getGameState() == GameState.CREATE_GAME){
            createGameScreen(g);
        } else {
            boardLayer(g);
            pieceLayer(g);
            hudLayer(g);
        }
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
     * draws the main menu
     * @param g graphics
     */
    private void mainMenu(Graphics g){
        controller.setGameState(GameState.CREATE_GAME); //TODO CREATE A MAIN MENU
        // TODO NEW GAME - call createGameScreen()
        // TODO ABOUT
        // TODO CREDITS etc..
        repaint();
    }

    /**
     * new game menu
     * @param g graphics
     */
    private void createGameScreen(Graphics g) {
        g.setColor(colorBackground);
        g.fillRect(0, 0, Constants.displayWidth, Constants.displayHeight);


        //Create and draw the buttons
        for (Button button : getCreateGameButtons()) { button.drawButton(g, colorButton);}

        //Highlight the pressed button.
        g.setColor(new Color(255, 255, 255, 100));
        Button whiteButton = createGame_buttonsList.get(0);
        Button blackButton = createGame_buttonsList.get(1);
        Button bothButton = createGame_buttonsList.get(2);
        if (selectTeam == Team.WHITE) { g.fillRect(whiteButton.xPos, whiteButton.yPos, whiteButton.width, whiteButton.height);}
        else if (selectTeam == Team.BLACK) { g.fillRect(blackButton.xPos, blackButton.yPos, blackButton.width, blackButton.height);}
        else if (selectTeam == null) { g.fillRect(bothButton.xPos, bothButton.yPos, bothButton.width, bothButton.height);}

        //Text
        g.setColor(Color.WHITE);
        g.setFont( new Font("SansSerif", Font.BOLD, 60));
        drawCenteredString(g,"Choose a team:",0,10, getWidth(), 70);
        g.fillRect(95,80, getStringWidth(g, g.getFont(), "Choose a team:"),2);
        g.setFont( new Font("SansSerif", Font.BOLD, 30));
        drawCenteredString(g, "Minutes per side: " + minutesPerSide, 0, 200, getWidth(), 70);
        g.fillRect(183,260, getStringWidth(g, g.getFont(), "Minutes per side: " + minutesPerSide),2);
        drawCenteredString(g, "Increment in seconds: " + secondsPerMove, 0, 350, getWidth(), 70);
        g.fillRect(149,410, getStringWidth(g, g.getFont(), "Increment in seconds: " + secondsPerMove),2);

        repaint();
    }

    private void createGame(){
        Constants.TIME_ADDED_EACH_MOVE_SECONDS = secondsPerMove;
        Constants.TIME_MINUTES = minutesPerSide;
        Team aiTeam = null;
        switch (selectTeam){
            case WHITE -> aiTeam = Team.BLACK;
            case BLACK -> aiTeam= Team.WHITE;
            case null -> aiTeam= null;
        }
        Model newModel = new Model();
        AI ai = new AI((Controller) controller, aiTeam);
        this.model = newModel;
        this.ai = ai;
        Clock newClock = new Clock(this, (Controller) controller); //TODO
        this.clock = newClock;
        newModel.installClock(newClock);
        controller.installModel(newModel);
        controller.installAI(ai);
        controller.setGameState(GameState.ACTIVE_GAME);
        if (selectTeam == Team.BLACK && selectTeam != null) {
            ai.createMove();
        }
    }



    /**
     * a method that draws the board
     */
    private void boardLayer(Graphics g){
        g.setColor(colorBackground);
        g.fillRect(0,0,Constants.displayWidth, Constants.displayHeight);
        int squareSize = Constants.squareSize;
        int boardOffset = Constants.boardOffset;


        // Drawing the board.
        for (int width = 0; width < 8; width++) {
            for (int height = 0; height < 8; height++) {
                Color squareColor = ((width + height) % 2 == 0 ? colorLightSquare : colorDarkSquare);
                g.setColor(squareColor);
                int x =  boardOffset + squareSize * width;
                int y =  boardOffset + squareSize * height;
                g.fillRect(x, y, squareSize, squareSize);
            }
        }

        // draw last move
        if (model.getLastMove() != null) {
            //TODO make an interface for the call to model
            int[] lastMove = new int[]{model.getLastMove().from, model.getLastMove().to};
            for (int square : lastMove){
                drawShapeInSquare(g, square, 1);
            }
        }

        // draw legal squares.
        legalSquares = controller.getLegalSquares();
        if (legalSquares.size() > 0) {
            for (Integer square : legalSquares) {
                if (model.getPiece(square) == null) {
                    drawShapeInSquare(g, square, 2);
                } else {
                    drawShapeInSquare(g, square, 3);
                }
            }
        }
    }

    /**
     * a helper method to draw a shape inside a square
     * @param g graphics
     * @param squareID square in which the shape will be drawn
     * @param shape id of the shape: 1 = circle, 2 = filledRect, 3 = filled corners
     */
    private void drawShapeInSquare(Graphics g, Integer squareID, int shape) {
        g.setColor(colorHighlightSquare);
        int squareSize = Constants.squareSize;
        int boardOffset = Constants.boardOffset;
        int[] legalSquare = inverseSquareToCoords(squareID);
        int x = boardOffset + squareSize * legalSquare[0];
        int y = boardOffset + squareSize * legalSquare[1];
        g.setColor(colorHighlightSquare);
        int circleSize = squareSize / 2;
        if (shape == 1) {
            g.fillRect(x, y, squareSize, squareSize);
        } else if (shape == 2){
            g.fillOval(x + circleSize / 2, y + circleSize / 2, circleSize, circleSize);
        } else if (shape == 3){
            List<Polygon> polygons = new ArrayList<>();
            int polySize = squareSize / 4;
            g.setColor(Color.red);

            polygons.add(new Polygon(new int[]{x, x, x + polySize}, new int[]{y, y + polySize, y}, 3));
            polygons.add(new Polygon(new int[]{x + squareSize, x + squareSize, x + squareSize - polySize}, new int[]{y, y + polySize, y}, 3));
            polygons.add(new Polygon(new int[]{x , x , x + polySize}, new int[]{y + squareSize, y - polySize + squareSize, y + squareSize}, 3));
            polygons.add(new Polygon(new int[]{x + squareSize, x + squareSize, x + squareSize - polySize}, new int[]{y + squareSize, y - polySize + squareSize, y + squareSize}, 3));

            for (Polygon polygon : polygons) {
                g.fillPolygon(polygon);
            }
        }
    }

    /**
     * A method that draws the pieces
     */
    private void pieceLayer(Graphics g){
        //TODO draw the move the piece before the ai finishes thinking.
        JLayeredPane piecePane = new JLayeredPane();
        List<ViewPiece> pieces = model.getPiecesOnTheBoard();

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

    /**
     * the method that draws stuff in front of the game
     * @param g graphics
     */
    private void hudLayer(Graphics g){
        JLayeredPane pane = new JLayeredPane();
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        drawClock(g);

        drawDeadPieces(g, pane);

        // if active game draw hud buttons
        for (Button button : getHudButtons()){
            button.drawButton(g, colorButton);
        }

        // If a pawn is upgrading
        if (controller.getGameState() == GameState.UPGRADE_PAWN){
            if (ai.isEnabled() && !ai.isAiTurn()){
                ai.upgradePawn();
                repaint();
                return;
            }

            g.setColor(colorPawnUpgradeBG);
            g.fillRect(0, getHeight() / 2 - upgradePawnBoxHeight, getWidth(), upgradePawnBoxHeight * 2);
            g.setColor(colorBackground);

            Team team;
            if (model.getTeam() == Team.WHITE){team = Team.BLACK;}
            else {team = Team.WHITE;}

            for (Button button : (team == Team.WHITE ? getUpgradeButtons(Team.WHITE) : getUpgradeButtons(Team.BLACK))) {
                button.drawButton(g, colorButton);
            }
        }

        // game over screens.
        if (controller.getGameState() == GameState.CHECK_MATE){ endScreen(g, "Check Mate");}
        if (controller.getGameState() == GameState.DRAW){ endScreen(g, "Draw");}
        if (controller.getGameState() == GameState.TIME_OUT){ endScreen(g, "Time ran out");}
        repaint();
    }

    /**
     * method that draws the screens when the game is over.
     * @param g graphis
     * @param text the main text that is displayed.
     */
    private void endScreen(Graphics g, String text) {
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, getHeight() / 2 - upgradePawnBoxHeight, getWidth(), (int) (upgradePawnBoxHeight * 2.5f));
        g.setFont(new Font("SansSerif", Font.BOLD, 80));

        g.setColor(Color.WHITE);
        drawCenteredString(g, text, 0, getHeight() / 2 - upgradePawnBoxHeight, getWidth(), upgradePawnBoxHeight * 2);

        for (Button button : getEndScreenButtons()) {
            button.drawButton(g, colorButton);
        }
    }

    /**
     * draw the pieces that have been killed for both teams
     * @param g Graphics object
     * @param pane pane
     */
    private void drawDeadPieces(Graphics g, JLayeredPane pane) {
        // Dead pieces
        for (Team team : Arrays.asList(Team.WHITE, Team.BLACK)){
            List<ViewPiece> viewPieces = model.getDeadViewPieces(team);
            int yPos = (team == Team.BLACK ? getHeight() - Constants.boardOffset / 2 : Constants.boardOffset / 2) - 8;
            int xPos = Constants.boardOffset;

            String text = String.valueOf((team == Team.BLACK ? model.getScore() : model.getScore() * -1));
            int textWidth = GraphicHelperMethods.getStringWidth(g, getFont(), text);
            int textHeight = GraphicHelperMethods.getStringHeight(g, getFont(), text);
            drawCenteredString(g, text, xPos - textWidth - 3, yPos, textWidth, textHeight);

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
        g.setColor(colorPawnUpgradeBG);
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
     * buttons are created this way to  prevent a stackoverflow error from addMouseListener.
     * @param team team the currently is playing.
     * @return a list of buttons.
     */
    public List<Button> getUpgradeButtons(Team team){
        List<Button> buttonList = (team == Team.WHITE ? upgradeButtonsWhite : upgradeButtonsBlack);
        if (buttonList.size() > 0){
            return buttonList;
        }

        int spacing = (getWidth() - upgradePawnBoxHeight * 2) / (4 - 1) - upgradePawnBoxWidth;
        int numButtons = 4;

        //creating 4 pieces that will be placed on top of the buttons (position does not matter here)
        List<ViewPiece> upgradePossibilities = new ArrayList<>();
        upgradePossibilities.add(new ViewPiece(Type.QUEEN, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.ROOK, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.BISHOP, team, 0));
        upgradePossibilities.add(new ViewPiece(Type.KNIGHT, team, 0));

        // creating the 4 buttons
        for (int i = 0; i < numButtons; i++) {
            int xPosition = spacing * (i + 1) + upgradePawnBoxHeight * i;
            int yPosition = (getHeight() - upgradePawnBoxWidth) / 2;
            buttonList.add(new imageButton(xPosition, yPosition, upgradePawnBoxWidth, upgradePawnBoxHeight, this, upgradePossibilities.get(i).image, upgradePossibilities.get(i).type));
        }
        return buttonList;
    }

    /**
     * method to create the buttons on the createGame screen.
     * buttons are created this way to  prevent a stackoverflow error from addMouseListener.
     * @return a list of buttons.
     */
    private List<Button> getCreateGameButtons(){
        if (createGame_buttonsList.size() > 0){
            return createGame_buttonsList;
        }

        int yButton = 100;
        int buttonWidth = 70;
        int center = (getWidth() - buttonWidth) / 2;
        //CHOOSE TEAM BUTTONS:
        createGame_buttonsList.add(new imageButton(center - 2 * buttonWidth, yButton, buttonWidth, 70, this, Constants.rookW, () -> selectTeam = Team.WHITE));
        createGame_buttonsList.add(new imageButton(center + 2 * buttonWidth, yButton, buttonWidth, 70, this, Constants.rookB, () -> selectTeam = Team.BLACK));
        createGame_buttonsList.add(new imageButton(center, yButton, buttonWidth, 70, this, Constants.rookWB, () -> selectTeam = null));

        //PLUSS MINUS BUTTONS:
        createGame_buttonsList.add(new TextButton(center + 30, 280, 40, 40, "+", this, () -> minutesPerSide += 1));
        createGame_buttonsList.add(new TextButton(center - 30, 280, 40, 40, "-", this, () -> {if(minutesPerSide > 1){ minutesPerSide -= 1;}}));
        createGame_buttonsList.add(new TextButton(center + 30, 427, 40, 40, "+", this, () -> secondsPerMove += 1));
        createGame_buttonsList.add(new TextButton(center - 30, 427, 40, 40, "-", this, () -> {if(secondsPerMove > 0){ secondsPerMove -= 1;}}));

        //CREATE GAME BUTTON:
        createGame_buttonsList.add(new TextButton(center - 55, 530, 150, 40, "Create game", this, () -> createGame()));

        return createGame_buttonsList;
    }

    /**
     * method to create the buttons that are drawn on the main game screen.
     * buttons are created this way to  prevent a stackoverflow error from addMouseListener.
     * @return a list of buttons
     */
    private List<Button> getHudButtons(){
        if (hudButtons.size() > 0){
            return hudButtons;
        }
        int xSize = 70;
        int ySize = 50;

        // CREATING AN UNDO BUTTON.
        hudButtons.add(new TextButton(getWidth() - xSize, getHeight() - ySize, xSize, ySize, "Undo", this, () -> {
            if (controller.getGameState() != GameState.ACTIVE_GAME){return;}
            boolean aiEnabled = ai.isEnabled();
            if (!aiEnabled){
                model.undoMove();
                return;
            }
            Team aiTeam = ai.getTeam();
            Team currentTeam = model.getTeam();
            if (currentTeam == aiTeam){
               model.undoMove();
            } else {
                model.undoMove(2);
            }
        }));

        return hudButtons;
    }

    /**
     * method to create the buttons that are drawn on the end screen.
     * buttons are created this way to  prevent a stackoverflow error from addMouseListener.
     * @return a list of buttons.
     */
    private List<Button> getEndScreenButtons(){
        if (endScreenButtons.size() > 0){
            return endScreenButtons;
        }

        int buttonY = (int) (getHeight() / 1.6f);
        int buttonX = getWidth() / 2;
        int buttonWidth = 120;
        int buttonHeight = 30;

        endScreenButtons.add(new TextButton(buttonX - 30 - buttonWidth, buttonY,buttonWidth,buttonHeight,"main menu", this, () -> controller.setGameState(GameState.MAIN_MENU)));
        endScreenButtons.add(new TextButton(buttonX + 30, buttonY,buttonWidth,buttonHeight, "Quit", this, () -> System.exit(0)));

        return endScreenButtons;
    }

}
