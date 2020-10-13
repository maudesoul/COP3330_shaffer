public class Sphere extends Shape3D {
    private final double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "sphere";
    }

    @Override
    public double getArea() {
        double sphereSurfaceArea;
        sphereSurfaceArea = 4 * Math.PI * radius * radius;                  // SA = 4*pi*(radius^2)
        return sphereSurfaceArea;
    }

    @Override
    public double getVolume() {
        double sphereVolume;
        sphereVolume = (4.0 / 3.0) * Math.PI * radius * radius * radius;    // V = (4/3)*pi*(radius^3)
        return sphereVolume;
    }
}