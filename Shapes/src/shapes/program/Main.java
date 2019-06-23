package shapes.program;

import shapes.exceptions.PropertyException;
import shapes.gui.GUI;

/**
 * Foprogram.
 */
public class Main {
    
    public static void main(String[] args) throws PropertyException {
        
        // tesztekhez:
        
        // args = new String[2];
        // args[0] = "--auto";
        // args[1] = "./input-output/shapes.txt";
        
        // args = new String[1];
        // args[0] = "--console";
        
        // args = new String[1];
        // args[0] = "--gui";
        
        if (args.length == 2 && args[0].equals("--auto")) {
            consoleApplication console = new consoleApplication();
            console.auto(args[1]);
        } else if (args.length == 1 && args[0].equals("--console")) {
            consoleApplication console = new consoleApplication();
            console.program();
        } else if (args.length == 1 && args[0].equals("--gui")) {
            GUI.program();
        } else {
            printUsage();
        }
        
    }
    
    private static void printUsage() {
        String message = "A program inditasa:\n" + "programneve < --auto fajlnev | --console | --gui > \n"
                + "--auto fajlnev : feldolgozza a fajlnev szoveges fajl sorait majd a stdin-rol koordinatakat olvas be\n"
                + "--console : parancssoros felulet\n" + "--gui : grafikus felulet" + "";
        
        System.out.println(message);
        System.exit(1);
    }
    
}
