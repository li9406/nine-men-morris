package actions;

import components.GameBoard;
import components.Position;
import constants.GameState;
import players.Player;

import java.util.ArrayList;

/**
 * Class representing a Hint Action in the game.
 * It is used to provide the players with all the legal moves they can make as their next move.
 */
public class HintAction implements Action{

    // A position currently selected by the player
    int selectedPos;

    // The current state of the game
    GameState gameState;

    // A list that stores all the legal moves the player can make as their next move
    ArrayList<Position> legalMoves = new ArrayList<>();

    /**
     * Create an instance of a HintAction for the situation where the player did not select a position
     *
     * @param gameState the current state of the game
     */
    public HintAction(GameState gameState) {
        this.selectedPos = -1;
        this.gameState = gameState;
    }

    /**
     * Create an instance of a HintAction for the situation where the player selected a position
     *
     * @param selectedPos the position currently selected by the player
     * @param gameState the current state of the game
     */
    public HintAction(int selectedPos, GameState gameState) {
        this.selectedPos = selectedPos;
        this.gameState = gameState;
    }

    /**
     * Get all the legal moves the player can make as their next move.
     *
     * @return a list that contains all the legal moves
     */
    public ArrayList<Position> getLegalMoves() {
        return legalMoves;
    }

    /**
     * Get the position that is currently selected by the player.
     * If the player did not select a position, the position of the first piece that the player can perform a legal move
     * will be returned.
     *
     * @return a position that is currently selected
     */
    public int getSelectedPos() {
        return selectedPos;
    }

    /**
     * Find all the legal moves the player can make as their next move based on the current state of the game.
     * Override of method to perform the Hint Action.
     *
     * @param board The Game Board the action is performed on
     * @param player The Player performing the action
     */
    @Override
    public void performAction(GameBoard board, Player player) {
        // Placing pieces state
        if (gameState == GameState.PLACE) {
            // Get all positions on the game board that have no piece
            for (Position position: board.getPositions()) {
                if (!position.hasPiece()) {
                    legalMoves.add(position);
                }
            }
        }

        // Sliding pieces state
        else if (gameState == GameState.SLIDE) {
            // If player has chosen a piece they would like to slide
            if (selectedPos != -1) {
                // Get all the adjacent positions the piece can slide to
                for (Position neighbour: board.getPositions()[selectedPos].getNeighbours()) {
                    if (!neighbour.hasPiece()) {
                        legalMoves.add(neighbour);
                    }
                }
            }
            // If player did not choose a piece they would like to slide
            else {
                // Choose the first piece that they slide to a new position
                for (Position position: board.getPositions()) {
                    if (position.hasPiece() && position.getPiece().getTeam() == player.getTeam()) {
                        int count = 0;
                        // Get all the adjacency positions the piece can slide to
                        for (Position neighbour: position.getNeighbours()) {
                            if (!neighbour.hasPiece()) {
                                legalMoves.add(neighbour);
                                count++;
                            }
                        }
                        if (count > 0) {
                            this.selectedPos = position.getId();
                            break;
                        }
                    }
                }
            }
        }

        // Removing piece state
        else if (gameState == GameState.REMOVE){
            // Get all the positions of the opponent pieces that are not part of a mill
            for (Position position: board.getPositions()){
                if (position.hasPiece() && position.getPiece().getTeam() != player.getTeam() && !position.getPiece().isPartOfMill()){
                    legalMoves.add(position);
                }
            }
        }

        // Jumping pieces state
        else if (gameState == GameState.JUMP) {
            // If player has chosen a piece they would like to jump
            if (selectedPos != -1) {
                // Get all the positions that have no piece
                for (Position position : board.getPositions()) {
                    if (!position.hasPiece() && board.getPieceAtPosition(selectedPos).getTeam() == player.getTeam() && player.getPiecesToPlace() <= 3) {
                        legalMoves.add(position);
                    }
                }
            }
            // If player did not choose a piece they would like to jump
            else {
                // Choose the first piece that they can jump to a new position
                for (Position position : board.getPositions()) {
                    if (position.hasPiece() && position.getPiece().getTeam() == player.getTeam()) {
                        int count = 0;
                        // Get all the positions that have no piece
                        for (Position innerPosition : board.getPositions()) {
                            if (!innerPosition.hasPiece() && player.getPiecesToPlace() <= 3) {
                                legalMoves.add(innerPosition);
                                count++;
                            }
                        }
                        if (count > 0) {
                            this.selectedPos = position.getId();
                            break;
                        }
                    }
                }
            }
        }

    }
}
