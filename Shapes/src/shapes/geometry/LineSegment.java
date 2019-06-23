package shapes.geometry;

/**
 * 2 dimenzios szakaszt megado osztaly, ami egy kezdopontbol es egy vegpontbol
 * all. Vektorkent is ertelmezheto: kezdopontbol vegpontba mutato vektor.
 */
public class LineSegment {
    
    private Point initialPoint;
    private Point terminalPoint;
    
    /**
     * Szakasz megadasa a kezdo es vegpontjaval.
     * 
     * @param initialPoint  Szakasz (vektor) kezdopontja.
     * @param terminalPoint Szakasz (vektor) vegpontja.
     */
    public LineSegment(Point initialPoint, Point terminalPoint) {
        this.initialPoint  = initialPoint;
        this.terminalPoint = terminalPoint;
    }
    
    /**
     * Szakasz kezdopontjat megado metodus.
     * 
     * @return Kezdopont.
     */
    public Point getInitialPoint() {
        return initialPoint;
    }
    
    /**
     * Szakasz kezdopontjat megado metodus.
     * 
     * @return Vegpont.
     */
    public Point getTerminalPoint() {
        return terminalPoint;
    }
    
    /**
     * Szakasz forgatasa a kezdopontja korul.
     * 
     * @param alfa Szog amivel pozitiv iranyba forgatjuk a szakaszt mint 2 dimenzios
     *             vektort.
     * @return Az elforgatas utan kapott szakasz (vektor).
     */
    public LineSegment rotateAboutInitialPoint(double alfa) {
        Point newTerminalPoint = terminalPoint.translation(initialPoint.minusOperator()).rotateAboutOrigin(alfa)
                .translation(initialPoint);
        return new LineSegment(initialPoint, newTerminalPoint);
    }
    
    @Override
    public String toString() {
        return "[" + initialPoint + " -> " + terminalPoint + "]";
    }
    
}
