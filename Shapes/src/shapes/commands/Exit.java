package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Kilepes a programbol.
 */
public class Exit extends Command {
    
    public Exit(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 1) {
            throw new CommandException("A parancs csak argumentumok nelkul hivhato.");
        }
        System.out.println("Kilepes.");
        System.exit(0);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Kilepes a programbol, pl.:\n"
                +   "    exit\n";
        return s;
    }
    
}
