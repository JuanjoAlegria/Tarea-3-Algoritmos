import java.util.ArrayList;
import java.util.List;


public class FibonacciHeap<T>{
	private int size;
	private Node<T> min;
	
	public FibonacciHeap(){
		size = 0;
		min = null;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int n) {
		this.size = n;
	}

	public Node<T> getMin() {
		return min;
	}

	public void setMin(Node<T> min) {
		this.min = min;
	}

	public static <T> FibonacciHeap<T> merge(FibonacciHeap<T> one, FibonacciHeap<T> two){
		FibonacciHeap<T> result = new FibonacciHeap<T>();
		result.setMin(mergeLists(one.getMin(), two.getMin()));
		result.setSize(one.getSize() + two.getSize());
		
		one.setSize(0);
		two.setSize(0);
		one.setMin(null);
		two.setMin(null);
		
		return result;
	}
	
	public void insert(Node<T> node){
		this.min = (mergeLists(min, node));
		node.setInHeap(true);
		size++;
	}
	
	public void insert(T elem, double key){
		Node<T> node = new Node<T>(elem, key);
		insert(node);
	}
	
	private static <T> Node<T> mergeLists(Node<T> one, Node<T> two){
		if (one == null && two == null)
			return null;
		else if (one != null && two == null)
			return one;
		else if (one == null && two != null)
			return two;
		else{
			Node<T> oneRight = one.getRight();
			one.setRight(two.getRight());
			one.getRight().setLeft(one);
			two.setRight(oneRight);
			two.getRight().setLeft(two);
			
			return one.getKey() < two.getKey()? one: two;
		}
	}
	
	public boolean isEmpty(){
		return min == null;
	}

	public Node<T> extractMin(){
		if (isEmpty())
			return null;
		
		size--;
		Node<T> resultMin = min;
		
		if (min.getRight() == min)
			return min;
		else{
			min.getLeft().setRight(min.getRight());
			min.getRight().setLeft(min.getLeft());
			min = min.getRight();
		}
		
		if (resultMin.getChild() != null){
			Node<T> current = min.getChild();
			do {
				current.setParent(null);
				current = current.getRight();
			} while (current != resultMin.getChild());
		}
		
		min = mergeLists(min, resultMin.getChild());
		
		if (min == null)
			return resultMin;
		
		List<Node<T>> treeTable = new ArrayList<Node<T>>();
		
		List<Node<T>> toVisit = new ArrayList<Node<T>>();
		
		for (Node<T> current = min; toVisit.isEmpty() || toVisit.get(0) != current; current = current.getRight())
			toVisit.add(current);
		
		for (Node<T> current : toVisit){
			while (true){
				int currentDegree = current.getDegree();
				while (currentDegree >= treeTable.size())
					treeTable.add(null);
				
				if (treeTable.get(currentDegree) == null){
					treeTable.set(currentDegree, current);
					break;
				}
				
				Node<T> other = treeTable.get(currentDegree);
				treeTable.set(currentDegree, null);
				
				Node<T> min = (current.getKey() < other.getKey())? current : other;
				Node<T> max = (current.getKey() < other.getKey())? other : current;
				
				max.getRight().setLeft(max.getLeft());
				max.getLeft().setRight(max.getRight());
				
				max.setLeft(null);
				max.setRight(null);
				
				min.setChild(mergeLists(max, min.getChild()));
				
				max.setParent(min);
				max.setMark(false);
				
				min.increaseDegree();
				
				current = min;
			}
			if (current.getKey() <= this.min.getKey())
				this.min = current;
		}
		resultMin.setInHeap(false);
		return resultMin;		
	}

	public void decreaseKey(Node<T> node, double newKey){
		if (node.getKey() < newKey)
			return; // no se hace nada
		decreaseKeyUnchecked(node, newKey);
	}

	private void decreaseKeyUnchecked(Node<T> node, double newKey) {
		node.setKey(newKey);
		Node<T> parent = node.getParent();
		if (parent != null && node.getKey() <= parent.getKey()){
			cutNode(node, parent);
			cascadingCut(parent);
		}
		
		if (node.getKey() < min.getKey())
			min = node;		
	}
	
	private void cutNode(Node<T> node, Node<T> parent){
		// sacamos node de la lista de hijos de parent;
		if (node.getRight() != node){
			node.getRight().setLeft(node.getLeft());
			node.getLeft().setRight(node.getRight());
		}
		node.setLeft(null);
		node.setRight(null);
		parent.decreaseDegree();
		
		// añadimos node a la lista de raíces del fib heap
		min = mergeLists(node, min); 
		
		// el padre ahora es null y se le quita la marca a node
		node.setParent(null);
				
		node.setMark(false);
	}
	
	private void cascadingCut(Node<T> node){
		Node<T> parent = node.getParent();
		if (parent != null){
			if (!node.isMarked())
				node.setMark(true);
			else{
				cutNode(node, parent);
				cascadingCut(parent);
			}
		}
	}
	
	public void delete(Node<T> node){
		decreaseKey(node, Double.NEGATIVE_INFINITY);
		extractMin();
	}
}
