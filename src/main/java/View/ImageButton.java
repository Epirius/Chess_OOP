package View;

import Controller.GameState;
import Main.Constants;
import Model.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageButton extends Button{

    public final BufferedImage image;
    public final Type type;

    public ImageButton(int xPos, int yPos, int width, int height, View view, BufferedImage image, Type type){
        super(xPos, yPos, width, height, view);
        this.image = image;
        this.type = type;
    }

    public void drawButton(Graphics g, JLayeredPane pane){
        super.drawButton(g, pane);

        int xImagePos = xPos + (Constants.upgradePawnBoxWidth - image.getWidth()) / 2;
        int yImagePos = yPos + (Constants.upgradePawnBoxHeight - image.getHeight()) / 2;
        g.drawImage(image, xImagePos, yImagePos, pane);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xMouse = e.getX();
        int yMouse = e.getY();
        if (!mouseIsOverButton(xMouse, yMouse)) {return;}

        if (e.getButton() == MouseEvent.BUTTON1) {
            view.controller.model.upgradePawn(type);
            view.controller.setGameState(GameState.ACTIVE_GAME);
            view.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
