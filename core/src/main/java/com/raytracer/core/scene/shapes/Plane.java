package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.*;
import com.raytracer.core.imaging.Color;

public class Plane extends Shape {
    private Vector v;

    public Plane(Color diffuse, Color specular, Point p, Vector v) {
        super(diffuse,specular,p);
        this.v = v;
    }

    @Override
    public String toString() {
        return "Plane(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + "," + v.toString() + ")";
    }
}
