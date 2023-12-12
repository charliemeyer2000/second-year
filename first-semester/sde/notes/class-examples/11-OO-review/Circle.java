public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return radius * radius * Math.PI;
    }

    @Override
    public double perimeter() {
        return radius * Math.PI * 2;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
