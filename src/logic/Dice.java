
package logic;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
    
    private int value;
    
    public Dice() {
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
    }
    
    public int getNumber() {
        return value;
    }
    public void setNumber(int i) {
        value = i;
    }
    public void generateAnotherNumber() {
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
    }
    
}
