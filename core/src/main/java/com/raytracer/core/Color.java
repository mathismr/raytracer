package com.raytracer.core;

public record Color(float r, float g, float b) {
    public Color() {
        this(0, 0, 0);
    }

    public boolean equals(Color c) {
        return this == c;
    }

    public int toRGB() {
        int red = (int) Math.round(r * 255);
        int green = (int) Math.round(g * 255);
        int blue = (int) Math.round(b * 255);

        return (((red & 0xff) << 16) + ((green & 0xff) << 8) + (blue & 0xff));
    }

    public String toString() {
        return "com.raytracer.core.Color(" + r + "," + g + "," + b + ")";
    }
}
