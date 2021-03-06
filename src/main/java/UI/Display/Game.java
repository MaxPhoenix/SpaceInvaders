package UI.Display;

import Logic.Player;
import UI.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Max on 2/3/2017.
 */
public class Game extends Canvas implements Runnable{

    //Resoution
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12*9;

    //Animation
    private Thread animator;
    private volatile boolean running = false;
    private volatile boolean gameOver = false;

    //States
    private State gameState;
    private State gameOverState;
    private State menuState;

    //in- game timing
    private static long tickCounter;

    public Game(){
        new Window(WIDTH, HEIGHT, "Space invaders",this);
        this.gameState = new GameState(this);
        this.gameOverState = new GameOverState(this);
        this.menuState = new MenuState(this);
        StateHandler.setCurrentState(gameState);

    }

    private void tick(){
        if(StateHandler.getCurrentState() != null)
            StateHandler.getCurrentState().tick();
        //TODO refractor these lines of code
        if(StateHandler.getCurrentState() instanceof GameState){
            if(((GameState) StateHandler.getCurrentState()).playerShip().getEntity() instanceof Player)
                if(((Player) ((GameState) StateHandler.getCurrentState()).playerShip().getEntity()).getHealth() <= 0)
                    StateHandler.setCurrentState(gameOverState);
        }
        return;
    }

    private void render(){
        BufferStrategy buffer = this.getBufferStrategy();
        if(buffer == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics gameGraphics = buffer.getDrawGraphics();

        //Before Drawing
        gameGraphics.clearRect(0, 0, WIDTH, HEIGHT);

        //Draw here!
        gameGraphics.setColor( new Color(0x000000) );
        gameGraphics.fillRect(0, 0, WIDTH, HEIGHT);
        if(StateHandler.getCurrentState() != null)
            StateHandler.getCurrentState().render(gameGraphics);

        //Drawing finished!
        gameGraphics.dispose();
        buffer.show();
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start(){
        if(!running || animator == null){
            animator = new Thread(this);
            animator.start();
            running = true;
        }
    }

    public synchronized void stop(){
        try {
            animator.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static int clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        if(var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String args[]){
        new Game();
    }

}
