package games.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class KeyHandler implements KeyListener {
    public boolean restart;
    public boolean esc = false;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_R) restart = true;
        if (key == KeyEvent.VK_ESCAPE) esc = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_R) restart = false;
        if (key == KeyEvent.VK_ESCAPE) esc = false;
    }
}
