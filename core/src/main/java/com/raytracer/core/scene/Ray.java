package com.raytracer.core.scene;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;

/**
 * Represents a ray in 3D space defined by an origin point and a direction vector.
 * Used as the fundamental tool in ray tracing to test for intersections with scene objects.
 */
public class Ray {
    private Point origin;
    private Vector direction;

    /**
     * Constructs a new Ray.
     *
     * @param origin the starting point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }

    public Vector getDirection() {
        return direction;
    }
}
