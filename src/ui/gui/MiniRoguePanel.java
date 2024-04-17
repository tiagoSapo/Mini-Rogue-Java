
package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JPanel;
import logic.ObservableGame;

class MiniRoguePanel extends JPanel implements Observer, Constants {
    
    private ObservableGame observableGame;
    
    // States
    //private JPanel main;
    private BeginningStatePanel begin;
    private CardStatePanel card;
    private AttackStatePanel monsterAttack;
    private DefenseStatePanel monsterDefense;
    private TradingStatePanel trade;
    private RestingStatePanel resting;
    
    public MiniRoguePanel(ObservableGame g) {
        observableGame = g;
        observableGame.addObserver(this);
        
        setupComponents(); 
        setupLayout();
        
        update(g, null);
    }
    
    private void setupComponents() {

        // Alocar Estados
        //main = new JPanel();
        
        begin = new BeginningStatePanel(observableGame);
        card = new CardStatePanel(observableGame);
        monsterAttack = new AttackStatePanel(observableGame);
        monsterDefense = new DefenseStatePanel(observableGame);
        trade = new TradingStatePanel(observableGame);
        resting = new RestingStatePanel(observableGame);
    }
    
    private void setupLayout(){
        
        // Adicionar Estados a Paineis
        
        //ATENCAO APAGAR
        //main.setLayout(new BorderLayout());
        
        Box main = Box.createHorizontalBox();

        main.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        main.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));       
        
        main.setBackground(Color.red);
        //
        main.add(begin);
        main.add(card);
        main.add(monsterAttack);
        main.add(monsterDefense);
        main.add(trade);
        main.add(resting);
        
        begin.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        begin.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        card.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        card.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        monsterAttack.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        monsterAttack.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        monsterDefense.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        monsterDefense.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        trade.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        trade.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        resting.setMinimumSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        resting.setPreferredSize(new Dimension(DIM_X_FRAME, DIM_Y_FRAME));
        
        begin.update(observableGame, null);
        card.update(observableGame, null);
        monsterAttack.update(observableGame, null);
        monsterDefense.update(observableGame, null);
        trade.update(observableGame, null);
        resting.update(observableGame, null);
        
        setLayout(new BorderLayout());  
        add(main, BorderLayout.CENTER);
    }
    
    @Override
    public void update(Observable t, Object o) {
        
    }
    
}
