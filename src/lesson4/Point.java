package lesson4;

public class Point {
    private double x;
    private double y;

    public static double getLength(Point p1, Point p2){
        double length = Math.sqrt(Math.pow(Math.abs(p1.getX() - p2.getX()),2) +Math.pow(Math.abs(p1.getY() - p2.getY()),2));
        return length;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
