
package logic.cards;

import java.io.Serializable;
import logic.Player;

public class Treasure extends Card implements Serializable{
    
    public Treasure() {
        super("TREASURE");
    }
    
    public void use(int option, Player p, boolean defeated) {
        if (defeated)
            p.setGold(p.getGold() + 2);
        else
            p.setGold(p.getGold() + 1);
        
        if (option == 1)
            p.setArmor(p.getArmor() + 1);
        else if (option == 2)
            p.setXp(p.getXp() + 2);
        else if (option == 3)
            p.setFire(p.getFire() + 1);
        else if (option == 4)
            p.setIce(p.getIce() + 1);
        else if (option == 5)
            p.setPoison(p.getPoison() + 1);
        else if (option == 6)
            p.setHeal(p.getHeal() + 1);
        
    }
    
}
