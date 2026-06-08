package games.pacman;

import java.awt.event.KeyEvent;

public class KeyHandler extends games.core.KeyHandler {
    public boolean up,down, right, left;

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key==KeyEvent.VK_UP){
            up = true;
        }
        if ( key==KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
            down = true;
        }
        if ( key==KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = true;
        }
        if ( key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key==KeyEvent.VK_UP) {
            up = false;
        }
        if (key==KeyEvent.VK_S || key == KeyEvent.VK_DOWN ){
            down = false;
        }
        if ( key==KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = false;
        }
        if ( key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = false;
        }
    }
}
