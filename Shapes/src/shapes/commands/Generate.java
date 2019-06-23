package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 *  Veletlen alakzatok generalasa (randomGeneratorProperties.xml alapjan).
 */
public class Generate extends Command {
    
    public Generate(ShapeRegistry s) {
        super(s);
    }
    
    @Override
    public void action(String[] cmd) throws CommandException {
        if (cmd.length != 2) {
            throw new CommandException("Hibas parancs.");
        }
        int numberOfShapes;
        try {
            numberOfShapes = Integer.parseInt(cmd[1]);
        } catch (NumberFormatException e) {
            throw new CommandException(cmd[1] + " nem egesz szam.");
        }
        shapeRegistry.generateShapes(numberOfShapes);
    }
    
    @Override
    public String getManual() {
        String s =      ""
                +   "    Veletlen alakzatok generalasa (randomGeneratorProperties.xml alapjan), pl.:\n"
                +   "    generate 100\n";
        return s;
    }
    
}
