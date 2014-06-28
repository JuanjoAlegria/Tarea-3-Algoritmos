import java.util.List;

public class QuickSort {
	public static void sort(List<Edge> list) {
		sort(list, 0, list.size() - 1);
	}

	private static void sort(List<Edge> list, int left, int right) {
		if (left >= right)
			return;
		int pivote = partition(list, left, right);
		sort(list, left, pivote - 1);
		sort(list, pivote + 1, right);
	}

	public static int partition(List<Edge> list, int left, int right) {
		Edge pivotValue = list.get(left);
		int min = left;
		int max = right;
		while (min <= max) {
			if (list.get(min).compareTo(pivotValue) <= 0)
				min++;
			else {
				Edge aux = list.get(max);
				list.set(max, list.get(min));
				list.set(min, aux);
				max--;
			}
		}
		Edge aux = list.get(max); // se hace swap con la mayores, ya que éste
		list.set(max, list.get(left)); // al finalizar el ciclo, guarda la posición del último de los menores
		list.set(left, aux);
		return max;
	}
}
