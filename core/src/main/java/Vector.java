public class Vector {
    public final double x;
    public final double y;
    public final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector v) {
        return v.x == x && v.y == y && v.z == z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
