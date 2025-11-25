package com.raytracer.core.scene.lights;

import com.raytracer.core.imaging.Color;

/**
 * Abstract base class representing a 3-component light entity (x, y, z).
 * This serves as the foundation for both {@link DirectionalLight} and {@link PointLight} classes in the ray tracer.
 */
public abstract class AbstractLight {
    protected Color c;

    /**
     * Constructs a new Light with the specified color.
     * @param c the color of the light
     */
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
