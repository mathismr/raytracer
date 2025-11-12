package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Point;

public abstract class AbstractLight {
    protected Point p;

    public AbstractLight(Point p) {
        this.p = p;
    }

    public String toString() {
        return "AbstractLight(" + p.toString() + ")";
    }
}
