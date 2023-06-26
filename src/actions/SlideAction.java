package actions;

import components.GameBoard;
import components.Piece;
import players.Player;

/**
 * Class representing a Slide Action - Sliding a Piece from one position to another.
 */
public class SlideAction extends PieceAction {

    // Constructor
    /**
     * Create an instance of a SlideAction with the given parameters.
     * @param from Starting location of position
     * @param to Destination location of position
     */
    public SlideAction(int from, int to) {
        super(to, from);
    }

    /**
     * Override of method to perform the Slide Action
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     */
    @Override
    public void performAction(GameBoard gameBoard, Player player) {

        // Add a new piece on destination position
        gameBoard.addPieceToPosition(this.to, new Piece(gameBoard.getPieceAtPosition(this.from).getTeam()));

        // Check if the piece from start position is a part of mill
        boolean isPartOfMill = gameBoard.getPieceAtPosition(from).isPartOfMill();

        // Remove the piece from start position
        gameBoard.removePieceAtPosition(from);

        if (isPartOfMill) {
            // Handle Removal of Piece from Mill
            super.separateMillSet(gameBoard);
        }
    }

    /**
     * Override of Validation method to check if the SlideAction is Valid.
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     * @return True if the SlideAction is valid, otherwise False
     */
    @Override
    public boolean isValid(GameBoard gameBoard, Player player) {
        /* Conditions:
         * 1. Destination (to) has no pieces.
         * 2. Piece at Source (from) has a piece belonging to that player
         * 3. The vacant destination (to) is a neighbour of the source (from)
         */
        return !gameBoard.getPositionHasPiece(this.to)
                && gameBoard.getPieceAtPosition(this.from).getTeam() == player.getTeam()
                && gameBoard.getPositions()[this.to].hasNeighbour(gameBoard.getPositions()[this.from]);
    }
}
