import java.util.ArrayList;
import java.util.List;


public class TreeNode<T> {
	private List<TreeNode<T>> children;
	private T element;
	
	public TreeNode(){
		this(null);
	}
	
	public TreeNode(T anElement){
		element = anElement;
		children = new ArrayList<TreeNode<T>>();
	}
	
	public void addChild(TreeNode<T> child){
		children.add(child);
	}
	public List<TreeNode<T>> getChildren(){
		return children;
	}
	public T getElement(){
		return element;
	}
	
	public void setElement(T element){
		this.element = element;
	}
}
