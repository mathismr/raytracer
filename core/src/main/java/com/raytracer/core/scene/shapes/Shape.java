package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

public class Shape {
    public Color diffuse;
    public Color specular;
    public Point point;

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

    public String toString() {
        return "Shape(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + ")";
    }
}
