package com.raytracer.core.geometry;

public class AbstractVec3 {
    public static Vector addition(Vector v1, Vector v2) {
        return new Vector(
            v1.getX() + v2.getX(),
            v1.getY() + v2.getY(),
            v1.getZ() + v2.getZ()
        );
    }

    public static Point addition(Point p1, Vector v1) {
        return new Point(
            p1.getX() + v1.getX(),
            p1.getY() + v1.getY(),
            p1.getZ() + v1.getZ(),
            p1.getColor()
        );
    }

    public static Point subtraction(Point p1, Vector v1) {
        return new Point(
            p1.getX() - v1.getX(),
            p1.getY() - v1.getY(),
            p1.getZ() - v1.getZ(),
            p1.getColor()
        );
    }

    public static Vector subtraction(Point p1, Point p2) {
        return new Vector(
            p1.getX() - p2.getX(),
            p1.getY() - p2.getY(),
            p1.getZ() - p2.getZ()
        );
    }

    public static Vector subtraction(Vector v1, Vector v2) {
        return new Vector(
            v1.getX() - v2.getX(),
            v1.getY() - v2.getY(),
            v1.getZ() - v2.getZ()
        );
    }

    public static Point scalarMultiplication(Point p1, double d) {
        return new Point(
            p1.getX() * d,
            p1.getY() * d,
            p1.getZ() * d,
            p1.getColor()
        );
    }

    public static Vector scalarMultiplication(Vector v1, double d) {
        return new Vector(
            v1.getX() * d,
            v1.getY() * d,
            v1.getZ() * d
        );
    }

    public static double scalarProduct(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    public static Vector vectorProduct(Vector v1, Vector v2) {
        return new Vector(
            v1.getY() * v2.getZ() - v1.getZ() * v2.getY(),
            v1.getZ() * v2.getX() - v1.getX() * v2.getZ(),
            v1.getX() * v2.getY() - v1.getY() * v2.getX()
        );
    }

    public static Vector schurProduct(Vector v1, Vector v2) {
        return new Vector(
            v1.getX() * v2.getX(),
            v1.getY() * v2.getY(),
            v1.getZ() * v2.getZ()
        );
    }

    public static double length(Vector v1) {
        return Math.sqrt(v1.getX() * v1.getX() + v1.getY() * v1.getY() + v1.getZ() * v1.getZ());
    }

    public static Vector normalize(Vector v1) {
        double length = length(v1);
        return new Vector(
            (1.0/length) * v1.getX(),
            (1.0/length) * v1.getY(),
            (1.0/length) * v1.getZ()
        );
    }
}
