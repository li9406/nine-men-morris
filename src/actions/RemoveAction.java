package actions;

import components.GameBoard;
import components.Piece;
import players.Player;

/**
 * Class representing a Remove Action - Removing an opponent's a piece when a mill is formed
 */
public class RemoveAction extends PieceAction{

    /**
     * Create an instance of a RemoveAction with the given parameters.
     * @param from indicating the position of opposition's piece which the player wish to remove
     */
    public RemoveAction(int from) {
        super(-1, from);
    }

    /**
     * Override of method to perform the Remove Action
     * @param board The Game Board the action is performed on
     * @param player The Player performing the action
     */
    @Override
    public void performAction(GameBoard board, Player player) {
        board.removePieceAtPosition(this.from);
        player.reducePiecesOnBoard();
    }

    /**
     * Override of Validation method to check if the RemoveAction is Valid.
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     * @return True if the RemoveAction is valid, otherwise False
     */
    @Override
    public boolean isValid(GameBoard gameBoard, Player player) {
        return (gameBoard.getPositionHasPiece(this.from)) && (gameBoard.getPieceAtPosition(this.from).getTeam() != player.getTeam()) &&
                (!gameBoard.getPieceAtPosition(this.from).isPartOfMill());
    }
}
