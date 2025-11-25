package com.raytracer.core.geometry;

/**
 * Represents a specific position in 3D space.
 * Points are distinct from Vectors in their semantic meaning and valid operations:
 * <ul>
 *   <li>Point + Vector results in a new Point (translation)</li>
 *   <li>Point - Point results in a Vector (displacement)</li>
 * </ul>
 */
public class Point extends AbstractVec3 {

    /**
     * Constructs a new Point with the specified coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Point(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param p the point to compare with
     * @return true if coordinates are equal, false otherwise
     */
    public boolean equals(Point p) {
        return Double.compare(p.x, x) == 0
            && Double.compare(p.y, y) == 0
            && Double.compare(p.z, z) == 0;
    }

    /**
     * Adds a Vector to this Point to get a new translated Point.
     *
     * @param obj the vector to add
     * @return the new point resulting from the translation
     */
    @Override
    public Point addition(AbstractVec3 obj) {
        Vector v = (Vector) obj;
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    /**
     * Subtracts another Point from this one to get the Vector between them.
     *
     * @param obj the point to subtract
     * @return the vector representing the difference between the two points
     */
    @Override
    public Vector subtraction(AbstractVec3 obj) {
        Point p = (Point) obj;
        return new Vector(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    /**
     * Multiplies the point coordinates by a scalar value.
     *
     * @param d the scalar factor
     * @return the scaled point
     */
    @Override
    public Point scalarMultiplication(double d) {
        return new Point(getX() * d,getY() * d,getZ() * d);
    }

    public String toString() {
        return "Point(" + x + "," + y + "," + z +")";
    }
}
