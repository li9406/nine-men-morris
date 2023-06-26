package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Guide for the Tutorial, containing briefs for the game rules and mechanics. Extends JPanel Class.
 */
public class TutorialGuide extends JPanel {

    // UwU --- Variables (Attributes) --- UwU //
    // Width of the screen
    private int screenWidth;
    // Height of the screen
    private int screenHeight;
    // Display - The manager of panels
    private Display display;
    // JLabel for Tutorial Brief Cards.
    private JLabel tutCardPanel = new JLabel();
    // The index corresponding to the current card being displayed to the tutorial guide.
    private int currIndex = 0;
    // All the tutorial cards.
    private ArrayList<BufferedImage> tutCards = new ArrayList<BufferedImage>();
    // The navigation button panel for the tutorial cards.
    private JPanel buttonPanel;

    // UwU --- Methods (Behaviours) --- //
    // --- Constructor --- //
    /**
     * Tutorial Guide Constructor.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param display The Display instance - The Manager of Panels.
     */
    public TutorialGuide(int screenWidth, int screenHeight, Display display) throws IOException {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        System.out.println(screenWidth);
        System.out.println(screenHeight);
        this.display = display;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(242, 165, 67));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 5));

        System.out.println("Fetching Cards");
        this.getTutCards();
        this.swapCard(currIndex);
        this.add(tutCardPanel, BorderLayout.CENTER);
        this.addButtons();
    }

    // --- Tutorial Card Management --- //
    /**
     * Method to retrieve all the tutorial cards.
     *
     * There is a posibility of it throwing a nullpointerexception if it fails to fetch the relevent resource.
     */
    private void getTutCards(){
        try{
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/welcome.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/boardinit.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/place.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/millremove.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/slide.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/jump.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/wincon.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/Utility.png")));
            tutCards.add(ImageIO.read(getClass().getResourceAsStream("/img/end.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method to swap the card to a different card,
     * @param index The new index corresponding to it's position in the card list.
     */
    private void swapCard(int index){

        currIndex = index;
        System.out.println(currIndex);
        tutCardPanel.setIcon(new ImageIcon(tutCards.get(currIndex).getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH)));
        this.repaint();
    }

    // --- Button Management --- //
    /**
     * Method to add all the buttons to the tutorial navigation button panel.
     */
    protected void addButtons(){
        JButton previousButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        JButton goButton = new JButton("Go");

        // Previous Button
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (currIndex != 0){
                    swapCard(currIndex - 1);
                }

            }
        });

        // Next Button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (currIndex < tutCards.size() - 1){
                    swapCard(currIndex + 1);
                }
            }
        });

        // Hint Button
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the tutorial game.
                display.swapTutGuideGame();
            }
        });

        // Put Buttons on the GamePanel.
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(goButton);

        buttonPanel.setBackground(new Color(242, 165, 67));
        this.add(buttonPanel, BorderLayout.NORTH);
    }
}
