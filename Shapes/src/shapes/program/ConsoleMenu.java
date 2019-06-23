package shapes.program;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import shapes.commands.Clear;
import shapes.commands.Command;
import shapes.commands.Deserialize;
import shapes.commands.EmptyCommand;
import shapes.commands.Exit;
import shapes.commands.Filter;
import shapes.commands.Generate;
import shapes.commands.Help;
import shapes.commands.List;
import shapes.commands.Load;
import shapes.commands.Save;
import shapes.commands.Serialize;
import shapes.exceptions.CommandException;

/**
 * Szoveges parancsok beolvasasat es futtatasat vegzo osztaly.
 * 
 * Az ismert parancsok osztalyai: {@link shapes.commands}
 */
public class ConsoleMenu {
    
    private TreeMap<String, Command> commands;
    private Scanner                  input;
    
    /**
     * Letrehozaskor szukseg van az alakzatokat tarolo objektumra, valamint a 
     * parancsok forrasat biztosito scanner objektumra.
     * 
     * @param s     Sikidomok taroloja amin a parancsok dolgoznak.
     * @param input Szoveges parancsok forrasa.
     */
    public ConsoleMenu(ShapeRegistry s, Scanner input) {
        
        this.input = input;
        commands   = new TreeMap<String, Command>();
        
        commands.put("help",            new Help            (this, s));
        commands.put("",                new EmptyCommand    (s));
        
        commands.put("exit",            new Exit            (s));
        commands.put("list",            new List            (s));
        commands.put("clear",           new Clear           (s));
        commands.put("filter",          new Filter          (s));
        commands.put("generate",        new Generate        (s));
        commands.put("save",            new Save            (s));
        commands.put("load",            new Load            (s));
        commands.put("serialize",       new Serialize       (s));
        commands.put("deserialize",     new Deserialize     (s));
        
    }
    
    /**
     * Parancsok listazasa.
     */
    public void printCommands() {
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            if (!entry.getKey().equals("")) {
                System.out.println("" + entry.getKey()
                        + (entry.getValue().getManual() != null ? ":\n" + entry.getValue().getManual() : ""));
            }
        }
    }
    
    /**
     * Parancsertelmezo futtatasa.
     */
    public void run() {
        System.out.println("Shape registry console program");
        while (true) {
            System.out.print(">>>");
            String[] cmd = input.nextLine().trim().split(" ", 2);
            if (commands.containsKey(cmd[0])) {
                try {
                    commands.get(cmd[0]).action(cmd);
                } catch (CommandException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Ismeretlen parancs: " + cmd[0]);
            }
        }
    }
    
}
