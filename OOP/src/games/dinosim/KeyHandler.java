package games.dinosim;

import java.awt.event.KeyEvent;

public class KeyHandler extends games.core.KeyHandler {
public boolean jump,down;

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key==KeyEvent.VK_UP){
            jump = true;
        }
        if (key == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyTyped(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE || key==KeyEvent.VK_UP) {
            jump = false;
        }
        if (key == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
