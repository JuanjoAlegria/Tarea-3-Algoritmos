

public class Splitaxis {
	
	final static protected String x = "x";
	final static protected String y = "y";
	
	private static Splitaxis splitX;
	private static Splitaxis splitY;
	
	protected String actual;
	
	private Splitaxis(String axis){
		this.actual = axis;
	}
	
	public static Splitaxis getSplitX(){
		if (splitX == null)
			splitX = new Splitaxis(x); 
		return splitX;
	}
	
	public static Splitaxis getSplitY(){
		if (splitY == null)
			splitY = new Splitaxis(y); 
		return splitY;
	}
	
	
	public static Splitaxis changeAxis(Splitaxis axis){
		return (axis.isX()? getSplitY() : getSplitX());
	}

	
	public boolean isX(){
		return actual.equals(x);
	}
	
	public boolean isY(){
		return actual.equals(y);
	}

}
