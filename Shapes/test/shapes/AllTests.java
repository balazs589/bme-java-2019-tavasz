package shapes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import shapes.geometry.CircleTest;
import shapes.geometry.PointTest;
import shapes.geometry.QuadraticEquationTest;
import shapes.geometry.RegularPolygonTest;

/**
 * Program osszes tesztjenek futtatasa.
 */
@RunWith(Suite.class)
@SuiteClasses({
    CircleTest.class,
    PointTest.class,
    QuadraticEquationTest.class,
    RegularPolygonTest.class,
    })

public class AllTests {
}

