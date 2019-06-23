package shapes.program;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import shapes.exceptions.PropertyException;
import shapes.exceptions.RegularPolygonException;
import shapes.geometry.Circle;
import shapes.geometry.Point;
import shapes.geometry.RegularPolygon;
import shapes.geometry.Shape;

/**
 * Veletlen sikidomok eloallitasara hasznalhato osztaly. A sikidomok
 * kozeppontjai egy teglalapon egyenletesen lesznek szetszorva. Meretuk es
 * elforgatasuk szinten veletlen szammal meghatarozva.
 */
public class ShapeGenerator {
    
    /**
     * Teglalap jobb szelenek x koordinataja.
     */
    private double       centerMaxX;
    /**
     * Teglalap tetejenek y koordinataja.
     */
    private double       centerMaxY;
    /**
     * Teglalap bal szelenek x koordinataja.
     */
    private double       centerMinX;
    /**
     * Teglalap aljanak y koordinataja.
     */
    private double       centerMinY;
    /**
     * Tulajdonsagok osszegyujtve.
     */
    private Properties   prop;
    /**
     * Sikidom generator sajat veletlenszam generatora.
     */
    private Random       random;
    /**
     * Beepitett veletlenszam generator inicializalasahoz, a letrehozott sikidomok
     * reprodukalhatosaganak erdekeben.
     */
    private long         randomSeed;
    /**
     * Sikidom korulirhato korenek legnagyobb sugara.
     */
    private double       rMax;
    /**
     * Sikidom korulirhato korenek legkisebb sugara.
     */
    private double       rMin;
    /**
     * Lehetseges sikidomok listaja.
     */
    private List<String> types;
    
    /**
     * Default konstruktor input fajl nelkul hivhato. Sikidomok 12.0 x 8.0 meretu
     * origo kozeppontu teglalapon lesznek szetszorva.
     */
    public ShapeGenerator() {
        types = new LinkedList<String>(RegularPolygon.edgeNumberToType.values());
        types.add(0, "Circle");
        random     = new Random();
        
        // default ertekek:
        rMin       = 0.5;
        rMax       = 1.0;
        centerMaxX = 6.0;
        centerMaxY = 4.0;
        centerMinX = -centerMaxX;
        centerMinY = -centerMaxY;
        
    }
    
    /**
     * Veletlen sikidom generator parameterei megadott XML fajlbol lesznek
     * beolvasva.
     * 
     * @param propertyFileName Parametereket tartalamzo fajl neve.
     * @throws PropertyException Amennyiben nem sikerult betolteni a parametereket.
     * @see loadProperties
     */
    public ShapeGenerator(String propertyFileName) throws PropertyException {
        this();
        loadProperties(propertyFileName);
    }
    
    /**
     * Veletlen sikidomok letrehozasa.
     * 
     * @param numberOfShapes Letrehozando sikidomok szama.
     * @return Sikidomok listaja.
     */
    public List<Shape> generateShapes(int numberOfShapes) {
        List<Shape> shapes = new ArrayList<Shape>();
        Shape       s      = null;
        for (int i = 1; i <= numberOfShapes; i++) {
            Point  center             = uniformRectangle(centerMinX, centerMinY, centerMaxX, centerMaxY);
            double circumCircleRadius = rMin + (rMax - rMin) * random.nextDouble();
            double rotationRadian     = 2 * Math.PI * random.nextDouble();
            Point  firstVertex        = center
                    .translation((new Point(0, circumCircleRadius)).rotateAboutOrigin(rotationRadian));
            
            String type               = types.get(random.nextInt(types.size()));
            if (type.equals("Circle")) {
                s = new Circle(center, firstVertex);
            } else {
                try {
                    s = new RegularPolygon(center, firstVertex, type);
                } catch (RegularPolygonException e) {
                    e.printStackTrace();
                }
            }
            shapes.add(s);
        }
        return shapes;
    }
    
    /**
     * Veletlen sikidomok parametereinek beallitasa.
     * 
     * A fajl formatuma pl.: <br>
     * <br>
     * 
     * {@code <properties>                                   } <br>
     * {@code <comment>Properties of random shapes</comment> } <br>
     * {@code <entry key="randomSeed">123456789</entry>      } <br>
     * {@code <entry key="centerMinX">-3.0</entry>           } <br>
     * {@code <entry key="centerMinY">-1.0</entry>           } <br>
     * {@code <entry key="centerMaxX">8.0</entry>            } <br>
     * {@code <entry key="centerMaxY">5.0</entry>            } <br>
     * {@code <entry key="circumCircleRMin">0.2</entry>      } <br>
     * {@code <entry key="circumCircleRMax">1.2</entry>      } <br>
     * {@code </properties>                                  } <br>
     * <br>
     * 
     * @param propertyFileName Parametereket tartalamzo fajl neve.
     * @throws PropertyException Amennyiben nem sikerult betolteni a parametereket.
     * 
     */
    private void loadProperties(String propertyFileName) throws PropertyException {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(propertyFileName));
            
            if (prop.stringPropertyNames().contains("randomSeed")) {
                randomSeed = Long.parseLong(prop.getProperty("randomSeed"));
                random.setSeed(randomSeed);
            }
            if (prop.stringPropertyNames().contains("circumCircleRMin")) {
                rMin = Double.parseDouble(prop.getProperty("circumCircleRMin"));
            }
            if (prop.stringPropertyNames().contains("circumCircleRMax")) {
                rMax = Double.parseDouble(prop.getProperty("circumCircleRMax"));
            }
            if (prop.stringPropertyNames().contains("centerMaxX")) {
                centerMaxX = Double.parseDouble(prop.getProperty("centerMaxX"));
            }
            if (prop.stringPropertyNames().contains("centerMaxY")) {
                centerMaxY = Double.parseDouble(prop.getProperty("centerMaxY"));
            }
            // minimum ertekeket nem kotelezo megadni, default a maximum -1 -szerese:
            centerMinX = Double.parseDouble(prop.getProperty("centerMinX", ((Double) (-centerMaxX)).toString()));
            centerMinY = Double.parseDouble(prop.getProperty("centerMinY", ((Double) (-centerMaxY)).toString()));
            
        } catch (IOException e) {
            throw new PropertyException("Hiba " + propertyFileName + " betoltesekor.");
        } catch (NumberFormatException e) {
            throw new PropertyException(
                    "Hiba a beolvasando fajlban: " + propertyFileName + " : adatok nem megfelelo formatumban.");
        }
        
        if (centerMinX > centerMaxX || centerMinX > centerMaxX || rMin > rMax) {
            throw new PropertyException("Hiba a sikidom generator beallitasakor:\n"
                    + "centerMinX > centerMaxX || centerMinX > centerMaxX || rMin > rMax");
        }
        
    }
    
    /**
     * {@code [minX, maxX] x [minY, maxY] } 2 dimenzios teglalapon egyenletes
     * eloszlasu pont letrehozasa.
     * 
     * @param minX Teglalap bal oldala.
     * @param minY Teglalap alja.
     * @param maxX Teglalap jobb oldala.
     * @param maxY Teglalap teteje.
     * @return Egyenletes eloszlasu veletlen pont.
     */
    private Point uniformRectangle(double minX, double minY, double maxX, double maxY) {
        double x = minX + random.nextDouble() * (maxX - minX);
        double y = minY + random.nextDouble() * (maxY - minY);
        return new Point(x, y);
    }
    
}
