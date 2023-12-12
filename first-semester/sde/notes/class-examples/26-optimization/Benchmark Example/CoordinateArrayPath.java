package edu.virginia.cs.sde.efficiency;

/**
 * Represents points as a 1-D of coordinates.
 *
 * Example: if we had the points (1, 2), (4, 6), (-3, 14), the array would be:
 *
 * [1, 2, 4, 6, -3, 14]
 *
 * That is, [x0, y0, x1, y1, x2, y2 ...]
 */

public class CoordinateArrayPath implements Path {
    private final double[] pointArray;
    private final int length;
    private int currentSize;

    public CoordinateArrayPath(int capacity) {
        pointArray = new double[capacity * 2];
        this.length = capacity;
        this.currentSize = 0;
    }

    @Override
    public void add(double xCoordinate, double yCoordinate) {
        pointArray[currentSize * 2] = xCoordinate;
        pointArray[currentSize * 2 + 1] = yCoordinate;
        currentSize++;
    }

    @Override
    public void add(Point point) {
        if (currentSize == length) {
            throw new IllegalStateException("CoordinateArray is full and cannot add more points");
        }
        add(point.getX(), point.getY());
    }

    @Override
    public Point get(int index) {
        if (index >= currentSize) {
            throw new IndexOutOfBoundsException(String.format(
                    "index - %d is out of bounds for CoordinateArray of size - %d", index, currentSize));
        }
        double pointX = pointArray[index * 2];
        double pointY = pointArray[index * 2 + 1];
        return new Point(pointX, pointY);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public double totalDistance() {
        double totalDistance = 0.0;
        for (int i = 0; i < currentSize - 1; i++) {
            double diffX = pointArray[2 * i] - pointArray[2 * i + 2];
            double diffY = pointArray[2 * i + 1] - pointArray[2 * i + 3];
            totalDistance += Math.sqrt(diffX * diffX + diffY * diffY);
        }
        return totalDistance;
    }
}
