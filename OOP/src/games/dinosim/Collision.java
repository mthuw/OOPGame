package games.dinosim;

import games.entity.Entity;
import java.util.List;
public class Collision {
    public Collision() {}

    public void checkCollision(List<Entity> entities, Player player, GameStatus gameStatus) {
        for (Entity e : entities) {
            if (e instanceof Player) continue;
            //skip obstacles behind player
            if (e.solid.x + e.solid.width < player.solid.x){
                continue;
            }
            if (player.solid.intersects(e.solid)) {
                gameStatus.onGameOver();
                break;
            }
        }
    }
}