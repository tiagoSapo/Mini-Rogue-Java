
package logic.cards;

import java.io.Serializable;
import logic.Player;

public class Merchant extends Card implements Serializable{
    
    public Merchant() {
        super("MERCHANT");
    }
    
    // Retorna 1 se erro, 0 se sucesso
    public int buy(Player p, String option, String option2) {
        
        if (option.equalsIgnoreCase("Ration") && (p.getGold() - 1 >= 0)) {
            p.setGold(p.getGold() - 1);
            p.setFood(p.getFood() + 1);
            return 0;
        }
        else if (option.equalsIgnoreCase("SmallHealth") && (p.getGold() - 1 >= 0)) {
            p.setGold(p.getGold() - 1);
            p.setHp(p.getHp() + 1);
            return 0;
        }
        else if (option.equalsIgnoreCase("BigHealth") && (p.getGold() - 3 >= 0)) {
            p.setGold(p.getGold() - 3);
            p.setHp(p.getHp() + 4);
            return 0;
        }
        else if (option.equalsIgnoreCase("Armor") && (p.getGold() - 6 >= 0)) {
            p.setGold(p.getGold() - 6);
            p.setArmor(p.getArmor() + 1);
            return 0;
        }
        else if (option.equalsIgnoreCase("Spell") && (p.getGold() - 8 >= 0)) {
            
            if (option2.equalsIgnoreCase("Fire")) {
                p.setGold(p.getGold() - 8);
                p.setFire(p.getFire() + 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Ice")) {
                p.setGold(p.getGold() - 8);
                p.setIce(p.getIce() + 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Poison")) {
                p.setGold(p.getGold() - 8);
                p.setPoison(p.getPoison() + 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Heal")) {
                p.setGold(p.getGold() - 8);
                p.setHeal(p.getHeal() + 1);
                return 0;
            }
        }
        
        return 1;    
    }
    
    // Retorna 1 se erro, 0 se sucesso
    public int sell(Player p, String option, String option2) {
        
        
        if (option.equalsIgnoreCase("Armor") && (p.getArmor() - 1 >= 0)) {
            p.setGold(p.getGold() + 3);
            p.setArmor(p.getArmor() - 1);
            return 0;
        }       
        else if (option.equalsIgnoreCase("Spell")) {
            
            if (option2.equalsIgnoreCase("Fire") && (p.getFire() - 1 >= 0)) {
                p.setGold(p.getGold() + 4);
                p.setFire(p.getFire() - 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Ice") && (p.getIce() - 1 >= 0)) {
                p.setGold(p.getGold() + 4);
                p.setIce(p.getIce() - 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Poison") && (p.getPoison() - 1 >= 0)) {
                p.setGold(p.getGold() + 4);
                p.setPoison(p.getPoison() - 1);
                return 0;
            }
            else if (option2.equalsIgnoreCase("Heal") && (p.getHeal() - 1 >= 0)) {
                p.setGold(p.getGold() + 4);
                p.setHeal(p.getHeal() - 1);
                return 0;
            }
        }
        return 1;
    }
    
}
