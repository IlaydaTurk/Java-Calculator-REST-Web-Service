package calculation.calculate;

import org.gavaghan.geodesy.*;

public class FindinGlobalPosition {

	public static void GlobalPosition() {
	
	Square s=new Square();
	
	GeodeticCalculator geoCalc = new GeodeticCalculator();

	Ellipsoid reference = Ellipsoid.WGS84;  

	GlobalPosition pointA = new GlobalPosition(s.getFirstCorner().getX(), s.getFirstCorner().getY(), 0.0); 

	GlobalPosition userPos = new GlobalPosition(s.getFourthCorner().getX(), s.getFourthCorner().getY(), 0.0); 

	double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); 
	System.out.println(pointA );
	System.out.println(userPos );
	System.out.println(distance);
}
	
	
	public static void main(String[] args) {
		GlobalPosition();
		
	}
}