package com.raytracer.core.geometry;

import com.raytracer.core.imaging.Color;
import com.raytracer.core.scene.Ray;
import com.raytracer.core.scene.lights.AbstractLight;
import com.raytracer.core.scene.lights.DirectionalLight;
import com.raytracer.core.scene.lights.PointLight;
import com.raytracer.core.scene.shapes.Shape;
import com.raytracer.core.scene.Scene;

import java.util.List;

/**
 * Represents a hit point where a {@link Ray} intersects with a {@link Shape}.
 * Stores all necessary information about the intersection to calculate lighting and shading, including:
 * <ul>
 *   <li>The ray that caused the intersection</li>
 *   <li>The specific point of intersection in 3D space</li>
 *   <li>The distance from the ray origin</li>
 *   <li>The surface normal at the intersection point</li>
 *   <li>The shape that was hit</li>
 * </ul>
 */
public class Intersection {
    private final Ray ray; // Ray that hit the shape
    private final double distance; // Distance from the ray origin to the intersection point
    private final Shape shape; // Shape that was hit
    private final Point intersectionAt; // Point where the ray hits the shape
    private final Vector normal; // Normal vector from shape center to intersection point

    /**
     * Constructs a new Intersection details object.
     * Calculates the intersection point and surface normal based on the ray and shape.
     *
     * @param ray the ray that intersected the shape
     * @param distance the distance along the ray to the intersection point
     * @param shape the geometric shape that was hit
     */
    public Intersection(Ray ray, double distance, Shape shape, Vector normal) {
        this.ray = ray;
        this.distance = distance;
        this.shape = shape;
        this.intersectionAt = ray.getOrigin().addition(ray.getDirection().scalarMultiplication(distance));
        //this.normal = intersectionAt.subtraction(shape.getCenter()).normalize();
        this.normal = normal.normalize();
    }

    /**
     * Gets the ray involved in the intersection.
     *
     * @return the ray
     */
    public Ray getRay() {
        return ray;
    }

    /**
     * Computes the color at the intersection point based on the scene's lights.
     * Aggregates contributions from all light sources (directional and point lights)
     * using the shape's material properties.
     *
     * @param lights the list of light sources in the scene
     * @return the computed RGB color value as an integer
     */
    public int getColor(List<AbstractLight> lights, Color ambient, Scene scene) throws Exception {
        Color finalColor = ambient;

        for (AbstractLight absLight : lights) {
            Color contribution;
            Vector lightDir;
            double distanceToLight;

            if (absLight instanceof DirectionalLight light) {
                lightDir = light.getVector().normalize();
                distanceToLight = Double.POSITIVE_INFINITY;
            } else {
                PointLight light = (PointLight) absLight;
                Vector lightVec = light.getPoint().subtraction(getIntersectionAt());
                distanceToLight = lightVec.length();
                lightDir = lightVec.normalize();
            }

            Ray shadowRay = new Ray(getIntersectionAt().addition(lightDir.scalarMultiplication(0.001)), lightDir);
            boolean isShadowed = false;

            List<Intersection> shadowIntersections = scene.getAllIntersections(shadowRay);
            for (Intersection intersection : shadowIntersections) {
                if (intersection.getDistance() > 0.001 && intersection.getDistance() < distanceToLight) {
                    isShadowed = true;
                    break;
                }
            }

            if (!isShadowed) {
                contribution = computeColor(lightDir, absLight);
                finalColor = (Color) finalColor.addition(contribution);
            }
        }

        return finalColor.toRGB();
    }

    private Color computeColor(Vector lightDir, AbstractLight light) throws Exception {
        return computeLambert(lightDir, light).addition(computePhong(lightDir, light));
    }

    private Color computeLambert(Vector lightDir, AbstractLight light) {
        Vector n = getNormal();
        double intensity = Math.max(n.scalarProduct(lightDir), 0);

        Color lightColor = light.getColor();
        Color shapeColor = shape.getDiffuse();

        Color lambertComputedColor = null;
        try {
            lambertComputedColor = (Color) lightColor.schurProduct(shapeColor).scalarMultiplication(intensity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lambertComputedColor;
    }

    private Color computePhong(Vector lightDir, AbstractLight light) throws Exception {
        Vector eyeDir = ray.getDirection().scalarMultiplication(-1.0).normalize();
        Vector h = lightDir.addition(eyeDir).normalize();
        double lbp = Math.max(h.scalarProduct(getNormal()), 0.0);
        double specularIntensity = Math.pow(lbp, shape.getShininess());
        try {
            return (Color) light.getColor().scalarMultiplication(specularIntensity).schurProduct(shape.getSpecular());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getDistance() {
        return distance;
    }

    public Shape getShape() {
        return shape;
    }

    public Vector getNormal() {
        return normal;
    }

    public Point getIntersectionAt() {
        return intersectionAt;
    }
}
