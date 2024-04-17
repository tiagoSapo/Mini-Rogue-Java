
package logic;

import java.io.Serializable;
import logic.dif.Difficulty;
import logic.states.BeginningState;
import logic.states.IStates;

public class MiniRogue implements Serializable{
    
    private GameData gameData;
    private IStates state; 
    
    public MiniRogue() {
        gameData = new GameData();
        setState(new BeginningState(gameData));
    }

    // Sets e Gets
    public GameData getGameLogic() {
        return gameData;
    }
    public void setGameLogic(GameData gameData) {
        this.gameData = gameData;
    }
    public IStates getState() {
        return state;
    }    
    public void setState(IStates state) {
        this.state = state;
    }        
    
     // Methods retrieve data from the game           
    public int getColumn() {
        return gameData.getColumn();
    }
    public boolean getAlreadyVisitedMonster() {
        return gameData.getAlreadyVisitedMonster();
    }
    public Player getPlayer() {
        return gameData.getPlayer();
    }
    public Dungeon getDungeon() {
        return gameData.getDungeon();
    }
    public boolean lastColumn() {
        return gameData.lastColumn();
    }
    public void nextColumn() {
        gameData.nextColumn();
    }
    public boolean hasWon() {
        return gameData.hasWon();
    }
    public void resetGame() {
        gameData.resetGame();
    }
    public boolean isAlive() {
        return gameData.isAlive();
    }
    
    
    
    // Methods States
    public void setDifficulty(Difficulty d) {
        setState(getState().setDifficulty(d));
    }
    public void setInitialArea(int area) {
        setState(getState().setInitialArea(area));
    }
    public void startGame() {
        setState(getState().startGame());
    }
    
    // State CardSelection
    public void solveResting() {
        setState(getState().solveResting());
    }
    public void solveTreasure(int o) {
        setState(getState().solveTreasure(o));
    }
    public void solveEvent(int option) {
        setState(getState().solveEvent(option));
    }
    public void solveTrap(int i) {
        setState(getState().solveTrap(i));
    }
    public void solveTrade() {
        setState(getState().solveTrade());
    }
    public void solveMonster() {
        setState(getState().solveMonster());
    }

    // State RestingSelection
    public void selectOption(String s) {
        setState(getState().selectOption(s));
    }

    // State Trade
    public void skip() {
        setState(getState().skip());
    }
    public void buy(String s1, String s2) {
        setState(getState().buy(s1, s2));
    }
    public void sell(String s1, String s2) {
        setState(getState().sell(s1, s2));
    }

    
    
    // State Combat
    public void roll6(int i) {
        setState(getState().roll6(i));
    }
    public void feats(int i) {
        setState(getState().feats(i));
    }
    public void solveAttack() {
        setState(getState().solveAttack());
    }
    
    // State DEFENSE
    public void solveSpell(String kind) {
        setState(getState().solveSpell(kind));
    }
    public void attack() {
        setState(getState().attack());
    }
    
    
    
    
    // All
    public void quit() {
        setState(getState().quit());
    }
}
