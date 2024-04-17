/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import java.io.Serializable;
import java.util.ArrayList;
import logic.Dice;
import logic.GameData;
import logic.cards.BossMonster;

public class AttackState extends StateAdapter implements Serializable{
    
    private ArrayList<Dice> d;
    private int damage6s;
    
    public AttackState(GameData g) {
        super(g);
        
        d = new ArrayList<>();
        for (int i = 0; i < g.getPlayer().getRank(); i++)
            d.add(new Dice());
        damage6s = 0;
        
        Dice dic = new Dice();
        int di = dic.getNumber();
        
        if (getGame().getColumn() < 5 ) {
            int monster = getGame().getDungeon().getArea() + di;  
            getGame().getDungeon().setMonsterHp(getGame().getDungeon().getMonsterHp() + monster);
        }
        else {
            BossMonster b = new BossMonster();
            int monster = b.getHpMonster(getGame().getDungeon().getCurrentLevel());
            getGame().getDungeon().setMonsterHp(getGame().getDungeon().getMonsterHp() + monster);
        }
    }
    
    @Override
    public IStates quit(){ return new CardState(getGame());}
    @Override
    public IStates roll6(int i) { 
        i--;
        if ((i >= 0 && i <= d.size() - 1) && (d.get(i).getNumber() == 6))  {
            d.get(i).generateAnotherNumber();
            if (d.get(i).getNumber() != 1)
                damage6s += 6;
        }
            
        return this;
    }
    @Override
    public IStates feats(int i){ 
        i--;
        if ((i >= 0 && i <= d.size() - 1) && (d.get(i).getNumber() != 6)) {
            if (getGame().getPlayer().getHp() - 2 > 0 && getGame().getPlayer().getXp() - 1 >= 0) {
                getGame().getPlayer().setHp(getGame().getPlayer().getHp() - 2);
                getGame().getPlayer().setXp(getGame().getPlayer().getXp() - 1);
                d.get(i).generateAnotherNumber();
            }
        }
                
        return this;
    }
    public void setDice() {
        d.clear();
        for (int i = 0; i < getGame().getPlayer().getRank(); i++)
            d.add(new Dice());
    }
    @Override
    public ArrayList<Dice> getDiceValue(){return d;}
    @Override
    public IStates solveAttack(){ return new DefenseState(getGame(), d, damage6s, this);  }
    
}
