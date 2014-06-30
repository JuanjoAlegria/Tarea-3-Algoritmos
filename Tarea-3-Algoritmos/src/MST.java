import java.util.ArrayList;
import java.util.List;


public class MST {
	
	private Instance instance;
	private List<Point> points;
	
	public MST(Instance i){
		instance = i;
		points = i.getPoints();
		
	}
	
	private double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}
	
	public double Prim(){
		FibonacciHeap<Point> heap = new FibonacciHeap<Point>();
		ArrayList<Edge> edges = new ArrayList<>();
		double sum = 0;
		
		double infinite = Double.POSITIVE_INFINITY;
		for (Point p : points){
			Node<Point> node = new Node<>(p, infinite);
			p.setNode(node);
			heap.insert(node);
		}
		Node<Point> first = heap.getMin();
		heap.decreaseKey(first, 0);
		
		while (!heap.isEmpty()){
			Node<Point> nodePoint = heap.extractMin();
			Point current = nodePoint.getElement();
			for (Point p : points){ // todos los puntos son adyacentes el uno al otro
				Node<Point> node = p.getNode();
				edges.add(new Edge(p, p.getMstParent()));
				sum += node.getKey();
				if (node.isInHeap()){
					double distance = calculateDistance(p, current);
					if (distance < node.getKey()){
						p.setMstParent(current);
						node.setKey(distance);
					}
				}				
			}			
		}	
		return sum;
	}	
}
