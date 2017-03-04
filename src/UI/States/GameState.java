package UI.States;

import Logic.Creature;
import Logic.Player;
import UI.Display.Game;
import UI.Graphics.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static UI.Graphics.ID.Enemy;

/**
 * Created by Max on 2/3/2017.
 */
public class GameState extends State {

    //resolution
    private int width, height;

    //Game objects handler
    private Handler objectsHandler;
    private SpaceShip playerShip;
    private EnemyShip firingShip;
    private ArrayList <EnemyShip> enemyShips = new ArrayList<EnemyShip>();
    private Random number = new Random(0);
    private long launchTick = 0;
    private BufferedImage backGround;
    private static int score;

    public GameState(Game game) {
        super(game);

        try {
            backGround = ImageIO.read(new File("res\\textures\\spaceWar.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = game.WIDTH;
        height = game.HEIGHT;

        objectsHandler = new Handler();
        playerShip = new SpaceShip(width / 2, (int) (height / 1.2), width/ 20 , height/ 20 ,  ID.Player);
        objectsHandler.addObject(playerShip);

        generateEnemies();

        this.game.addKeyListener( new KeyInput(objectsHandler) );
    }


    private void generateEnemies(){
        EnemyShip enemy = null;
        int startY = height / 9; //dibujado de primer fila

        for(int x = 0; x < 4 ; x++) {
            int startX = width / 11; //dibujado de primer columna

            int leftBound = startX - 100; //limite izq. para mov. de naves
            int rightBound = width / 7;   //limite der. para movimiento de naves

            for (int y = 0; y < 11; y++){
                enemy = new EnemyShip.Builder(startX, startY, Enemy).width(50).height(50).leftBound(leftBound).rightBound(rightBound).build();
                leftBound += 100;
                rightBound += 100;
                enemyShips.add(enemy);
                objectsHandler.addObject(enemy);
                startX += 100;
            }

            startY += 100;
        }
    }

    @Override
    public void tick() {
        launchTick++;

        if (launchTick % 10 == 0 && enemyShips.size() > 0) {
            int indexOfEnemies = number.nextInt(enemyShips.size());
            firingShip = enemyShips.get(indexOfEnemies);
            firingShip.shoot(objectsHandler);
        }

        for(int i = 0; i < objectsHandler.getObjectList().size() ; i++){
            GraphicObject bullet = objectsHandler.getObjectList().get(i);
            if( bullet instanceof  Bullet){
                if (bullet.getId() == ID.EnemyProjectile){
                    if (((Bullet) bullet).hitPlayer(playerShip) || ((Bullet) bullet).isCrashed()){
                        ((Bullet) bullet).setCrashed(true);
                        objectsHandler.removeObject(bullet);
                        if(((Bullet) bullet).hitPlayer(playerShip))
                            playerShip.increaseScoreBy(-30);
                    }
                }
                if(bullet.getId() == ID.PlayerProyectile) {
                    if (((Bullet) bullet).crashed(enemyShips) || ((Bullet) bullet).isCrashed()) {
                        ((Bullet) bullet).setCrashed(true);
                        objectsHandler.removeObject(bullet);
                        if( ((Bullet) bullet).crashed(enemyShips) )
                            playerShip.increaseScoreBy(50);
                    }
                }
            }
        }

        for(int i = 0; i < enemyShips.size(); i++){
            EnemyShip enemy = enemyShips.get(i);
            Creature enemyEntity = (Creature) enemy.getEntity();
            if( enemyEntity.getHealth()  <= 0 ){
                enemyShips.remove(i);
            }
        }

        objectsHandler.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backGround, 0, 0, width, height, null);
        int playerMaxHealth = ( (Player) playerShip.getEntity() ).getMaxHealth() *2;
        int playerHealth = ( (Player) playerShip.getEntity() ).getHealth() *2;
        int barXPosition =  width/20;
        int barYPosition = height/25;
        g.setColor(new Color(0xbbbbbb));
        g.drawRect(barXPosition, barYPosition,playerMaxHealth, 40);
        g.setColor(new Color(0xff0000));
        g.fillRect(barXPosition, barYPosition,playerMaxHealth, 40);
        g.setColor(Color.green);
        g.fillRect(barXPosition, barYPosition,playerHealth, 40);
        objectsHandler.render(g);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        String score = "Player score: "+ playerShip.getScore();
        g.drawString(score, (int) (width/1.5), height/12);
    }

    public SpaceShip playerShip(){
        return this.playerShip;
    }
    public static int getScore(){
        return score;
    }
}
