package shapes.geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import shapes.geometry.Circle;
import shapes.geometry.Point;
import shapes.geometry.RegularPolygon;

/**
 * {@link shapes.geometry.RegularPolygon} osztaly tesztjei.
 */
public class RegularPolygonTest {
    
    RegularPolygon square;
    RegularPolygon triangle;
    
    @Before
    public void setUp() throws Exception {
        square   = new RegularPolygon(new Point(0.0, 0.0), new Point(1.0, 0.0), "Square");
        triangle = new RegularPolygon(new Point(0.0, 0.0), new Point(1.0, 0.0), "Triangle");
    }
    
    /**
     * {@link shapes.geometry.RegularPolygon#containsPoint(Point)} metodus tesztjei.
     */
    @Test
    public void containsPointTest() {
        assertTrue(square.containsPoint(new Point(0.5, 0.0)));
        assertFalse(square.containsPoint(new Point(1.0, 1.0)));
        
        assertTrue(triangle.containsPoint(new Point(0.5, 0.0)));
        assertFalse(triangle.containsPoint(new Point(1.0, 1.0)));
    }
    
    /**
     * {@link shapes.geometry.RegularPolygon#hasIntersectionWithCircle(Circle)} metodus tesztjei.
     */
    @Test
    public void hasIntersectionWithCircleTest() {
        assertTrue(square.hasIntersectionWithCircle(new Circle(new Point(1.0, 0.0), new Point(0.5, 0.0))));
        assertFalse(square.hasIntersectionWithCircle(new Circle(new Point(3.0, 0.0), new Point(2.5, 0.0))));
        
        assertTrue(triangle.hasIntersectionWithCircle(new Circle(new Point(1.0, 0.0), new Point(0.5, 0.0))));
        assertFalse(triangle.hasIntersectionWithCircle(new Circle(new Point(3.0, 0.0), new Point(2.5, 0.0))));
    }
    
}
