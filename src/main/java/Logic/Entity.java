package Logic;

/**
 * Created by Max on 2/1/2017.
 */
public abstract class Entity {

    protected int x, y, xSpeed, ySpeed;


    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(){
        this.x += xSpeed;
        this.y += ySpeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
