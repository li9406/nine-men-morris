package components;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the game board in 9MM which has 24 positions.
 * The game board will be viewed as a 6x6 grid where the positions are the points in the grid.
 */
public class GameBoard {

    // The 24 positions of the game board
    private Position[] positions = new Position[24];

    // The 16 possible combinations of mills
    public ArrayList<List<Position[]>> mills = new ArrayList<>();

    // Constructor
    /**
     * Creates a new GameBoard object.
     */
    public GameBoard(){
        initBoard();
    }

    /**
     * Initialize all the 24 positions of the game board and all the 16 possible combinations of mills.
     */
    private void initBoard() {
        // Add all 24 positions
        positions[0] = new Position(0,0,0);
        positions[1] = new Position(1,3,0);
        positions[2] = new Position(2,6,0);
        positions[3] = new Position(3,1,1);
        positions[4] = new Position(4,3,1);
        positions[5] = new Position(5,5,1);
        positions[6] = new Position(6,2,2);
        positions[7] = new Position(7,3,2);
        positions[8] = new Position(8,4,2);
        positions[9] = new Position(9,0,3);
        positions[10] = new Position(10,1,3);
        positions[11] = new Position(11,2,3);
        positions[12] = new Position(12,4,3);
        positions[13] = new Position(13,5,3);
        positions[14] = new Position(14,6,3);
        positions[15] = new Position(15,2,4);
        positions[16] = new Position(16,3,4);
        positions[17] = new Position(17,4,4);
        positions[18] = new Position(18,1,5);
        positions[19] = new Position(19,3,5);
        positions[20] = new Position(20,5,5);
        positions[21] = new Position(21,0,6);
        positions[22] = new Position(22,3,6);
        positions[23] = new Position(23,6,6);

        // Add all adjacent positions of each position
        positions[0].addNeighbour(positions[1]);
        positions[0].addNeighbour(positions[9]);
        positions[1].addNeighbour(positions[0]);
        positions[1].addNeighbour(positions[2]);
        positions[1].addNeighbour(positions[4]);
        positions[2].addNeighbour(positions[1]);
        positions[2].addNeighbour(positions[14]);
        positions[3].addNeighbour(positions[4]);
        positions[3].addNeighbour(positions[10]);
        positions[4].addNeighbour(positions[1]);
        positions[4].addNeighbour(positions[3]);
        positions[4].addNeighbour(positions[5]);
        positions[4].addNeighbour(positions[7]);
        positions[5].addNeighbour(positions[4]);
        positions[5].addNeighbour(positions[13]);
        positions[6].addNeighbour(positions[7]);
        positions[6].addNeighbour(positions[11]);
        positions[7].addNeighbour(positions[6]);
        positions[7].addNeighbour(positions[8]);
        positions[7].addNeighbour(positions[4]);
        positions[8].addNeighbour(positions[7]);
        positions[8].addNeighbour(positions[12]);
        positions[9].addNeighbour(positions[0]);
        positions[9].addNeighbour(positions[21]);
        positions[9].addNeighbour(positions[10]);
        positions[10].addNeighbour(positions[9]);
        positions[10].addNeighbour(positions[11]);
        positions[10].addNeighbour(positions[3]);
        positions[10].addNeighbour(positions[18]);
        positions[11].addNeighbour(positions[10]);
        positions[11].addNeighbour(positions[6]);
        positions[11].addNeighbour(positions[15]);
        positions[12].addNeighbour(positions[8]);
        positions[12].addNeighbour(positions[13]);
        positions[12].addNeighbour(positions[17]);
        positions[13].addNeighbour(positions[12]);
        positions[13].addNeighbour(positions[14]);
        positions[13].addNeighbour(positions[5]);
        positions[13].addNeighbour(positions[20]);
        positions[14].addNeighbour(positions[13]);
        positions[14].addNeighbour(positions[2]);
        positions[14].addNeighbour(positions[23]);
        positions[15].addNeighbour(positions[16]);
        positions[15].addNeighbour(positions[11]);
        positions[16].addNeighbour(positions[15]);
        positions[16].addNeighbour(positions[17]);
        positions[16].addNeighbour(positions[19]);
        positions[17].addNeighbour(positions[12]);
        positions[17].addNeighbour(positions[16]);
        positions[18].addNeighbour(positions[19]);
        positions[18].addNeighbour(positions[10]);
        positions[19].addNeighbour(positions[18]);
        positions[19].addNeighbour(positions[20]);
        positions[19].addNeighbour(positions[16]);
        positions[19].addNeighbour(positions[22]);
        positions[20].addNeighbour(positions[13]);
        positions[20].addNeighbour(positions[19]);
        positions[21].addNeighbour(positions[9]);
        positions[21].addNeighbour(positions[22]);
        positions[22].addNeighbour(positions[21]);
        positions[22].addNeighbour(positions[23]);
        positions[22].addNeighbour(positions[19]);
        positions[23].addNeighbour(positions[22]);
        positions[23].addNeighbour(positions[14]);

        // Add all 16 possible combinations of mills
        // possible mills from position 0
        List<Position[]> millsInsertion = new ArrayList<>();
        Position[] var = {positions[0],positions[1],positions[2]};
        millsInsertion.add(var);
        Position[] var1 = {positions[0],positions[9],positions[21]};
        millsInsertion.add(var1);
        mills.add(millsInsertion);

        // possible mills from position 1
        List<Position[]> millsInsertion1 = new ArrayList<>();
        Position[] var2 = {positions[1],positions[4],positions[7]};
        millsInsertion1.add(var);
        millsInsertion1.add(var2);
        mills.add(millsInsertion1);

        // possible mills from position 2
        List<Position[]> millsInsertion2 = new ArrayList<>();
        Position[] var3 = {positions[2],positions[14],positions[23]};
        millsInsertion2.add(var);
        millsInsertion2.add(var3);
        mills.add(millsInsertion2);

        // possible mills from position 3
        List<Position[]> millsInsertion3 = new ArrayList<>();
        Position[] var4 = {positions[3],positions[4],positions[5]};
        millsInsertion3.add(var4);
        Position[] var5 = {positions[3],positions[10],positions[18]};
        millsInsertion3.add(var5);
        mills.add(millsInsertion3);

        // possible mills from position 4
        List<Position[]> millsInsertion4 = new ArrayList<>();
        millsInsertion4.add(var2);
        millsInsertion4.add(var4);
        mills.add(millsInsertion4);

        // possible mills from position 5
        List<Position[]> millsInsertion5 = new ArrayList<>();
        millsInsertion5.add(var4);
        Position[] var6 = {positions[5],positions[13],positions[20]};
        millsInsertion5.add(var6);
        mills.add(millsInsertion5);

        // possible mills from position 6
        List<Position[]> millsInsertion6 = new ArrayList<>();
        Position[] var7 = {positions[6],positions[7],positions[8]};
        millsInsertion6.add(var7);
        Position[] var8 = {positions[6],positions[11],positions[15]};
        millsInsertion6.add(var8);
        mills.add(millsInsertion6);

        // possible mills from position 7
        List<Position[]> millsInsertion7 = new ArrayList<>();
        millsInsertion7.add(var7);
        millsInsertion7.add(var2);
        mills.add(millsInsertion7);

        // possible mills from position 8
        List<Position[]> millsInsertion8 = new ArrayList<>();
        millsInsertion8.add(var7);
        Position[] var9 = {positions[8],positions[12],positions[17]};
        millsInsertion8.add(var9);
        mills.add(millsInsertion8);

        // possible mills from position 9
        List<Position[]> millsInsertion9 = new ArrayList<>();
        Position[] var10 = {positions[9],positions[10],positions[11]};
        millsInsertion9.add(var10);
        millsInsertion9.add(var1);
        mills.add(millsInsertion9);

        // possible mills from position 10
        List<Position[]> millsInsertion10 = new ArrayList<>();
        millsInsertion10.add(var5);
        millsInsertion10.add(var10);
        mills.add(millsInsertion10);

        // possible mills from position 11
        List<Position[]> millsInsertion11 = new ArrayList<>();
        millsInsertion11.add(var10);
        millsInsertion11.add(var8);
        mills.add(millsInsertion11);

        // possible mills from position 12
        List<Position[]> millsInsertion12 = new ArrayList<>();
        Position[] var11 = {positions[12],positions[13],positions[14]};
        millsInsertion12.add(var11);
        millsInsertion12.add(var9);
        mills.add(millsInsertion12);

        // possible mills from position 13
        List<Position[]> millsInsertion13 = new ArrayList<>();
        millsInsertion13.add(var11);
        millsInsertion13.add(var6);
        mills.add(millsInsertion13);

        // possible mills from position 14
        List<Position[]> millsInsertion14 = new ArrayList<>();
        millsInsertion14.add(var11);
        millsInsertion14.add(var3);
        mills.add(millsInsertion14);

        // possible mills from position 15
        List<Position[]> millsInsertion15 = new ArrayList<>();
        millsInsertion15.add(var8);
        Position[] var12 = {positions[15],positions[16],positions[17]};
        millsInsertion15.add(var12);
        mills.add(millsInsertion15);

        // possible mills from position 16
        List<Position[]> millsInsertion16 = new ArrayList<>();
        Position[] var13 = {positions[16],positions[19],positions[22]};
        millsInsertion16.add(var13);
        millsInsertion16.add(var12);
        mills.add(millsInsertion16);

        // possible mills from position 17
        List<Position[]> millsInsertion17 = new ArrayList<>();
        millsInsertion17.add(var9);
        millsInsertion17.add(var12);
        mills.add(millsInsertion17);

        // possible mills from position 18
        List<Position[]> millsInsertion18 = new ArrayList<>();
        millsInsertion18.add(var5);
        Position[] var14 = {positions[18],positions[19],positions[20]};
        millsInsertion18.add(var14);
        mills.add(millsInsertion18);

        // possible mills from position 19
        List<Position[]> millsInsertion19 = new ArrayList<>();
        millsInsertion19.add(var13);
        millsInsertion19.add(var14);
        mills.add(millsInsertion19);

        // possible mills from position 20
        List<Position[]> millsInsertion20 = new ArrayList<>();
        millsInsertion20.add(var6);
        millsInsertion20.add(var14);
        mills.add(millsInsertion20);

        // possible mills from position 21
        List<Position[]> millsInsertion21 = new ArrayList<>();
        Position[] var15 = {positions[21],positions[22],positions[23]};
        millsInsertion21.add(var15);
        millsInsertion21.add(var1);
        mills.add(millsInsertion21);

        // possible mills from position 22
        List<Position[]> millsInsertion22 = new ArrayList<>();
        millsInsertion22.add(var15);
        millsInsertion22.add(var13);
        mills.add(millsInsertion22);

        // possible mills from position 23
        List<Position[]> millsInsertion23 = new ArrayList<>();
        millsInsertion23.add(var15);
        millsInsertion23.add(var3);
        mills.add(millsInsertion23);
    }

    // Getters
    /**
     * Get all the 24 positions of the game board.
     *
     * @return A new list of the positions of the game board
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     * Get a position of the game board at a given index
     *
     * @param i The index of the position in the game board
     * @return The Position object at the given index
     */
    public Position getPosition(int i){
        return this.positions[i];
    }

    /**
     * Check if the position at a given index contains a piece.
     *
     * @param i The index of a position in the game board
     * @return True if the position contains a piece, false otherwise
     */
    public boolean getPositionHasPiece(int i) {
        return getPositions()[i].hasPiece();
    }

    /**
     * Get the piece at the position at index i.
     *
     * @param i The index of a position in the game board
     * @return The piece
     */
    public Piece getPieceAtPosition(int i) {
        return getPositions()[i].getPiece();
    }

    // Position Add and Remove
    /**
     * Add a piece to the position at index i.
     *
     * @param i The index of a position in the game board
     * @param piece The piece to be added to the position
     */
    public void addPieceToPosition(int i, Piece piece) {
        this.positions[i].setPiece(piece);
    }

    /**
     * Remove a piece at the position at index i.
     *
     * @param i The index of a position in the game board
     */
    public void removePieceAtPosition(int i) {
        this.positions[i].removePiece();
    }

    /**
     * Get all the possible combinations of mills including the position at index i
     *
     * @param i The index of a position in the game board
     * @return A list of possible combinations of mills
     */
    public List<Position[]> getMillSetsOfPosition(int i) {
        return mills.get(i);
    }

    /**
     * Check if the position at a given index forms a mill.
     *
     * @param i The index of a position in the game board
     * @return True if a mill including the position at index i is formed, otherwise False
     */
    public boolean checkMills(int i) {
        List<Position[]> possibleMillsList = mills.get(i);
        boolean millMade = false;
        for (Position[] x: possibleMillsList){
            if (x[0].hasPiece() && x[1].hasPiece() && x[2].hasPiece()) {
                // Mill formed if all the pieces in a mill are of the same player
                if (x[0].getPiece().getTeam() == x[1].getPiece().getTeam() && x[1].getPiece().getTeam() == x[2].getPiece().getTeam()){
                    millMade = true;
                    x[0].getPiece().setPartOfMill(true);
                    x[1].getPiece().setPartOfMill(true);
                    x[2].getPiece().setPartOfMill(true);
                    break;
                }
            }
        }
        return millMade;
    }

    /**
     * Check if all the opponent pieces are a part of a mill.
     *
     * @param i The index of a position in the game board
     * @return True if all the opponent pieces are a part of a mill, otherwise False
     */
    public boolean checkAllOpponentPiecesInMill(int i) {
        Team team = positions[i].getPiece().getTeam();
        for (Position x: positions) {
            if (x.hasPiece()){
                if (x.getPiece().getTeam() != team){
                    if (!x.getPiece().isPartOfMill()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
