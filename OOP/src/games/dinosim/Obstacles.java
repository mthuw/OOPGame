package games.dinosim;

import games.entity.Entity;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import static games.dinosim.GamePanel.TILE_SIZE;
import static games.dinosim.GamePanel.GROUND_Y;

public class Obstacles extends Entity {
    private Image obs1, obs2, obs3;
    private Image currentImg;
    private int drawWidth;
    private int drawHeight;
    private int solidOffsetX;
    private int solidOffsetY;
    //actual visible solid
    private int solidWidth;
    private int solidHeight;
    private static final Random random = new Random();

    public Obstacles(){
        x = GamePanel.SCREEN_WIDTH;
        getObstacleImage();
        randomObs();
        y = GROUND_Y + (TILE_SIZE - drawHeight);
        solid = new Rectangle(x + solidOffsetX, y + solidOffsetY,solidWidth,solidHeight);
    }

    private void getObstacleImage(){
        obs1 = new ImageIcon(getClass().getResource("/games/dinosim/res/obstacles/chainsaw.png")).getImage();
        obs2 = new ImageIcon(getClass().getResource("/games/dinosim/res/obstacles/traffic.png")).getImage();
        obs3 = new ImageIcon(getClass().getResource("/games/dinosim/res/obstacles/On.png")).getImage();
    }

    private void randomObs() {
        int rand_num = random.nextInt(3);
        if (rand_num == 0) {
            currentImg = obs1;        //chainsaw
            drawWidth  = 70;
            drawHeight = 70;
            solidOffsetX = 8;
            solidOffsetY = 8;
            solidWidth  = 54;
            solidHeight = 54;
        } else if (rand_num == 1) {
            currentImg = obs2;        //traffic barrier
            drawWidth  = 110;
            drawHeight = 35;
            solidOffsetX = 8;
            solidOffsetY = 0;
            solidWidth  = 94;
            solidHeight = 35;
        } else {
            currentImg = obs3;        //fire
            drawWidth  = 100;
            drawHeight = 80;
            solidOffsetX = 7;
            solidOffsetY = 7;
            solidWidth  = 79;
            solidHeight = 60;
        }
    }
    @Override
    public void update(){
        x -= getSpeed();
        solid.x = x + solidOffsetX;
        if (x < -TILE_SIZE){
            setAlive(false);
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(currentImg, x, y, drawWidth, drawHeight, null);

        g2d.setColor(Color.RED);
        g2d.drawRect(solid.x, solid.y, solid.width, solid.height);

    }
}
