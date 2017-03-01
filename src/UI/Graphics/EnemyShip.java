package UI.Graphics;

import Logic.Creature;

import java.awt.*;

/**
 * Created by Max on 2/3/2017.
 */
public class EnemyShip extends GraphicObject {

    private int leftBound, rightBound;

    public static class Builder{

        private int x, y;
        private ID id;
        private int width, height;
        private int leftBound, rightBound;

        public Builder(int x, int y, ID id){
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public Builder height(int height){
            this.height = height;
            return this;
        }

        public Builder leftBound(int leftBound){
            this.leftBound = leftBound;
            return this;
        }

        public Builder rightBound(int rightBound){
            this.rightBound = rightBound;
            return this;
        }

        public EnemyShip build(){
            return new EnemyShip(this);
        }

    }

    private EnemyShip(Builder builder) {
        super(builder.x, builder.y,builder.width, builder.height, builder.id);
        this.width = builder.width;
        this.height = builder.height;
        this.leftBound = builder.leftBound;
        this.rightBound = builder.rightBound;
        this.entity = new Creature.Builder(x, y).maxHealth(100).build();
        getEntity().setxSpeed(1);
    }

    @Override
    public void tick() {
        x += getEntity().getxSpeed();
        if( x < leftBound || x > rightBound) {
            int reverse = getEntity().getxSpeed() * -1;
            getEntity().setxSpeed(reverse);
        }
    }

    @Override
    public void render(Graphics g) {
        int maxHealth = ((Creature)getEntity() ).getMaxHealth();
        int currentHealth = ( (Creature)getEntity() ).getHealth();
        int over70 = (int) (maxHealth/1.5);
        int arround50 = (int) (maxHealth/2.5);
        if( currentHealth >= over70 )
            g.setColor( new Color(0x00ff00));
        else if(currentHealth < over70 && currentHealth >= arround50)
            g.setColor( new Color(0x0000ff));
        else if(currentHealth < arround50 && currentHealth > 0)
            g.setColor( new Color(0xff0000));
        else return;
        g.fillRect(x, y, width, height);

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(int leftBound) {
        this.leftBound = leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public void setRightBound(int rightBound) {
        this.rightBound = rightBound;
    }
}
