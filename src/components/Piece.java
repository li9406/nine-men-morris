package components;

/**
 * This class represents a piece in the 9MM.
 * Each piece has a team (color) and a fixed size in GUI.
 */
public class Piece {

    // The team (color) the piece belongs to
    private Team team;

    // Whether the piece is a part of a mill
    private boolean partOfMill;

    /**
     * Creates a new Piece object with the given team.
     *
     * @param team The team (color) of the piece
     */
    public Piece(Team team) {
        this.team = team;
    }

    /**
     * Returns the team (color) of the piece
     *
     * @return The team (color) of the piece
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Returns true if piece is part of a mill
     *
     * @return status of piece
     */
    public boolean isPartOfMill() {
        return this.partOfMill;
    }

    /**
     * Sets the piece to be a part of a mill
     *
     * @param partOfMill True if piece is part of mill or False if piece is not part of mill
     */
    public void setPartOfMill(boolean partOfMill) {
        this.partOfMill = partOfMill;
    }

}
