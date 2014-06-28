
public class Edge implements Comparable<Edge>{
	private Point p1;
	private Point p2;
	private double distance;
	public Edge(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		this.distance = calculateDistance();
	}
	
	private double calculateDistance() {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	//Note: this class has a natural ordering that is inconsistent with equals.
	public int compareTo(Edge anotherEdge) {
		double diff = (this.distance - anotherEdge.getDistance());
		if (diff == 0)
			return 0;
		else if (diff < 0)
			return -1;
		else 
			return 1;		
	}
	
	
	
	
}
