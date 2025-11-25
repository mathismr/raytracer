package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

public class DirectionalLight extends AbstractLight {
    private final Vector v;

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
