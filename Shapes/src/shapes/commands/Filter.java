package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok kozul azok listazasa, amik az megadott pontot tartalmazzak.
 */
public class Filter extends Command {
    
    public Filter(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        shapeRegistry.filterShapes(cmd[1]);
    }
    
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok kozul azok listazasa, amik az megadott pontot tartalmazzak, pl.:\n"
                +   "    filter (6.0 0.0)\n";
        return s;
    }
    
}
