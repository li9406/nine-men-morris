package players;

import actions.HintAction;
import components.GameBoard;
import components.Position;
import components.Team;
import constants.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represent a Computer Player, that can independently makes moves by itself.
 * It can be used in Tutorial Game, or in a Real Game where you are an introvert, and have no friends to play against.\
 *
 *  Difficulty for determining Bot Heuristic:
 *  1 - Random Moves
 *  2 - Basic Heuristic
 */
public class ComputerPlayer extends Player{

    // The position of the piece that is selected to perform the next move
    int selectedPos = -1;

    // The difficulty level
    int difficulty = 1;

    // Constructor
    /**
     * Creates a new ComputerPlayer object with the given parameters
     *
     * @param id The id (unique identifier) of the ComputerPlayer
     * @param team The team of the ComputerPlayer
     */
    public ComputerPlayer(String id, Team team){
        super(id, team);
    }

    /**
     * Get the position of the piece that is selected to perform the next move
     *
     * @return The index/id of the position in the game board
     */
    public int getSelectedPos() {
        return selectedPos;
    }

    /**
     * Compute the next move based on the current state of the game using HintAction.
     * HintAction gives a list of all legal moves that a player can perform as their next move based on the current
     * state of the game. Computer player will randomly select a move from all the legal moves.
     *
     * @param gameState The current state of the game
     * @param gameBoard The game board
     * @return A Position object that is selected as the next move
     */
    public Position computeNextMove(GameState gameState, GameBoard gameBoard){
        HintAction hintAction = new HintAction(gameState);
        // Find all the legal moves the player can perform based on the game state
        hintAction.performAction(gameBoard, this);

        // Get the position of the piece that is selected to perform the next move
        selectedPos = hintAction.getSelectedPos();

        // Randomly select a move from all the legal moves, i.e. the new position to move the selected piece
        Random random = new Random();
        return hintAction.getLegalMoves().get(random.nextInt(hintAction.getLegalMoves().size()));
    }

    /**
     * Return the boolean True because this player is a computer.
     *
     * @return True
     */
    public boolean isComputer() {
        return true;
    }
}
