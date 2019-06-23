package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok kiirasa szoveges fajlba.
 */
public class Save extends Command {
    
    public Save(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        shapeRegistry.saveToTextFile(cmd[1]);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                    +   "    Alakzatok kiirasa szoveges fajlba, pl.:\n"
                    +   "    save shapes.txt\n";
        return s;
    }
    
}
