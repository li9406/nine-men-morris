package main;
// Imports
import components.GameBoard;
import components.Piece;
import components.Team;
import control.GameManager;
import players.HumanPlayer;
import players.Player;
import view.Display;
import model.*;
import view.GamePanel;

/**
 * Main Driver code (PSVM) to start and run the 9MM Game.
 *
 */
public class Main {
    public static void main(String[] args) {

        // The size of the desktop
        int desktopSize = 16;

        // The scale
        int scale = 3;

        // The maximum screen column
        int maxScreenCol = 16;

        // The maximum screen row
        int maxScreenRow = 12;

        // The width of the screen
        int screenWidth = desktopSize * scale * maxScreenCol;

        // The height of the screen
        int screenHeight = desktopSize * scale * maxScreenRow;

        // Display to display the Front End UI of The Game.
        Display display = new Display(screenHeight, screenWidth);

        // Initialize Game
//        Player player1 = new HumanPlayer("player1", Team.WHITE);
//        Player player2 = new HumanPlayer("player2", Team.BLACK);
//        GamePanel gamePanel = new GamePanel(screenWidth, screenHeight, display);
//        GameBoard gameBoard = new GameBoard();
//        GameManager gameManager = new GameManager(gamePanel, gameBoard, player1, player2);
//        RealGame realGame = new RealGame(gameManager, player1, player2);

        // Position Tests
//        Position[] positions = new Position[5];
//        positions[0] = new Position(0,0, 1);
//        positions[1] = new Position(3,0, 2);
//        positions[2] = new Position(6,0, 3);
//        positions[3] = new Position(0,3, 4);
//        positions[4] = new Position(1,1, 5);

        // positions[0].setPiece(new Piece(Team.WHITE));
//
//        positions[1].setPiece(new Piece(Team.BLACK));
//
//        positions[2].setPiece(new Piece(Team.BLACK));
//
//        positions[3].setPiece(new Piece(Team.WHITE));
//
//        positions[4].setPiece(new Piece(Team.BLACK));

//        display.updateCanvas(positions);
    }
}
