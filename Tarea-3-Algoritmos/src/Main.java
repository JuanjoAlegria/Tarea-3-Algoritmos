import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		//String[] files = {"wi29","dj38","qa194","uy734","zi929","mu1979","ca4663","ja9847","gr9882","fi10369","it16862","vm22775","sw24978"};
		String[] files = {"gr9882","fi10369","it16862","vm22775","sw24978"};
		String[] countryName = {"Western Sahara","Djibouti","Qatar","Uruguay","Zimbabwe","Oman","Canada","Japan","Greece","Finland","Italy","Vietnam","Sweden"};
		for(String file : files){
		    FileReader inputFile = new FileReader(file + ".tsp");
		    BufferedReader bufferReader = new BufferedReader(inputFile);
		    String line;
		    ArrayList<Point> points = new ArrayList<Point>();
		    while ((line = bufferReader.readLine()) != null)   {
	    		StringTokenizer tokens = new StringTokenizer(line);
	    		String first = tokens.nextToken();
	    		if(isNumber(first)){
		    		double x = Double.parseDouble(tokens.nextToken());
		    		double y = Double.parseDouble(tokens.nextToken());
		    		
		    		points.add(new Point(x,y));
	    		}
		    }
		    bufferReader.close();
		    
		    long time_start;
			long time_stop;
			
			time_start = System.nanoTime();
			ArrayList<Point> res = NearestNeighbor.execute(points);
			time_stop = System.nanoTime();
			System.out.println("Pais: " + file);
			System.out.println("Time [s]: " + (time_stop - time_start) * Math.pow(10, -9));
			double total=0;
			for(Point p : res){
				Point p2;
				if(res.indexOf(p) == res.size()-1){
					p2 = res.get(0);
				}
				else{
					p2 = res.get(res.indexOf(p)+1);
				}
				double d1 = calculateDistance(p,p2);
				total+=d1;
			}
			System.out.println("Distance: " + total);
		}
		
	}
	
	
	private static boolean isNumber(String first) {
		try {
            Integer.parseInt(first);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }


//	public static void main2(String[] args) throws IOException{
//		String file = "ca4663.tsp";
//	    FileReader inputFile = new FileReader(file);
//	    BufferedReader bufferReader = new BufferedReader(inputFile);
//	    String line;
//	    int i=1;
//	    ArrayList<Point> points = new ArrayList<Point>();
//	    while ((line = bufferReader.readLine()) != null)   {
//	    	if(i>7 && i<4671){
//	    		StringTokenizer tokens = new StringTokenizer(line);
//
//	    		int ind = Integer.parseInt(tokens.nextToken());
//	    		double x = Double.parseDouble(tokens.nextToken());
//	    		double y = Double.parseDouble(tokens.nextToken());
//	    		
//	    		points.add(new Point(x,y));
//	    	}
//	    	i++;
//	    }
//	    bufferReader.close();
//	    
//	    long time_start;
//		long time_stop;
//		
//		time_start = System.nanoTime();
//		MST splayTree = MST.create(points);
//		splayTree.preOrderTraversal();
//		double distance = splayTree.calculateCircuitLength();
//		time_stop = System.nanoTime();
//		System.out.println((time_stop - time_start) * Math.pow(10, -9));
//		System.out.println("Distance: " + distance);
//
//		
//	}
//	
	private static double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}

}

