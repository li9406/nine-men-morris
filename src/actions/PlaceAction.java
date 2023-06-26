package actions;

import components.GameBoard;
import components.Piece;
import players.Player;

/**
 * Class representing a Place Action - Placing a new piece at an empty position
 */
public class PlaceAction extends PieceAction {

    // Variable: New Piece
    private Piece piece;

    /**
     * Create an instance of a PlaceAction with the given parameters
     * @param to Target location of position to place piece at
     * @param piece The piece
     */
    public PlaceAction(int to, Piece piece) {
        super(to, -1);
        this.piece = piece;
    }

    /**
     * Override of method to perform the Place Action
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     */
    public void performAction(GameBoard gameBoard, Player player) {
        gameBoard.addPieceToPosition(this.to, this.piece);
        player.reducePiecesToPlace();
        player.increasePiecesOnBoard();
    }

    /**
     * Override of Validation method to check if the PlaceAction is Valid.
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     * @return True if the PlaceAction is valid, otherwise False
     */
    public boolean isValid(GameBoard gameBoard, Player player) {
        return !gameBoard.getPositionHasPiece(this.to) && player.getPiecesToPlace() >= 1;
    }
}
