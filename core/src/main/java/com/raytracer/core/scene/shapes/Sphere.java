package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;

import java.util.Optional;

public class Sphere extends Shape {
    private double radius;

    public Sphere(Color diffuse, Color specular, Point p, double radius) {
        super(diffuse, specular, p);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public Optional<Intersection> getIntersection(Ray ray) {
        double a = ray.getDirection().scalarProduct(ray.getDirection());
        double b = (ray.getOrigin().subtraction(getCenter()).scalarMultiplication(2.0)).scalarProduct(ray.getDirection());
        double c = (ray.getOrigin().subtraction(getCenter()).scalarProduct(ray.getOrigin().subtraction(getCenter()))) - (getRadius() * getRadius());

        double delta = (b * b) - (4 * a * c);

        if (delta < 0) {
            return Optional.empty();
        } else if (delta == 0) {
            double t = -b / (2 * a);
            if (t < 0) {
                return Optional.empty();
            }
            return Optional.of(new Intersection(ray,t,this));
        } else {
            double t1 = (-b + Math.sqrt(delta)) / (2 * a);
            double t2 = (-b - Math.sqrt(delta)) / (2 * a);
            if (t2 > 0) {
                return Optional.of(new Intersection(ray,t2,this));
            } else if (t1 > 0) {
                return Optional.of(new Intersection(ray,t1,this));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public String toString() {
        return "Sphere(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + "," + radius + ")";
    }
}
