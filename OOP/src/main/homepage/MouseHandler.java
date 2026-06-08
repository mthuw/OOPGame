package main.homepage;
import java.awt.event.*;

public class MouseHandler implements ActionListener{
    private GameCard gameCard;
    public MouseHandler(GameCard gameCard){
        this.gameCard = gameCard;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        handlePlay();
    }

    private void handlePlay(){
        System.out.println("Play game: " + gameCard.getTitle());
        gameCard.navigateGame();
    }
    public GameCard getGameCard(){
        return gameCard;
    }
}
