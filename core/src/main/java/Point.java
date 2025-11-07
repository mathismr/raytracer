public class Point {
    private final double x,y,z;
    private Color color;

    public Point(double x, double y, double z, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public boolean equals(Point p) {
        return Double.compare(p.x, x) == 0
            && Double.compare(p.y, y) == 0
            && Double.compare(p.z, z) == 0
            && color.equals(p.color);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return "Point(" + x + "," + y + "," + z + ")";
    }
}
