package com.raytracer.core.scene;

import com.raytracer.core.geometry.Intersection;
import com.raytracer.core.scene.lights.*;
import com.raytracer.core.imaging.*;
import com.raytracer.core.scene.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a 3D scene containing shapes, lights, camera, and rendering configuration.
 * This class manages all scene elements required for ray tracing including:
 * <ul>
 *   <li>Output image dimensions (width and height)</li>
 *   <li>Camera position and orientation</li>
 *   <li>Ambient lighting</li>
 *   <li>Light sources</li>
 *   <li>3D shapes/objects</li>
 * </ul>
 */
public class Scene {
    private int width;
    private int height;
    private Camera camera;
    private String output = "output.png";
    private Color ambient = new Color();

    private final List<AbstractLight> lights = new ArrayList<>();
    private final List<Shape> shapes = new ArrayList<>();

    /**
     * Gets the width of the output image in pixels.
     *
     * @return the width of the rendered image
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the output image in pixels.
     *
     * @param width the width of the rendered image
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the output image in pixels.
     *
     * @return the height of the rendered image
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the output image in pixels.
     *
     * @param height the height of the rendered image
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the camera used to render the scene.
     *
     * @return the scene camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets the camera to be used for rendering the scene.
     *
     * @param camera the camera to set
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Gets the output file path for the rendered image.
     *
     * @return the output file path
     */
    public String getOutput() {
        return output;
    }

    /**
     * Sets the output file path for the rendered image.
     *
     * @param output the output file path
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * Gets the ambient light color for the scene.
     *
     * @return the ambient light color
     */
    public Color getAmbient() {
        return ambient;
    }

    /**
     * Sets the ambient light color for the scene.
     *
     * @param ambient the ambient light color to set
     */
    public void setAmbient(Color ambient) {
        this.ambient = ambient;
    }

    /**
     * Gets the list of all light sources in the scene.
     *
     * @return the list of lights
     */
    public List<AbstractLight> getLights() {
        return lights;
    }

    /**
     * Adds a light source to the scene.
     *
     * @param light the light to add
     */
    public void addLight(AbstractLight light) {
        this.lights.add(light);
    }

    /**
     * Gets the list of all shapes in the scene.
     *
     * @return the list of shapes
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * Adds a shape to the scene.
     *
     * @param shape the shape to add
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public List<Intersection> getAllIntersections(Ray ray) {
        List<Intersection> intersections = new ArrayList<>();
        for (Shape shape : getShapes()) {
            Sphere sphere = (Sphere) shape;
            Optional<Intersection> intersection = sphere.getIntersection(ray);
            if (intersection.isEmpty()) {
                continue;
            }
            intersections.add(intersection.get());
        }
        return intersections;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "width=" + width +
                ", height=" + height +
                ", camera=" + camera.toString() +
                ", output='" + output + '\'' +
                ", ambient=" + ambient.toString() +
                ", lights=" + lights +
                ", shapes=" + shapes +
                '}';
    }
}
