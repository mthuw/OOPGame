package games.pacman;

import games.pacman.tile.BlockManager;

import java.awt.*;
import static games.pacman.GamePanel.tileSize;

public class GameStatus extends games.core.GameStatus {
    private int lives = 3;
    private int score = 0;
    private boolean boardCleared = false;

    public int getLives(){
        return lives;
    }

    public int getScore(){
        return score;
    }

    public boolean isBoardCleared() {
        return boardCleared;
    }

    public boolean boardClear(BlockManager blockManager) {
        return blockManager != null && blockManager.getFoods().isEmpty();
    }

    public boolean isOutLives(){
        return lives < 1;
    }

    public void onBoardClear() {
        boardCleared = true;
    }

    @Override
    public void restart() {
        gameOver = false;
        boardCleared = false;
        lives = 3;
        score = 0;
    }

    public void overLives(){
        if (lives>0){
            lives--;
        }
        if (isOutLives()){
            gameOver = true;
        }
    }
    public void updateScore(int points){
        score += points;
    }

    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        if (gameOver) {
            g2d.drawString("Game Over: " + getScore(), tileSize/2, tileSize/2);
        } else if (boardCleared) {
            g2d.drawString("You Win! - Score: " + getScore(), GamePanel.tileSize / 2, GamePanel.tileSize / 2);
        }
        else {
            g2d.drawString("x" + getLives() + " Score: " + getScore(), tileSize/2, tileSize/2);
        }
    }
}
