
package logic.states;

import java.io.Serializable;
import java.util.ArrayList;
import logic.Dice;
import logic.Player;
import logic.dif.Difficulty;

public interface IStates extends Serializable {
    
    // State Beginning
    public IStates setDifficulty(Difficulty d);
    public IStates setInitialArea(int area);
    public IStates startGame();
    
    // State CardSelection
    public IStates solveResting();
    public IStates solveTreasure(int o);
    public IStates solveEvent(int option);
    public IStates solveTrap(int i);
    public IStates solveTrade();
    public IStates solveMonster();

    // State RestingSelection
    public IStates selectOption(String kind);

    // State Trade
    public IStates skip();
    public IStates buy(String s1, String s2);
    public IStates sell(String s1, String s2);
    
    // State ATTACK
    public IStates roll6(int i);
    public IStates feats(int i);
    public IStates solveAttack();
    public ArrayList<Dice> getDiceValue();
    
    // State DEFENSE
    public IStates solveSpell(String kind);
    public IStates attack();

    // All
    public IStates quit();
}
