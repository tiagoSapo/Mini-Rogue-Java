
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logic.Dice;
import logic.ObservableGame;
import logic.Player;
import logic.Resources;
import logic.cards.*;
import logic.states.CardState;

public class CardStatePanel extends JPanel implements Observer {
    
    private ObservableGame observableGame;
    private Image background;
    
    // Cartas
    private CardPanel boss, event, merchant, monster, resting, trap, treasure;
    
    private JLabel player, spells, area_column;
    private JLabel armor, hp, gold, food;
    private JLabel fire, ice, poison, heal;
    private JLabel xp;
    
    private JPanel p1, p2, side, cardsPanel, temp;
    private JPanel pPlayer, pSpells;
    private JPanel south;
    
    private JButton b1, b2;
    
    private Box sideBox;
    
    private JPanel center;
    private JPanel pLevel, pArea;
    private JLabel level;
    
    private JPanel title_north;
    private JLabel title_label;
    
    public CardStatePanel(ObservableGame g) {
        
        observableGame = g;
        observableGame.addObserver(this);
        
        setLayout(new BorderLayout());
        createObjects();
    }
    
    // Constructor Methods
    private void setPictures() {
        
        // background
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/dungeon.jpg"));
        } catch (IOException ex) {
            System.exit(1);
        } 
        
        // cards
        boss = new CardPanel("/ui/pictures/cards/BossMonster.jpeg");
        event = new CardPanel("/ui/pictures/cards/Event.jpeg");
        merchant = new CardPanel("/ui/pictures/cards/Merchant.jpeg");
        monster = new CardPanel("/ui/pictures/cards/Monster.jpeg");
        resting = new CardPanel("/ui/pictures/cards/Resting.jpeg");
        trap = new CardPanel("/ui/pictures/cards/Trap.jpeg");
        treasure = new CardPanel("/ui/pictures/cards/Treasure.jpeg");
        
    }
    private void malloc() {
        
        sideBox = Box.createVerticalBox();
        
        // TITLE
        title_north = new JPanel();
        title_label = new JLabel("CARDS BOARD");
        title_label.setForeground(Color.white);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_north.setBackground(new Color(2, 36, 75));
        
        title_north.add(title_label);
        this.add(title_north, BorderLayout.NORTH);
        
        // Labels
        player = new JLabel("Player"); spells = new JLabel("Spells"); area_column = new JLabel();
        armor = new JLabel(); hp = new JLabel(); gold = new JLabel(); food = new JLabel();
        fire = new JLabel(); ice = new JLabel(); poison = new JLabel(); heal = new JLabel();
        xp  = new JLabel();
        
        area_column.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Panels
        p1 = new JPanel(); p2 = new JPanel(); side = new JPanel();
        pPlayer = new JPanel(); pSpells = new JPanel();
        south = null; cardsPanel = null;
        
        pLevel = new JPanel();
        level = new JLabel();
        level.setAlignmentX(Component.CENTER_ALIGNMENT);
        pLevel.add(level);
        pArea = new JPanel();
        pArea.add(area_column);
        pLevel.setBackground( new Color(153, 204, 255));
        pArea.setBackground(new Color(204,229,255));
        
    }
    private void createObjects() {
        
        Color purple = new Color(2, 36, 75);
        Color light_blue = new Color(153, 204, 255);
        Color ultra_light_blue = new Color(204,229,255);
        
        setPictures();
        malloc();
        
        p1.setLayout(new GridLayout(3,2));
        p1.setBackground(ultra_light_blue);
        p1.add(armor);
        p1.add(hp);
        p1.add(gold);
        p1.add(food);
        p1.add(xp);
        
        p2.setLayout(new GridLayout(3,2));
        p2.setBackground(ultra_light_blue);
        p2.add(fire);
        p2.add(ice);
        p2.add(poison);
        p2.add(heal);
        
        pPlayer.setBackground(light_blue);
        pSpells.setBackground(light_blue);
        
        pPlayer.add(player);
        pSpells.add(spells);
        
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        spells.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Dispor objectos
        
        sideBox.add(pPlayer);
        sideBox.add(p1);
        sideBox.add(Box.createVerticalStrut(25));
        sideBox.add(pSpells);
        sideBox.add(p2);
        sideBox.add(Box.createVerticalStrut(50));
        sideBox.add(pLevel);
        sideBox.add(pArea);
        
        side.setBackground(purple);
        

        side.add(sideBox);
        this.add(side, BorderLayout.EAST);
        
    }
    
    // Update Methods
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
            
            area_column.setText("Area " + observableGame.getDungeon().getArea() + " - Column "+ observableGame.getColumn());
            
            level.setText("Level " + observableGame.getDungeon().getCurrentLevel());
    }
    private void updateCardsPanel(){
        
        Color purple = new Color(2, 36, 75);
        
        // remover anterior
        if (cardsPanel != null) {
            this.remove(cardsPanel);
            this.remove(south);
            this.remove(temp);
            this.remove(center);
        }
        
        cardsPanel = new JPanel();
        south = new JPanel();
        temp = new JPanel();
        
        // 1 linha para duas cartas
        cardsPanel.setLayout(new GridLayout(1,2));
        
        temp.setBackground(purple);
        
        // Trata das cartas
        // se a coluna for impar
        if (observableGame.getGameLogic().getColumn() % 2 != 0) {
            
            Card c1 = observableGame.getGameLogic().getCard();
            b1 = new JButton();
            
            if (c1 instanceof Event) {
                cardsPanel.add(event);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Event Card - Dice " + temp_number);
                b1.addActionListener(new BEvent(temp_number));
                temp.add(b1);
            }
            else if (c1 instanceof Merchant) {
                cardsPanel.add(merchant);
                b1.setText("Merchant Card");
                b1.addActionListener(new BMerchant());
                temp.add(b1);
            }
            else if (c1 instanceof Monster) {
                cardsPanel.add(monster);
                b1.setText("Monster Card");
                b1.addActionListener(new BMonster());
                temp.add(b1);
            }
            else if (c1 instanceof Resting) {
                cardsPanel.add(resting);
                b1.setText("Resting Card");
                b1.addActionListener(new BResting());
                temp.add(b1);
            }
            else if (c1 instanceof Trap) {
                cardsPanel.add(trap);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Trap Card - Dice " + temp_number);
                b1.addActionListener(new BTrap(temp_number));
                temp.add(b1);
            }
            else if (c1 instanceof Treasure) {
                cardsPanel.add(treasure);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Treasure Card - Dice " + temp_number);
                b1.addActionListener(new BTreasure(temp_number));
                temp.add(b1);
            }
            else if (c1 instanceof BossMonster) {
                cardsPanel.add(boss);
                b1.setText("BossMonster Card");
                b1.addActionListener(new BMonster());
                temp.add(b1);
            }
            
            observableGame.getGameLogic().nextColumn();
            cardsPanel.setPreferredSize(new Dimension(320, 450));
        }
        // se a coluna for par
        else {
            
            Card c1, c2;
            
            c1 = observableGame.getGameLogic().getCard();
            c2 = observableGame.getGameLogic().getCard();
            
            b1 = new JButton();
            b2 = new JButton();
            
            // Carta1
            if (c1 instanceof Event) {
                cardsPanel.add(event);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Event Card - Dice " + temp_number);
                b1.addActionListener(new BEvent(temp_number));
                temp.add(b1);
            }
            else if (c1 instanceof Merchant) {
                cardsPanel.add(merchant);
                b1.setText("Merchant Card");
                b1.addActionListener(new BMerchant());
                temp.add(b1);
            }
            else if (c1 instanceof Monster) {
                cardsPanel.add(monster);
                b1.setText("Monster Card");
                b1.addActionListener(new BMonster());
                temp.add(b1);
            }
            else if (c1 instanceof Resting) {
                cardsPanel.add(resting);
                b1.setText("Resting Card");
                b1.addActionListener(new BResting());
                temp.add(b1);
            }
            else if (c1 instanceof Trap) {
                cardsPanel.add(trap);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Trap Card - Dice " + temp_number);
                b1.addActionListener(new BTrap(temp_number));
                temp.add(b1);
            }
            else if (c1 instanceof Treasure) {
                cardsPanel.add(treasure);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b1.setText("Treasure Card - Dice " + temp_number);
                b1.addActionListener(new BTreasure(temp_number));
                temp.add(b1);
            }
            
            // Carta2
            if (c2 instanceof Event) {
                cardsPanel.add(event);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b2.setText("Event Card - Dice " + temp_number);
                b2.addActionListener(new BEvent(temp_number));
                temp.add(b2);
            }
            else if (c2 instanceof Merchant) {
                cardsPanel.add(merchant);
                b2.setText("Merchant Card");
                b2.addActionListener(new BMerchant());
                temp.add(b2);
            }
            else if (c2 instanceof Monster) {
                cardsPanel.add(monster);
                b2.setText("Monster Card");
                b2.addActionListener(new BMonster());
                temp.add(b2);
            }
            else if (c2 instanceof Resting) {
                cardsPanel.add(resting);
                b2.setText("Resting Card");
                b2.addActionListener(new BResting());
                temp.add(b2);
            }
            else if (c2 instanceof Trap) {
                cardsPanel.add(trap);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b2.setText("Trap Card - Dice " + temp_number);
                b2.addActionListener(new BTrap(temp_number));
                temp.add(b2);
            }
            else if (c2 instanceof Treasure) {
                cardsPanel.add(treasure);
                Dice dice_temp = new Dice();
                int temp_number = dice_temp.getNumber();
                b2.setText("Treasure Card - Dice " + temp_number);
                b2.addActionListener(new BTreasure(temp_number));
                temp.add(b2);
            }
            observableGame.getGameLogic().nextColumn();
            cardsPanel.setPreferredSize(new Dimension(600, 400));
        }
        
        center = new JPanel();
        center.setOpaque(false);
        center.add(cardsPanel);
        
        
        south.add(temp);
        south.setBackground(purple);
        this.add(south, BorderLayout.SOUTH);
        this.add(center);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    @Override
    public void update(Observable t, Object o) {
        
        boolean vis = observableGame.getState() instanceof CardState;
        
        if (vis) {
            isAlive();
            updateLabels();
            updateCardsPanel();
        }
        
        System.out.println("CardState visisvel " + vis);
        setVisible(vis);   
    }

    private void isAlive() {
        
        ObservableGame game = observableGame;
        
        if (game.getGameLogic().hasWon()) {
            JOptionPane.showMessageDialog(CardStatePanel.this, "You won the game!", "You Won", JOptionPane.PLAIN_MESSAGE);
            game.resetGame();
            game.quit();
        }
        else if (!game.getGameLogic().getPlayer().alive()) {
            JOptionPane.showMessageDialog(CardStatePanel.this, "You died", "You Lose", JOptionPane.PLAIN_MESSAGE);
            game.resetGame();
            game.quit();
        }
    }

    
    // Botoes
    class BEvent implements ActionListener {
        private int dice_value;
        public BEvent(int d) {
            dice_value = d;
        }
        public void actionPerformed(ActionEvent e) {
            observableGame.solveEvent(dice_value);
        }
    }
    class BMerchant implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveTrade();
        }
    }
    class BMonster implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveMonster();
        }
    }
    class BResting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveResting();
        }
    }
    class BTrap implements ActionListener {
        private int dice_value;
        public BTrap(int d) {
            dice_value = d;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveTrap(dice_value);
        }
    }
    class BTreasure implements ActionListener {
        private int dice_value;
        public BTreasure(int d) {
            dice_value = d;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.solveTreasure(dice_value);
        }
    }
    
}