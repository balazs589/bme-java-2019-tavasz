package shapes.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import shapes.geometry.QuadraticEquation;

/**
 * {@link shapes.geometry.QuadraticEquation} osztaly tesztjei.
 */
public class QuadraticEquationTest {
    
    /**
     * {@link shapes.geometry.QuadraticEquation#getRoots()} metodus tesztjei.
     */
    @Test
    public void getRootsTest() {
        QuadraticEquation e0 = new QuadraticEquation(1.0, 0.0, 1.0);
        assertEquals(0, e0.getRoots().size());
        
        QuadraticEquation e1 = new QuadraticEquation(1.0, 2.0, 1.0);
        assertEquals(1, e1.getRoots().size());
        double[] expecteds1 = { -1.0 };
        Double[] actuals1   = e1.getRoots().toArray(new Double[2]);
        assertEquals(expecteds1[0], (double) actuals1[0], 1e-8);
        
        QuadraticEquation e2 = new QuadraticEquation(1.0, 5.0, 6.0);
        assertEquals(2, e2.getRoots().size());
        double[] expecteds2 = { -3.0, -2.0 };
        Double[] actuals2   = e2.getRoots().toArray(new Double[2]);
        assertEquals(expecteds2[0], (double) actuals2[0], 1e-8);
        assertEquals(expecteds2[1], (double) actuals2[1], 1e-8);
    }
    
}
