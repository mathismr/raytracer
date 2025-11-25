package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

/**
 * Represents a directional light source in the scene.
 */
public class DirectionalLight extends AbstractLight {
    private final Vector v;

    /**
     * Constructs a new DirectionalLight.
     * @param c the color of the light
     * @param v the direction vector of the light
     */
    public DirectionalLight(Color c, Vector v) {
        super(c);
        this.v = v;
    }

    @Override
    public String toString() {
        return "DirectionalLight(" + c.toString() + "," + v.toString() + ")";
    }

    public Vector getVector() {
        return v;
    }
}
