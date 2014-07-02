


public class Point {
	protected double x;
	protected double y;
	private Point mstParent; // estos dos campos se utilizan en la aproximación MST
	private HeapNode<Point> heapNode; // referencia al nodo que lo contiene;
	private TreeNode<Point> treeNode;
	protected boolean isLine;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
		mstParent = null;
		this.isLine = false;
	}
	
	public Point (double x, double y, boolean isLine){
		this.x = x;
		this.y = y;
		this.isLine = isLine;	
	}
	
	public void moverHorizontal(double distance){
		x = x + distance;
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

	public HeapNode<Point> getHeapNode(){
		return heapNode;
	}

	public void setHeapNode(HeapNode<Point> node){
		this.heapNode = node;
	}
	public TreeNode<Point> getTreeNode(){
		return treeNode;
	}

	public void setTreeNode(TreeNode<Point> node){
		this.treeNode = node;
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

	public boolean isLine(){
		return isLine;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLine ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (isLine != other.isLine)
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}

