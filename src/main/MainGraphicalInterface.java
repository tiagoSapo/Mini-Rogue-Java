
package main;

import logic.ObservableGame;
import ui.gui.MiniRogueFrame;

public class MainGraphicalInterface {
    
    public static void main(String[] args) {
        new MiniRogueFrame(new ObservableGame());
    }
    
}
