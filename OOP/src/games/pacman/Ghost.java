package games.pacman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static games.pacman.GamePanel.tileSize;

public class Ghost extends MovingEntity {
    protected Image blueGhost, orangeGhost, pinkGhost, redGhost;
    public enum GhostColor { RED, BLUE, PINK, ORANGE }
    private final GhostColor color;
    private final Random random = new Random();
    private static final char[] DIRECTIONS = {'U', 'D', 'L', 'R'};

    private final Collision collision = new Collision();

    public Ghost(GhostColor color) {
        this.color = color;
        width = tileSize;
        height = tileSize;
        direction = 'R';
        nextDirection = 'R';
        loadImages();
        updateVelocity();
    }

    private void loadImages() {
        switch (color) {
            case RED -> redGhost = new ImageIcon(getClass().getResource("/games/pacman/res/ghosts/redGhost.png")).getImage();
            case BLUE -> blueGhost = new ImageIcon(getClass().getResource("/games/pacman/res/ghosts/blueGhost.png")).getImage();
            case PINK -> pinkGhost = new ImageIcon(getClass().getResource("/games/pacman/res/ghosts/pinkGhost.png")).getImage();
            case ORANGE -> orangeGhost = new ImageIcon(getClass().getResource("/games/pacman/res/ghosts/orangeGhost.png")).getImage();
        }
    }

    private Image activeImage() {
        return switch (color) {
            case RED -> redGhost;
            case BLUE -> blueGhost;
            case PINK -> pinkGhost;
            case ORANGE -> orangeGhost;
        };
    }

    private char opposite(char dir) {
        return switch (dir) {
            case 'U' -> 'D';
            case 'D' -> 'U';
            case 'L' -> 'R';
            default -> 'L';
        };
    }

    private boolean isAligned() {
        return (x % tileSize == 0) && (y % tileSize == 0);
    }

    public void chooseNextDirection() {
        if (blockManager == null) return;

        char forbidden = opposite(direction);
        List<Character> options = new ArrayList<>();

        for (char dir : DIRECTIONS) {
            if (dir == forbidden) continue;
            if (isDirectionPassable(dir)) options.add(dir);
        }

        if (options.isEmpty()) {
            // Dead end — reverse
            nextDirection = forbidden;
        } else {
            nextDirection = options.get(random.nextInt(options.size()));
        }
    }

    private boolean isDirectionPassable(char dir) {
        if (blockManager == null){
            return true;
        } else{
            char saved = direction;
            direction = dir;
            updateVelocity();
            x += velocityX;
            y += velocityY;

            boolean passable = !collision.collideWall(this, blockManager);

            x -= velocityX;
            y -= velocityY;
            direction = saved;
            updateVelocity();
            return passable;
        }
    }

    public void randomDirection() {
        chooseNextDirection();
        direction = nextDirection;
        updateVelocity();
    }

    @Override
    public void update() {
        // At every tile boundary, re-evaluate direction choice
        if (isAligned()) {
            chooseNextDirection();
        }
    }

    @Override
    public void move(boolean canTurn) {
        // Apply the chosen next direction immediately when aligned
        if (isAligned()) {
            direction = nextDirection;
            updateVelocity();
        }
        x += velocityX;
        y += velocityY;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Image img = activeImage();
        if (img == null) return;
        g2d.drawImage(img, x, y, tileSize, tileSize, null);
    }
}