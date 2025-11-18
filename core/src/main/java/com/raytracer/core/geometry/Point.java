package com.raytracer.core.geometry;

import com.raytracer.core.imaging.Color;

public class Point extends AbstractVec3 {

    public Point(double x, double y, double z) {
        super(x, y, z);
    }

    public boolean equals(Point p) {
        return Double.compare(p.x, x) == 0
            && Double.compare(p.y, y) == 0
            && Double.compare(p.z, z) == 0;
    }

    @Override
    public Point addition(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    @Override
    public Vector subtraction(AbstractVec3 obj) {
        Point p = (Point) obj;
        return new Vector(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    @Override
    public Point scalarMultiplication(double d) {
        return new Point(getX() * d,getY() * d,getZ() * d);
    }

    public String toString() {
        return "Point(" + x + "," + y + "," + z +")";
    }
}
