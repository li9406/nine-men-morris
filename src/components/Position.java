package components;

import java.util.ArrayList;

/**
 * This class represents a position of the game board.
 * The position is represented as (x,y) in the game board.
 */
public class Position {

    // The id (unique)
    private int id;

    // The x coordinate
    private int x;

    // The y coordinate
    private int y;

    // The piece on this position
    private Piece piece;

    // The adjacency positions of this position
    private ArrayList<Position> neighbours = new ArrayList<>();

    // Constructor
    /**
     * Creates a new Position object with the given x and y coordinates.
     *
     * @param x The x coordinate of the position in the game board
     * @param y The y coordinate of the position in the game board
     */
    public Position(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // Getters
    /**
     * Gets the id of this position
     *
     * @return The id of this position
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the x coordinate of this position
     *
     * @return The x coordinate of this position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate of this position.
     *
     * @return The y coordinate of this position
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the piece on this position.
     *
     * @return The piece on this position
     */
    public Piece getPiece() {
        return this.piece;
    }

    // Adding and Removing Pieces
    /**
     * Sets a piece on this position.
     *
     * @param piece The piece to be set on this position
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Removes the piece on this position.
     */
    public void removePiece() { this.piece = null; }

    // Other Methods - Neighbours and Checks
    /**
     * Adds a Position as an adjacency position of this position.
     *
     * @param position The position to be added
     */
    public void addNeighbour(Position position){
        neighbours.add(position);
    }

    /**
     * Gets all the adjacency positions of this position.
     *
     * @return All the adjacency positions of this position
     */
    public ArrayList<Position> getNeighbours() {
        return neighbours;
    }

    /**
     * Check if there is a piece on this position.
     *
     * @return True if there is a piece, false otherwise
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    /**
     * Check if a position is an adjacency position
     *
     * @param position The position to be checked
     * @return True if the position is an adjacency position, false otherwise
     */
    public boolean hasNeighbour(Position position) {
        return neighbours.contains(position);
    }
}