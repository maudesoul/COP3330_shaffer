public class Cube extends Shape3D {
    private final double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public String getName() {
        return "cube";
    }

    @Override
    public double getArea() {
        double cubeSurfaceArea;
        cubeSurfaceArea = 6 * (side * side);   // SA = 6*(side^2)
        return cubeSurfaceArea;
    }

    @Override
    public double getVolume() {
        double cubeVolume;
        cubeVolume = side * side * side;       // V = side^3
        return cubeVolume;
    }
}