package com.raytracer.core.scene;
//
import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.lights.*;
import com.raytracer.core.scene.shapes.*;
import com.raytracer.core.geometry.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneFileParser {
    private final Scene scene;
    private Color currentDiffuse;
    private Color currentSpecular;
    private int currentShininess;
    private ArrayList<Point> maxverts;

    public SceneFileParser() {
        this.scene = new Scene();
        this.currentDiffuse = new Color();
        this.currentSpecular = new Color();
        this.currentShininess = 0;
        scene.setMaxdepth(0);
    }

    public Scene getScene() {
        return scene;
    }

    public Color getCurrentDiffuse() {
        return currentDiffuse;
    }

    public Color getCurrentSpecular() {
        return currentSpecular;
    }

    public double getCurrentShininess() {
        return currentShininess;
    }

    public List<Point> getVertices() {
        return maxverts;
    }

    public Scene parse(String filePath) {
        try (FileReader file = new FileReader(filePath)){
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine())!= null){
                line.trim();
                if (line.isEmpty() || line.startsWith("#")){
                    continue;
                }
                String[] parts = line.split("\\s+");
                switch (parts[0].toLowerCase()) {
                    case "size":
                        parseSize(parts);
                        break;
                    case "output":
                        parseOutput(parts);
                        break;
                    case "camera":
                        parseCamera(parts);
                        break;
                    case "ambient":
                        parseAmbient(parts);
                        break;
                    case "directional":
                        parseDirectionalLight(parts);
                        break;
                    case "point":
                        parsePointLight(parts);
                        break;
                    case "diffuse":
                        parseDiffuse(parts);
                        break;
                    case "specular":
                        parseSpecular(parts);
                        break;
                    case "shininess":
                        parseShininess(parts);
                        break;
                    case "maxverts":
                        parseMaxverts(parts);
                        break;
                    case "maxdepth":
                        parseMaxDepth(parts);
                        break;
                    case "vertex":
                        parseVertex(parts);
                        break;
                    case "tri":
                        parseTriangle(parts);
                        break;
                    case "sphere":
                        parseShpere(parts);
                        break;
                    case "plane":
                        parsePlane(parts);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown line in scene file: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    private void parseMaxDepth(String[] line) {
        if (line.length < 2) {
            throw new IllegalArgumentException("Invalid format for 'maxdepth'. Expected: maxdepth <number>");
        }

        scene.setMaxdepth(Integer.parseInt(line[1]));
    }

    private void parseSize(String[] line){
        if (line.length < 3) {
            throw new IllegalArgumentException("Invalid format for 'size'. Expected: size <width> <height>");
        }
        int width = Integer.parseInt(line[1]);
        int height = Integer.parseInt(line[2]);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive integers.");
        }
        scene.setWidth(width);
        scene.setHeight(height);
    }

    private void parseOutput(String[] line){
        if (line.length < 2) {
            throw new IllegalArgumentException("Invalid format for 'output'. Expected: output <output.png>");
        }
        String output = line[1];
        scene.setOutput(output);
    }

    private void parseCamera(String[] line){
        if (line.length < 11) {
            throw new IllegalArgumentException("Invalid format for 'camera'. Expected: camera <x> <y> <z> <u> <v> <w> <m> <n> <o> <fov>");
        }
        double lookFromX = Double.parseDouble(line[1]);
        double lookFromY = Double.parseDouble(line[2]);
        double lookFromZ = Double.parseDouble(line[3]);

        double lookAtU = Double.parseDouble(line[4]);
        double lookAtV = Double.parseDouble(line[5]);
        double lookAtW = Double.parseDouble(line[6]);

        double upM = Double.parseDouble(line[7]);
        double upN = Double.parseDouble(line[8]);
        double upO = Double.parseDouble(line[9]);

        double fov = Double.parseDouble(line[10]);
        if (fov <= 0 || fov >= 180) {
            throw new IllegalArgumentException("Field of view (fov) must be between 0 and 180 degrees.");
        }
        Camera camera = new Camera(
            lookFromX, lookFromY, lookFromZ,
            lookAtU,lookAtV, lookAtW,
            upM, upN, upO,
            fov);

        scene.setCamera(camera);
    }

    private void parseAmbient(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Invalid format for 'ambient'. Expected: ambient <r> <g> <b>");
        }
        double ambientR = Double.parseDouble(line[1]);
        double ambientG = Double.parseDouble(line[2]);
        double ambientB = Double.parseDouble(line[3]);
        if (ambientR < 0 || ambientR > 1 || ambientG < 0 || ambientG > 1 || ambientB < 0 || ambientB > 1) {
            throw new IllegalArgumentException("RGB components of ambient color must be between 0 and 1.");
        }
        Color ambient = new Color(ambientR, ambientG, ambientB);
        scene.setAmbient(ambient);
    }

    private void parseDirectionalLight(String[] line){
        if (line.length < 7) {
            throw new IllegalArgumentException("Invalid format for 'directional'. Expected: directional <x> <y> <z> <r> <g> <b>");
        }
        double directionX = Double.parseDouble(line[1]);
        double directionY = Double.parseDouble(line[2]);
        double directionZ = Double.parseDouble(line[3]);
        Vector direction = new Vector(directionX, directionY, directionZ);

        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        if (colorR < 0 || colorR > 1 || colorG < 0 || colorG > 1 || colorB < 0 || colorB > 1) {
            throw new IllegalArgumentException("RGB components of directional light must be between 0 and 1.");
        }
        Color color = new Color(colorR, colorG, colorB);
        DirectionalLight directionalLight =  new DirectionalLight(color, direction);

        scene.addLight(directionalLight);
    }

    private void parsePointLight(String[] line){
        if (line.length < 7) {
            throw new IllegalArgumentException("Invalid format for 'point'. Expected: point <x> <y> <z> <r> <g> <b>");
        }
        double originX = Double.parseDouble(line[1]);
        double originY = Double.parseDouble(line[2]);
        double originZ = Double.parseDouble(line[3]);
        Point origin = new Point(originX, originY, originZ);

        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        if (colorR < 0 || colorR > 1 || colorG < 0 || colorG > 1 || colorB < 0 || colorB > 1) {
            throw new IllegalArgumentException("RGB components of point light must be between 0 and 1.");
        }
        Color color = new Color(colorR, colorG, colorB);
        PointLight pointLight =  new PointLight(color, origin);

        scene.addLight(pointLight);
    }

    private void parseDiffuse(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Invalid format for 'diffuse'. Expected: diffuse <r> <g> <b>");
        }
        double diffuseR = Double.parseDouble(line[1]);
        double diffuseG = Double.parseDouble(line[2]);
        double diffuseB = Double.parseDouble(line[3]);
        if (diffuseR < 0 || diffuseR > 1 || diffuseG < 0 || diffuseG > 1 || diffuseB < 0 || diffuseB > 1) {
            throw new IllegalArgumentException("RGB components of diffuse color must be between 0 and 1.");
        }

        this.currentDiffuse = new Color(diffuseR, diffuseG, diffuseB);;
    }

    private void parseSpecular(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Invalid format for 'specular'. Expected: specular <r> <g> <b>");
        }
        double specularR = Double.parseDouble(line[1]);
        double specularG = Double.parseDouble(line[2]);
        double specularB = Double.parseDouble(line[3]);
        if (specularR < 0 || specularR > 1 || specularG < 0 || specularG > 1 || specularB < 0 || specularB > 1) {
            throw new IllegalArgumentException("RGB components of specular color must be between 0 and 1.");
        }

        this.currentSpecular = new Color(specularR, specularG, specularB);
    }

    private void parseShininess(String[] line){
        if (line.length < 2) {
            throw new IllegalArgumentException("Invalid format for 'shininess'. Expected: shininess <value>");
        }
        int shininess = Integer.parseInt(line[1]);
        if (shininess == 0) {
            throw new IllegalArgumentException("Shininess == 0.");
        }

        this.currentShininess = shininess;
    }

    private void parseMaxverts(String[] line){
        if (line.length < 2) {
            throw new IllegalArgumentException("Invalid format for 'maxverts'. Expected: maxverts <number>");
        }
        this.maxverts = new ArrayList<>(Integer.parseInt(line[1]));
    }

    private void parseVertex(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Invalid format for 'vertex'. Expected: vertex <x> <y> <z>");
        }
        double vertexX = Double.parseDouble(line[1]);
        double vertexY = Double.parseDouble(line[2]);
        double vertexZ = Double.parseDouble(line[3]);
        Point vertex = new Point(vertexX, vertexY, vertexZ);
        this.maxverts.add(vertex);
    }

    private void parseTriangle(String[] parts) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid format for 'tri'. Expected: tri <a> <b> <c>");
        }
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);
        Triangle triangle = new Triangle(currentDiffuse, currentSpecular, currentShininess, maxverts.get(a), maxverts.get(b), maxverts.get(c));

        scene.addShape(triangle);
    }

    private void parsePlane(String[] line){
        if (line.length < 7) {
            throw new IllegalArgumentException("Invalid format for 'plane'. Expected: plane <x> <y> <z> <u> <v> <w>");
        }
        int x = Integer.parseInt(line[1]);
        int y = Integer.parseInt(line[2]);
        int z = Integer.parseInt(line[3]);
        Point point = new Point(x, y, z);

        int u = Integer.parseInt(line[4]);
        int v = Integer.parseInt(line[5]);
        int w = Integer.parseInt(line[6]);
        Vector normal = new Vector(u, v, w);
        Plane plane = new Plane(currentDiffuse, currentSpecular, currentShininess, point, normal);

        scene.addShape(plane);
    }

    private void parseShpere(String[] line){
        if (line.length < 5) {
            throw new IllegalArgumentException("Invalid format for 'sphere'. Expected: sphere <x> <y> <z> <radius>");
        }
        double centerX = Double.parseDouble(line[1]);
        double centerY = Double.parseDouble(line[2]);
        double centerZ = Double.parseDouble(line[3]);
        Point center = new Point(centerX, centerY, centerZ);

        double radius = Double.parseDouble(line[4]);
        if (radius <= 0) {
            throw new IllegalArgumentException("Sphere radius must be positive.");
        }
        Sphere sphere = new Sphere(currentDiffuse, currentSpecular, currentShininess,  center, radius);

        scene.addShape(sphere);
    }
}