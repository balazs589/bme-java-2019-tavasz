package shapes.exceptions;

/**
 * Konzol-menu hibas parancs parameterezese eseten dobhato kiveteltipus.
 * 
 * @see shapes.commands.Command#action(String[] cmd)
 */
public class CommandException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public CommandException() {
    }
    
    public CommandException(String message) {
        super(message);
    }
    
}
