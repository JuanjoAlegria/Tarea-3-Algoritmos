import java.util.ArrayList;
import java.util.Arrays;

public class MedianKdTree {

	public static KdTree construirKdTree(Points P, Splitaxis a) {
		return construirKdTree(P, a, 0, P.size() - 1);
	}

	private static KdTree construirKdTree(Points P, Splitaxis a, int minA,
			int maxA) {
		if (minA > maxA)
			return null;
		else if (minA == maxA)
			return new KdTree(P.getPoint(minA));

		else {
			double[] keyArray, dependantArray;
			int medianIndex = (maxA + minA) / 2;
			Point eje;
			if (a.isX()) {
				keyArray = P.getListX();
				dependantArray = P.getListY();
				double medianValue = select2(medianIndex, keyArray,
						dependantArray, minA, maxA);
				eje = new EjeY(medianValue);
			} else {
				keyArray = P.getListY();
				dependantArray = P.getListX();
				double medianValue = select2(medianIndex, keyArray,
						dependantArray, minA, maxA);
				eje = new EjeX(medianValue);
			}

			Splitaxis alterAxis = Splitaxis.changeAxis(a);
			return new KdTree(eje, construirKdTree(P, alterAxis, minA,
					Math.max(medianIndex, minA)), construirKdTree(P,
					alterAxis, Math.min(medianIndex + 1, maxA), maxA));
		}
	}

	public static double select(int k, double[] keyArray, double[] dependantArray) {
		return select2(k, keyArray, dependantArray, 0, keyArray.length - 1);
	}

	public static double select2(int k, double[] keyArray, double[] dependantArray,
			int low, int high) {
		if (high == low)
			return keyArray[low];
		else {
			int pivot = partition2(keyArray, dependantArray, low, high);
			if (k == pivot)
				return keyArray[pivot];
			else if (k < pivot)
				return select2(k, keyArray, dependantArray, low,
						Math.max(low, pivot - 1));
			else
				return select2(k, keyArray, dependantArray,
						Math.min(pivot + 1, high), high);
		}
	}

	public static int partition2(double[] keyArray, double[] dependantArray, int low,
			int high) {
		final int size = high - low + 1;
		int i, j, mark, first, last, pivotIndex;
		double pivotValue;
		if (size <= 5) {
			double[] aux = Arrays.copyOfRange(keyArray, low, high + 1);
			Arrays.sort(aux);
			pivotValue = aux[(high - low) / 2];
		} else {
			final int numMedians = (int) Math.ceil(size / 5d);

			double[] medians = new double[numMedians];

			for (i = 0; i < numMedians; i++) {
				first = low + 5 * i;
				last = Math.min(first + 4, high);
				double[] aux = Arrays.copyOfRange(keyArray, first, last + 1);
				Arrays.sort(aux);
				medians[i] = aux[(last - first + 1) / 2];
			}
			if (numMedians <= 5) {
				Arrays.sort(medians);
				pivotValue = medians[numMedians / 2];
			} else
				pivotValue = select(numMedians / 2, medians, null);
		}

		j = mark = low;
		for (i = low; i <= high; i++) {
			if (keyArray[i] == pivotValue) {
				swap(keyArray, i, j);
				if (dependantArray != null)
					swap(dependantArray, i, j);
				mark = j;
				j++;
			} else if (keyArray[i] < pivotValue) {
				swap(keyArray, i, j);
				if (dependantArray != null)
					swap(dependantArray, i, j);
				j++;
			}
		}

		pivotIndex = j - 1;
		swap(keyArray, mark, pivotIndex);
		if (dependantArray != null)
			swap(dependantArray, mark, pivotIndex);
		return pivotIndex;

	}

	public static void swap(double[] array, int low, int high) {
		double aux = array[low];
		array[low] = array[high];
		array[high] = aux;
	}
}
