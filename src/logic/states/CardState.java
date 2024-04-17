
package logic.states;

import java.io.Serializable;
import logic.Dungeon;
import logic.GameData;
import logic.Player;
import logic.cards.*;

public class CardState extends StateAdapter implements Serializable{

    public CardState(GameData game) {
        super(game);
    }
    
    @Override
    public IStates solveResting() {
        return new SelectionState(getGame());  
    }
    @Override
    public IStates solveTreasure(int o) {
        Treasure t = new Treasure();

        if (getGame().getAlreadyVisitedMonster())
            t.use(o, getGame().getPlayer(), true);
        else
            t.use(o, getGame().getPlayer(), false);
        return this;
    }
    @Override
    public IStates solveEvent(int option) {
        Event e = new Event();
        e.use(getGame().getPlayer(), option);
        return this;
    }
    @Override
    public IStates solveTrap(int i) {
        Trap t = new Trap();
        t.use(getGame().getPlayer(), i);
        return this;
    }
    @Override
    public IStates solveTrade() {    
        return new TradingState(getGame());
    }
    @Override
    public IStates solveMonster() {
        return new AttackState(getGame());
    }
}
