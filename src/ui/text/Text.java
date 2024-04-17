
package ui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Dice;
import logic.MiniRogue;
import logic.ObservableGame;
import logic.Player;
import logic.cards.*;
import logic.dif.*;
import logic.states.*;

public class Text implements Serializable{
    
    private ObservableGame game;
    private boolean quit;
    
    public Text(ObservableGame m) {
        game = m;
        quit = false;
    }
    
    // Metodos dos Estados
    public void uiBeginningState() {
        
        System.out.println("\n\n-------------------------------------------------");
        System.out.println("------------------- MINI ROGUE ------------------");
        System.out.println("-------------------------------------------------");
        
        int option;
        Scanner sc;
        
        while (true) {
            System.out.println("Opcao: ");
            System.out.println("(1) Definir Dificuldade");
            System.out.println("(2) Definir Area Inicial");
            System.out.println("(3) Comecar");
            System.out.println("(4) Carregar jogo");
            System.out.println("(0) Quit");
            System.out.print("> ");

            sc = new Scanner(System.in);
            option = sc.nextInt();
            if (option >= 0 && option <= 4)
                break;
        }
        if (option == 1) {
            while(true) {
                System.out.println("\nSeleccionar Dificuldade:");
                System.out.println("(1) Casual");
                System.out.println("(2) Normal");
                System.out.println("(3) Hard");
                System.out.println("(4) Impossible");
                System.out.print("> ");

                option = sc.nextInt();
                if (option >= 1 && option <= 4)
                    break;
            }
            
            if (option == 1)
                System.out.println("Dificuldade actualizada para 'Casual'");
            else if (option == 2) {
                Difficulty d = new Normal();
                game.setDifficulty(d);
                System.out.println("Dificuldade actualizada para '" + d.getName() + "'");
            }
            else if (option == 3) {
                Difficulty d = new Hard();
                game.setDifficulty(d);
                System.out.println("Dificuldade actualizada para '" + d.getName() + "'");
            }
            if (option == 4) {
                Difficulty d = new Impossible();
                game.setDifficulty(d);
                System.out.println("Dificuldade actualizada para '" + d.getName() + "'");
            }

            
        }
        else if (option == 2) {
            System.out.print("\nNumero da area (1 a 14):\n> ");
            int x = sc.nextInt();
            if (x >= 1 && x <= 14)
                game.setInitialArea(x);
        }
        else if (option == 3)
            game.startGame();
        else if (option == 4) {
            System.out.print("\nNome do ficheiro:\n> ");
            BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
            String fp;
                    
            try {         
                fp = bin.readLine();
                if (fp == null || fp.length() <= 0)
                    return;
                game = fread(fp);
                game = new ObservableGame();
            } catch (Exception e) {
                System.out.println("Erro: " + e);
            }
            
        }
        else if (option == 0)
            quit = true;
        
    }
    public void uiCardState() {
        
        int column = game.getColumn();
        
        if (game.getGameLogic().hasWon()) {
            System.out.println("Ganhou");
            game.resetGame();
            game.quit();
        }
        else if (!game.getGameLogic().getPlayer().alive()) {
            System.out.println("Morreu");
            game.resetGame();
            game.quit();
        }
        
        
        System.out.println("\n\n--------------- LEVEL " + game.getDungeon().getCurrentLevel() + " | AREA " + game.getDungeon().getArea() + " --------------------");
        
        if (column % 2 != 0) {
           
            Card c = game.getGameLogic().getCard();
            System.out.println("-------------------- COLUNA " + column + " -----------------------");
            System.out.println(game.getPlayer() + "\n-----------------------------------------------------");
            
            if (column == 1) {
                System.out.print("Opcao:\n(1) Continuar\n(0) Guardar\n> ");
                Scanner sc = new Scanner(System.in);
                int y = sc.nextInt();
                System.out.println();
                if (y != 1) {
                    
                    try {
                        filePointer();
                    } catch (Exception e) {
                        System.out.println("Erro: Nao foi possivel guardar o jogo! -> " + e);
                    }
                    
                    game = new ObservableGame();
                }
                    
            }
            
            System.out.println("Carta " + c);
            
            if (c instanceof Event) {
                Random rand = new Random();
                int n = rand.nextInt(6) + 1;
                System.out.println("Dado Sorteado = " + n);
                game.solveEvent(n);
            }
            else if (c instanceof Merchant) {
                System.out.println("Opcao: ");
                game.solveTrade();
            }
            else if (c instanceof Monster) {
                game.solveMonster();
            }
            else if (c instanceof Resting) {
                System.out.println("Opcao: ");
                game.solveResting();
            }
            else if (c instanceof Trap) {
                Random rand = new Random();
                int n = rand.nextInt(6) + 1;
                System.out.println("Valor do dado = " + n);
                game.solveTrap(n);
            }
            else if (c instanceof Treasure) {
                Random rand = new Random();
                int n = rand.nextInt(6) + 1;
                System.out.println("Valor do dado = " + n);
                game.solveTreasure(n);
            }
            else if (c instanceof BossMonster) {
                    game.solveMonster();
            }
            
            game.getGameLogic().nextColumn();
        }
        else {
            
            Card c1 = game.getGameLogic().getCard();
            Card c2 = game.getGameLogic().getCard();
            System.out.println("-------------------- COLUNA " + column + " -----------------------");
            System.out.println(game.getPlayer() + "\n-----------------------------------------------------");
            
            System.out.println("(1) Carta " + c1);
            System.out.println("(2) Carta " + c2);
            int option;
            while (true) {
                System.out.print("Seleccionar carta:\n> ");
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();
                if (option == 1 || option == 2)
                    break;
            }
            if (option == 1) {
                if (c1 instanceof Event) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveEvent(n);
                }
                else if (c1 instanceof Merchant) {
                    game.solveTrade();
                }
                else if (c1 instanceof Monster) {
                    game.solveMonster();
                }
                else if (c1 instanceof Resting) {
                    game.solveResting();
                }
                else if (c1 instanceof Trap) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveTrap(n);
                }
                else if (c1 instanceof Treasure) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveTreasure(n);
                }
                
            }
            else if (option == 2) {
                if (c2 instanceof Event) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveEvent(n);
                }
                else if (c2 instanceof Merchant) {
                    game.solveTrade();
                }
                else if (c2 instanceof Monster) {
                    game.solveMonster();
                }
                else if (c2 instanceof Resting) {
                    game.solveResting();
                }
                else if (c2 instanceof Trap) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveTrap(n);
                }
                else if (c2 instanceof Treasure) {
                    Random rand = new Random();
                    int n = rand.nextInt(6) + 1;
                    System.out.println("Valor do dado = " + n);
                    game.solveTreasure(n);
                }
            }
            
            game.getGameLogic().nextColumn();
        }
        
        
    }
    public void uiSelectionState() {
        System.out.println("(1) Reinforce your weapon: +1 XP");
        System.out.println("(2) Search for Ration: +1 FOOD");
        System.out.println("(3) Heal: +2 HP");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        int o = sc.nextInt();
        
        if (o == 1)
            game.selectOption("Weapon");
        else if (o == 2)
            game.selectOption("Ration");
        else
            game.selectOption("Heal");
    }
    public void uiTradingState() {
        
        System.out.println("(1) Comprar");
        System.out.println("(2) Vender");
        System.out.println("(3) PLAYER");
        System.out.print("(0) Skip\n> ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if (option == 1) {
            System.out.println("(1) Ration , +1 Food");
            System.out.println("(2) HealthS, +1 HP");
            System.out.println("(3) HealthB, +4 HP");
            System.out.println("(4) ArmorP , +1 Armor");
            System.out.println("(5) Spell  , +1 Spell");
            System.out.print("(6) Ration, +1 Food\n> ");
            option = sc.nextInt();
            if (option == 1)
                game.buy("Ration", "");
            else if (option == 2)
                game.buy("SmallHealth", "");
            else if (option == 3)
                game.buy("BigHealth", "");
            else if (option == 4)
                game.buy("Armor", "");
            else {
                System.out.println("(1) Fire");
                System.out.println("(2) Ice");
                System.out.println("(3) Poison");
                System.out.print("(4) Heal\n> ");
                option = sc.nextInt();
                if (option == 1)
                    game.buy("Spell", "Fire");
                else if (option == 2)
                    game.buy("Spell", "Ice");
                else if (option == 3)
                    game.buy("Spell", "Poison");
                else 
                    game.buy("Spell", "Heal");
            }
        }
        else if (option == 2) {
            System.out.println("(1) Armor Piece, -1 Armor, +3 Gold");
            System.out.print("(2) Spell, -1 Spell, +4 Gold\n> ");
            option = sc.nextInt();      
            
            if (option == 1)
                game.sell("Armor", "");
            else {
                System.out.println("(1) Fire");
                System.out.println("(2) Ice");
                System.out.println("(3) Poison");
                System.out.print("(4) Heal\n> ");
                option = sc.nextInt();
                if (option == 1)
                    game.sell("Spell", "Fire");
                else if (option == 2)
                    game.sell("Spell", "Ice");
                else if (option == 3)
                    game.sell("Spell", "Poison");
                else 
                    game.sell("Spell", "Heal");
            }
        }
        else if (option == 3)
            System.out.println(game.getPlayer());
        else
            game.skip();
    }
    public void uiAttackState() {
        
        //AttackState s = (AttackState)game.getState();
        ArrayList<Dice> di = game.getState().getDiceValue();
        int x = 0;
        
        do {
            System.out.println("\nMONSTRO HP = " + game.getGameLogic().getDungeon().getMonsterHp());
            System.out.print("Dado(s): ");
            for (int i = 0; i < di.size(); i++)
                System.out.print("[d" + (i + 1) + ": " + di.get(i).getNumber() + "]\t");

            System.out.println("\nSeleccionar opcao: ");
            System.out.println("1) Rolar 6s");
            System.out.println("2) Feats");
            System.out.print("3) OK\n> ");

            Scanner sc = new Scanner(System.in);
            x = sc.nextInt();

            if (x == 1) {
                System.out.println("Numero do dado: ");
                System.out.print("> ");
                int y = sc.nextInt();
                game.getState().roll6(y);
            }
            else if (x == 2) {
                System.out.println("Numero do dado: ");
                System.out.print("> ");
                int y = sc.nextInt();
                game.getState().feats(y);
            }
        } while(x != 3);
        
        game.solveAttack();
    }
    public void uiDefenseState() {

        System.out.println("Seleccionar Spell:");
        System.out.println("1) Fire");
        System.out.println("2) Ice");
        System.out.println("3) Poison");
        System.out.println("4) Heal");
        System.out.print("5) OK\n> ");

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        if (x == 1)
            game.solveSpell("Fire");
        else if (x == 2)
            game.solveSpell("Ice");
        else if (x == 3)
            game.solveSpell("Poison");
        else if (x == 4)
            game.solveSpell("Heal");

        game.attack();
    }
    public void run() {

        while (!quit) {
            
            IStates state = game.getState();
           
            if (state instanceof BeginningState)
                uiBeginningState();
            else if (state instanceof CardState)
                uiCardState();
            else if (state instanceof SelectionState)
                uiSelectionState();
            else if (state instanceof TradingState)
                uiTradingState();
            else if (state instanceof AttackState)
                uiAttackState();
            else if (state instanceof DefenseState)
                uiDefenseState();
        }
        
    }

    // Atira excepcao se a leitura ou escrita do ficheiro falhar
    private ObservableGame fread(String fileName) throws Exception {
        
        ObjectInputStream o = null;
        
        try {
            o = new ObjectInputStream(new FileInputStream(fileName));
            return (ObservableGame)o.readObject();
        } 
        finally {
            if (o != null)
                o.close();
        }
        
    }   
    private void filePointer() throws Exception {
        
        String fileName;
        
        System.out.print("Nome do ficheiro: ");
        fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

        if(fileName==null || fileName.length() <= 0)
            return;

        fwrite(fileName);            
    }  
    private void fwrite(String fileName) throws Exception {
        
        ObjectOutputStream o = null;
        
        try {
            o = new ObjectOutputStream(new FileOutputStream(fileName)); 
            o.writeObject(game);
            
        } finally {
            if(o != null)
                o.close();
            
        }
    }
    

}
