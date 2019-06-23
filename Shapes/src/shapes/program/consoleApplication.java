package shapes.program;

import java.util.Scanner;

import shapes.exceptions.PropertyException;

/**
 * Sikidomokkal muveleteket vegzo parancssori program osztalya.
 */
public class consoleApplication {
    
    private ShapeGenerator generator;
    private ShapeRegistry  registry;
    
    /**
     * Letrehozaskor szukseg van egy sikidom-tarolora, ami kepes sikidomokat is
     * generalni.
     * 
     * @throws PropertyException Amennyiben a sikidom generatort nem sikerul
     *                           megfeleloen letrehozni.
     */
    public consoleApplication() throws PropertyException {
        generator = new ShapeGenerator("./input-output/randomGeneratorProperties.xml");
        registry  = new ShapeRegistry(generator);
    }
    
    /**
     * Szoveges fajlbol sikidomokat beolvaso majd azokat szuro metodus.
     * 
     * @param fileName Sikidomokat tartalmazo fajl neve.
     */
    public void auto(String fileName) {
        registry.loadFromTextFile(fileName);
        registry.printShapes();
        registry.autoFilterShapes();
    }
    
    /**
     * Konzolos parancsertelmezo menu futtatasa.
     */
    public void program() {
        ConsoleMenu menu = new ConsoleMenu(registry, new Scanner(System.in));
        menu.run();
    }
    
}
