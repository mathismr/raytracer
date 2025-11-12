package com.raytracer.core.imaging;

import com.raytracer.core.geometry.AbstractVec3;

public class Color extends AbstractVec3 {
    public Color(double x, double y, double z) {
        super(x,y,z);
    }

    public Color() { super(0, 0, 0); }

    double r = x;
    double g = y;
    double b = z;

    public boolean equals(Color c) { return this == c; }

    public int toRGB() {
        int red = (int) Math.round(r * 255);
        int green = (int) Math.round(g * 255);
        int blue = (int) Math.round(b * 255);

        return (((red & 0xff) << 16) + ((green & 0xff) << 8) + (blue & 0xff));
    }

    public String toString() {
        return "com.raytracer.core.imaging.Color(" + r + "," + g + "," + b + ")";
    }
    public boolean validateObjectColor(Color c) {
        return r + c.r <= 1
            && g + c.g <= 1
            && b + c.b <= 1;
    }

    public AbstractVec3 addition(AbstractVec3 obj) {
        Color c = (Color) obj;
        return new Color(
            r + c.getX(),
            g + c.getY(),
            b + c.getZ()
        );
    }

    public AbstractVec3 scalarMultiplication(double d) {
        return new Color(r * d, g * d, b * d);
    }

    public AbstractVec3 schurProduct(AbstractVec3 obj) {
        Color c = (Color) obj;
        return new Color(
            r * c.getX(),
            g * c.getY(),
            b * c.getZ()
        );
    }
}
