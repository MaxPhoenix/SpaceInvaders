package UI.Graphics;

import Logic.Creature;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 2/3/2017.
 */
public class EnemyShip extends GraphicObject {

    private int leftBound, rightBound;
    private BufferedImage healthy, damaged, busted;

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

        try {
            this.healthy = ImageIO.read(new File("res\\textures\\healthShip.png"));
            this.damaged = ImageIO.read(new File("res\\textures\\midShip.png"));
            this.busted = ImageIO.read(new File("res\\textures\\damageShip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            g.drawImage(healthy, x ,y, width, height,null);
        else if(currentHealth < over70 && currentHealth >= arround50)
            g.drawImage(damaged, x, y, width, height,null);
        else if(currentHealth < arround50 && currentHealth > 0)
            g.drawImage(busted, x, y, width, height,null);
        else return;


    }

    public void shoot(Handler handler){
        Bullet misil = new Bullet(this.x, this.y, 10, 10, ID.EnemyProjectile);
        handler.addObject(misil);
        misil.setLaunched(true);
        misil.setySpeed(4);
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
