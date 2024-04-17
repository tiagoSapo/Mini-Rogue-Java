
package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster extends Card implements Serializable{
    
    class Data implements Serializable{
        
        private int dmg, reward, level;
        private String name;
        
        public Data(String n, int d, int r, int l) {
            name = n;
            dmg = d;
            reward = r;
            level = l;
            
        }
        
    }
    
    private final ArrayList<Data> monsters;
    
    public Monster() {
        
        super("MONSTER");
        monsters = new ArrayList<Data>();
        
        monsters.add(new Data("Undead Soldier", 2, 1, 1));
        monsters.add(new Data("Skelteon", 4, 1, 2));
        monsters.add(new Data("Undead Knight", 6, 2, 3));
        monsters.add(new Data("Serpent Soldier", 8, 2, 4));
        monsters.add(new Data("Og's Sanctum Guard", 10, 3, 5));
    }
    public int getDamage(int p) {
        return monsters.get(p - 1).dmg;
    }
    public int getReward(int p) {
        return monsters.get(p - 1).reward;
    }
    
}
