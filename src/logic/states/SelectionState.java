
package logic.states;

import java.io.Serializable;
import logic.GameData;
import logic.Player;
import logic.cards.Resting;

public class SelectionState extends StateAdapter implements Serializable{

    public SelectionState(GameData game) {
        super(game);
    }
    
    @Override
    public IStates selectOption(String kind) {
        Resting r = new Resting();
        r.use(kind, getGame().getPlayer());
        return new CardState(getGame());
    }
    
}
