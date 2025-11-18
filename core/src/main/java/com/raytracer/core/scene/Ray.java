package com.raytracer.core.scene;

import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;

public class Ray {
    private Point origin;
    private Vector direction;

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

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
}
