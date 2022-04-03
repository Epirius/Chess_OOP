package View;

import Controller.GameState;
import Main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * @author Felix Kaasa
 */
public abstract class Button implements MouseListener {
    protected final int xPos;
    protected final int yPos;
    protected final int width;
    protected final int height;
    protected Color previousColor;
    protected JLayeredPane pane;
    protected GameState gameStateWhenCreated;
    protected View view;

    /**
     * an Abstract class for buttons.
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param view the view (needed to the controller/model)
     */
    public Button(int xPos, int yPos, int width, int height, View view) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.pane = new JLayeredPane();
        gameStateWhenCreated = view.controller.getGameState();
        this.view = view;
        view.addMouseListener(this);

    }

    public void drawButton(Graphics g){
        if (!isVisible()){return;}
        previousColor = g.getColor();
        g.setColor(Constants.BUTTON);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(previousColor);
    }

    /**
     * method to get the start corner coordinates for the button
     * @return int[] with x and y coordinates
     */
    public int[] getStartPosition(){ //TODO DELETE IF NOT IN USE
        return new int[]{xPos, yPos};
    }

    /**
     * method to get the end corner coordinates for the button
     * @return int[] with the x and y coordinates
     */
    public int[] getEndPosition(){ //TODO DELETE IF NOT IN USE
        return new int[]{xPos + width, yPos + height};
    }

    /**
     * method that checks if the gamestate is currently the same as when the button was created.
     * @return boolean
     */
    public boolean isVisible(){
        return gameStateWhenCreated == view.controller.getGameState();
    }

    /**
     * method to check if the mouse is over the button (and the button is visible)
     * @param xMouse x coordinate of the mouse
     * @param yMouse y coordinate of the mouse
     * @return true if mouse over the button and the button is visible
     */
    public boolean mouseIsOverButton(int xMouse, int yMouse){
        return (isVisible() && xMouse > xPos && xMouse < xPos + width && yMouse > yPos && yMouse < yPos + height);
    }

}
