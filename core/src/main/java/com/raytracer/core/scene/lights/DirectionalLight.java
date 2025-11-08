package com.raytracer.core.scene.lights;

public class DirectionalLight extends AbstractLight {
    private int x,y,z;
    private int r,g,b;

    public DirectionalLight(int x, int y, int z, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
