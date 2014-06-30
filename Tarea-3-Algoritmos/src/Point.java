
public class Point {
	protected double x;
	protected double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
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
