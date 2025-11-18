package com.raytracer.core.scene.lights;

import com.raytracer.core.geometry.Vector;
import com.raytracer.core.imaging.Color;

public class PointLight extends AbstractLight {
   public PointLight(Vector v, Color c) {
        super(v,c);
    }
}
