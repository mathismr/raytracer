public class Point {
    private final double x;
    private final double y;
    private final double z;
    private final Color color;

    public Point(double x, double y, double z, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public boolean equals(Point p) {
        return p.x == x && p.y == y && p.z == z && color.equals(p.color);
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
}
