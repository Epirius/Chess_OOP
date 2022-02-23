package View;

import Main.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JComponent {

    public View(){

        addMouseListener(new MouseAdapter() {
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
                System.out.println(square);
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
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


    public void boardLayer(Graphics g){
        g.setColor(Constants.colorBackground);
        g.fillRect(0,0,Constants.displayWidth, Constants.displayHeight);
        int squareSize = Constants.squareSize;

        // Drawing the board.
        for (int width = 0; width < 8; width++) {
            for (int height = 0; height < 8; height++) {
                Color squareColor = ((width + height) % 2 == 0 ? Constants.colorLightSquare : Constants.colorDarkSquare);
                g.setColor(squareColor);
                int x =  Constants.boardOffset + squareSize * width;
                int y =  Constants.boardOffset + squareSize * height;
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
