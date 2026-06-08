package games.dinosim;

import games.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import static games.dinosim.GamePanel.TILE_SIZE;
import static games.dinosim.GamePanel.GROUND_Y;

public class Player extends Entity {
    protected Image idle, jump, run, land;
    private boolean isOnGround;

    private static final int OFFSET_X = 25;
    private static final int OFFSET_Y = 3;
    private static final int SOLID_WIDTH = 30;
    private static final int SOLID_HEIGHT = 58;

    private final KeyHandler key;

    public Player(KeyHandler key){
        this.key = key;
        setDefaultValue();
        solid = new Rectangle(x,y,TILE_SIZE, TILE_SIZE);
        getPlayerImage();
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    private void setDefaultValue(){
        x = 50;
        y = GROUND_Y;
        setSpeed(4);
        isOnGround = true;
    }
    private Image loadImage(String path) {
        URL imageUrl = getClass().getResource(path);

        if (imageUrl == null) {
            throw new IllegalArgumentException("Player image not found: " + path);
        }

        ImageIcon icon = new ImageIcon(imageUrl);

        if (icon.getIconWidth() <= 0) {
            throw new IllegalStateException("Player image could not be loaded: " + path);
        }

        return icon.getImage();
    }

    private void getPlayerImage() {
        try {
            idle = loadImage("/games/dinosim/res/player/Female_idle.gif");
            run  = loadImage("/games/dinosim/res/player/Female_run.gif");
            jump = loadImage("/games/dinosim/res/player/Female_jump.gif");
            land = loadImage("/games/dinosim/res/player/Female_land.gif");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error while loading player images: " + e.getMessage());
            throw e;
        }
    }
    public void update(){
        if (key.jump && isOnGround){
            velocityY = -18;
            isOnGround = false;
        }
        if (!isOnGround){
            if (velocityY < 0){
                velocityY += 1;
            } else {
                velocityY +=1;
                if (velocityY > 15){
                    velocityY = 15;
                }
            }
            if (key.down){
                velocityY += 3;
            }
        }
        y += velocityY;

        if (y >= GROUND_Y){
            y = GROUND_Y;
            velocityY = 0;
            isOnGround = true;
        }

        solid.x = x + OFFSET_X;
        solid.y = y + OFFSET_Y;
        solid.width = SOLID_WIDTH;
        solid.height = SOLID_HEIGHT;
    }

    public void draw(Graphics2D g2d){
        Image currentImg;

        if (key.jump){
            currentImg = jump;
        }
        else if (key.down){
            currentImg = land;
        } else {
            currentImg = run;
        }
        g2d.drawImage(currentImg,x,y, TILE_SIZE, TILE_SIZE, null);

        g2d.setColor(Color.RED);
        g2d.drawRect(solid.x, solid.y, solid.width, solid.height);
    }
}
