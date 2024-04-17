
package logic;

import java.io.Serializable;
import logic.dif.Difficulty;

public class Player implements Serializable{

    private String dif;
    private int armor, hp, gold, food;
    private int fire, ice, poison, heal;
    private int xp, rank;
    
    private boolean poison_activated;
    private int attack;

    public Player(Difficulty d) {
        setXp(0);
        armor = d.getArmor(); hp = d.getHp(); gold = d.getGold(); food = d.getFood();
        fire = 0; ice = 0; poison = 0; heal = 0;
        dif = d.getName();
        poison_activated = false;
        attack = 0;
    }

    // Sets
    public void setDificulty(Difficulty d) {
        armor = d.getArmor(); hp = d.getHp(); gold = d.getGold(); food = d.getFood();
        dif = d.getName();
    }
    public void setArmor(int i) {
        if (i < 0) {
            armor = 0;
            setHp(getHp() + i);
        }
        else if (i > 5)
            armor = 5;
        else
            armor = i;
    }
    public void setHp(int i) {
        
         if (i < 0)
            hp = 0;
        else if (i > 20)
            hp = 20;
        else
            hp = i;
    }
    public void setFood(int i) {
        
        if (i < 0)
            food = 0;
        else if (i > 6)
            food = 6;
        else
            food = i;
    }
    public void setGold(int i) {
        
        if (i < 0)
            gold = 0;
        else if (i > 20)
            gold = 20;
        else
            gold = i;
    }
    
    public void setFire(int i) {
        
        if (sumSpells() >= 2)
            return;
        else if (i < 0)
            fire = 0;
        else if (i > 2)
            fire = 2;
        else
            fire = i;
    }
    public void setIce(int i) {
        
        if (sumSpells() >= 2)
            return;
        else if (i < 0)
            ice = 0;
        else if (i > 2)
            ice = 2;
        else
            ice = i;
    }
    public void setPoison(int i) {
        
        if (sumSpells() >= 2)
            return;
        else if (i < 0)
            poison = 0;
        else if (i > 2)
            poison = 2;
        else
            poison = i;
    }
    public void setHeal(int i) {
        if (sumSpells() >= 2)
            return;
        else if (i < 0)
            heal = 0;
        else if (i > 2)
            heal = 2;
        else
            heal = i;
    }
    public void setXp(int i) {
        if (i >= 0 && i <= 18)
            xp = i;
        else if (i > 18)
            setHp(getHp() + 1);
        else
            xp = 0;
        updateRank();
    }
    
    // Gets
    public int getFood() {
        return food;
    }
    public int getArmor() {
        return armor;
    }
    public int getXp() {
        return xp;
    }
    public int getHp() {
        return hp;
    }
    public int getGold() {
        return gold;
    }
    public int getFire() {
        return fire;
    }
    public int getIce() {
        return ice;
    }
    public int getPoison() {
        return poison;
    }
    public int getHeal() {
        return heal;
    }
    public int getRank() {
        return rank;
    }
    
    
    // Other
    public int sumSpells() {
        return fire + ice + poison + heal;
    }
    public void updateRank() {
        if (xp >= 0 && xp < 6)
            rank = 1;
        else if (xp >= 6 && xp < 12)
            rank = 1;
        else if (xp >= 12 && xp < 18)
            rank = 1;
        else if (xp >= 18)
            rank = 4;
        else
            rank = -1; // Nunca entrara aqui
    }
    public boolean alive() {
        return hp > 0;
    }
    public void nextArea() {
        setFood(getFood() - 1);
    }
    public void setPoisonActivated(boolean b) {
        poison_activated = b;
    }
    public boolean getPoisonActivated() {
        return poison_activated;
    }
    public int getAttack(){
        return attack;
    }
    public void setAttack(int i) {
        attack = i;
    }
    
    @Override
    public String toString() {
        String s = new String();
        s += "Armor = " + armor + ", HP = " + hp + ", Gold = " + gold + ", Food = " + food + "\n";
        s += "Fire = " + fire + ", Ice = " + ice + ", Poison = " + poison + ", Heal = " + heal + "\n";
        s += "XP = " + xp + ", Rank = " + rank + ", NDados = " + rank;
        return s; 
    }

}
