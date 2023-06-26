package actions;

import components.GameBoard;
import components.Position;
import players.Player;

import java.util.List;

/**
 * Abstract class representing all types of actions that can be performed on Piece in the 9MM Game.
 * It provides common functionalities that the subclasses can inherit and extend based on their specific action.
 */
public abstract class PieceAction implements Action {

    // Variables - Source and Destination used in all action classes.
    /**
     * Destination (Gameboard Index to place the piece "to"). Set to -1, if not in use by an action.
     * Set to protected so that it's subclasses can access it.
     */
    protected int to = -1;
    /**
     * Source (Gameboard Index to fetch the piece "from"). Set to -1, if not in use by an action.
     * Set to protected so that it's subclasses can access it.
     */
    protected int from = -1;

    /**
     * Creates a PieceAction object
     */
    public PieceAction(int to, int from) {
        this.to = to;
        this.from = from;
    }

    /**
     * Checks if the action performed on a piece is valid or not.
     * @param gameBoard The Game Board the action is performed on
     * @param player The Player performing the action
     * @return True if it is valid, false otherwise.
     */
    public abstract boolean isValid(GameBoard gameBoard, Player player);

    /**
     * Separates a mill (a set of 3 pieces) back into 3 separate pieces, at a given position index
     * (which is known by the action).
     * @param gameBoard The current game board.
     */
    public void separateMillSet(GameBoard gameBoard){
        System.out.println("Is Part of Mill");
        List<Position[]> millSets = gameBoard.getMillSetsOfPosition(from);
        for (Position[] mill : millSets) {
            removeFromMill(mill[0], gameBoard);
            removeFromMill(mill[1], gameBoard);
            removeFromMill(mill[2], gameBoard);
        }

    }

    /**
     * Method to remove a given position from a Mill.
     * @param position A position that is part of a mill.
     * @param gameBoard The current game board.
     */
    protected void removeFromMill(Position position, GameBoard gameBoard){
        System.out.println("Id != From: " + (position.getId() != from));
        System.out.println("Has piece: " + (gameBoard.getPositionHasPiece(position.getId())));
        System.out.println("CheckMill: " + (gameBoard.checkMills(position.getId())));

        if (position.getId() != from
                && gameBoard.getPositionHasPiece(position.getId())
                && !gameBoard.checkMills(position.getId())) {
            position.getPiece().setPartOfMill(false);
        }
    }
}
