
package logic;

import java.util.Observable;
import java.util.Observer;
import logic.dif.Difficulty;
import logic.states.IStates;

public class ObservableGame extends Observable {
    
    private MiniRogue m;
    
    public ObservableGame() {
        m = new MiniRogue();
    }
    
    public MiniRogue getMiniRogue() {
        return m;
    }
    public void setGameModel(MiniRogue mr) {        
        m = mr;
        
        setChanged();
        notifyObservers();
    }  
    public GameData getGameLogic() {
        return m.getGameLogic();
    }
    
    // new Method
    public void setGameLogic(GameData g) {
        m.setGameLogic(g);
    }
    
    
    public IStates getState() {
        return m.getState();
    }     
    
     // Methods retrieve data from the game           
    public int getColumn() {
        return m.getColumn();
    }
    public boolean getAlreadyVisitedMonster() {
        return m.getAlreadyVisitedMonster();
    }
    public Player getPlayer() {
        return m.getPlayer();
    }
    public Dungeon getDungeon() {
        return m.getDungeon();
    }
    public boolean lastColumn() {
        return m.lastColumn();
    }
    public void nextColumn() {
        m.nextColumn();
    }
    public boolean hasWon() {
        return m.hasWon();
    }
    public void resetGame() {
        m.resetGame();
    }
    public boolean isAlive() {
        return m.isAlive();
    }
    
    
    // Methods States
    public void setDifficulty(Difficulty d) {
        m.setState(getState().setDifficulty(d));
        setChanged();
        notifyObservers();
    }
    public void setInitialArea(int area) {
        m.setState(getState().setInitialArea(area));
        setChanged();
        notifyObservers();
    }
    public void startGame() {
        m.setState(getState().startGame());
        setChanged();
        notifyObservers();
    }
    
    // State CardSelection
    public void solveResting() {
        m.setState(getState().solveResting());
        setChanged();
        notifyObservers();
    }
    public void solveTreasure(int o) {
        m.setState(getState().solveTreasure(o));
        setChanged();
        notifyObservers();
    }
    public void solveEvent(int option) {
        m.setState(getState().solveEvent(option));
        setChanged();
        notifyObservers();
    }
    public void solveTrap(int i) {
        m.setState(getState().solveTrap(i));
        setChanged();
        notifyObservers();
    }
    public void solveTrade() {
        m.setState(getState().solveTrade());
        setChanged();
        notifyObservers();
    }
    public void solveMonster() {
        m.setState(getState().solveMonster());
        setChanged();
        notifyObservers();
    }

    // State RestingSelection
    public void selectOption(String s) {
        m.setState(getState().selectOption(s));
        setChanged();
        notifyObservers();
    }

    // State Trade
    public void skip() {
        m.setState(getState().skip());
        setChanged();
        notifyObservers();
    }
    public void buy(String s1, String s2) {
        m.setState(getState().buy(s1, s2));
        setChanged();
        notifyObservers();
    }
    public void sell(String s1, String s2) {
        m.setState(getState().sell(s1, s2));
        setChanged();
        notifyObservers();
    }

    
    
    // State Combat
    public void roll6(int i) {
        m.setState(getState().roll6(i));
        setChanged();
        notifyObservers();
    }
    public void feats(int i) {
        m.setState(getState().feats(i));
        setChanged();
        notifyObservers();
    }
    public void solveAttack() {
        m.setState(getState().solveAttack());
        setChanged();
        notifyObservers();
    }
    
    // State DEFENSE
    public void solveSpell(String kind) {
        m.setState(getState().solveSpell(kind));
        setChanged();
        notifyObservers();
    }
    public void attack() {
        m.setState(getState().attack());
        setChanged();
        notifyObservers();
    }
    
    
    
    
    // All
    public void quit() {
        m.setState(getState().quit());
        setChanged();
        notifyObservers();
    }
}
