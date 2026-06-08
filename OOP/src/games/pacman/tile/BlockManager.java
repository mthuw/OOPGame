package games.pacman.tile;

import games.pacman.GamePanel;
import games.pacman.Ghost;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockManager {
    private int spawnX = 0;
    private int spawnY = 0;

    private int spawnRedX = 0, spawnRedY = 0;
    private int spawnBlueX = 0, spawnBlueY = 0;
    private int spawnPinkX = 0, spawnPinkY = 0;
    private int spawnOrangeX = 0, spawnOrangeY = 0;

    private final List<Block> blocks = new ArrayList<>();
    private final List<Block> walls  = new ArrayList<>();
    private final List<Block> foods  = new ArrayList<>();
    private final List<Ghost> ghosts = new ArrayList<>();

    private final String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "X       bpo       X",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    public BlockManager() {
        loadMap();
    }

    public void loadMap() {
        blocks.clear();
        walls.clear();
        foods.clear();

        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap[row].length(); col++) {
                char ch = tileMap[row].charAt(col);
                int x = col * GamePanel.tileSize;
                int y = row * GamePanel.tileSize;

                switch (ch) {
                    case 'X' -> {
                        Block wall = new Block(x, y, Block.Type.WALL);
                        blocks.add(wall);
                        walls.add(wall);
                    }
                    case ' ' -> {
                        Block dot = new Block(x, y, Block.Type.DOT);
                        blocks.add(dot);
                        foods.add(dot); // also tracked in foods for collision
                    }
                    case 'P' -> {
                        blocks.add(new Block(x, y, Block.Type.PACMAN));
                        spawnX = x;
                        spawnY = y;
                    }
                    case 'r' -> { spawnRedX = x; spawnRedY = y; }
                    case 'b' -> { spawnBlueX = x; spawnBlueY = y; }
                    case 'p' -> { spawnPinkX = x; spawnPinkY = y; }
                    case 'o' -> { spawnOrangeX = x; spawnOrangeY = y; }
                }
            }
        }
    }

    private Ghost createGhost(Ghost.GhostColor color, int x, int y) {
        Ghost ghost = new Ghost(color);
        ghost.setBlockManager(this);
        ghost.randomDirection();
        ghost.setX(x);
        ghost.setY(y);
        return ghost;
    }

    public void spawnGhosts() {
        ghosts.clear();
        ghosts.add(createGhost(Ghost.GhostColor.RED, spawnRedX, spawnRedY));
        ghosts.add(createGhost(Ghost.GhostColor.BLUE, spawnBlueX, spawnBlueY));
        ghosts.add(createGhost(Ghost.GhostColor.PINK, spawnPinkX, spawnPinkY));
        ghosts.add(createGhost(Ghost.GhostColor.ORANGE, spawnOrangeX, spawnOrangeY));
    }

    public List<Ghost> getGhosts() { return ghosts; }

    public int getSpawnX() { return spawnX; }
    public int getSpawnY() { return spawnY; }

    public List<Block> getFoods()  { return foods; }
    public List<Block> getWalls()  { return walls; }


    public void draw(Graphics2D g2) {
        for (Block b : blocks) b.draw(g2);
    }
}