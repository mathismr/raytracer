package com.raytracer.core.geometry;

import com.raytracer.core.scene.Ray;
import com.raytracer.core.scene.shapes.Shape;

public class Intersection {
    private final Ray ray; // Ray that hit the shape
    private final double distance; // Distance from the ray origin to the intersection point
    private final Shape shape; // Shape that was hit
    private final Point intersectionAt; // Point where the ray hits the shape
    private final Vector normal; // Normal vector from shape center to intersection point

    public Intersection(Ray ray, double distance, Shape shape) {
        this.ray = ray;
        this.distance = distance;
        this.shape = shape;
        this.intersectionAt = ray.getOrigin().addition(ray.getDirection().scalarMultiplication(distance));
        this.normal = intersectionAt.subtraction(shape.getCenter()).normalize();
    }

    public Ray getRay() {
        return ray;
    }

    public double getDistance() {
        return distance;
    }

    public Shape getShape() {
        return shape;
    }

    public Vector getNormal() {
        return normal;
    }

    public Point getIntersectionAt() {
        return intersectionAt;
    }
}
