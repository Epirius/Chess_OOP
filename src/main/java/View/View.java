package View;

import Main.Constants;

import javax.swing.*;
import java.awt.*;

public class View extends JComponent {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        boardLayer(g);
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
}
