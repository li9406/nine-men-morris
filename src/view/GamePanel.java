package view;

// Imports
import components.Position;
import components.Team;
import constants.GameState;
import control.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a JPanel on the window initialized by Display.
 * All GUI elements in the game will be drawn by this class.
 *
 * References:
 * 1. fr0sty99, (2018). muehle. Retrieved from: https://github.com/fr0sty99/muehle/blob/master/Muehle_MVC_UML.pdf
 */
public class GamePanel extends JPanel {

    // UwU --- Variables (Attributes)--- UwU //

    // The width of the screen
    private final int screenWidth;

    // The height of the screen
    private final int screenHeight;

    // The size of the game board
    private final int gameBoardSize = 420;

    // The x position of the game board
    private final int gameBoardXPos;

    // The y position of the game board
    private final int gameBoardYPos;

    // The distance between the positions of the game board
    private final int distanceBetweenPos = 60;

    // The image of the game board
    private BufferedImage gameBoardImage;

    // The width of a piece in the piece bar
    private final int sideBarWidth = 70;

    // The height of a piece in the piece bar
    private final int sideBarHeight = 20;

    // The distance between the pieces in the piece bar
    private final int sideBarGap = 30;

    // The left margin of the game board
    private final int gameBoardMarginLeft = 20;

    // The right margin of the game board
    private final int gameBoardMarginRight = 20;

    // The positions of the game board
    private Position[] positions;

    // Variable that indicates if the panel needs to be redrawn
    private boolean redraw = false;

    // The number of white piece on hand left
    private int whitePiecesLeft = 9;

    // The number of black piece on hand left
    private int blackPiecesLeft = 9;

    // Variable that indicates if the game is over
    private boolean isGameOver;

    // The current player turn
    private Team currentTurn;

    // The size of the piece
    private final int pieceSize = 40;

    // The message to be displayed based on the game state
    private String message;

    // GameManager instance
    private GameManager gameManager;

    // Display - The Manager of Panels
    private Display display;

    // --- Hint Related --- //
    // A list of all the legal moves
    private ArrayList<Position> legalMoves;

    // Variable that indicates if the hint button is on
    private boolean showHint;

    // Variable that indicates if the panel is highlighting a piece selected by the player
    private boolean pieceHighlight;

    // The Position of the highlighted piece
    private Position highlightPosition;

    // --- Game Buttons --- //
    // Return To Menu Button
    private JButton backToMenu;
    // Replay (Restart) Button
    private JButton replayButton;
    // Toggle Hint Button
    private JButton hintButton;
    // Button to open the Tutorial Guidebook - Tutorial Game Exclusive.
    private JButton guideButton;
    // A panel that has all the button of the game
    private JPanel buttonPanel;


    // UwU --- Methods (Behaviours) --- UwU //

    // --- Constructor ---//
    /**
     * Creates a new Canvas object with the given values related to the GUI elements
     *
     * @param screenWidth The width of the screen
     * @param screenHeight The height of the screen
     */
    public GamePanel(int screenWidth, int screenHeight, Display display){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameBoardXPos = screenWidth/2 - this.gameBoardSize/2;
        this.gameBoardYPos = screenHeight/2 - 230;
        this.gameManager = new GameManager(this);
        this.display = display;
        this.setBackground(new Color(242, 165, 67));
        this.setLayout(new BorderLayout());
        this.getGameBoardImage();
        updateMessage(gameManager.getGameState());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 5));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(
                        "Clicked " + e.getX() + ", " + e.getY());

                gameManager.processClick(e);
            }
        });
    }


    // --- Getters --- //
    /**
     * Gets the size of the game board.
     *
     * @return The size of the game board
     */
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    /**
     * Gets the distance between the positions of the game board.
     *
     * @return The distance between the positions of the game board
     */
    public int getDistanceBetweenPos() {
        return distanceBetweenPos;
    }

    /**
     * Gets the x position of the game board.
     *
     * @return The x position of the game board
     */
    public int getGameBoardXPos() {
        return gameBoardXPos;
    }

    /**
     * Gets the y position of the game board.
     *
     * @return The y position of the game board.
     */
    public int getGameBoardYPos() {
        return gameBoardYPos;
    }

    /**
     * Gets the color of the team the current player belongs to.
     *
     * @return The Color of the team the current player belongs to
     */
    private Color getCurrentTurnColor(){
        Color returnColor = Color.WHITE;
        if (currentTurn == Team.WHITE){
            returnColor = Color.WHITE;
        }
        if (currentTurn == Team.BLACK) {
            returnColor = Color.BLACK;
        }
        return returnColor;
    }

    /**
     * Gets the image of the game board.
     */
    private void getGameBoardImage(){
        try{
            gameBoardImage = ImageIO.read(getClass().getResourceAsStream("/img/gb3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // --- Setters --- //
    /**
     * Sets the current player turn to next player.
     *
     * @param currentTurn The team the next player belongs to
     */
    public void setCurrentTurn(Team currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Sets the game over state of the game
     * @param gameOver True if the game is over, otherwise False
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * Sets the game manager for the game panel
     * @param gameManager The game manager to be set
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Sets the message based on the give message
     * @param message The message to be displayed in the message box
     */
    public void setMessage(String message) {
        this.message = message;
    }

    // --- GUI Drawing Methods --- //
    /**
     * Draw the image of the game board qt a specific position and with a specific size.
     * @param graphics2D The Graphics2D object for drawing the image
     */
    private void draw(Graphics2D graphics2D){
        graphics2D.drawImage(
                gameBoardImage,
                gameBoardXPos,
                gameBoardYPos,
                gameBoardSize, gameBoardSize, null);

    }

    /**
     * Redraw all the pieces on the game board.
     * @param positions The positions of the game board
     */
    public void reDrawPieces(Position[] positions, int whitePiecesLeft, int blackPiecesLeft) {
        this.positions = positions;
        redraw = true;
        showHint = false;
        pieceHighlight = false;
        hintButton.setText("Hint: Off");
        this.whitePiecesLeft = whitePiecesLeft;
        this.blackPiecesLeft = blackPiecesLeft;
        repaint();
    }

    /**
     * Disable the show hint feature in the game panel
     */
    public void disableShowHint() {
        showHint = false;
        JButton hintButton = (JButton) buttonPanel.getComponent(2);
        hintButton.setText("Hint: Off");
    }

    /**
     * Draw the pieces on the game board if the positions of the game board contain a piece, and the piece bars.
     * @param graphics2D The Graphics2D object for drawing the pieces
     */
    private void drawPiecesOnBoard(Graphics2D graphics2D) {
        for (int i = 0; i < positions.length; i++) {
            Position position = positions[i];

            // Draw Piece
            if (position.hasPiece()) {
                Color pieceColor;
                if (position.getPiece().getTeam() == Team.WHITE) {
                    pieceColor = Color.WHITE;
                } else {
                    pieceColor = Color.BLACK;
                }
                drawPiece(graphics2D, position,pieceColor);

                if (position.getPiece().isPartOfMill()) {
                    highlight(graphics2D, position, Color.YELLOW, 10, pieceSize);
                }
            }

        }
        // Update Piece Bar
        drawPieceBar(graphics2D, this.whitePiecesLeft, Color.WHITE);
        drawPieceBar(graphics2D, this.blackPiecesLeft, Color.BLACK);

    }

    /***
     * Method to Draw A Single Piece.
     * @param graphics2D Graphics to draw the piece
     * @param position The position the piece will be added to.
     * @param pieceColor The color (team) of that piece.
     */
    private void drawPiece(Graphics2D graphics2D, Position position, Color pieceColor){
        graphics2D.setColor(pieceColor);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.fillOval(
                gameBoardXPos + (distanceBetweenPos * position.getX()) + 10,
                gameBoardYPos + (distanceBetweenPos * position.getY()) + 10,
                pieceSize, pieceSize);
    }

    /**
     * Draw the piece bars based on the number of pieces a player can still place onto the game board.
     *
     * @param graphics2D The Graphics2D object for drawing the image
     * @param pieceLeft The number of pieces the players can still place onto the game board
     * @param color The color of the team the player belongs to
     */
    private void drawPieceBar(Graphics2D graphics2D, int pieceLeft, Color color) {
        for (int i = 0; i < pieceLeft; i++) {
            int xPos = gameBoardXPos - gameBoardMarginLeft - sideBarWidth;
            int yPos = gameBoardYPos + 80 + ((sideBarGap) * i);
            if (color == Color.BLACK) {
                graphics2D.setColor(Color.BLACK);
                xPos =  gameBoardXPos + (distanceBetweenPos * 6) + gameBoardMarginRight + sideBarWidth;
            }
            else {
                graphics2D.setColor(Color.WHITE);
            }

            graphics2D.fillRect(xPos, yPos, sideBarWidth, sideBarHeight);
        }

    }

    /**
     * Draw piece in the turn indicator box based on the current player turn.
     * A white piece will be drawn if it is currently player 1 turn.
     * A black piece will be drawn if it is currently player 2 turn.
     *
     * @param graphics2D The Graphics2D object for drawing the image
     * @param color The color of the team the current player belongs to
     */
    private void drawTurn(Graphics2D graphics2D, Color color){

        // Draw the correct piece to indicate the player's turn.
        graphics2D.setColor(color);
        graphics2D.fillOval(421, 500, pieceSize, pieceSize);

    }

    // Paint Components - Main method to draw all GUI Components.
    /**
     * Draw all the GUI components onto the screen.
     *
     * @param g the Graphics object for drawing the GUI components
     */
    protected void paintComponent (Graphics g){
        super.paintComponent(g);

        // Draw First Player's Turn - Always White
        g.setColor(Color.WHITE);
        g.fillOval(421, 500, pieceSize, pieceSize);

        if (!isGameOver){
            // Display message based on game state
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString(message.toUpperCase(), 170, 50);
        }
        else{
            // Display message based on game state
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 23));
            g.drawString("CONGRATULATIONS!!", 260, 40);
            g.drawString(message.toUpperCase(), 180, 60);
        }

        // Display player's turn
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Turn".toUpperCase(), screenWidth/2 - 70, screenHeight-45);
        g.drawRect(screenWidth/2 - 100, screenHeight-90, 200, 70);

        Graphics2D g2 = (Graphics2D)g;

        // Draw game board
        draw(g2);

        // Drawing Pieces Bar
        if (!redraw) {
            // Initially the piece bar has 9 pieces
            drawPieceBar(g2, 9, Color.BLACK);
            drawPieceBar(g2, 9, Color.WHITE);
        }
        else {
            drawPiecesOnBoard(g2);
        }

        // Highlighting piece
        if (pieceHighlight){
            highlightPieceOnBoard(g2);
        }

        // Drawing Hints
        if (showHint) {
            // Redraw all the pieces
            if (redraw) {
                drawPiecesOnBoard(g2);
            }
            // Highlight the piece selected
            if (pieceHighlight){
                highlightPieceOnBoard(g2);
            }
            // Draw all the legal moves
            drawHintOnBoard(g2);
        }

        // Draw Team of Next Turn on the Canvas GUI
        drawTurn(g2,getCurrentTurnColor());
        g2.dispose();
    }

    // --- Displaying Hints --- //
    /**
     * Method that is called to signal GamePanel, so that it displays all hints onto the GUI.
     * @param legalMoves An Arraylist of all legal moves.
     */
    public void displayHint(ArrayList<Position> legalMoves) {
        this.legalMoves = legalMoves;
        this.showHint = true;
        repaint();
    }

    /**
     * Method that is called to signal GamePanel, so that it highlights the position
     * @param position The position to highlight
     */
    public void highlightPieceToMove(Position position) {
        this.highlightPosition = position;
        this.pieceHighlight = true;
        repaint();
    }

    /**
     * Method that highlights all the legal moves that the player can make, by drawing circles on the board.
     * @param graphics2D The graphics to draw the hints.
     */
    private void drawHintOnBoard(Graphics2D graphics2D) {
        for (Position position: legalMoves) {
            // Highlight the position
            if (gameManager.getGameState() == GameState.REMOVE) {
                highlight(graphics2D, position, Color.GREEN, 10, pieceSize);
            }
            else {
                highlight(graphics2D, position, Color.GREEN, 13, pieceSize - 8);
            }
        }
    }

    /**
     * Method to invoke the hint function, so that other related classes can invoke the hint if needed.
     * @return Hint Status, for the hint button to display toggle: On / Off.
     */
    public void invokeHint(){

        String ret = "";

        // Toggle Hint: On / Off
        if (!showHint){
            System.out.println("Hint button toggled: On");
            showHint = true;
            gameManager.showHint();
            ret = "Hint: On";
        } else if (showHint || !redraw) {
            System.out.println("Hint button toggled: Off");
            showHint = false;
            if (!gameManager.isPositionSelected()) {
                pieceHighlight = false;
            }
            repaint();
            ret = "Hint: Off";
        }
        hintButton.setText(ret);
    }

    // --- Highlighting --- //
    /**
     * Method highlights the position of the selected piece that user decides to move during SLIDE or JUMP action
     * @param graphics2D The graphics to draw the highlight mark.
     */
    private void highlightPieceOnBoard(Graphics2D graphics2D){
        Color color = Color.RED;
        if (showHint && !gameManager.isPositionSelected()) {
            color = Color.GREEN;
        }
        highlight(graphics2D, this.highlightPosition, color, 12, pieceSize - 3);
    }

    /**
     * Method to Highlight a position on the GamePanel (GameBoard GUI).
     * There is no need for a removeHighlight, as it can just be repainted.
     *
     * @param graphics2D Graphics to draw the highlight.
     * @param position The position to highlight.
     * @param highlightColor The highlight color.
     * @param offset The offset for the position to center the circle.
     * @param size The size of the highlight circle. Ratio of offset:size - 1:4 approximate
     */
    private void highlight(Graphics2D graphics2D, Position position, Color highlightColor, int offset, int size){
        graphics2D.setColor(highlightColor);
        graphics2D.setStroke(new BasicStroke(5.0f));
        graphics2D.drawOval(
                gameBoardXPos + (distanceBetweenPos * position.getX()) + offset,
                gameBoardYPos + (distanceBetweenPos * position.getY()) + offset,
                size, size);
    }

    // --- Turn Change Handling --- //
    /**
     * Change the player turn on the Canvas.
     *
     * @param team The team the current player belongs to
     */
    public void changeTurn(Team team){
        this.setCurrentTurn(team);
    }

    /**
     * Update message to be displayed to the players based on current game state.
     *
     * @param gameState The current game state
     */
    public void updateMessage(GameState gameState) {
        String message = "";
        switch (gameState) {
            case PLACE:
                message = "Put your pieces on the board";
                break;
            case SLIDE:
                message = "Slide your pieces on the board";
                break;
            case JUMP:
                message = "Jump your pieces on the board";
                break;
            case REMOVE:
                message = "Mill Formed! Remove an opponent's piece";
                break;
            case DRAW:
                message = "Draw";
        }
        this.setMessage(message);
    }

    /**
     * Method to add the guide button - only invoked by Tutorial Games.
     * @param guideButton the guide button jbutton.
     */
    protected void addGuideButton(JButton guideButton){
        this.guideButton = guideButton;
        this.buttonPanel.add(guideButton);
    }

    // --- Button Management --- //
    /**
     * Method to add relevant buttons to the GamePanel - Return to Menu, and Replay buttons.
     * @param mainFrame the main JFrame (window) this panel is currently on.
     */
    public void addButtons(JFrame mainFrame) {
        backToMenu = new JButton("Return to Menu");
        replayButton = new JButton("Replay");
        hintButton = new JButton("Hint: Off");
        // Back To Menu Button
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameManager.replay();
                clearButtons(mainFrame);
                display.swapToMainMenuPanel();
            }
        });

        // Replay Button
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restart game
                System.out.println("Restart button clicked");
                gameManager.replay();
                setGameOver(false);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        // Hint Button
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invokeHint();
            }
        });

        // Put Buttons on the GamePanel.
        buttonPanel.add(backToMenu);
        buttonPanel.add(replayButton);
        buttonPanel.add(hintButton);

        buttonPanel.setBackground(new Color(242, 165, 67));
        mainFrame.add(buttonPanel, BorderLayout.NORTH);

    }

    /**
     * Utility Method to remove all 3 game buttons from the button panel.
     * @param mainFrame The main window where the button panel is present.
     */
    protected void clearButtons(JFrame mainFrame){
        mainFrame.remove(buttonPanel);
        buttonPanel.remove(backToMenu);
        buttonPanel.remove(replayButton);
        buttonPanel.remove(hintButton);
        if (guideButton != null) {
            buttonPanel.remove(guideButton);
        }
    }

    // --- GameOver Handling --- //
    /**
     * Update message to be displayed to the players when GAMEOVER state reached
     * @param color winning player
     */
    public void gameOverMessage(String color){
        String message = "Player " + color + " has won this game";
        this.setMessage(message);
        this.finalMessage();
    }

    /**
     * Updates Gameover boolean and Redraws the display
     */
    public void finalMessage(){
        this.isGameOver = true;
        repaint();
    }

}
