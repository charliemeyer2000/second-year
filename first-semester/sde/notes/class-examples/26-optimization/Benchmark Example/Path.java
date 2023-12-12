package edu.virginia.cs.sde.efficiency;

public interface Path {
    void add(double xCoordinate, double yCoordinate);
    void add(Point point);
    Point get(int index);
    int size();
    double totalDistance();


}
