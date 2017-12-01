package UI.States;

import UI.Display.Game;

import java.awt.*;

/**
 * Created by Max on 2/3/2017.
 */
public abstract class State {
    //Class stuff
    protected Game game;

    public State(Game game){
        this.game = game;
    }


    public abstract void tick();
    public abstract void render(Graphics g);



}
