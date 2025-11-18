package com.raytracer.core.scene;

import com.raytracer.core.geometry.AbstractVec3;
import com.raytracer.core.geometry.Orthonormal;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

public class Camera {
    private double x,y,z; // position of the camera
    private double u,v,w; // camera looks at point
    private double m,n,o; // orientation of camera
    private double f; // fov
    private Point lookFrom, lookAt;
    private Vector up;

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
        this.lookFrom = new Point(x,y,z);
        this.lookAt = new Point(u,v,w);
        this.up = new Vector(m,n,o);
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

    public Orthonormal getOrthonormalRef() {
        Vector w = lookFrom.subtraction(lookAt).normalize();
        Vector u = up.normalize().vectorProduct(w).normalize();
        Vector v = w.vectorProduct(u).normalize();
        return new Orthonormal(u,v,w);
    }

    public double fovToRadians() {
        return (f * Math.PI) / 180.0;
    }

    public Point getLookFrom() {
        return lookFrom;
    }

    public Point getLookAt() {
        return lookAt;
    }

    public Vector getUp() {
        return up;
    }
}
