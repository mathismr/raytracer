package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

public class Sphere extends Shape {
    private double radius;

    public Sphere(Color diffuse, Color specular, Point p, double radius) {
        super(diffuse, specular, p);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + "," + radius + ")";
    }
}
