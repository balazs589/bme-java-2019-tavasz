package shapes.commands;

import shapes.exceptions.CommandException;
import shapes.program.ShapeRegistry;

/**
 * Parancsertelmezo altal hasznalt parancs-osztalyok ososztalya.
 * A parancsok shapeRegistry referencian keresztul erik el a tarolt alakzatokat.
 */
public abstract class Command {
    
    protected ShapeRegistry shapeRegistry;
    
    public Command(ShapeRegistry s) {
        this.shapeRegistry = s;
    }
    
    /**
     * @param cmd
     * Parancs stringek tombje.
     * @throws CommandException
     * Hibas parancs parameterezes eseten meg van a lehetoseg a hiba jelzesere.
     */
    public abstract void action(String[] cmd) throws CommandException;
    
    /**
     * Parancs rovid leirasa es pelda a hasznalatra.
     * @return A parancshoz tartozo leiras stringje.
     */
    public abstract String getManual();
    
}
