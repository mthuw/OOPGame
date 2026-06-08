package games.entity;
import java.awt.*;

public abstract class Entity {
    private boolean alive = true;
    private static int speed;
    protected int velocityX = 0;
    protected int velocityY = 0;

    protected int x,y;
    protected int width, height;
    public Rectangle solid;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Entity.speed = speed;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setVelocityY(int velocityY){
        this.velocityY = velocityY;
    }

    public int getX() {
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    public Entity(){}
    public abstract void update();
    public abstract void draw(Graphics2D g2d);


}
