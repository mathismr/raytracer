package com.raytracer.core.geometry;

import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;
import com.raytracer.core.scene.lights.AbstractLight;
import com.raytracer.core.scene.lights.DirectionalLight;
import com.raytracer.core.scene.lights.PointLight;
import com.raytracer.core.scene.shapes.Shape;

import java.util.List;

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

    public int getColor(List<AbstractLight> lights) {
        Color finalColor = new Color(0, 0, 0);

        for (AbstractLight absLight : lights) {
            Color contribution;
            if (absLight instanceof DirectionalLight light) {
                Vector lightDir = light.getVector().normalize();
                contribution = computeColor(lightDir, light);
            } else {
                PointLight light = (PointLight) absLight;
                Vector lightDir = light.getPoint().subtraction(getIntersectionAt()).normalize();
                contribution = computeColor(lightDir, light);
            }
            finalColor = (Color) finalColor.addition(contribution);
        }

        return finalColor.toRGB();
    }

    private Color computeColor(Vector lightDir, AbstractLight light) {
        Vector n = getNormal();
        double intensity = Math.max(n.scalarProduct(lightDir), 0);

        Color lightColor = light.getColor();
        Color shapeColor = shape.getDiffuse();

        Color finalColor = null;
        try {
            finalColor = (Color) lightColor.schurProduct(shapeColor).scalarMultiplication(intensity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return finalColor;
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
