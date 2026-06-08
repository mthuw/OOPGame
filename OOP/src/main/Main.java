package main;

import games.core.GameConfig;
import main.homepage.GamePanelHomePage;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final String HOME = "HOME";
    public static final String DINOSIM = "DINOSIM";
    public static final String PACMAN = "PACMAN";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame("My Game");
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setResizable(false);

            CardLayout cardLayout = new CardLayout();
            JPanel container = new JPanel(cardLayout);

            GamePanelHomePage homepage = new GamePanelHomePage(cardLayout, container, jframe);

            games.dinosim.GamePanel dinosim = new games.dinosim.GamePanel(cardLayout, container, jframe);
            games.pacman.GamePanel  pacman  = new games.pacman.GamePanel(cardLayout, container, jframe);


            container.add(homepage, HOME);
            container.add(dinosim, DINOSIM);
            container.add(pacman, PACMAN);

            container.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));

            cardLayout.show(container, HOME);

            jframe.add(container);
            jframe.pack();
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);
        });

    }
}