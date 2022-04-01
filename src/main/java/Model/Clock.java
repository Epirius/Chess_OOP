package Model;

import Controller.Controller;
import Main.Constants;
import View.View;
import Controller.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Felix Kaasa
 */
public class Clock implements ActionListener {

    boolean started = false;
    private int whiteClock;
    private int blackClock;
    Team currentPlayer;
    Timer timer;
    View view;
    Controller controller;

    public Clock(View view, Controller controller){
        whiteClock = Constants.TIME_SECONDS;
        blackClock = Constants.TIME_SECONDS;
        this.currentPlayer = Team.WHITE;
        timer = new Timer(1000, this);
        this.view = view;
        this.controller = controller;
    }

    /**
     * method to get the player who the clock is currently focusing on.
     * @return the team of the player.
     */
    public Team getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * method to switch which players clock is ticking down.
     * it also adds time to the current players clock if that option is set in Constants.
     */
    public void nextPlayer(){
        // adding time to the current players clock (if the option in constants is not set to 0.)
        if (currentPlayer == Team.WHITE){
            whiteClock += Constants.TIME_ADDED_EACH_MOVE_SECONDS;
        } else {
            blackClock += Constants.TIME_ADDED_EACH_MOVE_SECONDS;
        }

        // updating the current player.
        currentPlayer = (currentPlayer == Team.WHITE ? Team.BLACK : Team.WHITE);
    }

    /**
     * method to start the timer.
     */
    public void start(){
        if (started){return;}
        timer.start();
        started = true;
    }

    /**
     * Get the time of the player that is active.
     * @return time left.
     */
    public int getTime(){
        return (currentPlayer == Team.WHITE ? whiteClock : blackClock);
    }

    /**
     * Get the time of a given player
     * @param team player who's time you want
     * @return time left for given player
     */
    public int getTime(Team team){
        return (team == Team.WHITE ? whiteClock : blackClock);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller.getGameState() != GameState.ACTIVE_GAME) {return;}
        if (currentPlayer == Team.WHITE){
            whiteClock--;
            if (whiteClock < 0){
                whiteClock = 0;
                timer.stop();
                controller.gameState = GameState.TIME_OUT;
            }
        } else if (currentPlayer == Team.BLACK){
            blackClock--;
            if (blackClock < 0){
                blackClock = 0;
                timer.stop();
                controller.gameState = GameState.TIME_OUT;
            }
        }
        view.repaint();
        timer.setDelay(1000);
    }
}
