package shapes.exceptions;

/**
 * Veletlen sikidomok generatoranak letrehozasakor dobhato kiveteltipus.
 * 
 * @see shapes.program.ShapeGenerator#ShapeGenerator(String propertyFileName)
 */
public class PropertyException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public PropertyException() {
    }
    
    public PropertyException(String message) {
        super(message);
    }
    
}
