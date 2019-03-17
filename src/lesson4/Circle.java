package lesson4;

public class Circle extends Figure{
    Point center;
    double radius;

    public Circle(Point center, double radius) throws IllegalArgumentException{
        setCenter(center);
        setRadius(radius);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) throws IllegalArgumentException{
        if(radius < 0) throw new IllegalArgumentException("Радиус меньше нуля");
        this.radius = radius;
    }

    @Override
    public double getPerimetr() {
        return 2*radius*Math.PI;
    }

    @Override
    public double getArea() {
        return radius*radius*Math.PI;
    }
}
