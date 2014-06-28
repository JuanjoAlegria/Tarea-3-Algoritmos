import java.util.LinkedList;


public class FibonacciHeap {
	private int n;
	private Node min;
	private LinkedList<Node> roots; // la implementación de LinkedList es una lista doblemente enlazada
	
	private FibonacciHeap(){
		n = 0;
		min = null;
		roots = new LinkedList<Node>();
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Node getMin() {
		return min;
	}

	public void setMin(Node min) {
		this.min = min;
	}

	public LinkedList<Node> getRoots() {
		return roots;
	}

	public void setRoots(LinkedList<Node> roots) {
		this.roots = roots;
	}

	public static FibonacciHeap make(){
		return new FibonacciHeap();
	}
	
	public void insert(Node node){
		roots.add(node);
		if (min == null || node.getKey() < min.getKey())
			min = node;
		n++;		
	}
	
	public void union(FibonacciHeap h){
		roots.addAll(h.getRoots());
		if ((min == null) || (h.getMin() != null && h.getMin().getKey() < min.getKey()))
			min = h.getMin();
		n += h.getN();		
	}
	
	public Node extractMin(){
		Node z = min;
		if (z != null){
			addChildsToRoots(z);
			roots.remove(z);
			if (roots.size() == 0)
				min = null;
			else{
				min = roots.getFirst();
				consolidate();
			}
			n--;				
		}	
		return z;
	}
	
	private void addChildsToRoots(Node n){
		Node child = n.getChild();
		if (child != null){
			Node aux = child.getLeft();
			while (aux != child){ //comparamos punteros
				roots.add(aux);
				aux = aux.getLeft();
			}
			roots.add(child);
		}			
	}

	private void consolidate() {
		// TODO Auto-generated method stub
		
	}
}
