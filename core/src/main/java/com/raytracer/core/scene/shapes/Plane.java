package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.*;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;

import java.util.Optional;

/**
 * Represents a plane shape in the 3D scene.
 * Defined by a normal vector and a point on the plane.
 */
public class Plane extends Shape {
    private final Vector normal;

    public Plane(Color diffuse, Color specular, int shininess, Point p, Vector normal) {
        super(diffuse, specular, shininess, p);
        this.normal = normal.normalize();
    }

    /**
     * Calculates the intersection between a ray and this plane.
     * @param ray the ray to check for intersection
     * @return an Optional containing the intersection details if found, or empty if no intersection
     */
    @Override
    public Optional<Intersection> getIntersection(Ray ray) {
        double d = ray.getDirection().scalarProduct(normal);
        if (d == 0) {
            return Optional.empty();
        }
        Vector v = point.subtraction(ray.getOrigin());
        double t = v.scalarProduct(normal) / d;
        return Optional.of(new Intersection(ray,t,this, normal));
    }

    @Override
    public String toString() {
        return "Plane(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + "," + normal.toString() + ")";
    }
}
