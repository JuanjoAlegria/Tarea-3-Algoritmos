import java.util.LinkedList;
import java.util.List;


public class MST {
	private TreeNode<Point> root;
	private LinkedList<Point> traversal;
	
	private MST(TreeNode<Point> root){
		this.root = root;
	}

	public void preOrderTraversal(){
		traversal = new LinkedList<>();
		preOrderTraversal(root);
	}
	
	private void preOrderTraversal(TreeNode<Point> node){
		traversal.add(node.getElement());
		List<TreeNode<Point>> children = node.getChildren();
		for (TreeNode<Point> child : children)
			preOrderTraversal(child);
	}
	
	public double calculateCircuitLength(){
		double distance = 0;
		int size = traversal.size();
		for (int i = 0; i < size - 1; i++)
			distance += calculateDistance(traversal.get(i), traversal.get(i+1));
		distance += calculateDistance(traversal.get(0), traversal.get(size - 1));
		return distance;
	}
	
	
	public static MST create(List<Point> points){
		return new MST(prim(points));
	}
	
	
	private static double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}
	
	private static TreeNode<Point> prim(List<Point> points){
		FibonacciHeap<Point> heap = new FibonacciHeap<Point>();
		int i = 0;
		double infinite = Double.POSITIVE_INFINITY;
		for (Point p : points){
			HeapNode<Point> node = new HeapNode<>(p, infinite);
			p.setHeapNode(node);
			heap.insert(node);
		}
		HeapNode<Point> first = heap.getMin();
		heap.decreaseKey(first, 0);
		TreeNode<Point> root = new TreeNode<>();
		root.setElement(first.getElement());
		first.getElement().setTreeNode(root);
		while (!heap.isEmpty()){
			HeapNode<Point> currentHeapNode = heap.extractMin(); //extrae el mínimo
			Point currentPoint = currentHeapNode.getElement();
			System.out.println(i++);
			Point parent = currentPoint.getMstParent();
			if (parent != null){ //esto sólo ocurre en el caso del primer elemento
				TreeNode<Point> parentTreeNode = parent.getTreeNode();
				TreeNode<Point> currentTreeNode = new TreeNode<>(currentPoint);
				currentPoint.setTreeNode(currentTreeNode);
				parentTreeNode.addChild(currentTreeNode);
			}
			
			for (Point p : points){ // todos los puntos son adyacentes el uno al otro
				HeapNode<Point> heapNode = p.getHeapNode();
				if (heapNode.isInHeap()){
					double distance = calculateDistance(p, currentPoint);
					if (distance < heapNode.getKey()){
						p.setMstParent(currentPoint);
						heap.decreaseKey(heapNode, distance);
					}
				}				
			}			
		}
		return root;
	}	
}
