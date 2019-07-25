package calculation.calculate;

public class Coordinate {

    private double x;
    private  double y;

    public Coordinate() {
    	 this.x = x;
         this.y = y;
    }
    public Coordinate(double a, double b) {
    	x=a;
        y=b;
    }
    public void setX(double x) {
        this.x=x;
    }

    public void setY(double y) {
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}