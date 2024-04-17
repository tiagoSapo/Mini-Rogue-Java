
package logic.states;

import java.io.Serializable;
import java.util.ArrayList;
import logic.Dice;
import logic.GameData;
import logic.Player;
import logic.dif.Difficulty;

public class StateAdapter implements IStates, Serializable {
    
    private GameData game;

    public StateAdapter(GameData g) {
        this.game = g;
    }

    // Sets e Gets
    public GameData getGame() {
        return game;
    }
    public void setGame(GameData game) {
        this.game = game;
    }

    
    // State Beginning
    @Override
    public IStates setDifficulty(Difficulty d){ return this;}
    @Override
    public IStates setInitialArea(int area){ return this;}
    @Override
    public IStates startGame(){ return this;}
    
    // State CardSelection
    @Override
    public IStates solveResting(){ return this;}
    @Override
    public IStates solveTreasure(int o){ return this;}
    @Override
    public IStates solveEvent(int option){ return this;}
    @Override
    public IStates solveTrap(int i){ return this;}
    @Override
    public IStates solveTrade(){ return this;}
    @Override
    public IStates solveMonster(){ return this;}

    // State RestingSelection
    @Override
    public IStates selectOption(String kind){ return this;}

    // State Trade
    @Override
    public IStates skip(){ return this;}
    @Override
    public IStates buy(String s1, String s2){ return this;}
    @Override
    public IStates sell(String s1, String s2){ return this;}

    
    // All
    @Override
    public IStates quit(){ return this;}
    @Override
    public IStates roll6(int i) { return this; }
    @Override
    public IStates feats(int i){ return this; }
    @Override
    public ArrayList<Dice> getDiceValue(){ return null; }
    @Override
    public IStates solveAttack(){ return this; }
    
    
    @Override
    public IStates solveSpell(String kind) { return this; }
    @Override
    public IStates attack(){ return this; }

}
