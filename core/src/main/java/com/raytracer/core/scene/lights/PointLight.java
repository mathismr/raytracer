package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.imaging.Color;

/**
 * Represents a point light source in the scene.
 */
public class PointLight extends AbstractLight {
    private final Point p;

    /**
     * Constructs a new PointLight.
     * @param c the color of the light
     * @param p the position of the light
     */
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
