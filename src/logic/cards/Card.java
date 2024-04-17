
package logic.cards;

import java.io.Serializable;

public class Card implements Serializable{
    
    private final String name;
    private boolean used;
    
    public Card(String n) {
        name = n;
        used = false;
    }
    
    public String getName() {
        return name;
    }
    
    public void switchUsed() {
        used = !used;
    }
    public boolean getUsed() {
        return used;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
