package shapes.program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import java.util.Scanner;

import shapes.exceptions.PointReadException;
import shapes.exceptions.RegularPolygonException;
import shapes.exceptions.ShapeReadException;
import shapes.geometry.Circle;
import shapes.geometry.Point;
import shapes.geometry.RegularPolygon;
import shapes.geometry.Shape;

/**
 * Sikidomok tarolasara szolgalo osztaly.
 * 
 * @see shapes.geometry.Shape
 */
public class ShapeRegistry implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ShapeGenerator    generator;
    private List<Shape>       shapes;
    
    /**
     * Csak tarolasra.
     */
    public ShapeRegistry() {
        shapes         = new ArrayList<Shape>();
        this.generator = null;
    }
    
    /**
     * Tarolasra es veletlen sikidomok letrehozasahoz is hasznalhato tarolo.
     * 
     * @param generator Veletlen sikidomok letrehozasahoz szukseges objektum.
     */
    public ShapeRegistry(ShapeGenerator generator) {
        shapes         = new ArrayList<Shape>();
        this.generator = generator;
    }
    
    /**
     * Pontok beolvasasa fajl vegeig, majd az ezeket tartalmazo sikidomok kiirasa.
     */
    public void autoFilterShapes() {
        printFilteredShapes(readPoints());
    }
    
    /**
     * Osszes tarolt alakzat torlese.
     */
    public void clear() {
        shapes.clear();
    }
    
    /**
     * Korabban szerializalt alakzatlista visszaolvasasa fajlbol.
     * 
     * @param inFileName Fajl neve, amibol beolvassa a metodus az alakzat listat.
     */
    public void deserialize(String inFileName) {
        ObjectInputStream in = null;
        try {
            in     = new ObjectInputStream(new FileInputStream(inFileName));
            shapes = (ArrayList<Shape>) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tarolt sikidomok kozul azok kiirasa, amik tartalmazzak a parameterkent
     * szoveges formatumban megkapott pontot.
     * 
     * @param pointString Az a pont szoveges formatumban, amit tartalmazo sikidomok
     *                    ki lesznek irva.
     */
    public void filterShapes(String pointString) {
        try {
            Point point = new Point(pointString);
            filterShapes(point);
        } catch (PointReadException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Veletlen sikidomok letrehozasa es eltarolasa.
     * 
     * @param numberOfShapes Letrehozando sikidomok szama.
     */
    public void generateShapes(int numberOfShapes) {
        if (generator == null) {
            System.err.println("Hiba: Inicializalatlan alakzat generator.");
        } else {
            shapes = generator.generateShapes(numberOfShapes);
        }
    }
    
    /**
     * Sikidomok listajaval visszatero metodus.
     * 
     * @return Tarolt sikidomok listaja.
     */
    public List<Shape> getShapeList() {
        return shapes;
    }
    
    /**
     * Tarolt sikidomok kozul a legnagyobb x koordinataju ponttal rendelkezo
     * alakzatnak a legnagyobb x kooordinatajat megado metodus.
     * 
     * @return Sikidomok "jobb szelso pontjanak" x koordinataja.
     */
    public double getXMax() {
        double xMax = shapes.get(0).getXMax();
        for (Shape shape : shapes) {
            double x = shape.getXMax();
            if (x > xMax) {
                xMax = x;
            }
        }
        return xMax;
    }
    
    /**
     * Tarolt sikidomok kozul a legkisebb x koordinataju ponttal rendelkezo
     * alakzatnak a legkisebb x kooordinatajat megado metodus.
     * 
     * @return Sikidomok "bal szelso pontjanak" x koordinataja.
     */
    public double getXMin() {
        double xMin = shapes.get(0).getXMin();
        for (Shape shape : shapes) {
            double x = shape.getXMin();
            if (x < xMin) {
                xMin = x;
            }
        }
        return xMin;
    }
    
    /**
     * Tarolt sikidomok kozul a legnagyobb y koordinataju ponttal rendelkezo
     * alakzatnak a legnagyobb y kooordinatajat megado metodus.
     * 
     * @return Sikidomok "legfelso pontjanak" y koordinataja.
     */
    public double getYMax() {
        double yMax = shapes.get(0).getYMax();
        for (Shape shape : shapes) {
            double y = shape.getYMax();
            if (y > yMax) {
                yMax = y;
            }
        }
        return yMax;
    }
    
    /**
     * Tarolt sikidomok kozul a legkisebb y koordinataju ponttal rendelkezo
     * alakzatnak a legkisebb y kooordinatajat megado metodus.
     * 
     * @return Sikidomok "legalso pontjanak" y koordinataja.
     */
    public double getYMin() {
        double yMin = shapes.get(0).getYMin();
        for (Shape shape : shapes) {
            double y = shape.getYMin();
            if (y < yMin) {
                yMin = y;
            }
        }
        return yMin;
    }
    
    /**
     * Sikidomok beolvasasa szoveges fajlbol es ezek eltarolasa.
     * 
     * @param fileName Fajl neve, ami a beolvasando alakzatokat tartalmazza.
     */
    public void loadFromTextFile(String fileName) {
        
        Circle      baseCircle = new Circle(new Point(), new Point(0.0, 1.0));
        
        InputStream is         = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Hiba " + fileName + " megnyitasakor.");
            e.printStackTrace();
            return;
        }
        
        shapes.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            int lineNumber = 0;
            while (true) {
                String line = br.readLine();
                
                if (line == null) {
                    break;
                }
                lineNumber++;
                try {
                    String[] tokens = line.split("\t");
                    if (tokens.length != 3) {
                        throw new ShapeReadException("");
                    }
                    String type = tokens[0];
                    
                    Shape  shape;
                    if (type.equals("Circle")) {
                        Point center      = new Point(tokens[1]);
                        Point firstVertex = new Point(tokens[2]);
                        shape = new Circle(center, firstVertex);
                    } else {
                        Point center      = new Point(tokens[1]);
                        Point firstVertex = new Point(tokens[2]);
                        shape = new RegularPolygon(center, firstVertex, type);
                    }
                    if (!shape.hasIntersectionWithCircle(baseCircle)) {
                        shapes.add(shape);
                    }
                } catch (PointReadException | RegularPolygonException e) {
                    System.err.println("Hiba az input " + lineNumber + ". soraban:\n");
                    System.err.println(e.getMessage());
                } catch (ShapeReadException e) {
                    System.err.println("Hiba az input " + lineNumber + ". soraban:\n");
                    System.err.println(Shape.formatErrorMessage());
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Hiba a txt input beolvasasakor.");
            e.printStackTrace();
        }
    }
    
    /**
     * Tarolt sikidomok kiirasa.
     */
    public void printShapes() {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
        System.out.println("Tarolt sikidomok szama: " + shapes.size());
    }
    
    /**
     * Sikidomok beolvasasa szoveges fajlbol es ezek eltarolasa.
     * 
     * @param fileName A beolvasando szoveges fajl neve.
     */
    public void saveToTextFile(String fileName) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
        } catch (FileNotFoundException e1) {
            System.err.println("Hiba " + fileName + " outputkent valo megnyitasakor.");
            e1.printStackTrace();
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
            for (Shape shape : shapes) {
                writer.write(shape.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Hiba a txt output kiirasakor.");
            e.printStackTrace();
        }
    }
    
    /**
     * Tarolt sikidomok listajanak szerializalasa es fajlba irasa.
     * 
     * @param outFileName Szerializalando adatok mentesi helye.
     */
    public void serialize(String outFileName) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(outFileName));
            out.writeObject(shapes);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tarolt sikidomok szama.
     * 
     * @return Sikidomok szama.
     */
    public int size() {
        return shapes.size();
    }
    
    /**
     * Tarolt sikidomok kozul azok kiirasa, amik tartalmazzak a parameterkent
     * megkapott pontot.
     * 
     * @param point Az a pont, amit tartalmazo sikidomok ki lesznek irva.
     * @return Kiirt sikidomok szama.
     */
    private int filterShapes(Point point) {
        int numberOfFilteredShapes = 0;
        for (Shape shape : shapes) {
            if (shape.containsPoint(point)) {
                System.out.println(shape);
                numberOfFilteredShapes++;
            }
        }
        return numberOfFilteredShapes;
    }
    
    /**
     * A parameterkent megkapott listaban levo pontokat tartalmazo tarolt sikidomok
     * kiirasa vagy annak jelzese, hogy az adott pontot nem tartalmazza egy sikidom
     * sem.
     * 
     * @param points Pontok listaja.
     */
    private void printFilteredShapes(List<Point> points) {
        System.out.println("A beolvasott pontok:");
        for (Point point : points) {
            System.out.println("\n" + point);
            System.out.println("A pontot tartalmazo sikidomok:");
            int numberOfFilteredShapes = filterShapes(point);
            if (numberOfFilteredShapes == 0) {
                System.out.println("Nincs ilyen sikidom.");
            }
        }
    }
    
    /**
     * Pontok beolvasasa fajl vegeig es visszateres ezek listajaval.
     * 
     * @return Eltarolt pontok listaja.
     */
    private List<Point> readPoints() {
        List<Point> points         = new ArrayList<Point>();
        Scanner     input          = new Scanner(System.in);
        int         numberOfPoints = 0;
        while (input.hasNextLine()) {
            String s = input.nextLine();
            if (s.equals("")) {
                continue;
            }
            try {
                points.add(new Point(s));
                numberOfPoints++;
            } catch (PointReadException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("" + numberOfPoints + " darab sikeres beolvasas.\n");
        input.close();
        return points;
    }
    
}
