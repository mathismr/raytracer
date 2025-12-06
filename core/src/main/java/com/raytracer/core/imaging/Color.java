package com.raytracer.core.imaging;

import com.raytracer.core.geometry.AbstractVec3;

/**
 * Represents an RGB color.
 * Inherits from {@link AbstractVec3} where x, y, z map to Red, Green, and Blue components respectively.
 * Component values are typically in the range [0.0, 1.0].
 */
public class Color extends AbstractVec3 {
    /**
     * Constructs a Color with specified Red, Green, and Blue values.
     *
     * @param x red component
     * @param y green component
     * @param z blue component
     */
    public Color(double x, double y, double z) {
        super(x,y,z);
    }

    /**
     * Constructs a default black Color (0, 0, 0).
     */
    public Color() { super(0, 0, 0); }

    double r = x;
    double g = y;
    double b = z;

    public boolean equals(Color c) { return this == c; }

    /**
     * Converts the floating-point color components to a packed 32-bit integer RGB value.
     * Values are clamped and scaled to 0-255.
     *
     * @return the integer representation of the color
     */
    public int toRGB() {
        int red = (int) Math.round(r * 255);
        int green = (int) Math.round(g * 255);
        int blue = (int) Math.round(b * 255);

        return (((red & 0xff) << 16) + ((green & 0xff) << 8) + (blue & 0xff));
    }

    public String toString() {
        return "Color(" + r + "," + g + "," + b + ")";
    }

    /**
     * Validates if the sum of this color and another color stays within valid bounds (<= 1.0).
     *
     * @param c the color to add
     * @return true if the resulting components are valid
     */
    public boolean validateObjectColor(Color c) {
        return r + c.r <= 1
            && g + c.g <= 1
            && b + c.b <= 1;
    }

    /**
     * Adds another Color to this one.
     *
     * @param obj the color to add
     * @return the resulting mixed color
     */
    @Override
    public Color addition(AbstractVec3 obj) {
        Color c = (Color) obj;
        return new Color(
            r + c.getX(),
            g + c.getY(),
            b + c.getZ()
        );
    }

    /**
     * Multiplies the color components by a scalar value (adjusts brightness).
     *
     * @param d the scalar factor
     * @return the scaled color
     */
    @Override
    public AbstractVec3 scalarMultiplication(double d) {
        return new Color(r * d, g * d, b * d);
    }

    /**
     * Computes the Schur product (component-wise multiplication) with another Color.
     * Used for calculating light interaction with materials.
     *
     * @param obj the other color
     * @return the resulting color
     */
    @Override
    public Color schurProduct(AbstractVec3 obj) {
        Color c = (Color) obj;
        return new Color(
            r * c.getX(),
            g * c.getY(),
            b * c.getZ()
        );
    }
}
