package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

/**
 * Abstract base class representing a geometric shape in the scene.
 * Holds common properties such as position and material characteristics (diffuse and specular colors).
 */
public class Shape {
    public Color diffuse;
    public Color specular;
    public Point point;

    /**
     * Constructs a new Shape with specified material properties and position.
     *
     * @param diffuse the diffuse color component of the shape
     * @param specular the specular color component of the shape
     * @param point the center point or reference position of the shape
     */
    public Shape(Color diffuse, Color specular, Point point) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.point = point;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public Point getCenter() {
        return point;
    }

    public String toString() {
        return "Shape(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + ")";
    }
}
