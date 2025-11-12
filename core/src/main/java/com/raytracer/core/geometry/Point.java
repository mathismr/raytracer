package com.raytracer.core.geometry;

import com.raytracer.core.imaging.Color;

public class Point extends AbstractVec3 {
    private Color color;

    public Point(double x, double y, double z, Color color) {
        super(x, y, z);
        this.color = color;
    }

    public boolean equals(Point p) {
        return Double.compare(p.x, x) == 0
            && Double.compare(p.y, y) == 0
            && Double.compare(p.z, z) == 0
            && color.equals(p.color);
    }

    @Override
    public AbstractVec3 addition(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ(), getColor());
    }

    @Override
    public AbstractVec3 subtraction(AbstractVec3 obj) {
        if (obj instanceof Vector v) {
            return new Point(x - v.getX(), y - v.getY(), z - v.getZ(), new Color());
        } else if (obj instanceof Point p) {
            return new Vector(x - p.getX(), y - p.getY(), z - p.getZ());
        } else {
            throw new UnsupportedOperationException("subtraction() parameter must be instanceof Vector or Point");
        }
    }

    @Override
    public AbstractVec3 scalarMultiplication(double d) {
        return new Point(getX() * d,getY() * d,getZ() * d,getColor());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return "com.raytracer.core.geometry.Point(" + x + "," + y + "," + z + ")";
    }
}
