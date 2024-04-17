
package ui.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import logic.LoadSaveFile;
import logic.MiniRogue;

import logic.ObservableGame;

public class MiniRogueFrame extends JFrame implements Observer, Constants {
    
    private ObservableGame observableGame;       
    private MiniRoguePanel panel;
    
    public MiniRogueFrame(ObservableGame g) {
        this(g, 200,100, DIM_X_FRAME, DIM_Y_FRAME);
    }
    
    public MiniRogueFrame(ObservableGame g, int x, int y, int width, int height) {
        
        super("Mini Rogue"); 

        observableGame = g;
        g.addObserver(this);
               
        panel = new MiniRoguePanel(observableGame);
        
        this.setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        
        setMenus();
        
        setLocation(x, y); 
        setSize(width,height); 
        setMinimumSize(new Dimension(width,height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    
    }
    
    private void setMenus() {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new Listener("Exit"));
        
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setMnemonic(KeyEvent.VK_L);
        loadItem.addActionListener(new Listener("Load"));
        
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.addActionListener(new Listener("Save"));
        
        JMenuItem helpItem = new JMenuItem("Help Contents");
        helpItem.setMnemonic(KeyEvent.VK_H);
        helpItem.addActionListener(new Listener("Help"));
        
        JMenuItem aboutItem = new JMenuItem("About Mini Rogue");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new Listener("About"));
        
        this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(aboutMenu);
        aboutMenu.add(helpItem);
        aboutMenu.add(aboutItem);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    class Listener implements ActionListener {
        
        private String option;

        public Listener(String s) {
            option = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (option.equalsIgnoreCase("Exit")) {
                int a = JOptionPane.showConfirmDialog (null, "Are you sure?", "Exit Warning", JOptionPane.YES_NO_OPTION);
                if (a == 0)
                    System.exit(0);
            }
            else if (option.equalsIgnoreCase("About")) {
                JOptionPane.showMessageDialog(MiniRogueFrame.this, "Mini Rogue v2.0 RTM - build 9800\n\n© 2017 ISEC Corportation\nAll Rights Reserved", "About", JOptionPane.PLAIN_MESSAGE);
            }
            else if (option.equalsIgnoreCase("Help")) {
                JOptionPane.showMessageDialog(MiniRogueFrame.this, "You win the game if you can reach the last room of the Dungeon and defeat the final Boss Monster.", "Help", JOptionPane.PLAIN_MESSAGE);
            }
            else if (option.equalsIgnoreCase("Save")) {
                
                String fileName = "mini_rogue_save";
                
                try {  
                    
                    LoadSaveFile.fwrite(new File(fileName), (MiniRogue)observableGame.getMiniRogue());
                    JOptionPane.showMessageDialog(MiniRogueFrame.this, "The game was saved sucessfully!", "Save Success", JOptionPane.PLAIN_MESSAGE);
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MiniRogueFrame.this, "An error occurred while trying to save the game", "Save Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            else if (option.equalsIgnoreCase("Load")) {
                
                String fileName = "mini_rogue_save";
                
                try {
                        observableGame.setGameModel((MiniRogue)LoadSaveFile.fread(new File(fileName)));
                        JOptionPane.showMessageDialog(MiniRogueFrame.this, "The game was loaded sucessfully!", "Load Success", JOptionPane.PLAIN_MESSAGE);
                        
                    } catch(IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(MiniRogueFrame.this, "There is no previous game", "Load Error", JOptionPane.ERROR_MESSAGE);
                    } 
                
            }
        }
        
        
    }
  
}
