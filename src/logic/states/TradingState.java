
package logic.states;

import java.io.Serializable;
import logic.GameData;
import logic.Player;
import logic.cards.Merchant;

public class TradingState extends StateAdapter implements Serializable {

    public TradingState(GameData game) {
        super(game);
    }
    
    @Override
    public IStates skip() {
        return new CardState(getGame());
    }
    @Override
    public IStates buy(String s1, String s2) {
        Merchant m = new Merchant();
        m.buy(getGame().getPlayer(), s1, s2);
        return this;
    }
    @Override
    public IStates sell(String s1, String s2) {
        Merchant m = new Merchant();
        m.sell(getGame().getPlayer(), s1, s2);
        return this;
    }
}
