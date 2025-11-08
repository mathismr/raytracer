package com.raytracer.parser;

import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Camera;
import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.lights.PointLight;
import com.raytracer.core.scene.lights.DirectionalLight;
import com.raytracer.core.scene.shapes.Sphere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SceneFileParser {
    private String filepath;
    public SceneFileParser(String filepath) throws FileNotFoundException {
        this.filepath = filepath;
    }

    private final File scene = new File(filepath);
    Scanner reader = new Scanner(scene);

    public Scene parse() {
        Scene scene = new Scene();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] split_line = line.split(" ");

            Color diffuse = null;
            Color specular = null;
            
            switch (split_line[0]) {
                case "#" -> {continue;}
                case "size" -> {
                    scene.setWidth(Integer.parseInt(split_line[1]));
                    scene.setHeight(Integer.parseInt(split_line[2]));
                }
                case "output" -> scene.setOutput(split_line[1]);
                case "camera" -> {
                    Camera camera = new Camera(
                        Integer.parseInt(split_line[1]),
                        Integer.parseInt(split_line[2]),
                        Integer.parseInt(split_line[3]),
                        Integer.parseInt(split_line[4]),
                        Integer.parseInt(split_line[5]),
                        Integer.parseInt(split_line[6]),
                        Integer.parseInt(split_line[7]),
                        Integer.parseInt(split_line[8]),
                        Integer.parseInt(split_line[9]),
                        Integer.parseInt(split_line[10])
                    );
                    scene.setCamera(camera);
                }
                case "ambient" -> {
                    Color ambient = new Color(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3])
                    );
                    scene.setAmbient(ambient);
                }
                case "diffuse" -> {
                    diffuse = new Color(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3])
                    );
                }
                case "specular" -> {
                    specular = new Color(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3])
                    );
                }
                case "sphere" -> {
                    if (diffuse != null && specular != null) {
                        if (diffuse.validateObjectColor(specular)) {
                            Sphere sphere = new Sphere(
                                diffuse,
                                specular,
                                Double.parseDouble(split_line[1]),
                                Double.parseDouble(split_line[2]),
                                Double.parseDouble(split_line[3]),
                                Double.parseDouble(split_line[4])
                            );
                            scene.addShape(sphere);
                        } else {
                            throw new IllegalArgumentException("Diffuse and Specular colors are not valid");
                        }
                    } else {
                        throw new IllegalArgumentException("Object has no specular color nor diffuse color");
                    }
                }
                //case "triangle" -> {}
                case "directional" -> {
                    DirectionalLight d_light = new DirectionalLight(
                        Integer.parseInt(split_line[1]),
                        Integer.parseInt(split_line[2]),
                        Integer.parseInt(split_line[3]),
                        Integer.parseInt(split_line[4]),
                        Integer.parseInt(split_line[5]),
                        Integer.parseInt(split_line[6])
                    );
                    scene.addLight(d_light);
                }
                case "point" -> {
                    PointLight p_light = new PointLight(
                        Integer.parseInt(split_line[1]),
                        Integer.parseInt(split_line[2]),
                        Integer.parseInt(split_line[3]),
                        Integer.parseInt(split_line[4]),
                        Integer.parseInt(split_line[5]),
                        Integer.parseInt(split_line[6])
                    );
                    scene.addLight(p_light);
                }
                /*
                case "maxverts" -> {}
                case "tri" -> {}
                case "plane" -> {}
                */
                default -> throw new IllegalStateException("Unexpected value: " + split_line[0]);
            }
        }
        return scene;
    }
}