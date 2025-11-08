package com.raytracer.core.scene.shapes;

import com.raytracer.core.imaging.Color;

public class Sphere extends Shape {
    private double x,y,z; // position
    private double radius;

    public Sphere(Color diffuse, Color specular, double x, double y, double z, double radius) {
        super(diffuse, specular);
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }
}
