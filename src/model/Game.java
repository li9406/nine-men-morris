package model;

import components.GameBoard;
import control.GameManager;
import players.*;
import view.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This abstract class represents a generic 9MM Game
 * The game can either be a Real Game or a Tutorial Game
 */
public abstract class Game {

    // Players - Setting an external variable in case num of players change.
    protected int maxNumPlayers = 2;
    // The Player Array
    protected final Player[] players = new Player[maxNumPlayers];
    // The Game's Game Manager.
    protected GameManager gameManager;

    /**
     * Creates a Game with the given GameManager object and the players
     * @param player1 An object of Player class that signifies player1
     * @param player2 An object of Player class that signifies player2
     */
    public Game(GameManager gameManager, Player player1, Player player2){
        this.gameManager = gameManager;
        this.players[0] = player1;
        this.players[1] = player2;
    }

    /**
     * Creates a Game with the given GameManager object and the players
     * @param player1 An object of Player class that signifies player1
     * @param player2 An object of Player class that signifies player2
     */
    public Game(Player player1, Player player2){
        this.players[0] = player1;
        this.players[1] = player2;
    }

    /**
     * A setter for the maximum number of players in a game
     * @param maxNumPlayers The number of maximum players
     */
    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    /**
     * Manages players in Player Array
     * @param player An object of Player class
     */
    public void addNewPlayer(Player player) {
        this.players[players.length - 1] = player;
    }

    /**
     * Get all the players in the game.
     * @return A list of Player objects representing the players in the game
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Removes player from the Player Array
     * @param i The index of the player to be removed from the list
     */
    public void removePlayer(int i){this.players[i] = null;}

    /**
     * Starts the game by initializing and setting up all the necessary components of the game
     *
     * @param gamePanel The game panel, i.e. GUI of the game
     */
    public void start(GamePanel gamePanel){
    }

}
