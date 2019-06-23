package shapes.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shapes.exceptions.PropertyException;
import shapes.program.ShapeGenerator;
import shapes.program.ShapeRegistry;

/**
 * Grafikus feluletet indito osztaly.
 * 
 */
public class GUI extends Application {
    
    public static void program() {
        System.out.println("Shape registry GUI program");
        launch();
        System.out.println("Kilepes.");
    }
    
    private ShapeGenerator generator;
    private ShapeRegistry  registry;
    
    public GUI() throws PropertyException {
        generator = new ShapeGenerator("./input-output/randomGeneratorProperties.xml");
        registry  = new ShapeRegistry(generator);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/shapes/gui/ShapesGUI.fxml"));
        Parent     root       = (Parent) fxmlLoader.load();
        primaryStage.setMinWidth(root.minWidth(-1));
        primaryStage.setMinHeight(root.minHeight(-1));
        
        ShapesController controller = fxmlLoader.<ShapesController>getController();
        controller.setShapeRegistry(registry);
        primaryStage.setTitle("Sikidomok");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
}
