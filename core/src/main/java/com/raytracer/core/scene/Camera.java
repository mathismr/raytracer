package com.raytracer.core.scene;

public class Camera {
    private int x,y,z; // position of the camera
    private int u,v,w; // camera looks at point
    private int m,n,o; // orientation of camera
    private double f; // fov

    public Camera(int x, int y, int z, int u, int v, int w, int m, int n, int o, double f) {
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
}
