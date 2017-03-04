package UI.Graphics;

import Logic.Player;
import UI.Display.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 2/3/2017.
 */
public class SpaceShip extends GraphicObject {

    private BufferedImage shipImage;
    private int score;

    public SpaceShip(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        this.entity = new Player.PlayerBuilder(x, y).maxHealth(10).maxArmor(200).build();

        try {
            this.shipImage = ImageIO.read(new File("res\\textures\\playerShip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick(){
        x += getEntity().getxSpeed();
        y += getEntity().getySpeed();

        x = Game.clamp(x, 0, Game.WIDTH - 50);
        y = Game.clamp(y, 0, Game.HEIGHT - 50);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(shipImage, x, y, width, height, null);
    }

    public void shoot(Handler handler){
        Bullet misil = new Bullet((this.x + width/2) , this.y, 10, 10, ID.PlayerProyectile);
        handler.addObject(misil);
        misil.setLaunched(true);
        misil.setySpeed(- 3);
    }

    public int getScore() {return score;}

    public void increaseScoreBy(int score) {this.score += score;}
}
