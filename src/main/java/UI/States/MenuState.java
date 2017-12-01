package UI.States;

import UI.Display.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 2/3/2017.
 */
public class MenuState extends State {

    private BufferedImage backGround;

    public MenuState(Game game) {
        super(game);

        try {
            backGround = ImageIO.read(new File("res\\textures\\indice2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backGround, 0, 0, Game.WIDTH, Game.HEIGHT, null);
    }
}
