
package logic.cards;

import java.io.Serializable;
import logic.Player;

public class Event extends Card implements Serializable{
    
    public Event() {
        super("EVENT");
    }
    
    public void use(Player p, int option) {
        if (option == 1)
            p.setFood(p.getFood() + 1);
        else if (option == 2)
            p.setHp(p.getHp() + 2);
        else if (option == 3)
            p.setGold(p.getGold() + 2);
        else if (option == 4)
            p.setXp(p.getXp() + 2);
        else if (option == 5) 
            p.setArmor(p.getArmor() + 1);
    }
}
