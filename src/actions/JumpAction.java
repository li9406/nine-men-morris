package actions;

import components.GameBoard;
import components.Piece;
import players.Player;

/**
 *  Class that represents a Jump Action - Jumping (fly) pieces from one position to any other position on the board.
 *  Inheritance Ref: https://stackoverflow.com/questions/16992452/how-to-use-a-variable-from-a-superclass-to-a-subclass
 */
public class JumpAction extends PieceAction {

    // Constructor
    /**
     * Create an instance of a SlideAction with the given parameters.
     * @param from Starting location of position
     * @param to Destination location of position
     */
    public JumpAction(int from, int to) {
        super(to, from);
    }

    /**
     * Override of method to perform the Jump Action
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
     * Method to check validity of the jumpAction
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     * @return True if JumpAction is valid, otherwise False
     */
    @Override
    public boolean isValid(GameBoard gameBoard, Player player) {
        /* Conditions:
        * 1. Destination (to) has no pieces.
        * 2. Piece at Source (from) has a piece belonging to that player
        * 3. The player only has 3 or less of their pieces left on the board
        */
        return !gameBoard.getPositionHasPiece(to)
                && gameBoard.getPieceAtPosition(from).getTeam() == player.getTeam()
                && player.getPiecesToPlace() <= 3;
    }
}
