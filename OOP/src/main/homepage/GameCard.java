package main.homepage;

import javax.swing.*;
import java.awt.*;

public class GameCard extends JPanel{
    private final String title;
    private final String description;
    private final CardTheme cardColor;
    private JButton playButton;
    private GameNavigator gameNavigator;

    private static final int CARD_WIDTH = 400;
    private static final int CARD_HEIGHT = 100;

    private static final Color DESC_COLOR = new Color(100, 100, 100);

    public GameCard(String title, String description, CardTheme cardColor){
        this.title = title;
        this.description = description;
        this.cardColor = cardColor;
        setupPanel();
        createPlayButton();
    }

    private void setupPanel(){
        setPreferredSize(new Dimension(CARD_WIDTH,CARD_HEIGHT));
        setBackground(cardColor.getBgColor());
    }
    private void createPlayButton(){
        playButton = new JButton("PLAY");
        playButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        MouseHandler mouseHandler = new MouseHandler(this);
        playButton.addActionListener(mouseHandler);
        add(playButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawText(g2d);
    }

    private void drawText(Graphics2D g2d){
        g2d.setFont(new Font("SansSerif", Font.BOLD, 13));
        g2d.drawString(title, 30,50);

        g2d.setColor(DESC_COLOR);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 11));
        g2d.drawString(description, 40, 70);
    }
    public void navigateGame(){
        System.out.println("Navigating to: " + title);
        if (gameNavigator != null){
            gameNavigator.navigateSelectedGame(title);
        }
    }
    public void setGameNavigator(GameNavigator navigator){
        this.gameNavigator = navigator;
    }
    public String getTitle(){
        return title;
    }

}
