package games.core;

import main.Main;

import javax.swing.*;
import java.awt.*;

public abstract class GamePanel extends JPanel implements Runnable {
    public static final int FPS = 60;
    protected CardLayout cardLayout;
    protected JPanel container;
    protected JFrame jframe;
    protected Thread gameThread;

    public abstract void update();
    public abstract void restart();
    public abstract void draw(Graphics2D g2d);

    public GamePanel(CardLayout cardLayout, JPanel container, JFrame jframe){
        this.cardLayout = cardLayout;
        this.container = container;
        this.jframe = jframe;

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                container.setPreferredSize(getPreferredSize());
                jframe.pack();
                jframe.setLocationRelativeTo(null);
                startGameThread();
                // fires when CardLayout shows panel
                requestFocusInWindow();
            }

            @Override
            public void componentHidden(java.awt.event.ComponentEvent e) {
                stopGame();
                // fires when CardLayout hides this panel
            }
        });
    }


    @Override
    public void run() {
        double interval = 1e9/FPS;
        double nextDrawTime = System.nanoTime() + interval;
        while (gameThread != null){
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1e6;
                if (remainingTime<0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += interval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startGameThread(){
        if (gameThread != null && gameThread.isAlive()) return;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void stopGame(){
        gameThread = null;
    }

    public void returnHome(){
        stopGame();
        restart();
        cardLayout.show(container, Main.HOME);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw((Graphics2D) g);
    }
}
