
package logic.dif;

import java.io.Serializable;

public class Difficulty implements Serializable{
    
    private final String name;
    private final int armor, hp, gold, food;
    
    public Difficulty(int a, int h, int g, int f, String n) {
        armor = a; hp = h; gold = g; food = f; name = n;
    }
    
    public int getArmor() {
        return armor;
    }
    public int getHp() {
        return hp;
    }
    public int getGold() {
        return gold;
    }
    public int getFood() {
        return food;
    }
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Dificuldade = " + getName() + "Armor = " + getArmor() + ", Hp = " + getHp() + ", Gold = " + getGold() + ", Food = " + getFood();
    }
}
