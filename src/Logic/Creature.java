package Logic;

/**
 * Created by Max on 2/1/2017.
 */
public class Creature extends Entity{

    private int health, maxHealth;


    public static class Builder {

        private int x, y, health;

        public Builder (int x, int y){
            this.x = x;
            this.y = y;
        }

        public Builder maxHealth(int health){
            this.health = health;
            return this;
        }

        public Creature build(){
            return new Creature(this);
        }
    }

    protected Creature(Builder builder) {
        super(builder.x, builder.y);
        this.health = builder.health;
        this.maxHealth = builder.health;
    }



    public void refillHealth(){
        this.health = this.maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void modifyHealthBy(int health) {
        this.health += health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }


}
