package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok listajanak torlese.
 */
public class Clear extends Command {
    
    public Clear(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 1) {
            throw new CommandException("A parancs csak argumentumok nelkul hivhato.");
        }
        shapeRegistry.clear();
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok listajanak torlese, pl.:\n"
                +   "    clear\n";
        return s;
    }
    
}
