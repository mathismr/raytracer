package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

import java.util.List;

public class Triangle extends Shape {
    private Point p2, p3;

    public Triangle(Color diffuse, Color specular, Point p1, Point p2, Point p3) {
        super(diffuse,specular,p1);
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public String toString() {
            return "Triangle(" + diffuse.toString() + "," + specular.toString() + "," + point.toString() + "," + p2.toString() + "," + p3.toString() + ")";
    }
}
