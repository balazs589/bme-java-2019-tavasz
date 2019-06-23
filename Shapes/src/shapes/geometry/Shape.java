package shapes.geometry;

import java.io.Serializable;

/**
 * 2 dimenzios forgasszimmetrikus alakzatoz leiro osztaly, ami megadhato a
 * forgasi szimmetria kozeppontjaval es a hatarvonalanak egy pontjaval.
 */
public abstract class Shape implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Szoveges fajlbol valo beolvasas soran nem megfelelo formatum eseten, helyes
     * formatum leirasa.
     * 
     * @return Hibauzenet szovege.
     */
    public static String formatErrorMessage() {
        return "Nem megfeleloen megadott sikidom. " + "Helyes formatum tabulatorokkal elvalasztva: "
                + "TIPUS, kozeppont, csucspont\n" + "pl.:\n" + "Circle\t(2.2 3.3)\t(3.1 3.4)";
    }
    
    /**
     * Sikidom geometriai kozeppontja.
     */
    protected Point  center;
    
    /**
     * Szabalyos sokszog eseten a sokszog egy csucspontja, kor eseten a korvonal egy
     * pontja.
     */
    protected Point  firstVertex;
    
    /**
     * Sikidom tipusa: kor vagy szabalyos sokszog (Circle, Triangle, Square ... ).
     */
    protected String type;
    
    /**
     * @param center      A sikidom kozeppontja.
     * @param firstVertex A sikidom egy csucspontja, vagy a korvonal egy pontja.
     * @param type        A sikidom tipusa: Circle, Triangle, Square ...
     */
    public Shape(Point center, Point firstVertex, String type) {
        this.center      = center;
        this.firstVertex = firstVertex;
        this.type        = type;
    }
    
    /**
     * Megadja, hogy a sikidom tartalmazza-e a bemeno parameterkent kapott pontot.
     * 
     * @param point Pont amirol eldonti a metodus, hogy azt tartalmazza-e a sikidom.
     * @return true: ha tartalmazza a pontot, false: ha nem tartalmazza
     */
    public abstract boolean containsPoint(Point point);
    
    /**
     * Sikidom tipusat visszaado metodus.
     * 
     * @return Sikidom tipusa.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sikidom hatarvonalat alkoto pontok kozul a legnagyobb x koordinataju pontnak
     * x koordinataja.
     * 
     * @return Sikidom "jobb szelso pontjanak" x koordinataja.
     */
    public abstract double getXMax();
    
    /**
     * Sikidom hatarvonalat alkoto pontok kozul a legkisebb x koordinataju pontnak x
     * koordinataja.
     * 
     * @return Sikidom "bal szelso pontjanak" x koordinataja.
     */
    public abstract double getXMin();
    
    /**
     * Sikidom hatarvonalat alkoto pontok kozul a legnagyobb y koordinataju pontnak
     * y koordinataja.
     * 
     * @return Sikidom "legfelso pontjanak" y koordinataja.
     */
    public abstract double getYMax();
    
    /**
     * Sikidom hatarvonalat alkoto pontok kozul a legkisebb y koordinataju pontnak y
     * koordinataja.
     * 
     * @return Sikidom "legalso pontjanak" y koordinataja.
     */
    public abstract double getYMin();
    
    /**
     * Metodus ami eldonti, hogy a sikidomnak van-e kozos pontja a bemeno
     * parameterkent megkapott korlappal.
     * 
     * @param circle Kor amihez kepest vizsgaljuk a sikidom helyzetet.
     * @return true: ha van kozos pont, false: ha nincs kozos pont
     */
    public abstract boolean hasIntersectionWithCircle(Circle circle);
    
    /**
     * {@inheritDoc}
     * 
     * Egy sorban tabulatorokkal elvalasztva: Tipus, Kozeppont, Csucspont. Pl.:
     * 
     * <pre>
     * Type    (1.2 3.4)       (5.6 7.8)
     * </pre>
     */
    @Override
    public String toString() {
        return type + "\t" + center + "\t" + firstVertex;
    }
    
}
