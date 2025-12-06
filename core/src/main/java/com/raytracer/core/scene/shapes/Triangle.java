package com.raytracer.core.scene.shapes;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;

import java.util.Optional;

public class Triangle extends Shape {
    private final Point a,b,c;

    public Triangle(Color diffuse, Color specular, int shininess, Point p1, Point p2, Point p3) {
        super(diffuse, specular, shininess, p1);
        this.a = p1;
        this.b = p2;
        this.c = p3;
    }

    @Override
    public Optional<Intersection> getIntersection(Ray ray) {
        Vector edge_ba = b.subtraction(a); // (b-a)
        Vector edge_ca = c.subtraction(a); // (c-a)
        Vector p = ray.getDirection().vectorProduct(edge_ca); // d x (c-a)
        double det = edge_ba.scalarProduct(p); // (b-a) x p

        if (Math.abs(det) < 1e-9) { return Optional.empty(); }
        Vector t = ray.getOrigin().subtraction(a);
        Vector q = t.vectorProduct(edge_ba);
        double beta = t.scalarProduct(p) / det;
        double gamma = ray.getDirection().scalarProduct(q) / det;

        if (beta < 0) { return Optional.empty(); }
        if (gamma < 0 || beta + gamma > 1) { return Optional.empty(); }

        double _t = edge_ca.scalarProduct(q) / det;

        if (_t < 1e-9) { return Optional.empty(); }

        return Optional.of(new Intersection(ray,_t,this));
    }

    @Override
    public String toString() {
            return "Triangle(" + diffuse.toString() + "," + specular.toString() + "," + a.toString() + "," + b.toString() + "," + c.toString() + ")";
    }
}
