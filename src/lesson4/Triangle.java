package lesson4;

public class Triangle extends Figure {

    Point p1;
    Point p2;
    Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        setP1(p1);
        setP2(p2);
        setP3(p3);
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    @Override
    public double getPerimetr() {
        double perimetr = Point.getLength(p1, p2) + Point.getLength(p2,p3) + Point.getLength(p3, p1);
        return perimetr;
    }

    @Override
    public double getArea() {
        double semiPerimetr = getPerimetr()/2;
        double area = Math.sqrt( semiPerimetr * (semiPerimetr - Point.getLength(p1, p2)) *
                (semiPerimetr - Point.getLength(p2, p3)) * (semiPerimetr - Point.getLength(p3, p1)));

        return area;
    }
}
