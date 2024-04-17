
package logic.cards;

import java.io.Serializable;
import logic.Player;

public class Resting extends Card implements Serializable{
    
    private final int FOOD = 1, XP = 1, HP = 2;
    
    public Resting() {
        super("RESTING");
    }
    
    public void use(String kind, Player p) {
        if (kind.equalsIgnoreCase("Weapon"))
            p.setXp(p.getXp() + XP);
        else if (kind.equalsIgnoreCase("Ration"))
            p.setFood(p.getFood() + FOOD);
        else if (kind.equalsIgnoreCase("Heal"))
            p.setHp(p.getHp() + HP);
    }
    
}
