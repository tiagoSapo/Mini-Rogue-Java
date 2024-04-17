
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import logic.cards.*;
import logic.dif.*;

import java.util.Random;

public class GameData implements Serializable {
    
    private Player p;
    private Dungeon d;
    
    private int column;
    private boolean already_visited_monster;
    
    private ArrayList<Card> deck;
    
    public GameData() {
        resetGame();
    }

    // Gets
    public Player getPlayer() {
        return p;
    }
    public Dungeon getDungeon() {
        return d;
    }
    public int getColumn() {
        return column;
    }
    public boolean getAlreadyVisitedMonster() {
        return already_visited_monster;
    }
    
    // Sets
    public void setAlreadyVisitedMonster(boolean b) {
        already_visited_monster = b;
    }

    // Functions
    public boolean lastColumn() {
        if (getDungeon().LastArea() == true && column >= 5)
            return true;
        else if (getDungeon().LastArea() == false && column >= 4)
            return true;
        else
            return false;
    }
    public void nextColumn() {
        if (lastColumn()) {
            column = 1;
            deck.clear();
            deck = new ArrayList<>();
            deck.add(new Event());
            deck.add(new Merchant());
            deck.add(new Monster());
            deck.add(new Resting());
            deck.add(new Trap());
            deck.add(new Treasure());
            if (getDungeon().LastArea(getDungeon().getArea() + 1))
                deck.add(new BossMonster());
            
            already_visited_monster = false;
            getPlayer().nextArea();
            getDungeon().nextArea();
        }
        else
            column++;
        
    }
    public boolean hasWon() {
        return ((getDungeon().won()) && getPlayer().alive());
    }
    public void resetGame() {
        p = new Player(new Casual());
        d = new Dungeon(); 
        column = 1;
        already_visited_monster = false;
        
        deck = new ArrayList<>();
        deck.add(new Event());
        deck.add(new Merchant());
        deck.add(new Monster());
        deck.add(new Resting());
        deck.add(new Trap());
        deck.add(new Treasure());
        
    }
    public boolean isAlive() {
        return getPlayer().alive();
    }
    public Card getCard() {
        Random rand = new Random();
        while(true) {
            int n = rand.nextInt(deck.size());
            
            if (!deck.get(n).getUsed() && !(deck.get(n) instanceof BossMonster)) {
                deck.get(n).switchUsed();
                return deck.get(n);
            }
            else if (!deck.get(n).getUsed() && (deck.get(n) instanceof BossMonster && getColumn() >= 5)) {
                deck.get(n).switchUsed();
                return deck.get(n);
            }
            if (column >= 5 && deck.size() == 6)
                deck.add(new BossMonster());
        }
    }
    
    public ArrayList<Card> getCARDS() {
        return deck;
    }
    
    // Player
    public void setDifficulty(Difficulty d) {
        getPlayer().setDificulty(d);
    }
}
