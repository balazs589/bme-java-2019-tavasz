package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 * Alakzatok beolvasasa szoveges fajlbol es azok megtartasa,
 * amik az egysegsugaru koron kivul vannak.
 */
public class Load extends Command {
    
    public Load(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        shapeRegistry.loadFromTextFile(cmd[1]);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Alakzatok beolvasasa szoveges fajlbol es azok megtartasa, "
                +   "amik az egysegsugaru koron kivul vannak, pl.:\n"
                +   "    load shapes.txt\n";
        return s;
    }
    
}
