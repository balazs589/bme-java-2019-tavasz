package shapes.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.geometry.Circle;
import shapes.geometry.Point;
import shapes.geometry.RegularPolygon;
import shapes.geometry.Shape;
import shapes.program.ShapeGenerator;
import shapes.program.ShapeRegistry;

/**
 * Grafikus felulet megvalositasa, csak abrazolashoz, nagyjabol.
 * 
 * Forras:
 * <a href="https://www.youtube.com/channel/UCK_DAaLso6GNsOKyL2funLw/videos">
 * SanFranciscobol Jottem JavaFX videok</a> , StackOverflow, Google talalatok.
 */
public class ShapesController implements Initializable {
    
    /**
     * Tarolo osztalyra mutato referencia.
     */
    private ShapeRegistry shapeRegistry;
    
    @FXML
    private Pane          backgroundPane;
    @FXML
    private Button        clearButton;
    @FXML
    private Button        deserializeButton;
    @FXML
    private Button        drawButton;
    @FXML
    private Button        filterButton;
    @FXML
    private Button        generateButton;
    @FXML
    private Button        loadTxtButton;
    @FXML
    private TextField     numberOfShapesInput;
    @FXML
    private Button        saveTxtButton;
    @FXML
    private Button        serializeButton;
    @FXML
    private TextField     serializeFileNameInput;
    @FXML
    private TextField     textFileNameInput;
    @FXML
    private TextField     xPointInput;
    @FXML
    private TextField     yPointInput;
    
    public ShapesController() {
    }
    
    /**
     * Tarolt sikidomok torlese.
     */
    public void clearShapes(ActionEvent event) {
        shapeRegistry.clear();
        drawShapes(shapeRegistry);
    }
    
    /**
     * Sikidomok beolvasasa fajlba mentett szerializalt adatokbol.
     */
    public void deserialize(ActionEvent event) {
        shapeRegistry.clear();
        shapeRegistry.deserialize(serializeFileNameInput.getText());
        drawShapes(shapeRegistry);
    }
    
    /**
     * Tarolt sikidomok kirajzolasa.
     */
    public void draw(ActionEvent event) {
        drawShapes(shapeRegistry);
    }
    
    /**
     * A megadott pontot tartalmazo sikidomok megjelenitese. A tarolt alakzatok
     * ekozben megmaradnak.
     */
    public void filterShapes(ActionEvent event) {
        ShapeRegistry tmpShapeRegistry = new ShapeRegistry(new ShapeGenerator());
        double        x                = 0.0;
        double        y                = 0.0;
        try {
            x = Double.parseDouble(xPointInput.getText());
            y = Double.parseDouble(yPointInput.getText());
        } catch (NumberFormatException e) {
            return;
        }
        Point p = new Point(x, y);
        for (Shape shape : shapeRegistry.getShapeList()) {
            if (shape.containsPoint(p)) {
                tmpShapeRegistry.getShapeList().add(shape);
            }
        }
        drawShapes(tmpShapeRegistry);
    }
    
    /**
     * Megadott szamu veletlen sikidom letrehozasa es eltarolasa.
     */
    public void generateShapes(ActionEvent event) {
        int numberOfShapes = 0;
        try {
            numberOfShapes = Integer.parseInt(numberOfShapesInput.getText());
            if (numberOfShapes < 1 || numberOfShapes > 1000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            numberOfShapesInput.setText("Irj be 1000-nel kisebb pozitiv egeszt!");
            return;
        }
        shapeRegistry.generateShapes(numberOfShapes);
        drawShapes(shapeRegistry);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    /**
     * Sikidomok beolvasasa szoveges fajlbol.
     */
    public void loadFromTxt(ActionEvent event) {
        shapeRegistry.clear();
        String fileName = textFileNameInput.getText();
        shapeRegistry.loadFromTextFile(fileName);
        drawShapes(shapeRegistry);
    }
    
    /**
     * Sikidomok mentese szoveges fajlba.
     */
    public void saveToTxt(ActionEvent event) {
        drawShapes(shapeRegistry);
        String fileName = textFileNameInput.getText();
        shapeRegistry.saveToTextFile(fileName);
    }
    
    /**
     * Tarolt sikidomok szerializalasa es fajlba mentese.
     */
    public void serialize(ActionEvent event) {
        drawShapes(shapeRegistry);
        shapeRegistry.serialize(serializeFileNameInput.getText());
    }
    
    /**
     * 
     * Grafikus felulet inicializalasa utan legyen elerheto a backend taroloja.
     * 
     * @param registry Sikidomok taroloja.
     */
    public void setShapeRegistry(ShapeRegistry registry) {
        this.shapeRegistry = registry;
    }
    
    /**
     * Parameterkent megkapott taroloban levo sikidomok kirajzolasa.
     * 
     * @param tmpShapeRegistry Sikidomok taroloja.
     */
    private void drawShapes(ShapeRegistry tmpShapeRegistry) {
        double maxX = 6.0;
        double maxY = 4.0; // default
        
        if (shapeRegistry.size() > 0) {
            maxX = Math.max(maxX, Math.max(shapeRegistry.getXMax(), Math.abs(shapeRegistry.getXMin()))) * 1.05;
            maxY = Math.max(maxY, Math.max(shapeRegistry.getYMax(), Math.abs(shapeRegistry.getYMin()))) * 1.05;
        }
        
        int plotWidth  = (int) (backgroundPane.getWidth()) - 40;
        int plotHeight = (int) (backgroundPane.getHeight()) - 40;
        if (maxX < maxY) {
            plotHeight = (int) (maxY / maxX * plotWidth);
        } else if (maxX > maxY) {
            plotWidth = (int) (maxX / maxY * plotHeight);
        }
        
        Axes axes = new Axes(plotWidth, plotHeight, -maxX, maxX, maxX, -maxY, maxY, maxY);
        backgroundPane.setPrefWidth(plotWidth + 40.0);
        backgroundPane.setPrefHeight(plotHeight + 40.0);
        
        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(axes);
        
        axes.setLayoutX(20.0);
        axes.setLayoutY(20.0);
        javafx.scene.shape.Circle baseCircle = new javafx.scene.shape.Circle();
        
        baseCircle.setRadius(1.0 * plotWidth / maxX / 2.0);
        baseCircle.setCenterX(transformX(0.0, plotWidth, maxX));
        baseCircle.setCenterY(transformX(0.0, plotHeight, maxY));
        baseCircle.setFill(Color.rgb(150, 150, 150, 0.3));
        // c.setStrokeWidth(lineWidth);
        axes.getChildren().add(baseCircle);
        
        List<Node> fxShapes = axes.getChildren();
        for (Shape shape : tmpShapeRegistry.getShapeList()) {
            if (shape instanceof Circle) {
                javafx.scene.shape.Circle c = transform((Circle) shape, plotWidth, plotHeight, maxX, maxY);
                fxShapes.add(c);
            } else {
                javafx.scene.shape.Polyline polyline = transform(
                                                                 (RegularPolygon) shape,
                                                                 plotWidth,
                                                                 plotHeight,
                                                                 maxX,
                                                                 maxY);
                fxShapes.add(polyline);
                // fxShapes.get(fxShapes.size()-1).setVisible(false); //...
            }
        }
    }
    
    /**
     * Tarolt korokbol megfelelo meretu JavaFx Circle objektumok letrehozasa.
     */
    private javafx.scene.shape.Circle transform(Circle c, int width, int height, double xMax, double yMax) {
        
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle();
        circle.setCenterX(transformX(c.getCenter().getX(), width, xMax));
        circle.setCenterY(transformY(c.getCenter().getY(), height, yMax));
        circle.setRadius(c.getRadius() * width / xMax / 2.0);
        circle.setFill(Color.rgb(0, 0, 0, 0.0));
        circle.setStroke(Color.RED);
        return circle;
    }
    
    /**
     * Tarolt sokszogekbol megfelelo meretu JavaFx Polyline objektumok letrehozasa.
     */
    private javafx.scene.shape.Polyline transform(
                                                  RegularPolygon polygon,
                                                  int width,
                                                  int height,
                                                  double xMax,
                                                  double yMax) {
        
        javafx.scene.shape.Polyline polyline = new javafx.scene.shape.Polyline();
        ArrayList<Double>           points   = polygon.vertexCoordinates();
        points.add(points.get(0));
        points.add(points.get(1));
        for (int i = 0; i < points.size(); i++) {
            if (i % 2 == 0) {
                points.set(i, transformX(points.get(i), width, xMax));
            } else {
                points.set(i, transformY(points.get(i), height, yMax));
            }
        }
        polyline.getPoints().addAll(points);
        if (polygon.getType().equals("Triangle")) {
            polyline.setStroke(Color.BLUE);
        }
        if (polygon.getType().equals("Square")) {
            polyline.setStroke(Color.GREEN);
        }
        // Pentagon, Hexagon ... => BLACK
        return polyline;
    }
    
    /**
     * Sikidomok merete az ablakhoz igazitva.
     */
    private double transformX(double x, int width, double xMax) {
        return (0.5 * width / xMax) * x + 0.5 * width;
    }
    
    /**
     * Sikidomok merete az ablakhoz igazitva.
     */
    private double transformY(double y, int height, double yMax) {
        return (-0.5 * height / yMax) * y + 0.5 * height;
    }
    
}
