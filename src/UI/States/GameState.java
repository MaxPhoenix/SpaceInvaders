package UI.States;

import Logic.Creature;
import Logic.Player;
import UI.Display.Game;
import UI.Graphics.*;

import java.awt.*;
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
    private Handler bulletHandler;
    private SpaceShip playerShip;
    private EnemyShip firingShip;
    private ArrayList <EnemyShip> enemyShips = new ArrayList<EnemyShip>();
    private ArrayList <EnemyShip> deadShips = new ArrayList<EnemyShip>();
    private ArrayList <Bullet> crashedBullets = new ArrayList<Bullet> ();
    private Random number = new Random(0);
    private long launchTick = 0;

    public GameState(Game game) {
        super(game);

        width = game.WIDTH;
        height = game.HEIGHT;

        objectsHandler = new Handler();
        playerShip = new SpaceShip(width / 2, (int) (height / 1.2), width/ 9 , height/ 9 ,  ID.Player);
        objectsHandler.addObject(playerShip);

        generateEnemies();

        bulletHandler = new Handler();
        this.game.addKeyListener( new KeyInput(objectsHandler, bulletHandler) );
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
        if (launchTick % 10 == 0) {
            int indexOfEnemies = number.nextInt(enemyShips.size());
            firingShip = enemyShips.get(indexOfEnemies);
            bulletHandler.addObject(new Bullet(firingShip.getX(), firingShip.getY(), 10, 10, ID.EnemyProjectile));
        }

        for(GraphicObject bullet : bulletHandler.getObjects()){
            if( bullet instanceof  Bullet){
                if (bullet.getId() == ID.EnemyProjectile){
                    ((Bullet) bullet).setLaunched(true);
                    ((Bullet) bullet).setySpeed( 3 );
                }
            }
        }

        for(GraphicObject  bullet : bulletHandler.getObjects()){
           if(bullet instanceof  Bullet){
               if(bullet.getId() == ID.PlayerProyectile) {
                   if (((Bullet) bullet).crashed(enemyShips) || ((Bullet) bullet).isCrashed()) {
                       ((Bullet) bullet).setCrashed(true);
                       crashedBullets.add((Bullet) bullet);
                   }
               }
               else{
                   if (((Bullet) bullet).hitPlayer(playerShip) || ((Bullet) bullet).isCrashed()){
                       ((Bullet) bullet).setCrashed(true);
                       crashedBullets.add((Bullet)bullet);
                   }
               }
           }
        }
        for(Bullet  bullet : crashedBullets){
            bulletHandler.removeObject(bullet);
        }

        for(EnemyShip enemy : enemyShips){
            if(enemy.getEntity() instanceof Creature){
               if( ( (Creature) enemy.getEntity() ).getHealth()  <= 0 ){
                    deadShips.add(enemy);
                }
            }
        }
        for(EnemyShip enemy : deadShips){
            enemyShips.remove(enemy);
        }

        objectsHandler.tick();
        bulletHandler.tick();
    }

    @Override
    public void render(Graphics g) {
        objectsHandler.render(g);
        bulletHandler.render(g);
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
    }
}
