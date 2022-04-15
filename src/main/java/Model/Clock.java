package Model;

import Controller.Controller;
import Controller.GameState;
import Main.Constants;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Felix Kaasa
 */
public class Clock implements ActionListener, Cloneable {

    private boolean enabled = false;
    private int whiteClock_Seconds;
    private int blackClock_Seconds;
    Team currentPlayer;
    private Timer timer;
    private View view;
    private Controller controller;

    public Clock(View view, Controller controller){
        whiteClock_Seconds = Constants.TIME_MINUTES * 60;
        blackClock_Seconds = Constants.TIME_MINUTES * 60;
        this.currentPlayer = Team.WHITE;
        timer = new Timer(1000, this);
        this.view = view;
        this.controller = controller;
    }

    /**
     * this is only to be used for testing
     * @param TESTING set to true if the clock should be used for testing. (this clock will not work, but for testing it may not need to work)
     */
    public Clock(boolean TESTING) {
        whiteClock_Seconds = Constants.TIME_MINUTES * 60;
        blackClock_Seconds = Constants.TIME_MINUTES * 60;
        this.currentPlayer = Team.WHITE;
        timer = new Timer(1000, this);
        this.view = new View();
        this.controller = new Controller(view);
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
        if (currentPlayer == Team.WHITE){
            whiteClock_Seconds += Constants.TIME_ADDED_EACH_MOVE_SECONDS;
            if (whiteClock_Seconds > Constants.TIME_MINUTES * 60){ whiteClock_Seconds = Constants.TIME_MINUTES * 60;}
        } else {
            blackClock_Seconds += Constants.TIME_ADDED_EACH_MOVE_SECONDS;
            if (blackClock_Seconds > Constants.TIME_MINUTES * 60){ blackClock_Seconds = Constants.TIME_MINUTES * 60;}
        }

        currentPlayer = (currentPlayer == Team.WHITE ? Team.BLACK : Team.WHITE);
    }

    /**
     * method to start the timer.
     */
    public void start(){
        if (enabled){return;}
        timer.start();
        enabled = true;
        if (currentPlayer == Team.WHITE) {
            whiteClock_Seconds -= Constants.TIME_ADDED_EACH_MOVE_SECONDS;
        } else {
            blackClock_Seconds -= Constants.TIME_ADDED_EACH_MOVE_SECONDS;
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get the time of the player that is active.
     * @return time left.
     */
    public int getTime(){
        return (currentPlayer == Team.WHITE ? whiteClock_Seconds : blackClock_Seconds);
    }

    /**
     * Get the time of a given player
     * @param team player who's time you want
     * @return time left for given player
     */
    public int getTime(Team team){
        return (team == Team.WHITE ? whiteClock_Seconds : blackClock_Seconds);
    }

    /**
     * method to set the clock time to a specific value
     * @param team the team for which the clock should be set
     * @param seconds the time the clock should be set to (this time overwrites the current clock time)
     */
    public void setTime(Team team, int seconds){
        if (team == Team.WHITE) {
            whiteClock_Seconds = seconds;
        } else{
            blackClock_Seconds = seconds;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!enabled){return;}
        if (controller.getGameState() != GameState.ACTIVE_GAME) {return;}
        if (currentPlayer == Team.WHITE){
            whiteClock_Seconds--;
            if (whiteClock_Seconds < 0){
                whiteClock_Seconds = 0;
                timer.stop();
                controller.setGameState(GameState.TIME_OUT);
            }
        } else if (currentPlayer == Team.BLACK){
            blackClock_Seconds--;
            if (blackClock_Seconds < 0){
                blackClock_Seconds = 0;
                timer.stop();
                controller.setGameState(GameState.TIME_OUT);
            }
        }
        view.repaint();
        timer.setDelay(1000);
    }

    @Override
    public Clock clone() {
        try {
            Clock clone = (Clock) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
