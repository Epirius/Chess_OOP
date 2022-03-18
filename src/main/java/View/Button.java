package View;

import Main.Constants;
import Model.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends JComponent{
    public final int xPos;
    public final int yPos;
    public final int width;
    public final int height;
    public final BufferedImage image;
    public final Type type;

    public Button(int xPos, int yPos, int width, int height, BufferedImage image, Type type){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.image = image;
        this.type = type;
    }

    public void drawButton(Graphics g ,JLayeredPane pane){
        g.fillRect(xPos, yPos, width, height);

        int xImagePos = xPos + (Constants.upgradePawnBoxWidth - image.getWidth()) / 2;
        int yImagePos = yPos + (Constants.upgradePawnBoxHeight - image.getHeight()) / 2;
        g.drawImage(image, xImagePos, yImagePos, pane);
    }

    public int[] getStartPosition(){
        return new int[]{xPos, yPos};
    }

    public int[] getEndPosition(){
        return new int[]{xPos + width, yPos + height};
    }
}
