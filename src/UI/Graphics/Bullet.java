package UI.Graphics;

import Logic.Creature;
import Logic.Player;
import UI.Display.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Max on 2/20/2017.
 */
public class Bullet extends GraphicObject{

    private int xSpeed, ySpeed;
    private boolean launched = false;
    private boolean crashed = false;
    private long launchTimer = 0 ; //the amount of time sinced the misil is launched

    public Bullet(int x, int y, int width, int height, ID id) {
        super(x, y, width, height,  id);
    }

    @Override
    public void tick() {
        if(!crashed){
            this.y += ySpeed;
            launchTimer++;
        }
        //in case it didn't crashed and stepped out of the window
        if(this.y == Game.HEIGHT || this.y == 0)
            this.crashed = true;
    }

    @Override
    public void render(Graphics g) {
       if(launched && !crashed){
           if(id == ID.PlayerProyectile)
                g.setColor(Color.yellow);
           if(id == ID.EnemyProjectile)
               g.setColor(Color.CYAN);
       }
        g.fillRect(x, y, width, height);
    }

    /**
     * Verifies if the bullet crashed with any enemyShip of the list, decreasing the ship's health in case of colision
     * @param enemies the list of the enemies to check
     * @return true if the bullet hit one of the ships, decreaing the ship's health
     */
    public boolean crashed(ArrayList<EnemyShip> enemies){
        for(EnemyShip enemy : enemies){
           if( intersectsWith(enemy) ){
               ( (Creature) enemy.getEntity() ).modifyHealthBy(-10);
               return true;
           }
        }
        return false;
    }

    /**
     * Verifies if the bullet crashed with the player's ship, decreasing the ship's health in case of colision
     * @param player
     * @return true if the player was hit, decreasing his/her life
     */
    public boolean hitPlayer(SpaceShip player){
        if(intersectsWith(player)){
            ( (Player)player.getEntity() ).modifyHealthBy(-2);
            return true;
        }
        return false;
    }


    public int getxSpeed() {return xSpeed;}

    public void setxSpeed(int xSpeed) {this.xSpeed = xSpeed;}

    public int getySpeed() {return ySpeed;}

    public void setySpeed(int ySpeed) {this.ySpeed = ySpeed;}

    public boolean isLaunched() {return launched;}

    public void setLaunched(boolean launched) {this.launched = launched;}

    public void setCrashed(boolean crashed){ this.crashed = crashed; }

    public boolean isCrashed(){ return this.crashed; }
}
