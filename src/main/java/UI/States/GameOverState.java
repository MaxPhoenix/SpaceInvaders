package UI.States;

import UI.Display.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 3/3/2017.
 */
public class GameOverState extends State  {

    private BufferedImage backGround;
    private JDialog backToMenu;
    public GameOverState(Game game) {
        super(game);
        try {
            backGround = ImageIO.read(new File("res\\textures\\gameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToMenu = new JDialog((Frame) null, "Return to main menu");
        backToMenu.setUndecorated(true);
        backToMenu.setLayout(new BorderLayout());
        backToMenu.pack();
        backToMenu.setLocationRelativeTo(null);
        backToMenu.setBounds((int) (Game.WIDTH/1.5), (int) (Game.HEIGHT/2.5), Game.WIDTH/5, Game.HEIGHT/10);
        JButton button = new JButton("return... you get the picture");
        button.setBounds((int) (Game.WIDTH/1.5), (int) (Game.HEIGHT/2.5), Game.WIDTH/5, Game.HEIGHT/10);
        backToMenu.add(button);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StateHandler.setCurrentState(new MenuState(game));
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backGround, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        String message = "Game Over.";
        g.drawString(message, (int) (Game.WIDTH/2.5), (int) (Game.HEIGHT/3.5));
        String score = "Total score: "+ GameState.getScore();
        g.drawString(score, (int) (Game.WIDTH/2.5), Game.HEIGHT/2);
        backToMenu .setVisible(true);
    }
}
