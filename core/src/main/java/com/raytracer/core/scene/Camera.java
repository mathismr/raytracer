package com.raytracer.core.scene;

import com.raytracer.core.geometry.Orthonormal;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;

/**
 * Represents a virtual camera in the scene.
 * Defines the observer's position, viewing direction, orientation (up vector), and field of view.
 */
public class Camera {
    private final double x,y,z; // position of the camera
    private final double u,v,w; // camera looks at point
    private final double m,n,o; // orientation of camera
    private final double f; // fov
    private final Point lookFrom, lookAt;
    private final Vector up;

    /**
     * Constructs a new Camera with all parameters.
     *
     * @param x camera position x
     * @param y camera position y
     * @param z camera position z
     * @param u look-at point x
     * @param v look-at point y
     * @param w look-at point z
     * @param m up vector x component
     * @param n up vector y component
     * @param o up vector z component
     * @param f field of view in degrees
     */
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

    /**
     * Computes the orthonormal basis vectors for the camera's coordinate system.
     * Used to generate rays from the camera into the scene.
     *
     * @return the orthonormal basis (u, v, w)
     */
    public Orthonormal getOrthonormalRef() {
        Vector w = lookFrom.subtraction(lookAt).normalize();
        Vector u = up.normalize().vectorProduct(w).normalize();
        Vector v = w.vectorProduct(u).normalize();
        return new Orthonormal(u,v,w);
    }

    /**
     * Converts the Field of View from degrees to radians.
     *
     * @return the FOV in radians
     */
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
