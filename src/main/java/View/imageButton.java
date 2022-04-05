package View;

import Controller.GameState;
import Main.Constants;
import Model.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class imageButton extends Button{

    public final BufferedImage image;
    public final Type type;
    public final ButtonAction action;

    /**
     * this constructor is used for genereal image buttons with lambda functions.
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param view the view (needed to get the gameState)
     * @param image the image that will be drawn on the button.
     * @param action a lambda function that will be executed when the button is clicked.
     */
    public imageButton(int xPos, int yPos, int width, int height, View view, BufferedImage image, ButtonAction action){
        super(xPos, yPos, width, height, view);
        this.image = image;
        this.action = action;
        this.type = null;
    }

    /**
     * this constructor is used when drawing pawn upgrade buttons
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param view the view (needed to get the gameState)
     * @param image the image that will be drawn on the button.
     * @param type the type of the piece that the pawn will upgrade to.
     */
    public imageButton(int xPos, int yPos, int width, int height, View view, BufferedImage image, Type type){
        super(xPos, yPos, width, height, view);
        this.image = image;
        this.type = type;
        this.action = null;
    }


    public void drawButton(Graphics g){
        super.drawButton(g);
        int xImagePos = xPos + (width - image.getWidth()) / 2;
        int yImagePos = yPos + (height - image.getHeight()) / 2;
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
            if (type != null) {
                view.controller.model.upgradePawn(type);
                view.controller.setGameState(GameState.ACTIVE_GAME);
                //view.controller.ai.createMove(); //TODO AI bug
                view.repaint();
            } else if (action != null){
                action.executeAction();
            }
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
