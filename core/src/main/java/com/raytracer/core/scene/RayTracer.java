package com.raytracer.core.scene;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Orthonormal;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import com.raytracer.core.scene.shapes.Shape;
import com.raytracer.core.scene.shapes.Sphere;

import java.util.List;
import java.util.Optional;

public class RayTracer {
    public final Scene scene;

    public RayTracer(Scene scene) {
        this.scene = scene;
    }

    public double getPixelHeight() {
        return Math.tan(scene.getCamera().fovToRadians() / 2);
    }

    public double getPixelWidth() {
        return getPixelHeight() * scene.getWidth() / scene.getHeight();
    }

    public Vector getPixelDirection(int i, int j) {
        double a = (getPixelWidth() * (i - ((double) scene.getWidth() /2) + 0.5)) / ((double) scene.getWidth() /2);
        double b = (getPixelHeight() * (j - ((double) scene.getHeight() /2) + 0.5)) / ((double) scene.getHeight() /2);
        Orthonormal o = scene.getCamera().getOrthonormalRef();
        return o.getU().scalarMultiplication(a).addition(o.getV().scalarMultiplication(b)).subtraction(o.getW()).normalize();
    }
}
