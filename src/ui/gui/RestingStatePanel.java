
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.ObservableGame;
import logic.Player;
import logic.Resources;
import logic.states.SelectionState;



public class RestingStatePanel extends JPanel implements Observer {
    
    private ObservableGame observableGame;
    private Image background;
    
    // Labels do Jogador
    private JLabel armor, hp, gold, food;
    private JLabel fire, ice, poison, heal;
    private JLabel xp, player;
    private JPanel p1, pPlayer;
    
    private Box bside;
    private JPanel side, south;
    private JButton bxp, bfood, bheal;
    
    private JPanel title_north;
    private JLabel title_label;
    
    public RestingStatePanel(ObservableGame g) {
        
        observableGame = g;
        observableGame.addObserver(this);

        setBackground();
        setLayout(new BorderLayout());
        
        malloc();
        setComponents();
    }
    private void setBackground() {
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/resting.jpg"));
        } catch (IOException ex) {
            System.exit(1);
        } 
    }
    private void malloc() {
        
        // Title
        title_north = new JPanel();
        title_label = new JLabel("RESTING CARD");
        title_label.setForeground(Color.white);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_north.setBackground(new Color(51,36,31));
        
        title_north.add(title_label);
        this.add(title_north, BorderLayout.NORTH);
        
        armor = new JLabel(); hp = new JLabel(); gold = new JLabel(); food = new JLabel();
        fire = new JLabel(); ice = new JLabel(); poison = new JLabel(); heal = new JLabel();
        xp  = new JLabel(); player = new JLabel("Player");
        p1 = new JPanel();
        
        side = new JPanel(); south = new JPanel(); pPlayer = new JPanel();
        
        bxp = new JButton("+1 XP"); bfood = new JButton("+1 Food"); bheal = new JButton("+2 HP");
        
        bside = Box.createVerticalBox();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        boolean vis = observableGame.getState() instanceof SelectionState;
        
        if (vis)
            updateLabels();
        
        System.out.println("SelectionState visisvel " + vis);
        setVisible(vis);   
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    private void setComponents() {
        
        Color brown = new Color(51,36,31);
        
        // Player Panel
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
        
        // South Panel
        south.add(bxp);
        south.add(bfood);
        south.add(bheal);
        
        bxp.addActionListener(new Button("Weapon"));
        bfood.addActionListener(new Button("Ration"));
        bheal.addActionListener(new Button("Heal"));
        
        pPlayer.setBackground(brown);
        p1.setBackground(new Color(220,176,150));
        side.setBackground(brown);
        south.setBackground(brown);
        
        player.setForeground(Color.white); 
        pPlayer.add(player);
        
        bside.add(pPlayer);
        bside.add(p1);
             
        side.add(bside);
        this.add(side, BorderLayout.EAST);
        this.add(south, BorderLayout.SOUTH);
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

    class Button implements ActionListener {

        private String s;
        
        public Button(String ss) {
            s = ss;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.selectOption(s);
        }
    }
}
