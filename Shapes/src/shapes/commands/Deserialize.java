package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok deszerializalasa megadott fajlbol.
 */
public class Deserialize extends Command {
    
    public Deserialize(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        shapeRegistry.deserialize(cmd[1]);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok deszerializalasa megadott fajlbol, pl.:\n"
                +   "    deserialize shapes.ser\n";
        return s;
    }
    
}
