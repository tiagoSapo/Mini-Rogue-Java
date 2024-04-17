
package logic.states;

import java.io.Serializable;
import java.util.ArrayList;
import logic.Dice;
import logic.GameData;
import logic.cards.BossMonster;
import logic.cards.Monster;
import logic.cards.Treasure;

public class DefenseState extends StateAdapter implements Serializable{
    
    public boolean ice;
    public int damage;
    ArrayList<Dice> d;
    AttackState previous;
    
    public DefenseState(GameData g, ArrayList<Dice> d, int dmg6s, AttackState p) {
        super(g);
        ice = false;
        damage = dmg6s;
        this.d = d;
        previous = p;
    }
    @Override
    public IStates solveSpell(String kind) {
        if (kind.equalsIgnoreCase("Fire") && getGame().getPlayer().getFire() - 1 >= 0) {
            damage += 8;
            getGame().getPlayer().setFire(getGame().getPlayer().getFire() - 1);
        }
        else if (kind.equalsIgnoreCase("Ice") && getGame().getPlayer().getIce() - 1 >= 0) {
            ice = true;
            getGame().getPlayer().setIce(getGame().getPlayer().getIce() - 1);
        }
        else if (kind.equalsIgnoreCase("Poison") && getGame().getPlayer().getPoison() - 1 >= 0) {
            getGame().getPlayer().setPoisonActivated(true);
            getGame().getPlayer().setPoison(getGame().getPlayer().getPoison() - 1);
        }
        else if (kind.equalsIgnoreCase("Heal") && getGame().getPlayer().getHeal() - 1 >= 0) {
            getGame().getPlayer().setHp(getGame().getPlayer().getHp() + 8);
            getGame().getPlayer().setHeal(getGame().getPlayer().getHeal() - 1);
        }    
        
        return this;
    }
    @Override
    public IStates attack() {
        
        if (getGame().getPlayer().getPoisonActivated())
            damage += 5;
        
        // Calculate DMG from Dice
        for (int i = 0; i < d.size(); i++)
            if (d.get(i).getNumber() > 1 && d.get(i).getNumber() <= 6)
                damage += d.get(i).getNumber();
        
       
        if (getGame().getColumn() < 5) {
            

            // Attack
            getGame().getDungeon().setMonsterHp(getGame().getDungeon().getMonsterHp() - damage);
            
            
            if (ice && !getGame().getDungeon().monsterAlive()) {
                Monster m = new Monster();
                int xp = m.getReward(getGame().getDungeon().getCurrentLevel());
                getGame().getPlayer().setXp(getGame().getPlayer().getXp() + xp);
                getGame().setAlreadyVisitedMonster(true);
                getGame().getPlayer().setPoisonActivated(false);
                return new CardState(getGame());
            }
            else if (ice && getGame().getDungeon().monsterAlive()) {
                previous.setDice();
                return previous;
            }
                
                

            if (getGame().getDungeon().monsterAlive()) {
                
                Monster m = new Monster();
                int dmg = m.getDamage(getGame().getDungeon().getCurrentLevel());
                
                getGame().getPlayer().setArmor(getGame().getPlayer().getArmor() - dmg);
                if (!getGame().getPlayer().alive()) {
                    getGame().resetGame();
                    return new BeginningState(getGame());
                }
                else {
                    previous.setDice();
                    return previous;
                }
            }
            else {
                Monster m = new Monster();
                int xp = m.getReward(getGame().getDungeon().getCurrentLevel());
                getGame().getPlayer().setXp(getGame().getPlayer().getXp() + xp);
                getGame().setAlreadyVisitedMonster(true);
                getGame().getPlayer().setPoisonActivated(false);
                return new CardState(getGame());
            }
        
        } 
        
        
        else {

            BossMonster b = new BossMonster();


            // Attack
            getGame().getDungeon().setMonsterHp(getGame().getDungeon().getMonsterHp() - damage);

            if (ice && !getGame().getDungeon().monsterAlive()) {
                int gold = b.getGOLD(getGame().getDungeon().getCurrentLevel());
                int xp = b.getXP(getGame().getDungeon().getCurrentLevel()); 
                getGame().getPlayer().setGold(getGame().getPlayer().getGold() + gold);
                getGame().getPlayer().setXp(getGame().getPlayer().getXp() + xp);
                getGame().setAlreadyVisitedMonster(true);
                getGame().getPlayer().setPoisonActivated(false);
                
                Dice d = new Dice();
                Treasure t = new Treasure();
                t.use(d.getNumber(), getGame().getPlayer(), true);
                
                return new CardState(getGame());
            }
            else if (ice && getGame().getDungeon().monsterAlive()) {
                return new AttackState(getGame());
            }
            
            
            if (getGame().getDungeon().monsterAlive()) {
                //CONTER-BACK
                int dmg = b.getDamage(getGame().getDungeon().getCurrentLevel());
                getGame().getPlayer().setArmor(getGame().getPlayer().getArmor() - dmg);
                if (!getGame().getPlayer().alive()) {
                    getGame().resetGame();
                    return new BeginningState(getGame());
                }
                else {
                    previous.setDice();
                    return previous;
                }
            }
            else {
                int gold = b.getGOLD(getGame().getDungeon().getCurrentLevel());
                int xp = b.getXP(getGame().getDungeon().getCurrentLevel()); 
                getGame().getPlayer().setGold(getGame().getPlayer().getGold() + gold);
                getGame().getPlayer().setXp(getGame().getPlayer().getXp() + xp);
                getGame().setAlreadyVisitedMonster(true);
                getGame().getPlayer().setPoisonActivated(false);
                Dice d = new Dice();
                Treasure t = new Treasure();
                t.use(d.getNumber(), getGame().getPlayer(), true);
                return new CardState(getGame());
            }
        }
        
        
    }

}
