package com.raytracer.core.scene;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Orthonormal;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import com.raytracer.core.scene.shapes.Shape;
import com.raytracer.core.scene.shapes.Sphere;

import java.util.List;
import java.util.Optional;

/**
 * The RayTracer class is responsible for implementing basic ray tracing computations
 * for rendering a 3D scene. It provides functionality to determine pixel dimensions
 * and compute ray directions for specific pixels in the output image.
 */
public class RayTracer {
    public final Scene scene;

    /**
     * Constructs a new RayTracer instance for rendering a given 3D scene.
     *
     * @param scene the 3D scene to be rendered, containing all shapes, lights,
     *              camera, and rendering configurations
     */
    public RayTracer(Scene scene) {
        this.scene = scene;
    }

    /**
     * Calculates the pixel height for the camera's view in the scene based on
     * the field of view (FOV) of the camera.
     *
     * @return the height of a pixel in the camera's view as a double
     */
    public double getPixelHeight() {
        return Math.tan(scene.getCamera().fovToRadians() / 2);
    }

    /**
     * Calculates the pixel width for the camera's view in the scene based on the
     * width-to-height ratio of the output image and the calculated pixel height.
     *
     * @return the width of a pixel in the camera's view as a double
     */
    public double getPixelWidth() {
        return getPixelHeight() * scene.getWidth() / scene.getHeight();
    }

    /**
     * Computes the direction vector for a ray passing through a specific pixel in the scene.
     * The pixel is specified by its coordinates (i, j) in the output image.
     *
     * @param i the horizontal pixel index (column), where 0 is the leftmost pixel
     * @param j the vertical pixel index (row), where 0 is the topmost pixel
     * @return a normalized {@code Vector} representing the direction of the ray
     *         for the specified pixel
     */
    public Vector getPixelDirection(int i, int j) {
        double a = (getPixelWidth() * (i - ((double) scene.getWidth() /2) + 0.5)) / ((double) scene.getWidth() /2);
        double b = (getPixelHeight() * (((double) scene.getHeight() / 2) - j - 0.5)) / ((double) scene.getHeight() / 2);
        Orthonormal o = scene.getCamera().getOrthonormalRef();
        return o.getU().scalarMultiplication(a).addition(o.getV().scalarMultiplication(b)).subtraction(o.getW()).normalize();
    }
}
