
package logic.states;

import java.io.Serializable;
import logic.GameData;
import logic.dif.Difficulty;

public class BeginningState extends StateAdapter implements Serializable{
    
    public BeginningState(GameData g) {
        super(g);
    }
    
    @Override
    public IStates setDifficulty(Difficulty d) {
        getGame().setDifficulty(d);
        return this;
    }
    @Override
    public IStates startGame(){
        return new CardState(getGame());
    }
    
    @Override
    public IStates setInitialArea(int area) {
        getGame().getDungeon().setInitialArea(area);
        return this;
    }
}
