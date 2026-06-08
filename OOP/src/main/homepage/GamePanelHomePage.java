package main.homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import games.core.GameConfig;
import main.Main;

public class GamePanelHomePage extends JPanel {
    private HeaderSection headerSection;
    private CardContainer cardContainer;
    private final CardLayout cardLayout;
    private final JPanel container;
    protected final JFrame jframe;

    public GamePanelHomePage(CardLayout cardLayout, JPanel container, JFrame jframe) {
        this.cardLayout = cardLayout;
        this.container = container;
        this.jframe = jframe;

        // Use a vertical BoxLayout so everything stacks and centers naturally
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        // resize window back to homepage size when returning from a game
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                container.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
                jframe.pack();
                jframe.setLocationRelativeTo(null);
            }
        });
        createComponents();
        addComponentsToPanel();
    }

    private void createComponents() {
        headerSection = new HeaderSection("SELECT A GAME");
        cardContainer = new CardContainer(createGameCard());
    }

    private List<GameCard> createGameCard() {
        List<GameCard> cards = new ArrayList<>();

        GameCard dinosimCard = new GameCard(
                "Dinosim",
                "Dodge obstacles, survive as long as you can.",
                CardTheme.GREEN
        );

        dinosimCard.setGameNavigator(gameName -> launchGame(gameName));
        cards.add(dinosimCard);

        GameCard pacmanCard = new GameCard(
                "PacMan",
                "Explore and find all the gold.",
                CardTheme.GOLD
        );
        pacmanCard.setGameNavigator(gameName -> launchGame(gameName));
        cards.add(pacmanCard);

        return cards;
    }
    public void launchGame(String gameName) {
        String cardName = switch (gameName) {
            case "Dinosim" -> Main.DINOSIM;
            case "PacMan" -> Main.PACMAN;
            default -> null;
        };

        if (cardName == null) return;
        cardLayout.show(container, cardName);
    }


    private void addComponentsToPanel() {
        add(Box.createVerticalStrut(20));

        JPanel headerPanel = headerSection.createPanel();
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setOpaque(false);
        add(headerPanel);

        JPanel cardsPanel = cardContainer.createPanel();
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cardsPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}