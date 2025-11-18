package com.raytracer.core.geometry;

public class Vector extends AbstractVec3{

    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public Vector addition(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    @Override
    public Vector subtraction(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    @Override
    public Vector scalarMultiplication(double d) {
        return new Vector(getX() * d, getY() * d, getZ() * d);
    }

    @Override
    public double scalarProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    @Override
    public Vector vectorProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(
            getY() * v.getZ() - getZ() * v.getY(),
            getZ() * v.getX() - getX() * v.getZ(),
            getX() * v.getY() - getY() * v.getX()
        );
    }

    @Override
    public Vector schurProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(
            getX() * v.getX(),
            getY() * v.getY(),
            getZ() * v.getZ()
        );
    }

    @Override
    public double length() {
       return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    @Override
    public Vector normalize() {
        double norm = length();
        if (norm == 0) {
            throw new ArithmeticException("Cannot normalize a zero vector");
        } else {
            return new Vector(getX() / norm, getY() / norm, getZ() / norm);
        }
    }

    public boolean equals(Vector v) {
        return Double.compare(v.x, x) == 0
            && Double.compare(v.y, y) == 0
            && Double.compare(v.z, z) == 0;
    }

    public String toString() {
        return "Vector("+x+","+y+","+z+")";
    }
}
