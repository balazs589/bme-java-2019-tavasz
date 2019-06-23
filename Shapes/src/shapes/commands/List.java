package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Alakzatok listazasa std outputra.
 */
public class List extends Command {
    
    public List(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 1) {
            throw new CommandException("A parancs csak argumentumok nelkul hivhato.");
        }
        shapeRegistry.printShapes();
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok listazasa std outputra, pl.:\n"
                +   "    list";
        return s;
    }
    
}
