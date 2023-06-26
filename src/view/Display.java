package view;

import components.GameBoard;
import components.Team;
import control.GameManager;
import model.*;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class represents the GUI view of 9MM.
 * It handles all the GUI related elements.
 *
 * References:
 * 1.RyiSnow. (2021, February 22). How to make point and click adventure game in Java Part 1 - swing gui programming tutorial.
 * [YouTube]. Retrieved from https://www.youtube.com/watch?v=bn8MDLsubOQ&list=PL_QPQmz5C6WVLQ2_yYpN5BjEaS9uLRGbD
 * 2. Javapoint. (n.d.). Java Swing Tutorial. Retrieved from https://www.javatpoint.com/java-swing
 * 3. fr0sty99, (2018). muehle. Retrieved from: https://github.com/fr0sty99/muehle/blob/master/Muehle_MVC_UML.pdf
 */
public class Display{

    // UwU --- Variables (Attributes)--- UwU //
    // The width of the screen in pixels.
    private final int screenWidth;

    // The height of the screen in pixels.
    private final int screenHeight;

    // The panel to draw the elements
    private GamePanel gamePanel;

    // The window or main frame which the game is displayed
    private JFrame window;

    // --- Tutorial Game Related --- //
    // TutGame instance.
    private Game tutGame;

    //Tutorial Guide Instance
    private TutorialGuide tutorialGuide;

    //Guide Button - Tutorial Exclusive
    private JButton guideButton;

    // UwU --- Methods (Behaviours) --- UwU //

    // --- Constructor --- //
    /**
     * Create a Display object based on the given parameters
     *
     * @param screenHeight The height of the screen
     * @param screenWidth The width of the screen
     */
    public Display(int screenHeight, int screenWidth){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setCanvas(new GamePanel(getScreenWidth(), getScreenHeight(), this));
        initializeDesktopWindow();
    }

    // --- Setters --- //
    /**
     * Sets the Canvas with a new Canvas.
     *
     * @param gamePanel The Canvas to draw the elements
     */
    private void setCanvas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // --- Getters --- //
    /**
     * Gets the width of the screen.
     *
     * @return The width of the screen
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the height of the screen.
     *
     * @return The height of the screen
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    // --- Main JFrame Handling ---//
    /**
     * Initializes the main window that consists of all the GUI components of 9MM and adds a mouse lister used to detect
     * when the player clicks a mouse button.
     */
    private void initializeDesktopWindow(){
        window = createMainFrame(screenWidth, screenHeight);
        JPanel menu = new MainMenuPanel(this);
        window.getContentPane().add(menu, BorderLayout.CENTER);
        window.pack();
    }

    /**
     * Method to create the main JFrame for the window.
     * @param screenWidth The width of the screen in pixels
     * @param screenHeight The height of the screen in pixels
     * @return A JFrame representing the main window.
     */
    public JFrame createMainFrame(int screenWidth, int screenHeight){
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setTitle("9 Men’s Morris");
        window.getContentPane().setBackground(new Color(242, 165, 67));
        window.setPreferredSize(new Dimension(screenWidth + 50, screenHeight + 60));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }

    /**
     * Utility Method to repaint the window.
     */
    public void rePaint(){
        window.revalidate();
        window.repaint();
    }

    // --- Panel Swap Methods for PvP and PvC ---//
    /**
     * Swap the main menu panel with a game panel for a Player vs Player game
     *
     * @param menu The main menu panel
     */
    public void swapToPvPGamePanel(JPanel menu) {
        // Initialize and start the game
        Player player1 = new HumanPlayer("player1", Team.WHITE);
        Player player2 = new HumanPlayer("player2", Team.BLACK);
        GameManager gameManager = new GameManager(gamePanel, new GameBoard(), player1, player2);
        Game game = new RealGame(new GameManager(gamePanel, new GameBoard(), player1, player2), player1, player2);
        gamePanel.setGameManager(gameManager);

        // Swap
        window.remove(menu);
        window.add(gamePanel, BorderLayout.CENTER);
        gamePanel.addButtons(window);
        rePaint();
    }

    /**
     * Swap the main menu panel with a game panel for a Player vs Computer game
     *
     * @param menu The main menu panel
     */
    public void swapToPvCGamePanel(JPanel menu) {
        // Initialize and start the game
        Player player1 = new HumanPlayer("player1", Team.WHITE);
        Player player2 = new ComputerPlayer("player2", Team.BLACK);
        GameManager gameManager = new GameManager(gamePanel, new GameBoard(), player1, player2);
        Game game = new RealGame(gameManager, player1, player2);
        gamePanel.setGameManager(gameManager);

        // Swap
        window.remove(menu);
        window.add(gamePanel, BorderLayout.CENTER);
        gamePanel.addButtons(window);
        rePaint();
    }

    // --- Tutorial Panel Methods --- //
    /**
     * Swap the main menu panel with a game panel for a Player vs Computer game
     *
     * @param menu The main menu panel
     */
    public void swapToTutorialGamePanel(JPanel menu) throws IOException {

        // Initialize Tutorial
        initTutorialGame();
        tutorialGuide = new TutorialGuide(screenWidth, screenHeight, this);

        // Add "Guide" Button
        guideButton = new JButton("Guide");
        guideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("Guide button pressed");
                    gamePanel.clearButtons(window);
                    window.remove(gamePanel);
                    window.add(tutorialGuide, BorderLayout.CENTER);
                    rePaint();
                }
        });

        // Start the Tutorial with a guide.
        window.remove(menu);
        window.add(tutorialGuide, BorderLayout.CENTER);
        rePaint();
    }

    /**
     *  Utility method to swap to the tutorial guide within the tutorial game.
     */
    public void swapTutGuideGame(){
        window.remove(tutorialGuide);
        window.add(gamePanel, BorderLayout.CENTER);
        gamePanel.addButtons(window);
        gamePanel.addGuideButton(guideButton);
        tutGame.start(gamePanel);
        rePaint();
    }

    /**
     * Method to initialize a new tutorial game.
     */
    public void initTutorialGame(){
        // Initialize and start the game
        if (tutGame == null) {
            Player player1 = new HumanPlayer("player1", Team.WHITE);
            Player player2 = new ComputerPlayer("player2", Team.BLACK);
            GameManager gameManager = new GameManager(gamePanel, new GameBoard(), player1, player2);
            tutGame = new TutorialGame(gameManager, player1, player2);
            gamePanel.setGameManager(gameManager);
        }
    }

    // --- Main Menu Return Method --- //
    /**
     * Method to swap back to the main menu panel.
     */
    public void swapToMainMenuPanel() {
        System.out.println("Return to Main Menu");
        gamePanel.setGameOver(false);
        window.remove(gamePanel);

        JPanel menu = new MainMenuPanel(this);
        window.getContentPane().add(menu, BorderLayout.CENTER);

        window.getContentPane().add(menu, BorderLayout.CENTER);
        menu.revalidate();
        menu.repaint();
    }

}