package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class representing the Main Menu for the game.
 * References:
 * 1. Box Layout Documentation: https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * 2. Adding BufferedImage to JLabel: https://www.javacodex.com/More-Examples/2/1#:~:text=Use%20a%20BufferedImage%20and%20add,the%20JLabel%20to%20the%20JPanel.
 * 3. Image References 1: https://en.wikipedia.org/wiki/Nine_men%27s_morris
 * 4. Image Reference 2: https://www.thehistoricgamesshop.co.uk/onlineshop/prod_6559736-Nine-Mens-Morris.html
 */
public class MainMenuPanel extends JPanel {

    // UwU --- Variables (Attributes)--- UwU //
    // Display - The Manager of Panels
    private Display display;

    // The image for 9MM
    private BufferedImage mainImage;

    // Variable to indicate whether has been notified
    private Boolean isANotified = false;

    // UwU --- Methods (Behaviours) --- UwU //

    // --- Constructor ---//
    /**
     * Create the main menu's GUI
     * @param display The display instance for managing panels.
     */
    public MainMenuPanel(Display display) {

        this.display = display;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(new Color(242, 165, 67));

        // Add Title
        this.add(Box.createRigidArea(new Dimension(100, 25)));

        JLabel gameTitle = new JLabel("9 Men's Morris");
        gameTitle.setFont(new Font("Arial", 1, 60));
        gameTitle.setForeground(Color.BLACK);
        gameTitle.setBorder(new MatteBorder(3,3,3,3, Color.black));
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(gameTitle);

        // Add Team Name
        this.add(Box.createRigidArea(new Dimension(100, 25)));

        JLabel teamName = new JLabel("By: Team Buggy Coders");
        teamName.setFont(new Font("Arial", 1, 25));
        teamName.setForeground(Color.BLACK);
        teamName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(teamName);

        // Add Front Image
        this.add(Box.createRigidArea(new Dimension(100, 10)));

        getMainImage();
        JLabel image = new JLabel(new ImageIcon(mainImage));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(image);

        // Add Text for Selecting buttons
        this.add(Box.createRigidArea(new Dimension(100, 10)));

        JLabel selectText = new JLabel("Choose your Game Mode:");
        selectText.setFont(new Font("Arial", 1, 20));
        selectText.setForeground(Color.BLACK);
        selectText.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(selectText);

        // --- Add Buttons ---//
        // PvP Game
        this.add(Box.createRigidArea(new Dimension(100, 15)));

        JButton buttonPvP = new JButton("Player Vs Player");
        buttonPvP.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonPvP);

        // PvC Game
        this.add(Box.createRigidArea(new Dimension(100, 10)));

        JButton buttonPvC = new JButton("Player Vs Computer");
        buttonPvC.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonPvC);

        // Tutorial Game
        this.add(Box.createRigidArea(new Dimension(100, 10)));

        JButton buttonTut = new JButton("Tutorial Game");
        buttonTut.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonTut);

        this.add(Box.createRigidArea(new Dimension(100, 10)));

        // --- Button Listeners --- //
        // PvP Button Listener
        buttonPvP.addActionListener((ae) -> {
            display.swapToPvPGamePanel(this);
        });

        // PvC Button Listener
        buttonPvC.addActionListener((ae) -> {
            display.swapToPvCGamePanel(this);
        });

        // Tutorial Game Button Listener
        buttonTut.addActionListener((ae) -> {
            try {
                display.swapToTutorialGamePanel(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    // --- Retrieve Main Image for Main Menu ---//
    /**
     * Gets the image of 9MM for the main menu.
     */
    private void getMainImage(){
        try{
            mainImage = ImageIO.read(getClass().getResourceAsStream("/img/mainImage.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
