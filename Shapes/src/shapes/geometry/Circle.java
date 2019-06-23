package shapes.geometry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 2 dimezios kort leiro osztaly.
 */
public class Circle extends Shape {
    
    private static final long serialVersionUID = 1L;
    private transient double  radius;
    
    /**
     * Kor megadasa kozeppontjaval es oldalvonalanak egy pontjaval.
     * 
     * @param center      Kor kozeppontja.
     * @param firstVertex Kor oldalvonalanak egy pontja.
     */
    public Circle(Point center, Point firstVertex) {
        super(center, firstVertex, "Circle");
        radius = center.distance(firstVertex);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor akkor tartalmazza a pontot, ha a pont tavolsaga a kor kozeppontjatol
     * kevesebb mint a kor sugara.
     */
    @Override
    public boolean containsPoint(Point point) {
        return this.center.distance(point) <= this.radius;
    }
    
    /**
     * Kor kozeppontjat megado metodus.
     * 
     * @return Kor kozeppontja.
     */
    public Point getCenter() {
        return center;
    }
    
    /**
     * Kor sugarat megado metodus.
     * 
     * @return Kor sugara.
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor eseten a kozeppont x koordinatajatol egy korsugarnyival nagyobb ertek.
     */
    @Override
    public double getXMax() {
        return center.getX() + radius;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor eseten a kozeppont x koordinatajatol egy korsugarnyival kisebb ertek.
     */
    @Override
    public double getXMin() {
        return center.getX() - radius;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor eseten a kozeppont y koordinatajatol egy korsugarnyival nagyobb ertek.
     */
    @Override
    public double getYMax() {
        return center.getY() + radius;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor eseten a kozeppont y koordinatajatol egy korsugarnyival kisebb ertek.
     */
    @Override
    public double getYMin() {
        return center.getY() - radius;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Kor akkor van fedesben egy masik korrel, ha a ket kor kozeppontjanak a
     * tavolsaga kevesebb mint a ket kor sugaranak osszege.
     */
    @Override
    public boolean hasIntersectionWithCircle(Circle circle) {
        return this.center.distance(circle.center) <= this.radius + circle.radius;
    }
    
    /**
     * Metodus ami eldonti, hogy a korvonalnak van-e kozos pontja a bemeno
     * parameterkent megkapott szakasszal.
     * 
     * Megvizsgaljuk, hogy a szakaszt tartalmazo egyenesnek van-e metszespontja a
     * korrel es hogy ha van, akkor ez a szakaszra esik-e. (A kor es az egyenes
     * parameteres egyenleteibol allo masodfoku egyenletrendszert kell ehhez
     * megoldani.)
     * 
     * @param lineSegment Szakasz amirol eldonti a metodus, hogy annak van-e kozos
     *                    pontja a korrel.
     * @return true: ha van kozos pontjuk, false: ha nincs kozos pontjuk
     */
    public boolean hasIntersectionWithLineSegment(LineSegment lineSegment) {
        
        double            x0 = center.getX();
        double            y0 = center.getY();
        
        double            x1 = lineSegment.getInitialPoint().getX();
        double            y1 = lineSegment.getInitialPoint().getY();
        double            x2 = lineSegment.getTerminalPoint().getX();
        double            y2 = lineSegment.getTerminalPoint().getY();
        
        double            a  = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        double            b  = 2 * ((x1 - x0) * (x2 - x1) + (y1 - y0) * (y2 - y1));
        double            c  = (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) - radius * radius;
        
        QuadraticEquation e  = new QuadraticEquation(a, b, c);
        for (Double t : e.getRoots()) {
            if (0 <= t && t <= 1) { // szakasznak van kozos belso pontja a korvonallal
                return true;
            }
        }
        return false;
    }
    
    /**
     * Kor objektum deszerializalasahoz: a konstruktor altal is elvart adattagok
     * beolvasasa es az ott lefuto inicializalo kodreszletek lefuttatasa.
     * 
     * @param stream Adatok beolvasasahoz forras.
     * @throws IOException            Ha hiba keletkezik a beolvasas soran.
     * @throws ClassNotFoundException Ha a Point osztaly nem elerheto.
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        center      = (Point) stream.readObject();
        firstVertex = (Point) stream.readObject();
        radius      = center.distance(firstVertex);
    }
    
    /**
     * Kor objektum szerializalasahoz: csak a konstruktor szamara szukseges
     * adattagokat irjuk ki.
     * 
     * @param stream Adatok kuldesehez cel.
     * @throws IOException Ha hiba keletkezik a kuldes soran.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(center);
        stream.writeObject(firstVertex);
    }
    
}
