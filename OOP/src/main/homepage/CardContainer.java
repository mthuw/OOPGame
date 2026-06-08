package main.homepage;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardContainer {
    private final List<GameCard> cards;

    public CardContainer(List<GameCard> cards){
        this.cards = cards;
    }

    public JPanel createPanel(){
        JPanel panel = new JPanel();

        for (GameCard card : cards){
            panel.add(card);
        }
        return panel;
    }
}
