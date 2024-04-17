
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
import logic.states.DefenseState;

public class DefenseStatePanel extends JPanel implements Observer {

    private ObservableGame observableGame;
    private Image background;
    
    
    private Box bside;
    private JPanel side, south;
    private JLabel monsterHp, player;
    
    private JLabel armor, hp, gold, food;
    private JLabel fire, ice, poison, heal;
    private JLabel xp;
    private JPanel p1;
    
    private JButton fire1, ice1, poison1, heal1, ok;
    
    private JPanel title_north;
    private JLabel title_label;
    
    public DefenseStatePanel(ObservableGame g) {
        
        observableGame = g;
        observableGame.addObserver(this);

        setBackground();
        setLayout(new BorderLayout());
        
        malloc();
    }
    
    private void malloc() {
        
        Color orange = new Color(141,30,13);
        
        // TITLE
        title_north = new JPanel();
        title_label = new JLabel("MONSTER CARD");
        title_label.setForeground(Color.white);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_north.setBackground(orange);
        
        title_north.add(title_label);
        this.add(title_north, BorderLayout.NORTH);
        
        
        bside = Box.createVerticalBox();
        
        side = new JPanel();
        
        monsterHp = new JLabel();
        monsterHp.setAlignmentX(Component.CENTER_ALIGNMENT);
        player = new JLabel("Player");
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        armor = new JLabel(); hp = new JLabel(); gold = new JLabel(); food = new JLabel();
        fire = new JLabel(); ice = new JLabel(); poison = new JLabel(); heal = new JLabel();
        xp  = new JLabel();
        
        fire1 = new JButton("+1 Fire");
        ice1 = new JButton("+1 Ice");
        poison1 = new JButton("+1 Poison");
        heal1 = new JButton("+1 Heal");
        ok = new JButton("OK");
        
        fire1.addActionListener(new Button("Fire"));
        ice1.addActionListener(new Button("Ice"));
        poison1.addActionListener(new Button("Poison"));
        heal1.addActionListener(new Button("Heal"));
        ok.addActionListener(new Button("Ok"));
        
        p1 = new JPanel();
        south = new JPanel();
        
        south.add(fire1);
        south.add(ice1);
        south.add(poison1);
        south.add(heal1);
        south.add(ok);
        
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
        
        side.setBackground(orange);
        south.setBackground(orange);
        p1.setBackground(new Color(255, 229, 204));
        
        player.setForeground(Color.white);
        monsterHp.setForeground(Color.white);
        
        bside.add(Box.createVerticalStrut(10));
        bside.add(player);
        bside.add(Box.createVerticalStrut(5));
        bside.add(p1);
        bside.add(Box.createVerticalStrut(10));
        bside.add(monsterHp);
        
        side.add(bside);
        this.add(side, BorderLayout.EAST);
        this.add(south, BorderLayout.SOUTH);
    }  
    private void setBackground() {
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/monster2.jpg"));
        } catch (IOException ex) {
            System.exit(1);
        } 
    }
    
    @Override
    public void update(Observable o, Object arg) {
        boolean vis = observableGame.getState() instanceof DefenseState;
        
        if (vis)
            updateLabels();
        
        System.out.println("Defense visisvel " + vis);
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
            
            monsterHp.setText("MonsterHP " + observableGame.getDungeon().getMonsterHp());
    }

    class Button implements ActionListener {
        
        private String s;

        public Button(String ss) {
            s = ss;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (s.equalsIgnoreCase("OK"))
                observableGame.attack();
            else {
                observableGame.solveSpell(s);
                updateLabels();
            }
        }
    }
    
}
