package games.pacman.tile;

import games.entity.Entity;
import games.pacman.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Block extends Entity {
    protected Image wallImage, powerFood;
    public enum Type { WALL, DOT, PACMAN, GHOST };
    private final Type type;
    public boolean active = true;

    public Block(int x, int y,Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        loadImages();
    }

    private void loadImages(){
        wallImage = new ImageIcon(getClass().getResource("/games/pacman/res/objects/wall.png")).getImage();
        powerFood = new ImageIcon(getClass().getResource("/games/pacman/res/objects/powerFood.png")).getImage();
    }

    @Override
    public void update() {
    }

    public void draw(Graphics2D g2d) {
        if (!active) return;
        switch (type) {
            case WALL -> g2d.drawImage(wallImage, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            case DOT  -> g2d.drawImage(powerFood, x+14, y+14, 5, 5, null);
        }
    }
}
