package Logic;

/**
 * Created by Max on 2/1/2017.
 */
public class Player extends Creature {


    private int armor, maxArmor;

    public static class PlayerBuilder extends Creature.Builder{

        private int armor, maxArmor ;

        public PlayerBuilder(int x, int y) {
            super(x, y);
        }


        public PlayerBuilder maxArmor(int armor){
            this.armor = armor;
            this.maxArmor = armor;
            return this;
        }

        public PlayerBuilder maxHealth(int health) {
            super.maxHealth(health);
            return this;
        }


        public Player build() {
            return new Player(this);
        }
    }

    private Player(PlayerBuilder playerBuilder) {
        super(playerBuilder);
        this.armor = playerBuilder.armor;
        this.maxArmor = playerBuilder.maxArmor;
    }


    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMaxArmor() {
        return maxArmor;
    }

    public void setMaxArmor(int maxArmor) {
        this.maxArmor = maxArmor;
    }

    public void reduceHealthBy(int number){ this.modifyHealthBy(number);}


}
