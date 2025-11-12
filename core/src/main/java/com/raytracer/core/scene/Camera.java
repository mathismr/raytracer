package com.raytracer.core.scene;

public class Camera {
    private double x,y,z; // position of the camera
    private double u,v,w; // camera looks at point
    private double m,n,o; // orientation of camera
    private double f; // fov

    public Camera(double x, double y, double z, double u, double v, double w, double m, double n, double o, double f) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
        this.w = w;
        this.m = m;
        this.n = n;
        this.o = o;
        this.f = f;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                ", m=" + m +
                ", n=" + n +
                ", o=" + o +
                ", f=" + f +
                '}';
    }
}
