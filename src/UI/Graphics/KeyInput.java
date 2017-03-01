package UI.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Max on 2/3/2017.
 */
public class KeyInput extends KeyAdapter{

    private Handler handler;
    private Handler bulletHandler;
    private int launchCounter = 0;

    public KeyInput(Handler handler, Handler auxHandler){
        this.handler = handler;
        this.bulletHandler = auxHandler;
    }

    public void keyPressed(KeyEvent e){
        int asciiCode = e.getKeyCode();

        for(GraphicObject object : handler.getObjects()){
            if(object.getId() == ID.Player){
                if( asciiCode == KeyEvent.VK_A) object.getEntity().setxSpeed(-5);
                if( asciiCode == KeyEvent.VK_D) object.getEntity().setxSpeed(5);
                if( asciiCode == KeyEvent.VK_O) {
                    launchCounter++;
                    if(launchCounter % 2 == 0){
                        Bullet misil = new Bullet(object.x,object.y, 10, 10, ID.PlayerProyectile);
                        bulletHandler.addObject( misil );
                        misil.setLaunched(true);
                        misil.setySpeed(-4);
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int asciiCode = e.getKeyCode();

        for(GraphicObject object : handler.getObjects()){
            if(object.getId() == ID.Player){
                if( asciiCode == KeyEvent.VK_A) object.getEntity().setxSpeed(0);
                if( asciiCode == KeyEvent.VK_D) object.getEntity().setxSpeed(0);
            }

        }
    }


}
