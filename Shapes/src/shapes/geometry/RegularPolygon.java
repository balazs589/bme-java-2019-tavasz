package shapes.geometry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import shapes.exceptions.RegularPolygonException;

/**
 * 2 dimezios szabalyos sokszoget leiro osztaly.
 */
public class RegularPolygon extends Shape implements Serializable {
    
    public static HashMap<Integer, String>  edgeNumberToType;
    
    private static final long               serialVersionUID = 1L;
    private static HashMap<String, Integer> typeToEdgenumber;
    
    static {
        initializeTypes();
    }
    
    /**
     * Lehetseges szabalyos sokszogek megadasa: oldalak szamaval es nevvel. Jelenleg
     * szabalyos haromszog es negyzet, de tetszoleges oldalszamu sokszog
     * megvalosithato a forraskodban egy-egy megfelelol sor hozzaadasaval.
     * 
     * {@link #edgeNumberToType} HashMap-et modosito metodus, es hozzatartozo
     * parancs (vagy grafikus vezerles) megvalositasaval az is megoldhato lenne,
     * hogy a felhasznalo a program futasa kozben adjon hozza vagy toroljon
     * szabalyos sokszog tipusokat tetszoleges oldalszammal es elnevezessel.
     */
    private static void initializeTypes() {
        edgeNumberToType = new HashMap<Integer, String>();
        typeToEdgenumber = new HashMap<String, Integer>();
        
        edgeNumberToType.put(3, "Triangle");
        edgeNumberToType.put(4, "Square");
        // edgeNumberToType.put(5, "Pentagon");
        // edgeNumberToType.put(6, "Hexagon");
        // ...
        
        for (Entry<Integer, String> entry : edgeNumberToType.entrySet()) {
            typeToEdgenumber.put(entry.getValue(), entry.getKey());
        }
    }
    
    private transient ArrayList<LineSegment> edges;
    private transient int                    numberOfEdges;
    
    /**
     * Szabalyos sokszog letrehozasa annak kozeppontjaval, egy csucspontjaval
     * valamint, tipusaval.
     * 
     * @param center      Szabalyos sokszog kozeppontja.
     * @param firstVertex Szabalyos sokszog egyik csucspontja.
     * @param type        Szabalyos sokszog tipusa.
     * @throws RegularPolygonException Ha az osztaly altal nem ismert tipus lett
     *                                 megadva bemeno parameterkent.
     */
    public RegularPolygon(Point center, Point firstVertex, String type) throws RegularPolygonException {
        super(center, firstVertex, type);
        if (!typeToEdgenumber.containsKey(type)) {
            throw new RegularPolygonException("Ismeretlen szabalyos sokszog tipus : " + type);
        }
        this.numberOfEdges = typeToEdgenumber.get(type);
        calculateEdges();
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog akkor tartalmazza a pontot, ha a sokszog mindegyik oldalara
     * igaz, hogy az oldal (pozitiv koruljarasi irany szerint kivalasztott)
     * kezdopontjabol az oldal vegpontjaba muatato vektor es az oldal kezdopontjabol
     * a vizsgalt pontba mutato vektor vektorialis szorzata nem negativ (a pont
     * felulnezetben mindig balra van az oldal altal meghatarozott egyenestol). Ha
     * barmelyik oldalra ez nem igaz, akkor a vizsgalt pont a szabalyos sokszogon
     * kivul van.
     */
    @Override
    public boolean containsPoint(Point point) {
        for (LineSegment edge : edges) {
            Point a = edge.getTerminalPoint().translation(edge.getInitialPoint().minusOperator());
            Point b = point.translation(edge.getInitialPoint().minusOperator());
            if (a.crossProduct(b) < 0.0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog eseten a legnagyobb x koordinataju csucspont x
     * koordinataja.
     */
    @Override
    public double getXMax() {
        double xMax = edges.get(0).getInitialPoint().getX();
        for (LineSegment edge : edges) {
            double x = edge.getInitialPoint().getX();
            if (x > xMax) {
                xMax = x;
            }
        }
        return xMax;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog eseten a legkisebb x koordinataju csucspont x koordinataja.
     */
    @Override
    public double getXMin() {
        double xMin = edges.get(0).getInitialPoint().getX();
        for (LineSegment edge : edges) {
            double x = edge.getInitialPoint().getX();
            if (x < xMin) {
                xMin = x;
            }
        }
        return xMin;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog eseten a legnagyobb y koordinataju csucspont y
     * koordinataja.
     */
    @Override
    public double getYMax() {
        double yMax = edges.get(0).getInitialPoint().getY();
        for (LineSegment edge : edges) {
            double y = edge.getInitialPoint().getY();
            if (y > yMax) {
                yMax = y;
            }
        }
        return yMax;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog eseten a legkisebb y koordinataju csucspont y koordinataja.
     */
    @Override
    public double getYMin() {
        double yMin = edges.get(0).getInitialPoint().getY();
        for (LineSegment edge : edges) {
            double y = edge.getInitialPoint().getY();
            if (y < yMin) {
                yMin = y;
            }
        }
        return yMin;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Szabalyos sokszog akkor es csak akkor van teljes terjedelmeben egy koron
     * kivul, ha egyszerre igaz, hogy: 1. a szabalyos sokszog egyik oldala sem
     * metszi a korvonalat 2. a kor nincs teljes terjedelmeben a szabalyos sokszogon
     * belul 3. a szabalyos sokszog nincs teljes terjedelmeben a koron belul.
     * 
     * Amennyiben az 1. feltetel igaz, akkor a 2. feltetel teljesulesenek szukseges
     * es elegseges feltetele, hogy a korlap egy tetszolegesen kivalasztott pontja
     * (mondjuk a kozeppontja) nincs a szabalyos sokszogon belul.
     * 
     * Illetve ha az 1. feltetel igaz, akkor a 3. feltetel teljesulesenek szukseges
     * es elegseges feltetele, hogy a szabalyos sokszog egy tetszolegesen
     * kivalasztott belso pontja (mondjuk a kozeppontja) nincs a korvonalon belul.
     * 
     * Ha barmelyik feltetel nem teljesul, akkor a sokszog fedesben van a korlappal.
     * Ha mindharom feltetel teljesul, akkor a sokszog nincs fedesben a korlappal,
     * azaz teljes terjedelmeben a koron kivul van.
     */
    @Override
    public boolean hasIntersectionWithCircle(Circle circle) {
        for (LineSegment edge : edges) {
            if (circle.hasIntersectionWithLineSegment(edge)) {
                return true;
            }
        }
        if (containsPoint(circle.center)) {
            return true;
        }
        if (circle.containsPoint(center)) {
            return true;
        }
        return false;
    }
    
    /**
     * Csucspontok koordinatait listakent megado metodus.
     * 
     * @return A csucspontok koordinatait tartalmazo lista: (x1, y1, x2, y2, x3, y3,
     *         ...) formatumban.
     */
    public ArrayList<Double> vertexCoordinates() {
        ArrayList<Double> points = new ArrayList<Double>();
        for (LineSegment edge : edges) {
            points.add(edge.getInitialPoint().getX());
            points.add(edge.getInitialPoint().getY());
        }
        return points;
    }
    
    /**
     * Szabalyos sokszog oldalait meghatarozo metodus.
     */
    private void calculateEdges() {
        edges = new ArrayList<LineSegment>();
        for (int i = 0; i < numberOfEdges - 1; i++) {
            edges.add(new LineSegment(getVertex(i), getVertex(i + 1)));
        }
        edges.add(new LineSegment(getVertex(numberOfEdges - 1), getVertex(0)));
    }
    
    /**
     * A szabalyos sokszog egy adott csucspontjat meghatarozo metodus.
     * 
     * @param i A csucspont indexe: az elso csucspont a 0. indexu.
     * @return A megadott indexu csucspont.
     */
    private Point getVertex(int i) {
        LineSegment vector = new LineSegment(this.center, this.firstVertex);
        return vector.rotateAboutInitialPoint(2 * Math.PI / numberOfEdges * i).getTerminalPoint();
    }
    
    /**
     * Szabalyos sokszog objektum deszerializalasahoz: a konstruktor altal is elvart
     * adattagok beolvasasa es az ott lefuto inicializalo kodreszletek lefuttatasa.
     * 
     * @param stream Adatok beolvasasahoz forras.
     * @throws IOException            Ha hiba keletkezik a beolvasas soran.
     * @throws ClassNotFoundException Ha a Point osztaly nem elerheto.
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        center             = (Point) stream.readObject();
        firstVertex        = (Point) stream.readObject();
        type               = (String) stream.readObject();
        this.numberOfEdges = typeToEdgenumber.get(type);
        this.calculateEdges();
    }
    
    /**
     * Szabalyos sokszog objektum szerializalasahoz: csak a konstruktor szamara
     * szukseges adattagokat irjuk ki.
     * 
     * @param stream Adatok kuldesehez cel.
     * @throws IOException Ha hiba keletkezik a kuldes soran.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(center);
        stream.writeObject(firstVertex);
        stream.writeObject(type);
    }
    
}
