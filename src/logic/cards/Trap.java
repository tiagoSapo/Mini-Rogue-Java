
package logic.cards;

import java.io.Serializable;
import logic.Player;

public class Trap extends Card implements Serializable{
    
    public Trap() {
        super("TRAP");
    }
    
    public void use(Player p, int option) {
        if (option == 1)
            p.setFood(p.getFood() - 1);
        else if (option == 2)
            p.setGold(p.getGold() - 1);
        else if (option == 3)
            p.setArmor(p.getArmor() - 1);
        else if (option == 4)
            p.setHp(p.getHp() - 1);
        else if (option == 5)
            p.setXp(p.getXp() - 1);
        else if (option == 6)
            p.setHp(p.getHp() - 2);
    }
    
}
