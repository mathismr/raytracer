package com.raytracer.core.scene;

import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.lights.*;
import com.raytracer.core.scene.shapes.*;
import com.raytracer.core.geometry.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SceneFileParser {
    private final Scanner reader;

    public SceneFileParser(String filepath) throws FileNotFoundException {
        File scene_file = new File(filepath);
        this.reader = new Scanner(scene_file);
    }

    public Scene parse() {
        Scene scene = new Scene();
        Color diffuse = null;
        Color specular = null;
        List<Point> verts = null;

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] split_line = line.split(" ");

            switch (split_line[0]) {
                case "#", "" -> {continue;}
                case "size" -> {
                    scene.setWidth(Integer.parseInt(split_line[1]));
                    scene.setHeight(Integer.parseInt(split_line[2]));
                }
                case "output" -> scene.setOutput(split_line[1]);
                case "camera" -> {
                    scene.setCamera(new Camera(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3]),
                        Double.parseDouble(split_line[4]),
                        Double.parseDouble(split_line[5]),
                        Double.parseDouble(split_line[6]),
                        Double.parseDouble(split_line[7]),
                        Double.parseDouble(split_line[8]),
                        Double.parseDouble(split_line[9]),
                        Double.parseDouble(split_line[10])
                    ));
                }
                case "ambient" -> {
                    scene.setAmbient(new Color(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3])
                    ));
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
                    if (isLightVerified(diffuse, specular)) {
                        scene.addShape(new Sphere(
                            diffuse,
                            specular,
                            new Point(
                                Double.parseDouble(split_line[1]),
                                Double.parseDouble(split_line[2]),
                                Double.parseDouble(split_line[3])
                            ),
                            Double.parseDouble(split_line[4])
                        ));
                    } else {
                        throw new IllegalArgumentException("Sphere has no specular color nor diffuse color");
                    }
                }
                case "directional" -> {
                    scene.addLight(new DirectionalLight(
                        new Vector(
                            Double.parseDouble(split_line[1]),
                            Double.parseDouble(split_line[2]),
                            Double.parseDouble(split_line[3])
                        ),
                        new Color(
                            Double.parseDouble(split_line[4]),
                            Double.parseDouble(split_line[5]),
                            Double.parseDouble(split_line[6])
                        )
                    ));
                }
                case "point" -> {
                    scene.addLight(new PointLight(
                        new Vector(
                            Double.parseDouble(split_line[1]),
                            Double.parseDouble(split_line[2]),
                            Double.parseDouble(split_line[3])
                        ),
                        new Color(
                            Double.parseDouble(split_line[4]),
                            Double.parseDouble(split_line[5]),
                            Double.parseDouble(split_line[6])
                        )
                    ));
                }
                case "maxverts" -> {
                    verts = new ArrayList<Point>(Integer.parseInt(split_line[1]));
                }
                case "vertex" -> {
                    try {verts.add(new Point(
                        Double.parseDouble(split_line[1]),
                        Double.parseDouble(split_line[2]),
                        Double.parseDouble(split_line[3])
                    ));}
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "tri" -> {
                    if (verts != null ) {
                        int idx1 = Integer.parseInt(split_line[1]);
                        int idx2 = Integer.parseInt(split_line[2]);
                        int idx3 = Integer.parseInt(split_line[3]);

                        if (idx1 <= verts.size()
                         && idx2 <= verts.size()
                         && idx3 <= verts.size()) {

                            if (isLightVerified(diffuse, specular)) {
                                scene.addShape(new Triangle(
                                    diffuse,
                                    specular,
                                    verts.get(idx1),
                                    verts.get(idx2),
                                    verts.get(idx3)));
                            } else {
                                throw new IllegalArgumentException("tri has no specular color nor diffuse color");
                            }
                        } else {
                            throw new IllegalArgumentException("tri has invalid indices");
                        }
                    } else {
                        throw new IllegalArgumentException("tri declared without vertex before");
                    }
                }
                case "plane" -> {
                    if (isLightVerified(diffuse, specular)) {
                        scene.addShape(new Plane(
                            diffuse,
                            specular,
                            new Point(
                                Double.parseDouble(split_line[1]),
                                Double.parseDouble(split_line[2]),
                                Double.parseDouble(split_line[3])
                            ),
                            new Vector(
                                Double.parseDouble(split_line[4]),
                                Double.parseDouble(split_line[5]),
                                Double.parseDouble(split_line[6])
                            )
                        ));
                    } else {
                        throw new IllegalArgumentException("Plane has no specular color nor diffuse color");
                    }
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + split_line[0]);
            }
        }
        return scene;
    }

    private boolean isLightVerified(Color diffuse, Color specular) {
        if (diffuse != null && specular != null) {
            return diffuse.validateObjectColor(specular);
        }
        return false;
    }
}