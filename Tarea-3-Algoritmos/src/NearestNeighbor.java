import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class NearestNeighbor {
	
//	public static ArrayList<Point> execute(ArrayList<Point> points) {
//		Random ran = new Random();
//		int ranInd = ran.nextInt(points.size());
//		ArrayList<Point> result = new ArrayList<Point>();
//		result.add(points.get(ranInd));
//		points.remove(ranInd);
//		while(points.size()>0){
//			ArrayList<Point> minPoints = new ArrayList<Point>();
//			ArrayList<Point >nextTo = new ArrayList<Point>();
//			ArrayList<Double> minDistance = new ArrayList<Double>();
//			for(Point p : points){
//				double min = Double.MAX_VALUE;
//				Point selP = new Point(0,0);
//				for(Point p2 : result){
//					double dist = calculateDistance(p,p2);
//					if(dist < min){
//						min = dist;
//						selP=p2;
//					}
//				}
//				nextTo.add(p);
//				minPoints.add(selP);
//				minDistance.add(min);				
//			}
//			int minIndex = minIndex(minDistance);
//			Point after = nextTo.get(minIndex);
//			Point before = minPoints.get(minIndex);
//			result.add(result.indexOf(before)+1,after);
//			points.remove(after);
//
//		}
//		
//		return result;
//	
//	}
	
	public static ArrayList<Point> execute2(ArrayList<Point> points) {
		Random ran = new Random();
		int ranInd = ran.nextInt(points.size());
		ArrayList<Point> result = new ArrayList<Point>();
		Point first = points.get(ranInd);
		result.add(first);		
		points.remove(ranInd);
		while(points.size()>0){			
			double[] listX = new double[result.size()];
			double[] listY = new double[result.size()];
			for(int i=0; i<result.size();i++){
				listX[i] = result.get(i).getX();
				listY[i] = result.get(i).getY();
			}
			Points P = new Points(listX,listY);
			KdTree Meantree = MedianKdTree.construirKdTree(P, Splitaxis.getSplitX());
			Vecino vecino = new Vecino();
			
			ArrayList<Point> minPoints = new ArrayList<Point>();
			ArrayList<Point >nextTo = new ArrayList<Point>();
			ArrayList<Double> minDistance = new ArrayList<Double>();
			for(Point p : points){
				Point masCercano = vecino.masCercano(Meantree,p);
				double minDist = calculateDistance(p,masCercano);
				nextTo.add(p);
				minPoints.add(masCercano);
				minDistance.add(minDist);
			}
			int minIndex = minIndex(minDistance);
			Point after = nextTo.get(minIndex);
			Point before = minPoints.get(minIndex);
			result.add(result.indexOf(before)+1,after);
			points.remove(after);

		}
		
		return result;
	
	}

	
	private static double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}
	
	private static int minIndex(ArrayList<Double> distances){
		return distances.indexOf (Collections.min(distances));
	}

}
