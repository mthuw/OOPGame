package games.dinosim;

import games.entity.Entity;

import java.awt.*;
import java.util.List;
import java.util.Random;
import static games.dinosim.GamePanel.SCREEN_WIDTH;
import static games.dinosim.GamePanel.SCREEN_HEIGHT;

public class GameStatus extends games.core.GameStatus {
    private static final Random random = new Random();

    private int scoreTimer = 0;
    private int obsTimer = 0;
    private int nextSpawnTime = 120;
    private int minGap = 300;
    
    public GameStatus() {}

    public void update(List<Entity> entities) {
        if (gameOver) return;
        updateScore();
        spawnObstacles(entities);
    }

    private void updateScore() {
        scoreTimer++;
        if (scoreTimer >= 6) {
            score++;
            scoreTimer = 0;
        }
    }

    private void spawnObstacles(List<Entity> entities) {
        obsTimer++;
        if (obsTimer < nextSpawnTime) return;

        Obstacles lastObs = null;
        for (Entity e : entities){
            if (e instanceof Obstacles obs){
                lastObs = obs;
            }
        }
        boolean canSpawn = (lastObs == null) || (lastObs.getX() <= SCREEN_WIDTH - minGap);
        if (canSpawn){
            entities.add(new Obstacles());
            obsTimer = 0;
            nextSpawnTime = GamePanel.FPS + random.nextInt(GamePanel.FPS * 2);
            minGap = 50 + random.nextInt(350);
        }
    }

    public void restart() {
        gameOver = false;
        score = 0;
        scoreTimer = 0;
        obsTimer = 0;
        nextSpawnTime = 120;
        minGap = 300;
    }

    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreText = String.format("%05d", score);
        int textWidth = g2d.getFontMetrics().stringWidth(scoreText);
        g2d.setColor(new Color(0, 0, 0, 120));
        g2d.fillRoundRect(SCREEN_WIDTH - textWidth - 30, 15, textWidth + 20, 40, 10, 10);
        g2d.setColor(Color.WHITE);
        g2d.drawString(scoreText, SCREEN_WIDTH - textWidth - 20, 45);
    }

    public void gameOverDraw(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0,150));
        g2d.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 60));
        g2d.drawString("GAME OVER", SCREEN_WIDTH / 2 - 180, SCREEN_HEIGHT / 2);

        g2d.setFont(new Font("Arial", Font.PLAIN, 25));
        g2d.drawString("Press R to restart", SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 90);

    }
}