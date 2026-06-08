package games.core;

public abstract class GameStatus {
    protected boolean gameOver;
    protected int score;

    public boolean isGameOver(){
        return gameOver;
    }
    public void onGameOver(){
        gameOver = true;
    }
    public abstract void restart();

}
