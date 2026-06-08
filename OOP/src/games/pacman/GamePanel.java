package games.pacman;

import games.pacman.tile.BlockManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends games.core.GamePanel {
    protected final int SCREEN_WIDTH  = 608;
    protected final int SCREEN_HEIGHT = 672;
    public static final int tileSize  = 32;

    private final BlockManager blockManager = new BlockManager();
    private final KeyHandler key = new KeyHandler();
    private final GameStatus gameStatus = new GameStatus();
    private final Pacman pacman = new Pacman(key, gameStatus);
    private final Collision collision = new Collision();

    public GamePanel(CardLayout cardLayout, JPanel container, JFrame jframe) {
        super(cardLayout, container, jframe);
        pacman.setBlockManager(blockManager);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);

        pacman.setX(blockManager.getSpawnX());
        pacman.setY(blockManager.getSpawnY());

        blockManager.spawnGhosts();
    }

    @Override
    public void update() {
        if (gameStatus.isGameOver() || gameStatus.isBoardCleared() ) {
            if (key.restart) restart();
            if (key.esc) returnHome();
            return;
        }
        if (key.esc) { returnHome(); return; }

        updatePacman();
        updateGhosts();
    }

    private void updatePacman() {
        pacman.update();
        pacman.move(pacman.canTurn());
        if (collision.collideWall(pacman, blockManager)) pacman.undoMove();
    }

    private void updateGhosts() {
        for (Ghost ghost : blockManager.getGhosts()) {
            ghost.update();
            ghost.move(false);
            if (collision.collideWall(ghost, blockManager)) {
                ghost.undoMove();
                ghost.randomDirection();
            }
            if (collision.collides(pacman, ghost)) {
                gameStatus.overLives();
                if (gameStatus.isGameOver()) {
                    return;
                }
                resetPositions();
                return;
            }
        }
    }

    private void resetPositions() {
        pacman.setX(blockManager.getSpawnX());
        pacman.setY(blockManager.getSpawnY());
        pacman.setDirection('R');
        pacman.setNextDirection('R');
        blockManager.spawnGhosts();
    }

    @Override
    public void restart() {
        gameStatus.restart();
        pacman.setX(blockManager.getSpawnX());
        pacman.setY(blockManager.getSpawnY());
        pacman.setDirection('R');
        pacman.setNextDirection('R');

        key.restart = false;
        key.esc = false;

        blockManager.loadMap();
        blockManager.spawnGhosts();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        blockManager.draw(g2d);
        for (Ghost ghost : blockManager.getGhosts()) ghost.draw(g2d);
        pacman.draw(g2d);
        gameStatus.draw(g2d);
    }
}