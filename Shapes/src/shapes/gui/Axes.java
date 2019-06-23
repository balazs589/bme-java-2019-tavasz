package shapes.gui;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

/**
 * Ketdimenzios koordinatarendszer tengelyeit megvalosito osztaly,
 * forras: <a href="https://stackoverflow.com/a/24008426"> https://stackoverflow.com/a/24008426 </a>.
 * <br><br>
 * 
 * Csak abrazolashoz.
 */
public class Axes extends Pane {
    
    private NumberAxis xAxis;
    
    private NumberAxis yAxis;
    
    public Axes() {
    }
    
    public Axes(int width, int height, double xLow, double xHi, double xTickUnit, double yLow, double yHi,
            double yTickUnit) {
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        
        xAxis = new NumberAxis(xLow, xHi, xTickUnit);
        xAxis.setSide(Side.BOTTOM);
        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        xAxis.setLayoutY(height / 2);
        
        yAxis = new NumberAxis(yLow, yHi, yTickUnit);
        yAxis.setSide(Side.LEFT);
        yAxis.setMinorTickVisible(false);
        yAxis.setPrefHeight(height);
        yAxis.layoutXProperty().bind(Bindings.subtract((width / 2) + 1, yAxis.widthProperty()));
        getChildren().setAll(xAxis, yAxis);
    }
    
    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }
    
    public NumberAxis getXAxis() {
        return xAxis;
    }
    
    public NumberAxis getYAxis() {
        return yAxis;
    }
    
}
