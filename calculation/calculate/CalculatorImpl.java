package calculation.calculate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;


@Path("/displayservice")

public class CalculatorImpl {
	public CalculatorImpl() {
		clazz = new Class1();
	}
	
	private  Class1 clazz;
	@Path("/calculator")
	@GET
	//@Produces("application/xml")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response ReturnResult(@Context UriInfo info) throws InterruptedException {
		String as = info.getQueryParameters().getFirst("as");
		String c = info.getQueryParameters().getFirst("c");
		String bs = info.getQueryParameters().getFirst("bs");
		int a = Integer.valueOf(as);
		int b = Integer.valueOf(bs);

		return Response.status(200).entity(returnCalculator(a, c, b)).build();
	}

	public Integer returnCalculator(int a, String c, int b) throws InterruptedException {
		int retVal = 0;	
		
		 if (c.equals("+")) {
			retVal = a + b;
		} else if (c.equals("-")) {
			retVal = a - b;
		} else if (c.equals("*")) {
			retVal = a * b;
		} else if (c.equals("/")) {
			retVal = a / b;
		} else if (c.equals("%")) {
			retVal = a % b;
		} else if (c.equals("^")) {
			retVal = (int) Math.pow(a, b);
		} else if (c.equals("!")) {
			retVal = 1;
			if (a == 0) {
				for (int i = 1; i <= b; i++) {
					retVal = retVal * i;
				}
			} else {
				for (int i = 1; i <= a; i++) {
					retVal = retVal * i;
				}
			}
		}
		//retVal = retVal + clazz.getTmp(); //random süre beklesin ve return 10
		return retVal;

		//http://localhost:8080/CalculatorProject/calculation/displayservice/calculator?as=7&c=-&bs=0
	}
	
	@Path("/coordinate")
	@GET
	//@Produces("application/xml")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCoordinates(@Context UriInfo info) throws InterruptedException {
		String rs = info.getQueryParameters().getFirst("rs");
		String xs = info.getQueryParameters().getFirst("xs");
		String ys = info.getQueryParameters().getFirst("ys");
		
		
		double r = Double.valueOf(rs);
		double x =Double.valueOf(xs);
		double y = Double.valueOf(ys);
		Square s=new Square();
		s.setPoint(x, y);
		s.setRadius(r);
		s.setFirstcorner(r, s.getPoint());
		s.setSecondcorner(r, s.getPoint());
		s.setThirdcorner(r, s.getPoint());
		s.setFourthcorner(r, s.getPoint());
		
		
		GeodeticCalculator geoCalc = new GeodeticCalculator();

		Ellipsoid reference = Ellipsoid.WGS84;  

		GlobalPosition first = new GlobalPosition(s.getFirstCorner().getX() , s.getFirstCorner().getY(), 0.0); 
		GlobalPosition second = new GlobalPosition(s.getSecondCorner().getX(), s.getSecondCorner().getY(), 0.0); 
		GlobalPosition third = new GlobalPosition(s.getThirdCorner().getX(), s.getThirdCorner().getY(), 0.0); 

		GlobalPosition fourth = new GlobalPosition(s.getFourthCorner().getX(), s.getFourthCorner().getY(), 0.0); 

		double edge = (geoCalc.calculateGeodeticCurve(reference,first, second).getEllipsoidalDistance())/1000;
		
		
		String first1=getFormattedLocationInDegree(first.getLatitude(), first.getLongitude());
		String second1=getFormattedLocationInDegree(second.getLatitude(), second.getLongitude());
		String third1=getFormattedLocationInDegree(third.getLatitude(), third.getLongitude());
		String fourth1=getFormattedLocationInDegree(fourth.getLatitude(), fourth.getLongitude());
		
	

		return Response.status(200).entity("  firstcorner: " + first1  +  "\n" + "  secondcorner: " + second1 + "\n" + "  thirdcorner: " + third1 + "\n" + "  fourthcorner: " + fourth1 + "\n" + "  Square's edge: " +edge+"km").build();
		
		//return Response.status(200).entity("  firstcorner: " + s.getFirstCorner().getX() +"," + s.getFirstCorner().getY() + "\n" + "  secondcorner: "+ s.getSecondCorner().getX() +"," + s.getSecondCorner().getY()+ "  thirdcorner: " + s.getThirdCorner().getX() +"," + s.getThirdCorner().getY() +  "\n" + "  fourthcorner: " + s.getFourthCorner().getX() +"," + s.getFourthCorner().getY()+ "\n" + "  Square's edge: " +edge+"km").build();
		
		
	}
	
	public static String getFormattedLocationInDegree(double latitude, double longitude) {
	    try {
	        double latSeconds = (int) Math.round(latitude * 3600);
	        double latDegrees = latSeconds / 3600;
	        latSeconds = Math.abs(latSeconds % 3600);
	       double latMinutes = latSeconds / 60;
	        latSeconds %= 60;

	        double longSeconds = (double) Math.round(longitude * 3600);
	        double longDegrees = longSeconds / 3600;
	        longSeconds = Math.abs(longSeconds % 3600);
	        double longMinutes = longSeconds / 60;
	        longSeconds %= 60;
	        String latDegree = latDegrees >= 0 ? "N" : "S";
	        String lonDegrees = longDegrees >= 0 ? "E" : "W";

	        return  Math.abs(latDegrees) + "°" + latMinutes + "'" + latSeconds
	                + "\"" + latDegree +" "+ Math.abs(longDegrees) + "°" + longMinutes
	                + "'" + longSeconds + "\"" + lonDegrees;
	    } catch (Exception e) {
	        return ""+ String.format("%8.5f", latitude) + "  "
	                 + String.format("%8.5f", longitude) ;
	    }
	}
		
		//http://localhost:8080/CalculatorProject/calculation/displayservice/coordinate?rs=4&xs=2&ys=2
	
		//http://localhost:8080/CalculatorProject/calculation/displayservice/coordinate?rs=40&xs=23.44694444&ys=23.44694444
		
		
	@Path("/control")
	@GET
	//@Produces("application/xml")
	@Produces(MediaType.APPLICATION_JSON)
	public Response controlresult(@Context UriInfo info)  throws InterruptedException {
		
		
		String rs = info.getQueryParameters().getFirst("rs");
		String xs = info.getQueryParameters().getFirst("xs");
		String ys = info.getQueryParameters().getFirst("ys");
		
		String pxs = info.getQueryParameters().getFirst("pxs");
		String pys = info.getQueryParameters().getFirst("pys");
		
		double r = Double.valueOf(rs);
		double x =Double.valueOf(xs);
		double y = Double.valueOf(ys);
		Square s=new Square();
		s.setPoint(x, y);
		s.setRadius(r);
		s.setFirstcorner(r, s.getPoint());
		s.setSecondcorner(r, s.getPoint());
		s.setThirdcorner(r, s.getPoint());
		s.setFourthcorner(r, s.getPoint());
		
		double px = Double.valueOf(pxs);
		double py = Double.valueOf(pys);

		GeodeticCalculator geoCalc = new GeodeticCalculator();
		Ellipsoid reference = Ellipsoid.WGS84;  
		
		GlobalPosition point= new GlobalPosition(px , py, 0.0);
		GlobalPosition center= new GlobalPosition(x , y, 0.0);
		
	
		
		double distance = geoCalc.calculateGeodeticCurve(reference, point, center).getEllipsoidalDistance(); 
		String point1=getFormattedLocationInDegree(point.getLatitude(), point.getLongitude());
		
		if(px>=s.getFirstCorner().getX() && px<=s.getSecondCorner().getX() && py>=s.getFourthCorner().getY() && px<=s.getFirstCorner().getY()) {
			
		return Response.status(200).entity("Point : " +point1 + "\n" + "Point in square's coordinates: "+true + "\n" + "Point distance to the center of circle : " +distance/1000 +"km" ).build();
		}
		else {
			return Response.status(200).entity("Point : " +point1 + "\n" +"Point in square's coordinates: "+false + "\n" + "Distance to point of circle center: " +distance/1000 +"km").build();
		}

		//http://localhost:8080/CalculatorProject/calculation/displayservice/control?rs=4&xs=2&ys=2&pxs=7&pys=3
	
		//http://localhost:8080/CalculatorProject/calculation/displayservice/control?rs=40&xs=23.44694444&ys=23.44694444&pxs=62.44694444&pys=62.44694444
	}
}


	
	
	



