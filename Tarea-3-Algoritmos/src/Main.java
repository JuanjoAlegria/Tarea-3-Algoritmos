import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {

		int salida = 0;
		String resAl = "";
		String resExp = "";
		String sal = "";
		while (salida != 1) {
			System.out.print("Ingrese el Algoritmo para el que desea correr el experimento. o (S) para Salir\n");

			while (!resAl.equals("M") && !resAl.equals("N") && !resAl.equals("C") && !resAl.equals("S")) {
				System.out.print("MST (M), Nearest Neighbor (N) o Convex Hull (C)\n");
				resAl = Leer().trim();
			}
			if (resAl.equals("S"))
				break;
			else{
				String[] files = {"wi29","dj38","qa194","uy734","zi929","mu1979","ca4663","ja9847","gr9882","fi10639","it16862","vm22775","sw24978"};
				String[] countryName = {"Western Sahara","Djibouti","Qatar","Uruguay","Zimbabwe","Oman","Canada","Japan","Greece","Finland","Italy","Vietnam","Sweden"};		
				double[] optimal = {27603,6656,9352,79114,95345,86891,1290319,491924,300899,520527,557315,569288,855597};
				int i=0;
				double[] distances = new double[files.length];
				double[] times = new double[files.length];
				for(String file : files){
				    FileReader inputFile = new FileReader(file + ".tsp");
				    BufferedReader bufferReader = new BufferedReader(inputFile);
				    String line;
				    ArrayList<Point> points = new ArrayList<Point>();
				    while ((line = bufferReader.readLine()) != null)   {
			    		StringTokenizer tokens = new StringTokenizer(line);
			    		String first = tokens.nextToken();
			    		if(isNumber(first)){
				    		double x = Double.parseDouble(tokens.nextToken());
				    		double y = Double.parseDouble(tokens.nextToken());
				    		
				    		points.add(new Point(x,y));
			    		}
				    }
				    bufferReader.close();
				    
				    long time_start;
					long time_stop;
					System.out.println("Experiment for Pais: " + countryName[i]);
					time_start = System.nanoTime();
					if (resAl.equals("M")){
						time_start = System.nanoTime();
						MST splayTree = MST.create(points);
						splayTree.preOrderTraversal();
						time_stop = System.nanoTime();
						double totalTime = (time_stop - time_start) * Math.pow(10, -9);
						double totalDistance=splayTree.calculateCircuitLength();
						times[i] = totalTime;
						distances[i] = totalDistance;
					}
					else if (resAl.equals("N")){
						time_start = System.nanoTime();
						ArrayList<Point> res = NearestNeighbor.execute2(points);
						time_stop = System.nanoTime();
						double totalTime = (time_stop - time_start) * Math.pow(10, -9);
						double totalDistance=0;
						for(Point p : res){
							Point p2;
							if(res.indexOf(p) == res.size()-1){
								p2 = res.get(0);
							}
							else{
								p2 = res.get(res.indexOf(p)+1);
							}
							double distance = calculateDistance(p,p2);
							totalDistance+=distance;
						}
						times[i] = totalTime;
						distances[i] = totalDistance;
					}
					else if (resAl.equals("C")){
						time_start = System.nanoTime();
						ArrayList<Point> res = ConvexHullHeuristic.execute(points);
						time_stop = System.nanoTime();
						double totalTime = (time_stop - time_start) * Math.pow(10, -9);
						double totalDistance=0;
						for(Point p : res){
							Point p2;
							if(res.indexOf(p) == res.size()-1){
								p2 = res.get(0);
							}
							else{
								p2 = res.get(res.indexOf(p)+1);
							}
							double distance = calculateDistance(p,p2);
							totalDistance+=distance;
						}		
						times[i] = totalTime;
						distances[i] = totalDistance;
					}
					else{
						break;
					}
					i++;
				}
				saveResults(resAl,countryName,times,distances,optimal);
			}

			while (!sal.equals("si") && !sal.equals("no")) {
				System.out.println("Desea realizar otra operacion? si/no: ");
				sal = Leer().trim();
			}
			if (sal.equals("no")) {
				salida = 1;
			}
			if (sal.equals("si")) {
				resAl = resExp = sal = "";
			}
		}

	}

	public static String Leer() throws IOException { // Funcion para leer archivos
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(isr);
		return teclado.readLine();
	}
	
	private static boolean isNumber(String first) {
		try {
            Integer.parseInt(first);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }

	private static double calculateDistance(Point p1, Point p2) {
		double squareDistance = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
		return Math.sqrt(squareDistance);
	}
	
	private static void saveResults(String algorithm, String[] pais, double[] time, double[] distance, double[] optimal) throws IOException {
		String algo = "";
		if(algorithm.equals("M")){
			algo = "MST";
		}
		else if(algorithm.equals("N")){
			algo = "NearestNeighbor";
		}
		else{
			algo = "ConvexHull";
		}
		String filename = algo + "Experiment.txt";
		try {
			FileWriter file = new FileWriter(filename);
			PrintWriter prt = new PrintWriter(file);
			prt.write("Resultados del experimento con " + algo + "\r\n");

			for (int i = 0; i < pais.length; i++) {
				
				prt.write("Pais: " + pais[i] +  "\r\n");
				prt.write("Tiempo [s]: " + time[i] + "\r\n");
				prt.write("Distance: " + distance[i] + "\r\n");
				prt.write("Ratio: " + (distance[i]/optimal[i]) + "\r\n");
				prt.write("\r\n");
				
			}
			prt.close();
		} catch (IOException e) {
			System.out.println("Hubo un error");
		}
	}

}

