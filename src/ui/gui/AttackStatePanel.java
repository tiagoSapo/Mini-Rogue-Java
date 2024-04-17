
package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.Dice;
import logic.ObservableGame;
import logic.Player;
import logic.Resources;
import logic.states.AttackState;

public class AttackStatePanel extends JPanel implements Observer {
    
    private ObservableGame observableGame;
    private Image background;
    
    private JPanel side, featsPanel, roll6Panel;
    private JComboBox featsCombo, roll6Combo;
    private JButton ok, okf, ok6s;
    private JLabel featsLabel, roll6Label, diceLabel, updateFeats, update6s;
    private Box bside;
    
    private JLabel armor, hp, gold, food;
    private JLabel fire, ice, poison, heal;
    private JLabel xp;
    private JPanel p1, p2;
    
    private JPanel title_north;
    private JLabel title_label;
    
    public AttackStatePanel(ObservableGame g) {
        
        observableGame = g;
        observableGame.addObserver(this);
        
        setLayout(new BorderLayout());
        
        setBackground();
        createObjects();
    }
    
    private void setBackground() {
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/monster1.png"));
        } catch (IOException ex) {
            System.exit(1);
        } 
    }
    private void createObjects() {
        side = null;
        featsPanel = null;
        roll6Panel = null;
        featsCombo = null;
        roll6Combo = null;
        ok = null;
        okf = null;
        ok6s = null;
        featsLabel = null;
        roll6Label = null;
        diceLabel = null;
        updateFeats = null;
        update6s = null;
        
        
        // TITLE
        title_north = new JPanel();
        title_label = new JLabel("MONSTER CARD");
        title_label.setForeground(Color.white);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_north.setBackground(new Color(102, 0, 0));
        
        title_north.add(title_label);
        this.add(title_north, BorderLayout.NORTH);
        
        armor = new JLabel(); hp = new JLabel(); gold = new JLabel(); food = new JLabel();
        fire = new JLabel(); ice = new JLabel(); poison = new JLabel(); heal = new JLabel();
        xp  = new JLabel();
        
        p1 = new JPanel();
        p2 = new JPanel();
        
        p1.setLayout(new GridLayout(3,2));
        p1.add(armor);
        p1.add(hp);
        p1.add(gold);
        p1.add(food);
        p1.add(xp);
        
        p2.setLayout(new GridLayout(3,2));
        p2.add(fire);
        p2.add(ice);
        p2.add(poison);
        p2.add(heal);
        
        updateLabels();
    }
  
    private void updateLabels() {
        ArrayList<Dice> di = observableGame.getState().getDiceValue();
        if (diceLabel != null) {
            diceLabel = new JLabel("Dice: ");
            for (int i = 0; i < di.size(); i++)
                diceLabel.setText(diceLabel.getText() + di.get(i).getNumber() + " ");
        }
        Player ptr = observableGame.getPlayer();

            armor.setText(" Armor " + ptr.getArmor());
            hp.setText(" HP " + ptr.getHp());
            gold.setText(" Gold " + ptr.getGold());
            food.setText(" Food " + ptr.getFood());
            xp.setText(" XP " + ptr.getXp());

            fire.setText(" Fire " + ptr.getFire());
            ice.setText(" Ice " + ptr.getIce());
            poison.setText(" Poison " + ptr.getPoison());
            heal.setText(" Heal " + ptr.getHeal());
    }
    private void updatePanel() {
        
        Color red = new Color(102, 0, 0);
        
        if (side != null)
            this.remove(side);
        
        side = new JPanel();
        
        featsPanel = new JPanel(); 
        roll6Panel = new JPanel();
        featsPanel.setBackground(red);
        roll6Panel.setBackground(red);
                
        ok = new JButton("Set Spells"); ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        okf = new JButton("OK");
        ok6s = new JButton("OK");
        
        ok.addActionListener(new BOk());
        okf.addActionListener(new BOkf());
        ok6s.addActionListener(new BOk6s());
        
        featsCombo = new JComboBox(); 
        roll6Combo = new JComboBox();
        ArrayList<Dice> di = observableGame.getState().getDiceValue();
        for (int i = 1; i < (di.size() + 1); i++) {
            featsCombo.addItem(i); 
            roll6Combo.addItem(i);
        }
        
        updateFeats = new JLabel();
        update6s = new JLabel();
        
        featsLabel = new JLabel("Feats - Die Position");
        roll6Label = new JLabel("Roll 6s - Die Position");
        diceLabel = new JLabel("Dice values: ");
        for (int i = 0; i < di.size(); i++)
            diceLabel.setText(diceLabel.getText() + di.get(i).getNumber() + " ");
        
        featsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roll6Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        diceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateFeats.setAlignmentX(Component.CENTER_ALIGNMENT);
        update6s.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        featsLabel.setForeground(Color.white);
        roll6Label.setForeground(Color.white);
        diceLabel.setForeground(Color.white);
        updateFeats.setForeground(Color.green);
        update6s.setForeground(Color.green);
        
        featsPanel.add(featsCombo);
        featsPanel.add(okf);
        
        roll6Panel.add(roll6Combo);
        roll6Panel.add(ok6s);
        
        bside = Box.createVerticalBox();
        bside.add(Box.createVerticalStrut(10));
        bside.add(diceLabel);
        bside.add(Box.createVerticalStrut(10));
        bside.add(featsLabel);
        bside.add(featsPanel);
        bside.add(updateFeats);
        bside.add(Box.createVerticalStrut(25));
        bside.add(roll6Label);
        bside.add(roll6Panel);
        bside.add(update6s);
        bside.add(Box.createVerticalStrut(25));
        bside.add(p1);
        bside.add(Box.createVerticalStrut(25));
        bside.add(p2);
        bside.add(Box.createVerticalStrut(25));
        bside.add(ok);
        
        side.add(bside);
        side.setBackground(red);
        this.add(side, BorderLayout.EAST);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (side != null)
            g.drawImage(background, 0, 0, getWidth() - side.getWidth(), getHeight(), this);
    }
    
    @Override
    public void update(Observable t, Object o) {
        
        boolean vis = observableGame.getState() instanceof AttackState;
        
        if (vis) {
            updatePanel();
            updateLabels();
        }
        
        System.out.println("AttackState visisvel " + vis);
        setVisible(vis);   
    }

    class BOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveAttack();
            System.out.println(observableGame.getState());
        } 
    }
    class BOkf implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((Integer)featsCombo.getSelectedItem()).intValue();
            observableGame.getState().feats(i);
            updateFeats.setText("Die " + i + " updated");
            updateLabels();
        } 
    }
    class BOk6s implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((Integer)roll6Combo.getSelectedItem()).intValue();
            observableGame.getState().roll6(i);
            update6s.setText("Die " + i + " updated");
            updateLabels();
        } 
    }
}
