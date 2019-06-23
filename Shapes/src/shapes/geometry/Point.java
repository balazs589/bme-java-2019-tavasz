package shapes.geometry;

import java.io.Serializable;
import java.text.DecimalFormatSymbols;

import shapes.exceptions.PointReadException;

/**
 * 2 dimenzios pontot leiro osztaly.
 */
public class Point implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private double            x;
    private double            y;
    
    /**
     * Default konstruktor: origo
     */
    public Point() {
        x = 0.0;
        y = 0.0;
    }
    
    /**
     * Ketparameters konstruktor: 2 dimenzios Descartes koordinatakkal.
     * 
     * @param x 2 dimenzios pont x koordinataja.
     * @param y 2 dimenzios pont y koordinataja.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Szoveges formatumbol valo beolvasashoz (inverz toString).
     * 
     * @param s Pont objektum szoveges formatumban, ahogyan toString eloallitja.
     * @throws PointReadException Nem megfelelo formatum eseten.
     */
    public Point(String s) throws PointReadException {
        String[] tokens = pointFormatFilter(s).split(" ");
        try {
            this.x = Double.parseDouble(tokens[0]);
            this.y = Double.parseDouble(tokens[1]);
        } catch (NumberFormatException e) {
            throw new PointReadException(formatErrorMessage());
        }
    }
    
    /**
     * 2 dimennzios vektorok vektorialis szorzata. Csak 2 dimenzios helyvektorok
     * altal bezart szog szinuszanak elojelehez.
     * 
     * @param b Helyvektorkent ertelmezett pont, amivel jobbrol vektorialisan
     *          szorzunk.
     * @return A 3 dimenzios vektorialis szorzat eredmenyvektoranak harmadik
     *         komponense.
     */
    public double crossProduct(Point b) {
        Point a = this;
        return a.x * b.y - a.y * b.x;
    }
    
    /**
     * Tavolsag egy megadott ponttol.
     * 
     * @param p A masik pont amitol valo tavolsagot meghatarozzuk.
     * @return 2D euklideszi tavolsag.
     */
    public double distance(Point p) {
        return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
    }
    
    /**
     * Elso koordinata.
     * 
     * @return x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Masodik koordinata.
     * 
     * @return y
     */
    public double getY() {
        return y;
    }
    
    /**
     * A pontot az origora kozeppontosan tukrozo muvelet.
     * 
     * @return A tukrozes utan kapott pont.
     */
    public Point minusOperator() {
        return new Point(-this.x, -this.y);
    }
    
    /**
     * Pont forgatasa origo korul.
     * 
     * @param alfa Szog amivel pozitiv iranyba forgatjuk a pontot mint helyvektor
     *             vegpontjat.
     * @return A forgatas vegeredmenyekent kapott pont.
     */
    public Point rotateAboutOrigin(double alfa) {
        double x1 = +Math.cos(alfa) * x - Math.sin(alfa) * y;
        double y1 = +Math.sin(alfa) * x + Math.cos(alfa) * y;
        return new Point(x1, y1);
    }
    
    /**
     * {@inheritDoc}
     * 
     * A koordinatak zarojelek kozott egy darab szokozzel elvalasztva. Pl.:
     * 
     * <pre>
     * (1.23 4.56)
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("(%+.4f %+.4f)", x, y);
    }
    
    /**
     * Pont eltolasa egy helyvektorral
     * 
     * @param p Pont minat mint helyvektor vegpontja.
     * @return Az eltolas utan kapott pont.
     */
    public Point translation(Point p) {
        return new Point(this.x + p.x, this.y + p.y);
    }
    
    /**
     * Beolvasas soran nem megfelelo formatum eseten, helyes formatum leirasa.
     * 
     * @return Hibauzenet szovege.
     */
    private String formatErrorMessage() {
        DecimalFormatSymbols sym         = new DecimalFormatSymbols();
        char                 charDecimal = sym.getDecimalSeparator();
        return "Nem megfelelo formatum pont letrehozasahoz." + " Helyes formatum pl.: (1" + charDecimal + "2 4"
                + charDecimal + "7)";
    }
    
    /**
     * Pont objektum szoveges formatumanak ellenorzesehez: eloszuro metodus.
     * 
     * @param s Pont objektum szoveges formatumban
     * @return Koordinatakat 1db szokozzel elvalasztva tartalmazo szoveg.
     * @throws PointReadException Hibas formatum eseten.
     */
    private String pointFormatFilter(String s) throws PointReadException {
        if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
            throw new PointReadException(formatErrorMessage());
        }
        int spaceCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        if (spaceCount != 1) {
            throw new PointReadException(formatErrorMessage());
        }
        return s.substring(1, s.length() - 1);
    }
    
}
