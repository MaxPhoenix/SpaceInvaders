package UI.Graphics;

import Logic.Player;
import UI.Display.Game;

import java.awt.*;

/**
 * Created by Max on 2/3/2017.
 */
public class SpaceShip extends GraphicObject {

    public SpaceShip(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        this.entity = new Player.PlayerBuilder(x, y).maxHealth(200).maxArmor(200).build();
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
        g.setColor( new Color(0xff0000) );
        g.fillRect(x, y, 50, 50);
    }


}
