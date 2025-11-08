package com.raytracer.core.scene.shapes;

import com.raytracer.core.imaging.Color;

public class Shape {
    public Color diffuse;
    public Color specular;

    public Shape(Color diffuse, Color specular) {
        this.diffuse = diffuse;
        this.specular = specular;
    }
}
