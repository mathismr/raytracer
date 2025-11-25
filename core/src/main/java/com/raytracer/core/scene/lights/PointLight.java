package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

public class PointLight extends AbstractLight {
    private final Point p;

    public PointLight(Color c, Point p) {
        super(c);
        this.p = p;
   }

    @Override
    public String toString() {
        return "PointLight(" + c.toString() + "," + p.toString() + ")";
    }

    public Point getPoint() {
        return p;
    }
}
