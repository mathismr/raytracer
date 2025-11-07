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
        return Double.compare(v.x, x) == 0
            && Double.compare(v.y, y) == 0
            && Double.compare(v.z, z) == 0;
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

    public String toString() {
        return "Vector("+x+","+y+","+z+")";
    }
}
