package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok szerializalasa es fajlba mentese.
 */
public class Serialize extends Command {
    
    public Serialize(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        shapeRegistry.serialize(cmd[1]);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok szerializalasa es fajlba mentese, pl.:\n"
                +   "    serialize shapes.ser\n";
        return s;
    }
    
}
