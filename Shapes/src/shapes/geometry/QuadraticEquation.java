package shapes.geometry;

import java.util.ArrayList;

/**
 * \( a x^2 + b x + c = 0 \) Masodfoku egyenletet megoldo osztaly.
 */
public class QuadraticEquation {
    
    private double            a;
    private double            b;
    private double            c;
    private ArrayList<Double> roots;
    
    /**
     * \( a x^2 + b x + c = 0 \) egyenlet letrehozasa valos parameterekkel, majd az
     * egyenlet valos gyokeinek meghatarozasa is.
     * 
     * @param a Egyenlet elso egyutthatoja.
     * @param b Egyenlet masodik egyutthatoja.
     * @param c Egyenlet harmadik egyutthatoja.
     */
    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        roots  = new ArrayList<Double>();
        solve();
    }
    
    /**
     * Az egyenlet valos gyokei amennyiben leteznek. \[ x_1 = \frac{ -b - \sqrt{b ^
     * 2 - 4 a c}}{2 a} \] \[ x_2 = \frac{ -b + \sqrt{b ^ 2 - 4 a c}}{2 a} \]
     * 
     * @return Az egyenlet valos gyokeit tartalmazo lista (2, 1 vagy 0 darab valos
     *         ertekkel).
     */
    public ArrayList<Double> getRoots() {
        return roots;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Polinom alakban x valtozoval. Pl.:
     * 
     * <pre>
     * 1.0*x^2 +5.0*x +6.0 = 0.0
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("%f*x^2 %+f*x %+f = 0.0", a, b, c);
    }
    
    /**
     * Az egyenlet valos gyokeit eloallito metodus.
     */
    private void solve() {
        double D = b * b - 4 * a * c;
        
        if (D < 0) {
            
        } else if (D == 0) {
            roots.add((-b / (2 * a)));
        } else {
            roots.add((-b - Math.sqrt(D)) / (2 * a));
            roots.add((-b + Math.sqrt(D)) / (2 * a));
        }
    }
    
}
