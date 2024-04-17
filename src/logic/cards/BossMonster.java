
package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;

public class BossMonster extends Card implements Serializable{
    
    class Data implements Serializable{
        
        private int dmg, gold, xp, level, hp;
        private String name;
        
        public Data(String n, int d, int g, int x, int l, int h) {
            name = n;
            dmg = d;
            gold = g;
            xp = x;
            level = l;
            hp = h;
        }
        public int getDamage() {
            return dmg;
        } 
        public int getXP() {
            return xp;
        }
        public int getGOLD() {
            return gold;
        }
        public int getLevel() {
            return level;
        }
        public int getHp() {
            return hp;
        }
        
    }
    
    private final ArrayList<Data> monsters;
    
    public BossMonster() {
        super("BOSS MONSTER");
        monsters = new ArrayList<>();
        
        monsters.add(new Data("Undead Gigant", 2, 2, 2, 1, 10));
        monsters.add(new Data("Skelteon Lord", 4, 2, 3, 2, 15));
        monsters.add(new Data("Undead Lord", 6, 3, 4, 3, 20));
        monsters.add(new Data("Serpent Demon", 8, 3, 5, 4, 25));
        monsters.add(new Data("Og's Remains", 10, 0, 0, 5, 30));
    }
    
    public int getDamage(int p) {
        for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getLevel() == p)
                return monsters.get(i).getDamage();
        return 0;
    }
    public int getHpMonster(int p) {
       for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getLevel() == p)
                return monsters.get(i).getHp();
        return 0; 
    }
    public int getXP(int p) {
       for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getLevel() == p)
                return monsters.get(i).getXP();
        return 0; 
    }
    public int getGOLD(int p) {
       for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getLevel() == p)
                return monsters.get(i).getGOLD();
        return 0; 
    }
    
}
