package shapes.exceptions;

/**
 * Sikidom letrehozasakor dobhato kiveteltipus.
 * 
 * @see shapes.program.ShapeRegistry#loadFromTextFile(String fileName)
 * 
 */
public class ShapeReadException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public ShapeReadException() {
    }
    
    public ShapeReadException(String message) {
        super(message);
    }
    
}
