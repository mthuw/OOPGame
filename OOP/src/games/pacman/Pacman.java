package games.pacman;
import games.pacman.tile.Block;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

import static games.pacman.GamePanel.tileSize;

public class Pacman extends MovingEntity {
    protected Image pacmanUp, pacmanDown, pacmanLeft, pacmanRight;
    private final KeyHandler key;
    private final GameStatus gameStatus;
    private final Collision collision = new Collision();

    public Pacman(KeyHandler key, GameStatus gameStatus) {
        this.key = key;
        this.gameStatus = gameStatus;
        getPacmanImage();
    }

    private void getPacmanImage() {
        pacmanUp = new ImageIcon(getClass().getResource("/games/pacman/res/pacman/pacmanUp.png")).getImage();
        pacmanDown = new ImageIcon(getClass().getResource("/games/pacman/res/pacman/pacmanDown.png")).getImage();
        pacmanRight = new ImageIcon(getClass().getResource("/games/pacman/res/pacman/pacmanRight.png")).getImage();
        pacmanLeft = new ImageIcon(getClass().getResource("/games/pacman/res/pacman/pacmanLeft.png")).getImage();
    }

    @Override
    public void update() {
        if (key.up) nextDirection = 'U';
        else if (key.down) nextDirection = 'D';
        else if (key.left) nextDirection = 'L';
        else if (key.right) nextDirection = 'R';
    }

    public void move(boolean canTurn) {
        if (canTurn){
            x = Math.round((float) x / tileSize) * tileSize;
            y = Math.round((float) y / tileSize) * tileSize;
            direction = nextDirection;
        }
        updateVelocity();
        x += velocityX;
        y += velocityY;
        eatDots();
    }

    private void eatDots() {
        if (blockManager == null) return;

        List<Block> foods  = blockManager.getFoods();
        Iterator<Block> it = foods.iterator();

        while (it.hasNext()) {
            Block dot = it.next();
            if (collision.collides(this, dot)) {
                dot.active = false;
                it.remove();        // remove from foods list
                gameStatus.updateScore(10);
            }
        }
        if (gameStatus.boardClear(blockManager)) {
            gameStatus.onBoardClear();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Image currentImg = switch (direction) {
            case 'U' -> pacmanUp;
            case 'D' -> pacmanDown;
            case 'L' -> pacmanLeft;
            default  -> pacmanRight;
        };
        g2d.drawImage(currentImg, x, y, tileSize, tileSize, null);
    }
}