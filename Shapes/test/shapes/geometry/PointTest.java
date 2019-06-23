package shapes.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shapes.geometry.Point;

/**
 * {@link shapes.geometry.Point} osztaly tesztjei.
 */
public class PointTest {
    
    Point p1;
    Point p2;
    
    @Before
    public void setUp() throws Exception {
        p1 = new Point(3.0, 4.0);
        p2 = new Point(-4.0, 3.0);
    }
    
    /**
     * {@link shapes.geometry.Point#crossProduct(Point)} metodus tesztjei.
     */
    @Test
    public void crossProductTest() {
        assertEquals(25.0, p1.crossProduct(p2), 1e-8);
    }
    
    /**
     * {@link shapes.geometry.Point#distance(Point)} metodus tesztjei.
     */
    @Test
    public void distanceTest() {
        assertEquals(5.0 * Math.sqrt(2.0), p1.distance(p2), 1e-8);
    }
    
    /**
     * {@link shapes.geometry.Point#getX()} metodus tesztjei.
     */
    @Test
    public void getXTest() {
        assertEquals(3.0, p1.getX(), 1e-8);
        assertEquals(-4.0, p2.getX(), 1e-8);
    }
    
    /**
     * {@link shapes.geometry.Point#getY()} metodus tesztjei.
     */
    @Test
    public void getYTest() {
        assertEquals(4.0, p1.getY(), 1e-8);
        assertEquals(3.0, p2.getY(), 1e-8);
    }
    
    /**
     * {@link shapes.geometry.Point#minusOperator()} metodus tesztjei.
     */
    @Test
    public void minusOperatorTest() {
        assertEquals(-3.0, p1.minusOperator().getX(), 1e-8);
        assertEquals(-4.0, p1.minusOperator().getY(), 1e-8);
        
        assertEquals(4.0, p2.minusOperator().getX(), 1e-8);
        assertEquals(-3.0, p2.minusOperator().getY(), 1e-8);
    }
    
}
