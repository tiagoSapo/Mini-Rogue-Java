
package ui.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import logic.Resources;

public class CardPanel extends JPanel {
    
    private Image i;
    
    public CardPanel(String path) {
        
        try {
            i = ImageIO.read(Resources.getResourceFile(path));
        } catch (IOException ex) {
            System.exit(1);
        }
                    
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
    }
}
