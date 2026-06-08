package games.pacman;

import games.entity.Entity;
import games.pacman.tile.Block;
import games.pacman.tile.BlockManager;

import static games.pacman.GamePanel.tileSize;

public class Collision {

    public boolean collides(Entity e, Block block) {
        int padding = 4;
        return e.getX() + padding < block.getX() + tileSize &&
                e.getX() + tileSize - padding > block.getX() &&
                e.getY() + padding < block.getY() + tileSize &&
                e.getY() + tileSize - padding > block.getY();
    }

    public boolean collides(Entity e1, Entity e2) {
        int padding = 4;
        return e1.getX() + padding < e2.getX() + tileSize &&
                e1.getX() + tileSize - padding > e2.getX() &&
                e1.getY() + padding < e2.getY() + tileSize &&
                e1.getY() + tileSize - padding > e2.getY();
    }

    public boolean collideWall(Entity e, BlockManager blockManager) {
        for (Block block : blockManager.getWalls()) {
            if (e.getX() < block.getX() + GamePanel.tileSize &&
                    e.getX() + GamePanel.tileSize > block.getX() &&
                    e.getY() < block.getY() + GamePanel.tileSize &&
                    e.getY() + GamePanel.tileSize > block.getY()) {
                return true;
            }
        }
        return false;
    }
}
