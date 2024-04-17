
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
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.ObservableGame;
import logic.Player;
import logic.Resources;
import logic.states.TradingState;

public class TradingStatePanel  extends JPanel implements Observer {

    private ObservableGame observableGame;
    private Image background;
    
    // Labels do Jogador
    private JLabel armor, hp, gold, food;
    private JLabel fire, ice, poison, heal;
    private JLabel xp, player, buy, sell;
    private JPanel p1;
    
    private Box bside;
    private JPanel side, buyP, sellP;
    private JButton skip;
    
    private JButton ration, hs, hb, armorP, okSpellBuy; 
    private JButton ar, okSpellSell; 
    private JComboBox spellBuy, spellSell;
    
    private JPanel title_north;
    private JLabel title_label;
    
    public TradingStatePanel(ObservableGame g) {
        observableGame = g;
        observableGame.addObserver(this);

        setBackground();
        setLayout(new BorderLayout());
        
        createObjects();
        setObjects();
    }
    private void setBackground() {
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/merchant.jpg"));
        } catch (IOException ex) {
            System.exit(1);
        } 
    }
    private void createObjects() {
        
        // Title
        title_north = new JPanel();
        title_label = new JLabel("MERCHANT CARD");
        title_label.setForeground(Color.white);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_north.setBackground(new Color(218,165,32));
        
        title_north.add(title_label);
        this.add(title_north, BorderLayout.NORTH);
        
        armor = new JLabel(); hp = new JLabel(); gold = new JLabel(); food = new JLabel();
        fire = new JLabel(); ice = new JLabel(); poison = new JLabel(); heal = new JLabel();
        xp  = new JLabel(); player = new JLabel("Player"); buy = new JLabel("Buy"); sell = new JLabel("Sell");
        p1 = new JPanel();
        
        side = new JPanel();
        buyP = new JPanel();
        sellP = new JPanel();
        
        ration = new JButton("+1 Food"); hs = new JButton("+1 HP"); hb = new JButton("+4 HP"); armorP = new JButton("+1 Armor"); okSpellBuy = new JButton("OK");
        ar = new JButton("+3 Gold"); okSpellSell = new JButton("OK");
        
        ration.addActionListener(new Button("Buy","Ration"));
        hs.addActionListener(new Button("Buy", "SmallHealth"));
        hb.addActionListener(new Button("Buy","BigHealth"));
        armorP.addActionListener(new Button("Buy","Armor")); 
        okSpellBuy.addActionListener(new ButtonBox("Buy"));
        ar.addActionListener(new Button("Spell","Armor"));
        okSpellSell.addActionListener(new ButtonBox("Sell"));
        
        spellBuy = new JComboBox(); spellSell = new JComboBox();
        spellBuy.addItem("Fire");
        spellBuy.addItem("Ice");
        spellBuy.addItem("Poison");
        spellBuy.addItem("Heal");
        spellSell.addItem("Fire");
        spellSell.addItem("Ice");
        spellSell.addItem("Poison");
        spellSell.addItem("Heal");
        
        skip = new JButton("Skip");
        
        bside = Box.createVerticalBox();
    }
    private void setObjects() {
        
        Color golden = new Color(218,165,32);
        Color light_golden = new Color(238,232,170);
        
        skip.setAlignmentX(Component.CENTER_ALIGNMENT);
        skip.addActionListener(new SkipButton());
        
        p1.setLayout(new GridLayout(5,2));
        p1.add(armor);
        p1.add(hp);
        p1.add(gold);
        p1.add(food);

        p1.add(fire);
        p1.add(ice);
        p1.add(poison);
        p1.add(heal);
        p1.add(xp);
        
        buyP.setLayout(new GridLayout(3,2));
        buyP.add(ration);
        buyP.add(hs);
        buyP.add(hb);
        buyP.add(armorP);
        buyP.add(spellBuy);
        buyP.add(okSpellBuy);
        
        sellP.setLayout(new GridLayout(2,2));
        sellP.add(spellSell);
        sellP.add(okSpellSell);
        sellP.add(ar);
        
        player.setForeground(Color.white);
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        buy.setForeground(Color.white);
        buy.setAlignmentX(Component.CENTER_ALIGNMENT);
        sell.setForeground(Color.white);
        sell.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        p1.setBackground(light_golden);
        buyP.setBackground(light_golden);
        sellP.setBackground(light_golden);
        
        side.setBackground(golden);
        
        bside.add(Box.createVerticalStrut(10));
        bside.add(player);
        bside.add(Box.createVerticalStrut(5));
        bside.add(p1);
        bside.add(Box.createVerticalStrut(10));
        bside.add(buy);
        bside.add(Box.createVerticalStrut(5));
        bside.add(buyP);
        bside.add(Box.createVerticalStrut(10));
        bside.add(sell);
        bside.add(Box.createVerticalStrut(5));
        bside.add(sellP);
        bside.add(Box.createVerticalStrut(20));
        bside.add(skip);
        
        side.add(bside);
        this.add(side, BorderLayout.EAST);
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        boolean vis = observableGame.getState() instanceof TradingState;
        
        if (vis)
            updateLabels();
        
        System.out.println("TradingState visisvel " + vis);
        setVisible(vis);   
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth() - side.getWidth(), getHeight(), this);
    }

    private void updateLabels() {
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

    class SkipButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.skip();
        }
    }

    class ButtonBox implements ActionListener {
        
        private String s;

        public ButtonBox(String ss) {
            s = ss;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (s.equalsIgnoreCase("Buy")) {
                String a = (String)spellBuy.getSelectedItem();
                if (a.equalsIgnoreCase("Fire"))
                    observableGame.buy("Spell", "Fire");
                else if (a.equalsIgnoreCase("Ice"))
                    observableGame.buy("Spell", "Ice");
                else if (a.equalsIgnoreCase("Poison"))
                    observableGame.buy("Spell", "Poison");
                else if (a.equalsIgnoreCase("Heal"))
                    observableGame.buy("Spell", "Heal");
            }
            else {
                String a = (String)spellSell.getSelectedItem();
                if (a.equalsIgnoreCase("Fire"))
                    observableGame.sell("Spell", "Fire");
                else if (a.equalsIgnoreCase("Ice"))
                    observableGame.sell("Spell", "Ice");
                else if (a.equalsIgnoreCase("Poison"))
                    observableGame.sell("Spell", "Poison");
                else if (a.equalsIgnoreCase("Heal"))
                    observableGame.sell("Spell", "Heal");
            }
            updateLabels();
        }
        
    }

    class Button implements ActionListener {
        
        private String s1, s2;

        public Button(String ss1, String ss2) {
            s1 = ss1;
            s2 = ss2;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (s1.equalsIgnoreCase("Buy")) {
                if (s2.equals("Ration"))
                    observableGame.buy("Ration", "");
                else if (s2.equals("SmallHealth"))
                    observableGame.buy("SmallHealth", "");
                else if (s2.equals("BigHealth"))
                    observableGame.buy("BigHealth", "");
                else if (s2.equals("Armor"))
                    observableGame.buy("Armor", "");
            }
            else {
                if (s2.equalsIgnoreCase("Armor"))
                    observableGame.sell("Armor", "");
            }
            updateLabels();
        }
        
    }
    
}
