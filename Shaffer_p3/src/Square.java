public class Square extends Shape2D {
    private final double side;

    public Square(double side) {
        this.side = side;
    }
    @Override
    public String getName() {
        return "square";
    }
    @Override
    public double getArea() {
        double squareArea;
        squareArea = side * side;           // A = side^2
        return squareArea;
    }
}
