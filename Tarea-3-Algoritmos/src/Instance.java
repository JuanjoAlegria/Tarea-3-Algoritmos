import java.util.ArrayList;
import java.util.List;


public class Instance {
	private List<Point> points;
	private List<Edge> edges;
	
	public Instance(List<Point> points){
		this.points = points;
		buildEdges();
		sortEdges();
	}
	
	public  List<Point> getPoints(){
		return points;
	}
	
	private void buildEdges(){
		int n = points.size();
		edges = new ArrayList<Edge>(n*(n-1)/2);
		for (Point p1: this.points){
			for (Point p2: this.points){
				edges.add(new Edge(p1, p2));
			}
		}			
	}
	
	private void sortEdges(){
		QuickSort.sort(edges);		
	}
	
	
}
