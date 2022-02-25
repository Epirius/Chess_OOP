package View;

import Controller.Controller;
import Main.Constants;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class View extends JComponent {
    Controller controller;
    List<Integer> legalSquares = new ArrayList<>();

    public View(Controller controller){
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
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
                    int square = rawCoordsToSquare(rawX, rawY);
                    System.out.println(square);
                    controller.handleClicks(square);
                    legalSquares = controller.getLegalSquares();
                    repaint();
                }
            }
        });
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardLayer(g);
        pieceLayer(g);
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
     * converts a square id to coordinates with 0,0 at TOP LEFT.
     * @param square
     * @return coords with 0,0 at TOP LEFT
     */
    private int[] inverseSquareToCoords(int square){
        int[] coords = IBoard.squareToCoordinates(square);
        int x = coords[0];
        int y = Math.abs(coords[1] - 7);
        return new int[]{x, y};

    }


    public void boardLayer(Graphics g){
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
        //List<Integer> legalSquares = controller.getLegalSquares();
        if (legalSquares.size() > 0) {
            for (Integer i : legalSquares) {
                // convert the square into coordinates. NB!! 0,0 is at bottom left.
                System.out.println(i + "-----");
                int[] legalSquare = inverseSquareToCoords(i);
                int x = boardOffset + squareSize * legalSquare[0];
                int y = boardOffset + squareSize * legalSquare[1];
                g.setColor(Constants.colorHighlightSquare);
                g.fillRect(x, y, squareSize, squareSize);
            }
        }

    }

    public void pieceLayer(Graphics g){
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
}
