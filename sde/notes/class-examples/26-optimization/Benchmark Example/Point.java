package edu.virginia.cs.sde.efficiency;

public class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Point point) {
        double xDiff = this.getX() - point.getX();
        double yDiff = this.getY() - point.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(getX(), point.getX()) != 0) return false;
        return Double.compare(getY(), point.getY()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getX());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "edu.virginia.cs.sde.efficiency.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
