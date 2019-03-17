package lesson4;

public class Rectangle extends Figure {
    Point p1;
    Point p2;
    Point p3;
    Point p4;

    public Rectangle(Point p1, Point p3) {
        setP1(p1);
        setP3(p3);
        setP2();
        setP4();
    }


    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }


    public Point getP2() {
        return p2;
    }

    private void setP2() {
        p2 = new Point(p3.getX(), p1.getY());
    }

    public Point getP4() {
        return p4;
    }

    private void setP4() {
        p4 = new Point(p1.getX(), p2.getY());
    }

    @Override
    public double getPerimetr() {
        double perimetr = Point.getLength(p1,p2) + Point.getLength(p2, p3)
                + Point.getLength(p3, p4) + Point.getLength(p4, p1);
        return perimetr;
    }

    @Override
    public double getArea() {
        double area = Point.getLength(p1, p2)* Point.getLength(p2,p3);
        return area;
    }
}
