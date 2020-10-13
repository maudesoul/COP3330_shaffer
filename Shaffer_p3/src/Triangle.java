public class Triangle extends Shape2D {
    private final double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public double getArea() {
        double triangleArea;
        triangleArea = 0.5 * base * height;         // A = 1/2 * base * height
        return triangleArea;
    }
}