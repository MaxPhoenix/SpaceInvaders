package UI.Graphics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Max on 2/3/2017.
 */
public class Handler {

    private ArrayList<GraphicObject> objects = new ArrayList<GraphicObject>();

    public void tick(){
        for(GraphicObject object : objects){
            object.tick();
        }
    }

    public void render(Graphics g){
        for(GraphicObject object : objects){
            object.render(g);
        }
    }

    public void addObject(GraphicObject object){
        this.objects.add(object);
    }

    public void removeObject(GraphicObject object){
        this.objects.remove(object);
    }

    public ArrayList<GraphicObject> getObjects(){
        return this.objects;
    }
}
