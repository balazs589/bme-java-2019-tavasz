package shapes.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import shapes.geometry.Circle;
import shapes.geometry.LineSegment;
import shapes.geometry.Point;

/**
 * {@link shapes.geometry.Circle} osztaly tesztjei.
 */
public class CircleTest {
    
    Circle circle;
    
    @Before
    public void setUp() throws Exception {
        circle = new Circle(new Point(0.0, 0.0), new Point(1.0, 0.0));
    }
    
    /**
     * {@link shapes.geometry.Circle#getRadius()} metodus tesztjei.
     */
    @Test
    public void getRadiusTest() {
        assertEquals(1.0, circle.getRadius(), 1e-8);
    }
    
    /**
     * {@link shapes.geometry.Circle#hasIntersectionWithLineSegment(LineSegment)} metodus tesztjei.
     */
    @Test
    public void hasIntersectionWithLineSegmentTest() {
        assertTrue(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, 0.0), new Point(-2.0, 0.0))));
        assertFalse(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, 2.0), new Point(-2.0, 2.0))));
        
        assertTrue(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, -1.0), new Point(-1.0, 2.0))));
        assertFalse(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, 0.0), new Point(0.0, 2.0))));
        
        assertTrue(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, 2.0), new Point(0.5, 0.5))));
        assertFalse(circle.hasIntersectionWithLineSegment(new LineSegment(new Point(2.0, 2.0), new Point(1.0, 1.0))));
    }
    
}
