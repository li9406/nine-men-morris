package model;

import components.GameBoard;
import components.Piece;
import components.Team;
import control.GameManager;
import players.*;
import view.GamePanel;

import javax.swing.*;

/**
 * This class represents a Tutorial Game
 * A tutorial game is in which a Human Player plays against a Computer Player and the Human Player is taught how to play the game
 */
public class TutorialGame extends Game{

    // Key String
    private String key = "FIT3077TutorialGame";

    /**
     * Creates a new TutorialGame object with the given parameters
     * @param player1 An object of Player class that signifies player1
     * @param player2 An object of Player class that signifies player2
     */
    public TutorialGame(Player player1, Player player2){
        super(player1, player2);
    }

    /**
     * Creates a new TutorialGame object with the given parameters
     * @param gameManager An object of GameManager class
     * @param player1 An object of Player class that signifies player1
     * @param player2 An object of Player class that signifies player2
     */
    public TutorialGame(GameManager gameManager, Player player1, Player player2){
        super(gameManager,player1, player2);
        this.gameManager.setGameKey(key);
    }

    /**
     * Method to start the tutorial game.
     * @param gamePanel The game panel, i.e. GUI of the game
     */
    @Override
    public void start(GamePanel gamePanel){
        gameManager.autoHint();
    }

}
