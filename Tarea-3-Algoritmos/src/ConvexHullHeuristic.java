import java.util.ArrayList;


public class ConvexHullHeuristic{
	
	public static ArrayList<Point> execute(ArrayList<Point> points) {
		ArrayList<Point> convexHull = ConvexHull.execute(points);
		ArrayList<Point> notInCH = new ArrayList<Point>();
		ArrayList<Points> noInCH = new ArrayList<Points>();
		
		for(Point p : points){
			if(convexHull.contains(p)){continue;}
			notInCH.add(p);
		}
		
		while(notInCH.size()>0){
			for(Point p : notInCH){
				Points auxPnts = new Points(p);
				double min=Double.MAX_VALUE;
				for(Point p1 : convexHull){
					Point p2;
					if(convexHull.indexOf(p1) == convexHull.size()-1){
						p2 = convexHull.get(0);
					}
					else{
						p2 = convexHull.get(convexHull.indexOf(p1)+1);
					}
					double d1 = calculateDistance(p1,p);
					double d2 = calculateDistance(p2,p);
					double d3 = calculateDistance(p1,p2);
					double d = (d1 + d2) - d3;
					if(d<min){
						min = d;
						auxPnts.setP1(p1);
						auxPnts.setP2(p2);
					}
				}
				noInCH.add(auxPnts);
			}
			
			Points result = new Points(null, null, null);
			
			for(Points pts : noInCH){
				double min2=Double.MAX_VALUE;
				double dd1 = calculateDistance(pts.getP(),pts.getP1());
				double dd2 = calculateDistance(pts.getP(),pts.getP2());
				double dd3 = calculateDistance(pts.getP1(),pts.getP2());
				double dd = (dd1 + dd2) / dd3;
				if(dd < min2){
					min2 = dd;
					result.setP(pts.getP());
					result.setP1(pts.getP1());
					result.setP2(pts.getP2());
				}
			}
			
			convexHull.add(convexHull.indexOf(result.getP2()),result.getP());
			notInCH.remove(notInCH.indexOf(result.getP()));
		}
		
		return convexHull;
		
	}
	
	private static double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}
}
