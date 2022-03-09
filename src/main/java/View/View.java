package View;

import Controller.IClickable;
import Main.Constants;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JComponent implements IClickable {
    List<Integer> legalSquares = new ArrayList<>();

    public View(){
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardLayer(g);
        pieceLayer(g);
    }

    @Override
    public void setLegalSquares(List<Integer> legalSquares){
        this.legalSquares = legalSquares;
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


    /**
     * a method that draws the board layer.
     * @param g
     */
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
        // TODO this needs to be changed
        JLayeredPane piecePane = new JLayeredPane();
        JLabel piece1 = new JLabel(new ImageIcon(Constants.rookW));
        add(piece1);
        g.drawImage(Constants.rookW, 100, 100, piecePane);
    }
}
