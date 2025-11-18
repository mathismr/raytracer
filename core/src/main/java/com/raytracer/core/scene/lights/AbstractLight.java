package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

public abstract class AbstractLight {
    protected Vector v;
    protected Color c;

    public AbstractLight(Vector v, Color c) {
        this.v = v;
        this.c = c;
    }

    public String toString() {
        return "AbstractLight(" + v.toString() + "," + c.toString() + ")";
    }
}
