package com.raytracer.core.geometry;

public abstract class AbstractVec3 {
    protected double x,y,z;

    public AbstractVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public AbstractVec3 addition(AbstractVec3 obj) throws Exception {
        throw new Exception("Function addition() not available for this class");
    }
    public AbstractVec3 subtraction(AbstractVec3 obj) throws Exception {
        throw new Exception("Function subtraction() not available for this class");
    }
    public AbstractVec3 scalarMultiplication(double d) throws Exception {
        throw new Exception("Function scalarMultiplication() not available for this class");
    }
    public double scalarProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function scalarProduct() not available for this class");
    }
    public AbstractVec3 vectorProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function vectorProduct() not available for this class");
    }
    public AbstractVec3 schurProduct(AbstractVec3 obj) throws Exception {
        throw new Exception("Function schurProduct() not available for this class");
    }
    public double length() throws Exception {
        throw new Exception("Function length() not available for this class");
    }
    public AbstractVec3 normalize() throws Exception {
        throw new Exception("Function normalize() not available for this class");
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public double getZ() {return z;}
}