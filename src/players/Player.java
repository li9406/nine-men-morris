package players;

// Imports
import components.Team;

/**
 * This abstract class represents a generic player in 9MM.
 * The player can be a human or a bot (computer).
 */
public abstract class Player {

    // Unique identifier of the player
    private String id;

    // The team the player belongs to
    private Team team;

    // The number of pieces the player has placed onto the game board
    private int piecesOnBoard = 0;

    // The number of pieces the player can still place onto the game board
    private int piecesToPlace = 9;

    // Constructor
    /**
     * Creates a new Player with the given id and team
     *
     * @param id The id of the player
     * @param team The team the player belongs to
     */
    public Player(String id, Team team){
        setId(id);
        setTeam(team);
    }

    // Getters
    /**
     * Gets the id of the player.
     *
     * @return The id of the player
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the team the player belongs to.
     *
     * @return The team the player belongs to
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets the number of pieces the player has placed onto the game board.
     *
     * @return The number of pieces the player has placed onto the game board
     */
    public int getPiecesOnBoard() {
        return piecesOnBoard;
    }

    /**
     * Gets the number of pieces the player can still place onto the game board.
     *
     * @return The number of pieces the player can still place onto the game board
     */
    public int getPiecesToPlace() {
        return piecesToPlace;
    }

    // Setters
    /**
     * Sets the id of the player with a new id.
     *
     * @param id The new id of the player
     */
    private void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the team the player belongs to with a new team.
     *
     * @param team The new team the player belongs to
     */
    private void setTeam(Team team) {
        this.team = team;
    }

    // Other Methods - Managing Player's Pieces.
    /**
     * Increases the number of pieces the player has placed onto the game board.
     */
    public void increasePiecesOnBoard() {
        this.piecesOnBoard++;
    }

    /**
     * Reduces the number of pieces the player has on the game board.
     */
    public void reducePiecesOnBoard() {
        this.piecesOnBoard--;
    }

    /**
     * Reduces the number of pieces the player can still place onto the game board.
     */
    public void reducePiecesToPlace() {
        this.piecesToPlace--;
    }

    /**
     * Check if the player is a computer.
     *
     * @return True if the player is a computer, otherwise False
     */
    public boolean isComputer() {
        return false;
    }
}
