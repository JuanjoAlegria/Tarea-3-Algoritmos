
public class TriPoints {
	
	private Point p;
	private Point p1;
	private Point p2;
	
	public TriPoints(Point p, Point p1, Point p2) {
		this.p = p;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public TriPoints(Point p) {
		this.p = p;
		this.p1 = null;
		this.p2 = null;
	}
	
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
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
	
	

}
