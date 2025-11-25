package com.raytracer.core.geometry;

/**
 * Abstract base class representing a 3-component mathematical entity (x, y, z).
 * This serves as the foundation for both {@link Point} and {@link Vector} classes in the ray tracer.
 * It provides common storage for coordinates and defines the interface for mathematical operations.
 */
public abstract class AbstractVec3 {
    protected double x,y,z;

    /**
     * Constructs a new 3D entity with the specified coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public AbstractVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds another 3D entity to this one.
     *
     * @param obj the object to add
     * @return the result of the addition
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 addition(AbstractVec3 obj) throws Exception {
        throw new Exception("Function addition() not available for this class");
    }

    /**
     * Subtracts another 3D entity from this one.
     *
     * @param obj the object to subtract
     * @return the result of the subtraction
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 subtraction(AbstractVec3 obj) throws Exception {
        throw new Exception("Function subtraction() not available for this class");
    }

    /**
     * Multiplies the entity by a scalar value.
     *
     * @param d the scalar value
     * @return the result of the scalar multiplication
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 scalarMultiplication(double d) throws Exception {
        throw new Exception("Function scalarMultiplication() not available for this class");
    }

    /**
     * Calculates the dot product (scalar product) with another entity.
     *
     * @param obj the other entity
     * @return the scalar product result
     * @throws Exception if the operation is not supported for the concrete class
     */
    public double scalarProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function scalarProduct() not available for this class");
    }

    /**
     * Calculates the cross product (vector product) with another entity.
     *
     * @param obj the other entity
     * @return the vector resulting from the cross product
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 vectorProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function vectorProduct() not available for this class");
    }

    /**
     * Calculates the Schur product (element-wise multiplication) with another entity.
     *
     * @param obj the other entity
     * @return the result of the element-wise multiplication
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 schurProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function schurProduct() not available for this class");
    }

    /**
     * Calculates the length of the vector.
     *
     * @return the length
     * @throws Exception if the operation is not supported for the concrete class
     */
    public double length() throws Exception {
        throw new Exception("Function length() not available for this class");
    }

    /**
     * Returns a normalized version of this vector (length of 1).
     *
     * @return the normalized vector
     * @throws Exception if the operation is not supported for the concrete class
     */
    public AbstractVec3 normalize() throws Exception {
        throw new Exception("Function normalize() not available for this class");
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public double getZ() {return z;}
}