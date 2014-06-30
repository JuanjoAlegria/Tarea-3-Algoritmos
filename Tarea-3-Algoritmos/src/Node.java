
public class Node<T> {
	private int degree;
	private boolean mark;
	
	private Node<T> parent;
	private Node<T> child;
	private Node<T> left;
	private Node<T> right;
	private T element;
	private double key;
	private boolean inHeap;
	
	public Node(T elem){
		this(elem, Double.POSITIVE_INFINITY);
	}
	
	public Node(T elem, double aKey){
		element = elem;
		parent = child = null;
		left = right = this;
		mark = false;
		degree = 0;
		key = aKey;
		inHeap = false;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getChild() {
		return child;
	}

	public void setChild(Node<T> child) {
		this.child = child;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void increaseDegree(){
		this.degree++;
	}
	
	public void decreaseDegree(){
		this.degree--;
	}

	public boolean isMarked() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T elem) {
		this.element = elem;
	}

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}
	
	public boolean isInHeap(){
		return inHeap;
	}

	public void setInHeap(boolean b) {
		inHeap = b;
	}
	
	
}
