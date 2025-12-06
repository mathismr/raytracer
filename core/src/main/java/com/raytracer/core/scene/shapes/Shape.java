package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;

import java.util.Optional;

/**
 * Abstract base class representing a geometric shape in the scene.
 * Holds common properties such as position and material characteristics (diffuse and specular colors).
 */
public abstract class Shape {
    public Color diffuse;
    public Color specular;
    public Point point;
    public int shininess;

    /**
     * Constructs a new Shape with specified material properties and position.
     *
     * @param diffuse the diffuse color component of the shape
     * @param specular the specular color component of the shape
     * @param point the center point or reference position of the shape
     */
    public Shape(Color diffuse, Color specular, int shininess, Point point) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
        this.point = point;
    }

    public Optional<Intersection> getIntersection(Ray ray) {
        return Optional.empty();
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

    public int getShininess() { return shininess; }

    public String toString() {
        return "Shape(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + ")";
    }
}
