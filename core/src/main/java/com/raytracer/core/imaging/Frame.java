package com.raytracer.core.imaging;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import com.raytracer.core.scene.Ray;
import com.raytracer.core.scene.RayTracer;
import com.raytracer.core.scene.Scene;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Manages the rendering process for a scene.
 * Iterates over image pixels, casts rays, determines intersections, and computes pixel colors.
 */
public class Frame {
    private final RayTracer rayTracer;
    private final Scene scene;

    /**
     * Constructs a Frame renderer.
     *
     * @param rayTracer the ray tracer instance containing the scene and logic
     */
    public Frame(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
        this.scene = rayTracer.scene;
    }

    /**
     * Renders the scene to a BufferedImage.
     * Loops through every pixel of the defined width and height, generating rays from the camera.
     * If an intersection is found, lighting calculations are applied.
     *
     * @return the rendered image
     */
    public BufferedImage render() {
        BufferedImage image = new BufferedImage(scene.getWidth(), scene.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < scene.getWidth(); i++) {
            for (int j = 0; j < scene.getHeight(); j++) {
                Vector direction = rayTracer.getPixelDirection(i, j);
                Point o = scene.getCamera().getLookFrom();
                Ray r = new Ray(o, direction);
                List<Intersection> intersections = scene.getAllIntersections(r);

                if (!intersections.isEmpty()) {
                    for (Intersection intersection : intersections) {
                        if (!scene.getLights().isEmpty()) image.setRGB(i, j, intersection.getColor(scene.getLights()));
                        else image.setRGB(i, j, scene.getAmbient().toRGB());
                    }
                }
                else {
                    image.setRGB(i, j, 0);
                }
            }
        }
        return image;
    }
}
