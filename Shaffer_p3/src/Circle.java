public class Circle extends Shape2D {
    private final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public String getName() {
        return "circle";
    }
    @Override
    public double getArea() {
        double circleArea;
        circleArea = Math.PI * radius * radius;         // A = pi*(radius^2)
        return circleArea;
    }
}
