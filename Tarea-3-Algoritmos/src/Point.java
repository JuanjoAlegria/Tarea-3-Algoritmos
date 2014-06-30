
public class Point {
	private double x;
	private double y;
	private Point mstParent; // estos dos campos se utilizan en la aproximación MST
	private boolean inFibHeap;
	private Node<Point> node; // referencia al nodo que lo contiene;

	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
		mstParent = null;
		inFibHeap = true;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

	public Point getMstParent() {
		return mstParent;
	}

	public void setMstParent(Point mstParent) {
		this.mstParent = mstParent;
	}
	
	public Node<Point> getNode(){
		return node;
	}
	
	public void setNode(Node<Point> node){
		this.node = node;
	}
	
	public int compareTo(Point o2) {
		if(o2.x == this.x){
			return 0;
		}
		else if(o2.x > this.x){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	public boolean equals(Point p2){
		if(p2.x == this.x && p2.y == this.y){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "x: " + this.x + " y: " + this.y;
	}
}
