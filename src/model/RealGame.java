package model;

import control.GameManager;
import players.Player;

/**
 * This class represents a Real Game
 * A real game is a game between either two Human Players or one Human Player and one Computer Player
 */
public class RealGame extends Game{

    /**
     * Creates a new RealGame object with the given parameters
     * @param player1 An object of Player class that signifies player1
     * @param player2 An object of Player class that signifies player2
     */
    public RealGame(GameManager gameManager, Player player1, Player player2) {
        super(gameManager, player1, player2);
    }

}
