package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 * Ures sor: ne legyen felesleges hibauzenet.
 */
public class EmptyCommand extends Command {
    
    public EmptyCommand(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        // az ures sor nem csinal semmit
    }
    
    @Override
    public String getManual() {
        return null;
    }
    
}
