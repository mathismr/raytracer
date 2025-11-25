package com.raytracer.core.geometry;

/**
 * Represents a geometric vector in 3D space.
 * Provides implementation for standard vector operations including:
 * <ul>
 *   <li>Vector addition and subtraction</li>
 *   <li>Scalar multiplication</li>
 *   <li>Dot (scalar) and Cross (vector) products</li>
 *   <li>Normalization and length calculations</li>
 * </ul>
 */
public class Vector extends AbstractVec3{

    /**
     * Constructs a new Vector with the specified coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Adds another Vector to this one.
     *
     * @param obj the vector to add
     * @return the sum of the two vectors
     */
    @Override
    public Vector addition(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    /**
     * Subtracts another Vector from this one.
     *
     * @param obj the vector to subtract
     * @return the difference of the two vectors
     */
    @Override
    public Vector subtraction(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    /**
     * Multiplies this vector by a scalar value.
     *
     * @param d the scalar factor
     * @return the scaled vector
     */
    @Override
    public Vector scalarMultiplication(double d) {
        return new Vector(getX() * d, getY() * d, getZ() * d);
    }

    /**
     * Computes the scalar product (dot product) with another vector.
     *
     * @param obj the other vector
     * @return the dot product value
     */
    @Override
    public double scalarProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    /**
     * Computes the vector product (cross product) with another vector.
     *
     * @param obj the other vector
     * @return the cross product vector
     */
    @Override
    public Vector vectorProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(
            getY() * v.getZ() - getZ() * v.getY(),
            getZ() * v.getX() - getX() * v.getZ(),
            getX() * v.getY() - getY() * v.getX()
        );
    }

    /**
     * Computes the Schur product (component-wise multiplication) with another vector.
     *
     * @param obj the other vector
     * @return the resulting vector
     */
    @Override
    public Vector schurProduct(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Vector(
            getX() * v.getX(),
            getY() * v.getY(),
            getZ() * v.getZ()
        );
    }

    /**
     * Calculates the length of the vector.
     *
     * @return the length of the vector
     */
    @Override
    public double length() {
       return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    /**
     * Returns a normalized version of this vector (unit vector).
     *
     * @return a new vector with the same direction but length of 1
     * @throws ArithmeticException if the vector is a zero vector
     */
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
