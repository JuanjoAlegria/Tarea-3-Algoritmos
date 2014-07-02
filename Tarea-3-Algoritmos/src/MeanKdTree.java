

public class MeanKdTree {

	public static KdTree construirKdTree(Points P, Splitaxis a){
		return construirKdTree(P, a, 0, P.size() - 1);
	}
	
	private static KdTree construirKdTree(Points P, Splitaxis a, int minA, int maxA) {
		if (minA > maxA)
			return null;		
		else if (minA == maxA) 
			return new KdTree(P.getPoint(minA));
		

		else {
			double[] keyArray, dependantArray;
			double mean;
			Point eje;
			if (a.isX()) {
				keyArray = P.getListX();
				dependantArray = P.getListY();
				mean = mean(keyArray, minA, maxA);
				eje = new EjeY(mean);
			} else {
				keyArray = P.getListY();
				dependantArray = P.getListX();
				mean = mean(keyArray, minA, maxA);
				eje = new EjeX(mean);
			}

			int index = partition(keyArray, dependantArray, minA, maxA, mean);
			if (index > maxA)
				index = maxA;
			Splitaxis alterAxis =  Splitaxis.changeAxis(a);
			return new KdTree(eje, construirKdTree(P, alterAxis, minA, index - 1), construirKdTree(P, alterAxis, index, maxA));
		}
	}

	private static int partition(double[] keyArray, double[] dependantArray,
			int minA, int maxA, double pivotValue) {
		int smaller = minA;
		int greater = maxA;
		while (smaller <= greater) {
			if (keyArray[smaller] <= pivotValue)
				smaller++;
			else {
				// swap keyArray
				double aux = keyArray[greater];
				keyArray[greater] = keyArray[smaller];
				keyArray[smaller] = aux;
				// swap dependantArray
				aux = dependantArray[greater];
				dependantArray[greater] = dependantArray[smaller];
				dependantArray[smaller] = aux;
				greater--;
			}
		}
		// al finalizar el ciclo, la variable smaller indicará la posición
		// del primero de los números más grandes que el pivote
		return smaller; 
	}

	public static double mean(double[] array, int start, int end){
	    for (int i = start; i < end; i+=2){
            if (array[i] < array[i+1]) {
            	double tmp = array[i];
            	array[i] = array[i+1];
            	array[i+1] = tmp;
            }
	    }
	    double max = array[start]; 
	    for (int i = start + 2; i <= end; i+=2){
            if (max < array[i])
                    max = array[i];
	    }
	    double min = array[start + 1];
	    for (int i = start + 1; i <= end; i+=2){
            if (min > array[i])
                    min = array[i];
	    }
	    return (min + max)/2.0;
	}

}
