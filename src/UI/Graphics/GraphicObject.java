package UI.Graphics;

import Logic.Entity;

import java.awt.*;

/**
 * Created by Max on 2/3/2017.
 */
public abstract class GraphicObject {

    protected int x, y;
    protected ID id;
    protected Entity entity;
    protected int width, height;

    public GraphicObject(int x, int y, int width, int height,ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public boolean intersectsWith(GraphicObject obj){
        //si esta dentro del rango horizontal
        int objHorRange = obj.x + obj.width;
        int objVerRange = obj.y + obj.height;
        int thisHorRange = this.x + this.width;
        int thisVerRange = this.y + this.height;

        if( ( (this.x >= obj.x && this.x <= objHorRange) || (obj.x >= this.x && obj.x <= thisHorRange) )
           && ( (this.y >= obj.y && this.y <= objVerRange) || (obj.y >= this.y && obj.y <= thisVerRange) ) )
              return true;
        return false;
    }


    public abstract void tick();
    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public Entity getEntity(){ return this.entity; }
}
