
package ui.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
import logic.Resources;
import logic.dif.Casual;
import logic.dif.Hard;
import logic.dif.Impossible;
import logic.dif.Normal;
import logic.states.BeginningState;

public class BeginningStatePanel extends JPanel implements Observer, Constants {
    
    private ObservableGame observableGame;
    private Image background;
    
    private JPanel side, area, dif, areaLabelPanel, difLabelPanel, selectedArea, selectedDif;
    private JComboBox areaSelection, difSelection;
    private JButton okArea, okDif, start;
    private JLabel areaa, diffic, selectedAreaLabel, selectedDifLabel;
    private Box sideBox;
    
    private static String difStrings[] = {"Casual", "Normal", "Hard", "Impossible"};
    
    public BeginningStatePanel(ObservableGame g) {
        
        observableGame = g;
        this.observableGame.addObserver(this);
        
        try {
            background = ImageIO.read(Resources.getResourceFile("/ui/pictures/intro.jpeg"));
        } catch (IOException ex) {
            System.exit(1);
        }
        
        setLayout(new BorderLayout());
        
        createObjects();
        setJCombos();
        setObjects();
    }
    
    private void setJCombos() {
        int i;
        for (i = 1; i < 15; i++)
            areaSelection.addItem(i);
        for (i = 0; i < 4; i++)
            difSelection.addItem(difStrings[i]);
    }
    private void createObjects() {
        
        side = new JPanel();
        area = new JPanel();
        dif = new JPanel();
        areaLabelPanel = new JPanel();
        difLabelPanel = new JPanel();
        selectedArea = new JPanel();
        selectedDif = new JPanel();
        
        areaSelection = new JComboBox();
        difSelection = new JComboBox();
        
        okArea = new JButton("OK");
        okDif = new JButton("OK");
        start = new JButton("Start game");
        
        areaa = new JLabel("Area");
        diffic = new JLabel("Difficulty");
        selectedAreaLabel = new JLabel();
        selectedDifLabel = new JLabel();
        
        sideBox = Box.createVerticalBox();
    }
    private void setObjects() {
        
        area.add(areaSelection);
        area.add(okArea);
        
        dif.add(difSelection);
        dif.add(okDif);
        
        areaLabelPanel.add(areaa);
        difLabelPanel.add(diffic);
        
        selectedArea.add(selectedAreaLabel);
        selectedDif.add(selectedDifLabel);
        
        sideBox.add(Box.createVerticalStrut(25));
        sideBox.add(areaLabelPanel);
        sideBox.add(area);
        sideBox.add(selectedArea);
        sideBox.add(Box.createVerticalStrut(15));
        sideBox.add(difLabelPanel);
        sideBox.add(dif);
        sideBox.add(selectedDif);
        sideBox.add(Box.createVerticalStrut(15));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBox.add(start);
        
        Color c = new Color(138, 105, 68);
        
        areaa.setForeground(Color.white);
        diffic.setForeground(Color.white);
        area.setBackground(c);
        dif.setBackground(c);
        areaLabelPanel.setBackground(c);
        difLabelPanel.setBackground(c);
        selectedArea.setBackground(c);
        selectedDif.setBackground(c);
        side.setBackground(new Color(62, 38, 12));
        side.add(sideBox);
        this.add(side, BorderLayout.EAST);
        
        okArea.addActionListener(new BArea());
        okDif.addActionListener(new BDif());
        start.addActionListener(new BStart());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth() - side.getWidth(), getHeight(), this);
    }
    
    @Override
    public void update(Observable t, Object o) {
        System.out.println("Beginning visisvel " + (observableGame.getState() instanceof BeginningState));
        setVisible(observableGame.getState() instanceof BeginningState);   
    }
    
    class BArea implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int a = (areaSelection.getSelectedIndex() + 1);
            selectedAreaLabel.setForeground(Color.green);
            selectedAreaLabel.setText("Area " + a);
            observableGame.setInitialArea(a);
        }
        
    }
    class BDif implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int a = difSelection.getSelectedIndex();
            selectedDifLabel.setForeground(Color.green);
            if (a == 0) {
                selectedDifLabel.setText("Casual");
                observableGame.setDifficulty(new Casual());
            }
            else if (a == 1) {
                selectedDifLabel.setText("Normal");
                observableGame.setDifficulty(new Normal());
            }
            else if (a == 2) {
                selectedDifLabel.setText("Hard");
                observableGame.setDifficulty(new Hard());
            }
            else {
                selectedDifLabel.setText("Impossible");
                observableGame.setDifficulty(new Impossible());
            }
        }
        
    }
    class BStart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.startGame();
        }
        
    }
}
