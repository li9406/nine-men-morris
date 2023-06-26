package players;

// Imports
import components.Team;

/**
 * This class represents a human player.
 */
public class HumanPlayer extends Player{

    // The name of Player - Extra Unique Identifier.
    private String name;

    /**
     * Creates a new HumanPlayer object with the given id and team
     *
     * @param id The id of the human player
     * @param team The team the human player belongs to
     */
    public HumanPlayer(String id, Team team){
        super(id, team);
    }

    /**
     * Creates a new HumanPlayer object with the given id, team, and name.
     * @param id The id of the human player
     * @param team The team the human player belongs to
     * @param name The name of the human player
     */
    public HumanPlayer(String id, Team team, String name){
        super(id, team);
        setName(name);
    }

    // Getters

    /**
     * Retrieve the Player's name.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    // Setters
    /**
     * Allow player to change their name.
     * @param name The new name of The Player.
     */
    private void setName(String name) {
        this.name = name;
    }

}
