package calculation.calculate;

public class Square {
	
	

	public Square() {
		point= new Coordinate();
		firstcorner=new Coordinate();
		secondcorner=new Coordinate();
		thirdcorner=new Coordinate();
		fourthcorner=new Coordinate();
	}
	private Coordinate firstcorner;
	private Coordinate secondcorner;
	private Coordinate thirdcorner;
	private Coordinate fourthcorner;
	private Coordinate point;
	private double radius;
	
	public void setPoint(double x,double y) {
		point.setY(y);
		point.setX(x);
	}
	public Coordinate getPoint() {
		return point;
	}
	
	public void setRadius(double r) {
		radius=r;
	}
	public double getRadius() {
		return radius;
	}
	public Coordinate getFirstCorner() {
		return firstcorner;
	}

	public void setFirstcorner(double radius, Coordinate point) {
		double a=point.getX()-radius;
		firstcorner.setX(a);
		double b=point.getY()+radius;
		firstcorner.setY(b);
		
	}
	public Coordinate getSecondCorner() {
		return secondcorner;
	}

	public void setSecondcorner(double radius, Coordinate point) {
		double a=point.getX()+radius;
		secondcorner.setX(a);
		double b=point.getY()+radius;
		secondcorner.setY(b);
	}
	
	public Coordinate getThirdCorner() {
		return thirdcorner;
	}

	public void setThirdcorner(double radius, Coordinate point) {
		double a=point.getX()+radius;
		thirdcorner.setX(a);
		double b=point.getY()-radius;
		thirdcorner.setY(b);
	}
	
	public Coordinate getFourthCorner() {
		return fourthcorner;
	}

	public void setFourthcorner(double radius, Coordinate point) {
		double a=point.getX()-radius;
		fourthcorner.setX(a);
		double b=point.getY()-radius;
		fourthcorner.setY(b);
	}
	

}
