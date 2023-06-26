package actions;

import components.GameBoard;
import players.Player;

/**
 * Interface for all Action related behaviors, performed by Players in the 9MM Game.
 * It provides a common interface for different types of actions. Classes that implements this interface should provide
 * a concrete implementation for the performAction method which represents the action to be executed by the players
 * during the game.
 */
public interface Action {

    /**
     * Perform the action
     *
     * @param board The Game Board the action is performed on
     * @param player The Player performing the action
     */
    void performAction(GameBoard board, Player player);
}
