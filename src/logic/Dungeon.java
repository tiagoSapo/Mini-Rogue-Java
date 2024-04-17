
package logic;

import java.io.Serializable;

public class Dungeon implements Serializable {
    
    private final int LAST_AREA = 15;

    private int area;
    private int monster;

    public Dungeon() {
        area = 1;
        monster = 0;
    }
    
    // Sets
    public void setInitialArea(int area) {
        setArea(area);
        monster = 0;
    }
    public void setArea(int i) {
        if (i >= 0 && i <= LAST_AREA)
            area = i;
    }
    public void setMonsterHp(int i) {

        if (i >= 0 && i <= 30) {

            monster = i;
        }
        else if (i < 0)
            monster = 0;
        else if (i >= 30)
            monster = 30;
    }
    
    // Gets
    public int getArea() {
        return area;
    }
    public int getMonster() {
        return monster;
    }
    public int getCurrentLevel() {

        if (area == 1 || area == 2)
            return 1;
        else if (area == 3 || area == 4)
            return 2;
        else if ((area == 5 || area == 6) || area == 7)
            return 3;
        else if ((area == 8 || area == 9) || area == 10)
            return 4;
        else if ((area == 11 || area == 12) || (area == 13 || area >= 14))
            return 5;
        else
            return -1;
    }

    public boolean monsterAlive() {
        return !(monster <= 0);
    }

    public boolean LastArea() {
        return (((area == 2 || area == 4) || (area == 7 || area == 10)) || area == 14);
    }
    public boolean LastArea(int i) {
        return (((i == 2 || i == 4) || (i == 7 || i == 10)) || i == 14);
    }
    
    public void nextArea() {
        setArea(getArea() + 1);
    }
    public boolean won() {
        return area >= LAST_AREA;
    }
    public int getMonsterHp() {
        return monster;
    }
    
    @Override
    public String toString() {
        return "Dungeon: area = " + getArea() + ", monster = " + monster + ", ganhou = " + won();
    }


}
