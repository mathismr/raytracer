package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

public abstract class AbstractLight {
    protected Color c;

    public AbstractLight(Color c) {
        this.c = c;
    }

    public String toString() {
        return "AbstractLight(" + c.toString() + ")";
    }

    public Color getColor() {
        return c;
    }
}
