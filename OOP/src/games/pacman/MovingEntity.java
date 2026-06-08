package games.pacman;

import games.entity.Entity;
import games.pacman.tile.BlockManager;

import java.awt.*;

public abstract class MovingEntity extends Entity {
    protected BlockManager blockManager;
    protected char direction     = 'R';
    protected char nextDirection = 'R';

    private static final int pSpeed = 3;

    private final Collision collision = new Collision();

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setNextDirection(char nextDirection) {
        this.nextDirection = nextDirection;
    }

    public void setBlockManager(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    public void updateVelocity() {
        velocityX = 0;
        velocityY = 0;
        switch (direction) {
            case 'U' -> velocityY = -pSpeed;
            case 'D' -> velocityY =  pSpeed;
            case 'L' -> velocityX = -pSpeed;
            case 'R' -> velocityX =  pSpeed;
        }
    }

    public void move(boolean canTurn) {
        if (canTurn) direction = nextDirection;
        updateVelocity();
        x += velocityX;
        y += velocityY;
    }
    public void undoMove() {
        x -= velocityX;
        y -= velocityY;
        velocityX = 0;
        velocityY = 0;
    }

    public boolean canTurn() {
        if (nextDirection == direction) return false;

        // Snap to nearest tile grid before testing the turn
        int snappedX = Math.round((float) x / GamePanel.tileSize) * GamePanel.tileSize;
        int snappedY = Math.round((float) y / GamePanel.tileSize) * GamePanel.tileSize;

        int savedX = x, savedY = y;
        char savedDirection = direction;

        x = snappedX;
        y = snappedY;
        direction = nextDirection;
        updateVelocity();
        x += velocityX;
        y += velocityY;

        boolean ok = !collision.collideWall(this, blockManager);

        // Restore everything
        x = savedX;
        y = savedY;
        direction = savedDirection;
        updateVelocity();
        return ok;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics2D g2d);
}