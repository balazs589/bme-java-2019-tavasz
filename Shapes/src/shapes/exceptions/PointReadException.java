package shapes.exceptions;

/**
 * Pont szovegbol valo (inverz toString) letrehozasakor dobhato kiveteltipus.
 * 
 * @see shapes.geometry.Point#Point(String s)
 */
public class PointReadException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public PointReadException() {
    }
    
    public PointReadException(String message) {
        super(message);
    }
    
}
