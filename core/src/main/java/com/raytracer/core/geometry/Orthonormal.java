package com.raytracer.core.geometry;

public class Orthonormal {
    private final Vector u,v,w;

    public Orthonormal(Vector u, Vector v, Vector w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Vector getU() {
        return u;
    }

    public Vector getV() {
        return v;
    }

    public Vector getW() {
        return w;
    }
}
