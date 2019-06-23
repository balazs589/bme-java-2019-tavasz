package shapes.exceptions;

import shapes.geometry.Point;

/**
 * Szabalyos sokszog letrehozasakor dobhato kiveteltipus.
 * 
 * @see shapes.geometry.RegularPolygon#RegularPolygon(Point center, Point firstVertex, String type)
 */
public class RegularPolygonException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public RegularPolygonException() {
    }
    
    public RegularPolygonException(String message) {
        super(message);
    }
    
}
