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
    protected int currentXPos;
    protected int currentYPos;
    private boolean stickToEdgeOfScreen = false;

    protected Color previousColor;
    protected JLayeredPane pane;
    protected GameState gameStateWhenCreated;
    protected View view;
    protected boolean updatePosition = false;

    /**
     * an Abstract class for buttons.
     * NB! buttons can not be created directly each frame, because the addMouseListner will cause a stackOverflowError.
     * So if you want to create a button, it has to be reused instead of created each frame.
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param view the view (needed to the controller/model)
     */
    public Button(int xPos, int yPos, int width, int height, View view, boolean stickToEdgeOfScreen) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.currentXPos = xPos;
        this.currentYPos = yPos;
        this.stickToEdgeOfScreen = stickToEdgeOfScreen;
        this.pane = new JLayeredPane();
        gameStateWhenCreated = view.controller.getGameState();
        this.view = view;
        view.addMouseListener(this);

    }

    public void drawButton(Graphics g, Color buttonColor){
        if (!isVisible()){return;}
        if (updatePosition){
            updateButtonPosition();
        }
        previousColor = g.getColor();
        g.setColor(buttonColor);
        g.fillRect(currentXPos, currentYPos, width, height);
        g.setColor(previousColor);
        updatePosition = false;
    }

    /**
     * method to update the position of the button if the screen was resized
     */
    private void updateButtonPosition() {
        int extraScreenWidth = view.getWidth() - Constants.defaultDisplayWidth;
        int extraScreenHeight = view.getHeight() - Constants.defaultDisplayHeight;

        if (stickToEdgeOfScreen){
            this.currentXPos = this.xPos + extraScreenWidth;
            this.currentYPos = this.yPos + extraScreenHeight;
        } else {
            this.currentXPos = this.xPos + (int) (extraScreenWidth / 2);
            this.currentYPos = this.yPos + (int) (extraScreenHeight / 2);
        }
    }

    /**
     * method to get the end corner coordinates for the button
     * @return int[] with the x and y coordinates
     */
    public int[] getEndPosition(){
        return new int[]{currentXPos + width, currentYPos + height};
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
        return (isVisible() && xMouse > currentXPos && xMouse < currentXPos + width && yMouse > currentYPos && yMouse < currentYPos + height);
    }

}
