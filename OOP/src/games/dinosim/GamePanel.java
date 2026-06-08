package games.dinosim;

import games.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends games.core.GamePanel {

    private final Image backgroundCity;
    // SCREEN SETTING
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 400;
    public static final int TILE_SIZE = 94;
    public static final int GROUND_Y = SCREEN_HEIGHT - TILE_SIZE;
    private int bgX = 0;

    private final KeyHandler key = new KeyHandler();
    private final ArrayList<Entity> entities = new ArrayList<>();
    private final Player player = new Player(key);
    private final GameStatus gameStatus = new GameStatus();
    private final Collision collision = new Collision();

    public GamePanel(CardLayout cardLayout, JPanel container, JFrame jframe) {
        super(cardLayout,container, jframe);
        backgroundCity = new ImageIcon(getClass().getResource("/games/dinosim/res/map/10.png")).getImage();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true); //improve game rendering performance
        this.addKeyListener(key);
        this.setFocusable(true);
        setupGame();
    }

    private void setupGame(){
        entities.clear();
        entities.add(player);
        entities.add(new Obstacles());
    }

    @Override
    public void update(){
        if (gameStatus.isGameOver()){
            if (key.restart) restart();
            if (key.esc) returnHome();
            return;
        }
        if (key.esc){
            returnHome();
            return;
        }

        bgX -= 4;
        if (bgX <= -SCREEN_WIDTH){
            bgX = 0; //reset it when off-screen
        }
        gameStatus.update(entities);

        for (int i=entities.size()-1; i>=0; i--){
            Entity e = entities.get(i);
            e.update();
            if (!e.isAlive()){
                entities.remove(i);
            }
        }
        collision.checkCollision(entities, player, gameStatus);
    }
    @Override
    public void restart(){
        gameStatus.restart();
        player.setX(50);
        player.setY(GamePanel.GROUND_Y);
        player.setVelocityY(0);
        player.setOnGround(true);
        key.esc = false;
        key.restart = false;
        setupGame();
    }
    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(backgroundCity, bgX, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        g2d.drawImage(backgroundCity, bgX + SCREEN_WIDTH,0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

        for (Entity e: entities) e.draw(g2d);

        gameStatus.draw(g2d);
        if (gameStatus.isGameOver()){
            gameStatus.gameOverDraw(g2d);
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}

