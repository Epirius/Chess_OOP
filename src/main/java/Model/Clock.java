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
        this.currentPlayer = Team.BLACK;
        timer = new Timer(1000, this);
        this.view = view;
        this.controller = controller;
    }

    public Team getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer(){
        currentPlayer = (currentPlayer == Team.WHITE ? Team.BLACK : Team.WHITE);
    }

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
