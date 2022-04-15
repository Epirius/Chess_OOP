package View;

import java.awt.*;
import java.awt.event.MouseEvent;

import static View.GraphicHelperMethods.drawCenteredString;

/**
 * @author Felix Kaasa
 */
public class TextButton extends Button{
    ButtonAction action;
    String text;

    /**
     * method to draw a button with text and an action
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param text text on the button
     * @param view the view (needed to get the gameState)
     * @param action a lambda function that will be executed when the button is clicked.
     */
    public TextButton(int xPos, int yPos, int width, int height, String text, View view, ButtonAction action) {
        super(xPos, yPos, width, height, view, false);
        this.action = action;
        this.text = text;
    }

    /**
     * method used when you want the text button to stay still relative to the right/bottom screen edge when resizing
     * @param xPos start position of the button.
     * @param yPos start position of the button.
     * @param width width of the button
     * @param height height of the button
     * @param text text on the button
     * @param view the view (needed to get the gameState)
     * @param stickToEdgeOfScreen True if you want the button to stay still relative to the screen edge when resizing the screen
     * @param action a lambda function that will be executed when the button is clicked.
     */
    public TextButton(int xPos, int yPos, int width, int height, String text, View view, boolean stickToEdgeOfScreen, ButtonAction action) {
        super(xPos, yPos, width, height, view, stickToEdgeOfScreen);
        this.action = action;
        this.text = text;
    }

    public void drawButton(Graphics g, Color buttonColor) {
        super.drawButton(g, buttonColor);
        if (!isVisible()){return;}
        Font font = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        drawCenteredString(g, text, currentXPos, currentYPos, width, height);
        g.setColor(previousColor);
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
            action.executeAction();
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
