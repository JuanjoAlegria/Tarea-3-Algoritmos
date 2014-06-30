
public class HeapNode<T> {
	private int degree;
	private boolean mark;
	
	private HeapNode<T> parent;
	private HeapNode<T> child;
	private HeapNode<T> left;
	private HeapNode<T> right;
	private T element;
	private double key;
	private boolean inHeap;
	
	public HeapNode(T elem){
		this(elem, Double.POSITIVE_INFINITY);
	}
	
	public HeapNode(T elem, double aKey){
		element = elem;
		parent = child = null;
		left = this;
		right = this;
		mark = false;
		degree = 0;
		key = aKey;
		inHeap = false;
	}

	public HeapNode<T> getParent() {
		return parent;
	}

	public void setParent(HeapNode<T> parent) {
		this.parent = parent;
	}

	public HeapNode<T> getChild() {
		return child;
	}

	public void setChild(HeapNode<T> child) {
		this.child = child;
	}

	public HeapNode<T> getLeft() {
		return left;
	}

	public void setLeft(HeapNode<T> left) {
		this.left = left;
	}

	public HeapNode<T> getRight() {
		return right;
	}

	public void setRight(HeapNode<T> right) {
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
