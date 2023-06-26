package control;

import actions.*;
import components.GameBoard;
import components.Piece;
import components.Position;
import components.Team;
import constants.GameState;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import view.GamePanel;

import java.awt.event.MouseEvent;

/**
 * GameManager is a class that communicates between the model (GameBoard, etc.) and the view (GamePanel, etc.), to
 * correctly manage and update the state of the game.
 *
 * Reporting to the Game Classes (Board of Directors), GameManager is like the CEO.
 */
public class GameManager {

    // UwU --- Variables (Attributes) --- UwU //
    // The game panel for displaying the game
    private GamePanel gamePanel;

    // The game board in the game
    private GameBoard gameBoard;

    // Unique identifier if it is a Tutorial Game.
    private String gameKey;

    // The current player that take turn
    private Player currentPlayer;

    // Player 1
    private Player player1;

    // Player 2
    private Player player2;

    // Variables containing start and destination location during slide and jump //
    // -1 means that no position being selected
    private int gameBoardLoc1 = -1; // The position of the piece selected by the player

    private int gameBoardLoc2 = -1; // The position that the player wishes to move the selected piece to

    // Variable to indicate if a position has been selected to perform an action on the board
    private boolean isPositionSelected = false;

    // Current game state of the game
    private GameState gameState = GameState.PLACE;

    // Variable to count the number of turns in a game
    private int playerTurnCounter = 0;

    // UwU --- Methods (Behaviours) --- UwU //
    // --- Constructor --- //
    /**
     * Creates a GameManager instance with the given parameters. Automatically Creates two human players.
     *
     * @param gamePanel The GamePanel for GUI of the game.
     */
    public GameManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        gameBoard = new GameBoard();
        player1 = new HumanPlayer("player1", Team.WHITE);
        player2 = new HumanPlayer("player2", Team.BLACK);
        currentPlayer = player1;
        autoHint();
    }

    /**
     * Creates a GameManager instance with the given parameters. Can be used to toggle PvP or PvC modes.
     *
     * @param gamePanel The Display class to manage the GUI
     * @param gameBoard The GameBoard class to manage the Model and Mechanics
     * @param player1 The first player
     * @param player2 The second player
     */
    public GameManager(GamePanel gamePanel, GameBoard gameBoard, Player player1, Player player2) {
        this.gamePanel = gamePanel;
        this.gameBoard = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    // --- Getters --- //
    /**
     * Get the current state of the game.
     *
     * @return an enum value that represents the current state of the game
     */
    public GameState getGameState() {
        return gameState;
    }

    // --- Setters --- //
    /**
     * Set the game key (unique game identifier)
     * @param gameKey the game key, a unique id for game classes.
     */
    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    // --- Positions and Turn Detection and Handling --- //
    /**
     * Check if a position has been selected to perform an action on the board.
     *
     * @return True if a position has been selected, otherwise False
     */
    public boolean isPositionSelected() {
        return isPositionSelected;
    }

    /**
     * Process the mouse click event on the GUI, i.e. GamePanel.
     * It handles the mouse click event and determine if the clicked coordinates are within the range of a position on
     * the game board. If it is, appropriate actions will be performed based on the clicked position and the current
     * state of the game.
     *
     * @param e The MouseEvent representing the click event
     */
    public void processClick(MouseEvent e) {

        for (int i=0; i < gameBoard.getPositions().length; i++) {
            // Get the x and y coordinates of a position on the game board
            int xPos = gamePanel.getGameBoardXPos() + 30 + (gamePanel.getDistanceBetweenPos() * gameBoard.getPositions()[i].getX());
            int yPos = gamePanel.getGameBoardYPos() + 30 + (gamePanel.getDistanceBetweenPos() * gameBoard.getPositions()[i].getY());

            // Check if the clicked coordinates are within the range of a position on the game board
            if ((e.getX() >= xPos - 15 && e.getX() <= xPos + 15) &&
                    (e.getY() >= yPos - 15 && e.getY() <= yPos + 15)) {

                // Console Checks
                System.out.println("Position Checks");
                System.out.println(i);
                System.out.println(xPos);
                System.out.println(yPos);

                // Perform appropriate action for the clicked position and update the current state of the game
                handlePlayerAction(i);

                // After executing handlePlayerAction, it changes to next player turn

                // Check if the player is a computer
                if (currentPlayer.isComputer() && !(hasLessThanThreePieces(currentPlayer) || noLegalMoves(currentPlayer))) {
                    // Randomly choose and perform a legal move based on current state of the game
                    ComputerPlayer computerPlayer = (ComputerPlayer) player2;

                    Position nextPos = computerPlayer.computeNextMove(gameState, gameBoard);

                    // Get the position of the piece selected by the computer
                    gameBoardLoc1 = computerPlayer.getSelectedPos();

                    handlePlayerAction(nextPos.getId());
                }
            }
        }
    }

    /**
     * Handle the player's action based on the current game state and the position clicked/selected by the player.
     *
     * @param i The index of the position clicked/selected by the player
     */
    private void handlePlayerAction(int i){

        if (gameBoard.getPositionHasPiece(i)) {
            System.out.println("Piece is part of mill: " + gameBoard.getPieceAtPosition(i).isPartOfMill());
        }

        // Intuition: Jumps can only happen during later in the "Sliding Phase".
        if (currentPlayer.getPiecesOnBoard() <= 3 && gameState == GameState.SLIDE) {
            gameState = GameState.JUMP;
            gamePanel.updateMessage(gameState);
        }

        // Execute corresponding actions based on the current state of the game and update the state according
        switch (gameState) {
            case PLACE:
                System.out.println("Current state: PLACE");
                handlePlace(i, new Piece(currentPlayer.getTeam()));

                // If placed all 9 tokens onto the game board, change to the next game state, i.e. slide
                if (currentPlayer.getPiecesToPlace()==0 && gameState == GameState.PLACE) {
                    gameState = GameState.SLIDE;
                    gamePanel.updateMessage(gameState);
                }
                break;

            case SLIDE:
                System.out.println("Current state: SLIDE");
                handleSlide(i);
                break;

            case JUMP:
                System.out.println("Current state: JUMP");
                handleJump(i);
                break;

            case REMOVE:
                System.out.println("Current state: REMOVE");
                handleRemove(i);
                break;
        }

        // Update GamePanel, should not run on selection of a piece to move
        if (!isPositionSelected){
            // Redraw the pieces on the game panel
            gamePanel.reDrawPieces(gameBoard.getPositions(), player1.getPiecesToPlace(), player2.getPiecesToPlace());
            autoHint();
        }

        // Win Condition Checks - Game Over
        if (hasLessThanThreePieces(currentPlayer) || noLegalMoves(currentPlayer)) {
            gameState = GameState.GAMEOVER;

            // Displaying Game Over message in GUI
            if (currentPlayer.getTeam() == Team.WHITE){
                gamePanel.gameOverMessage("Black");
            }
            else{
                gamePanel.gameOverMessage("White");
            }
        }
        if (playerTurnCounter == 100){
            gameState = GameState.DRAW;
            gamePanel.updateMessage(gameState);
        }
    }

    /**
     * Reset Move Locations after a Slide/Jump Action.
     */
    private void resetMoveLocations() {
        gameBoardLoc1 = -1;
        gameBoardLoc2 = -1;
    }

    // --- Methods for Action Handling --- //
    /**
     * Method to handle Place Actions - Placing a Piece at a Position on the GameBoard.
     *
     * @param position Position that the piece is to be placed.
     * @param piece The Piece to put at the chosen position.
     */
    private void handlePlace(int position, Piece piece) {
        PieceAction placeAction = new PlaceAction(position, piece);

        if (placeAction.isValid(gameBoard, currentPlayer)) {
            placeAction.performAction(gameBoard, currentPlayer);

            // Win Condition Checks - Game Over
            if (noLegalMoves(currentPlayer)) {
                gameState = GameState.GAMEOVER;
                //displaying Game Over message in GUI
                if (currentPlayer.getTeam() == Team.WHITE) {
                    gamePanel.gameOverMessage("Black");
                } else {
                    gamePanel.gameOverMessage("White");
                }
            } else {
                // Handle Mills if any.
                handleMills(gameBoard, position);
            }
        }
    }
    /**
     * Method to handle Slide Actions - Sliding a Piece from One Position to Another.
     * It allows the player to first select a piece to move and then select a destination position to slide the piece to.
     *
     * @param position The initial position.
     */
    private void handleSlide(int position){
        // If the selected position contains the current player's piece
        if (gameBoard.getPositionHasPiece(position)
                && gameBoard.getPosition(position).getPiece().getTeam() == currentPlayer.getTeam()){
            // Select it as the piece to slide
            gameBoardLoc1 = position;
            this.isPositionSelected = true;

            // Highlight the selected piece
            gamePanel.highlightPieceToMove(gameBoard.getPosition(position));

            // Disable the Show Hint because new position has been selected
            gamePanel.disableShowHint();
        }

        // If a piece has already been selected and the destination position is now selected
        else if(gameBoardLoc1 != -1 && gameBoardLoc2 == -1){
            System.out.println("Make a slide to " + position);
            PieceAction slideAction = new SlideAction(gameBoardLoc1,position);
            if(slideAction.isValid(gameBoard, currentPlayer)){
                gameBoardLoc2 = position;
                slideAction.performAction(gameBoard, currentPlayer);
                resetMoveLocations();

                // Handle Mills if any.
                handleMills(gameBoard, position);
                // Setting boolean back to default after SLIDE action is executed
                this.isPositionSelected = false;
            }
        }
    }

    /**
     * Method to handle Jump Action - Jumping (Fly) a Piece from One Position to Another.
     * It allows the player to first select a piece to jump and then select a destination position to jump the piece to.
     *
     * @param position The position selected by the player
     */
    private void handleJump(int position) {
        // If the selected position contains the current player's piece
        if (gameBoard.getPositionHasPiece(position)
                && gameBoard.getPosition(position).getPiece().getTeam() == currentPlayer.getTeam()) {
            // Select it as the piece to jump
            gameBoardLoc1 = position;
            this.isPositionSelected = true;

            // Highlight the selected piece
            gamePanel.highlightPieceToMove(gameBoard.getPosition(position));

            // Disable the Show Hint because new position has been selected
            gamePanel.disableShowHint();
        }

        // If a piece has already been selected and the destination position is now selected
        else if(gameBoardLoc1 != -1 && gameBoardLoc2 == -1) {
            JumpAction jumpAction = new JumpAction(gameBoardLoc1, position);
            if (jumpAction.isValid(gameBoard, currentPlayer)) {
                gameBoardLoc2 = position;
                jumpAction.performAction(gameBoard, currentPlayer);
                resetMoveLocations();

                // Handle Mills if any.
                handleMills(gameBoard, position);

                // setting boolean back to default after JUMP action is executed
                this.isPositionSelected = false;
            }
        }
    }

    /**
     * Method to handle Mills. After Place, Slide or Jump Action - Check mills and handle.
     * @param gameBoard The game board
     * @param position The current position
     */
    public void handleMills(GameBoard gameBoard, int position){
        // Check if the position is a part of a mill
        boolean decision = gameBoard.checkMills(position);

        // Check if all opponent pieces are part of a mill
        boolean secondDecision = gameBoard.checkAllOpponentPiecesInMill(position);

        // If not all opponent pieces are part of a mill, i.e. the player can remove an opponent piece
        if (decision && !secondDecision) {
                System.out.println("Change to REMOVE");

                // Change game state to REMOVE
                gameState = GameState.REMOVE;
                gamePanel.updateMessage(gameState);

                // Check if the player is a computer
                if (currentPlayer.isComputer()) {
                    // Randomly choose an opponent piece to remove
                    ComputerPlayer computerPlayer = (ComputerPlayer) player2;
                    Position nextPos = computerPlayer.computeNextMove(gameState, gameBoard);
                    handleRemove(nextPos.getId());
                }
        }
        // If all opponent pieces are part of a mill
        else {
            System.out.println("Not REMOVE");

            // Change the player turn to the next player
            changePlayerTurn();

            // Update the game state based on current player's remaining pieces
            if (currentPlayer.getPiecesToPlace() == 0){
                if (currentPlayer.getPiecesOnBoard() > 3) {
                    gameState = GameState.SLIDE;
                }
                else {
                    gameState = GameState.JUMP;
                }
            }
            else{
                gameState = GameState.PLACE;
            }
            gamePanel.updateMessage(gameState);
        }
    }

    /**
     * Method to handle Remove Actions - Removing an opposition's piece when a mill is formed
     * @param position The position of piece you want to remove.
     */
    private void handleRemove(int position){
        RemoveAction removeAction = new RemoveAction(position);

        if(removeAction.isValid(gameBoard, currentPlayer)){
            if (currentPlayer == player1){
                removeAction.performAction(gameBoard, player2);
            }
            else{
                removeAction.performAction(gameBoard, player1);
            }

            // Change the player turn to next player
            changePlayerTurn();

            // Update the game state based on the current player's remaining pieces
            if (currentPlayer.getPiecesToPlace() == 0){
                if (currentPlayer.getPiecesOnBoard() > 3) {
                    gameState = GameState.SLIDE;
                }
                else {
                    gameState = GameState.JUMP;
                }
            }
            else{
                gameState = GameState.PLACE;
            }
            gamePanel.updateMessage(gameState);
        }
    }

    // --- Turn Management and Legal Move Detection --- //
    /**
     * Method to correctly change the turns after a player completes a move.
     */
    private void changePlayerTurn() {
        if (currentPlayer.getTeam() == Team.WHITE) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }

        // Update Player Turn Indicator on GUI
        gamePanel.changeTurn(currentPlayer.getTeam());
        playerTurnCounter += 1;
    }

    /**
     * Check if a given player has no legal move.
     *
     * @param player A player in the game
     * @return True if the player has no legal move, otherwise False
     */
    private boolean noLegalMoves(Player player) {
        if (player.getPiecesToPlace() > 0) {
            return false;
        }
        else {
            // loop through each position of the game board
            for (int i = 0; i < gameBoard.getPositions().length; i++) {
                // if the position has a piece of the player
                if (gameBoard.getPositionHasPiece(i)) {
                    if (gameBoard.getPieceAtPosition(i).getTeam() == player.getTeam()) {
                        // check if any of the adjacency position does not have piece
                        for (Position neighbour : gameBoard.getPositions()[i].getNeighbours()) {
                            if (!neighbour.hasPiece()) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    /**
     * Check if a given player has less than 3 pieces.
     *
     * @param player A player in the game
     * @return True if the player has less than 3 pieces, otherwise False.
     */
    private boolean hasLessThanThreePieces(Player player) {
        if (player.getPiecesToPlace() == 0 && player.getPiecesOnBoard() < 3) {
            return true;
        }
        return false;
    }

    // --- Restart Game --- //
    /**
     * Reset the game to its initial state when the replay button is clicked.
     */
    public void replay() {
        // Reset the game to initial state, i.e. place
        gameState = GameState.PLACE;

        // Create a new game board
        gameBoard = new GameBoard();

        // Initialize the players
        player1 = new HumanPlayer("player1", Team.WHITE);
        if (player2.isComputer()) {
            player2 = new ComputerPlayer("player2", Team.BLACK);
        }
        else {
            player2 = new HumanPlayer("player2", Team.BLACK);
        }

        // Set current player to player 1
        currentPlayer = player1;

        // Update the game panel
        gamePanel.changeTurn(currentPlayer.getTeam());
        gamePanel.updateMessage(gameState);
        gamePanel.reDrawPieces(gameBoard.getPositions(), player1.getPiecesToPlace(), player2.getPiecesToPlace());
        autoHint();
    }

    // --- Hint Management --- //
    /**
     * Show a hint for the current player.
     * If the player requests a hint for a selected piece, all the legal moves for that piece will be displayed.
     * Otherwise, all the legal moves for the first movable piece will be displayed.
     */
    public void showHint() {
        if (gameState != GameState.PLACE && isPositionSelected &&
                gameBoard.getPositions()[gameBoardLoc1].getPiece().getTeam() == currentPlayer.getTeam()) {
            // Find all the legal moves for the selected piece
            HintAction hintAction = new HintAction(gameBoardLoc1, gameState);
            hintAction.performAction(gameBoard, currentPlayer);

            // Display all the legal moves for the selected piece in the game panel
            gamePanel.displayHint(hintAction.getLegalMoves());
        }
        else {
            // Find all the legal moves for the first movable piece
            HintAction hintAction = new HintAction(gameState);
            hintAction.performAction(gameBoard, currentPlayer);

            // Display all the legal moves for the first movable piece in the game panel
            gamePanel.displayHint(hintAction.getLegalMoves());

            // Highlight the first movable piece
            if (hintAction.getSelectedPos() != -1) {
                gamePanel.highlightPieceToMove(gameBoard.getPosition(hintAction.getSelectedPos()));
            }
        }
    }

    /**
     * Enable Auto hint based on type of game.
     */
    public void autoHint(){
        // Unique Identifier for TutorialGame - has automatic hints.
        if (gameKey == "FIT3077TutorialGame"){
            gamePanel.invokeHint();
        }
    }
}
