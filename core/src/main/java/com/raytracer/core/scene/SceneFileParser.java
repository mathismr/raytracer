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

/**
 * A parser for scene description files used in rendering applications.
 * This class reads a file and constructs a {@link Scene} object based on the specified parameters.
 * The scene file specifies scene dimensions, output file, camera configuration, lighting,
 * and shapes such as spheres, triangles, and planes to be rendered.
 */
public class SceneFileParser {
    private final Scanner reader;

    /**
     * Constructs a new SceneFileParser instance for reading a scene description file.
     * @param filepath the filepath of the scene description file
     * @throws FileNotFoundException if the specified file does not exist
     */
    public SceneFileParser(String filepath) throws FileNotFoundException {
        File scene_file = new File(filepath);
        this.reader = new Scanner(scene_file);
    }

    public SceneFileParser(java.io.InputStream inputStream) {
        this.reader = new Scanner(inputStream);
    }

    /**
     * Parses a scene description file and constructs a Scene object based on the data provided.
     * The method reads lines of input, interprets specific commands such as definition of size, camera,
     * lighting, shapes, and other components, and adds them to the resulting Scene object.
     *
     * @return a fully populated Scene object constructed from the input data
     * @throws IllegalArgumentException if invalid or incomplete data is encountered during parsing
     */
    public Scene parse() {
        Scene scene = new Scene();
        Color diffuse = new Color(0, 0, 0);
        Color specular = new Color(0, 0, 0);
        int shininess = 0;
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
                    if (!isLightVerified(diffuse, specular, shininess)) {
                        System.err.println("Warning: Sphere has invalid lighting (diffuse + specular > 1).");
                    }

                    scene.addShape(new Sphere(
                        diffuse,
                        specular,
                        shininess,
                        new Point(
                            Double.parseDouble(split_line[1]),
                            Double.parseDouble(split_line[2]),
                            Double.parseDouble(split_line[3])
                        ),
                        Double.parseDouble(split_line[4])
                    ));
                }
                case "directional" -> {
                    scene.addLight(new DirectionalLight(
                        new Color(
                            Double.parseDouble(split_line[4]),
                            Double.parseDouble(split_line[5]),
                            Double.parseDouble(split_line[6])
                        ),
                        new Vector(
                            Double.parseDouble(split_line[1]),
                            Double.parseDouble(split_line[2]),
                            Double.parseDouble(split_line[3])
                        )
                    ));
                }
                case "point" -> {
                    scene.addLight(new PointLight(
                        new Color(
                            Double.parseDouble(split_line[4]),
                            Double.parseDouble(split_line[5]),
                            Double.parseDouble(split_line[6])
                        ),
                        new Point(
                            Double.parseDouble(split_line[1]),
                            Double.parseDouble(split_line[2]),
                            Double.parseDouble(split_line[3])
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
                    if (!isLightVerified(diffuse, specular, shininess)) {
                        System.err.println("Warning: Sphere has invalid lighting (diffuse + specular > 1).");
                    }
                    if (verts != null ) {
                        int idx1 = Integer.parseInt(split_line[1]);
                        int idx2 = Integer.parseInt(split_line[2]);
                        int idx3 = Integer.parseInt(split_line[3]);

                        if (idx1 <= verts.size()
                         && idx2 <= verts.size()
                         && idx3 <= verts.size()) {
                            scene.addShape(new Triangle(
                                diffuse,
                                specular,
                                shininess,
                                verts.get(idx1),
                                verts.get(idx2),
                                verts.get(idx3)));
                        } else {
                            throw new IllegalArgumentException("tri has invalid indices");
                        }
                    } else {
                        throw new IllegalArgumentException("tri declared without vertex before");
                    }
                }
                case "plane" -> {
                    if (!isLightVerified(diffuse, specular, shininess)) {
                        System.err.println("Warning: Sphere has invalid lighting (diffuse + specular > 1).");
                    }
                    scene.addShape(new Plane(
                        diffuse,
                        specular,
                        shininess,
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
                }
                case "shininess" -> {
                    shininess = Integer.parseInt(split_line[1]);
                }
                case "maxdepth" -> {
                    scene.setMaxdepth(Integer.parseInt(split_line[1]));
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + split_line[0]);
            }
        }
        return scene;
    }

    /**
     * Checks if the light configuration, defined by the given diffuse and specular colors, is valid.
     * The validation is performed by ensuring that the combined color values
     * of diffuse and specular remain within acceptable bounds.
     *
     * @param diffuse the diffuse color component
     * @param specular the specular color component
     * @return true if both colors are non-null and their combined values meet the validation criteria,
     *         false otherwise
     */
    private boolean isLightVerified(Color diffuse, Color specular, int shininess) {
        if (diffuse != null && specular != null && shininess >= 0) {
            return diffuse.validateObjectColor(specular);
        }
        return diffuse != null || specular != null;
    }
}