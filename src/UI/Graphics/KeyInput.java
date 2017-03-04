package UI.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Max on 2/3/2017.
 */
public class KeyInput extends KeyAdapter{

    private Handler handler;
    private int launchCounter = 0;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int asciiCode = e.getKeyCode();

        for(int i = 0 ; i < handler.getObjectList().size(); i++){
            GraphicObject object = handler.getObjectList().get(i);
            if(object.getId() == ID.Player){
                if( asciiCode == KeyEvent.VK_A) object.getEntity().setxSpeed(-5);
                if( asciiCode == KeyEvent.VK_D) object.getEntity().setxSpeed(5);
                if( asciiCode == KeyEvent.VK_O) {
                    launchCounter++;
                    if(launchCounter % 2 == 0){
                        ((SpaceShip) object).shoot(handler);
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int asciiCode = e.getKeyCode();

        for(int i = 0 ; i < handler.getObjectList().size(); i++){
            GraphicObject object = handler.getObjectList().get(i);
            if(object.getId() == ID.Player){
                if( asciiCode == KeyEvent.VK_A) object.getEntity().setxSpeed(0);
                if( asciiCode == KeyEvent.VK_D) object.getEntity().setxSpeed(0);
                if( asciiCode == KeyEvent.VK_O) {

                }
            }
        }
    }

}
