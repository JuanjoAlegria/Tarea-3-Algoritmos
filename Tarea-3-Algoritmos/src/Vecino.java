

public class Vecino {
	private Point mejorActual;
	private double distanciaActual;

	public Vecino() {
		mejorActual = new Point(0, 0);
		distanciaActual = Double.MAX_VALUE;
	}

	private double distancia(Point p, Point q) {
		return Math.sqrt(Math.pow((q.getX() - p.getX()), 2)
				+ Math.pow((q.getY() - p.getY()), 2));
	}
	
	public Point masCercano(KdTree T, Point q){
		buscarMasCercano(T, q);
		return mejorActual;
	}

	private void buscarMasCercano(KdTree T, Point q) {
		if (T.isLine()) {
			KdTree nodoCercano, nodoDistante;
			double distanciaAlEje;
			if (T.getPoint().getX() == 0) { // si es que estamos en una línea paralela al eje x
				distanciaAlEje = Math.abs((T.getPoint().getY() - q.getY()));
				if (q.getY() < T.getPoint().getY()) {
					nodoCercano = T.izq;
					nodoDistante = T.der;

				} else {
					nodoCercano = T.der;
					nodoDistante = T.izq;
				}

			} else { // estamos en una línea paralela al eje y
				distanciaAlEje = Math.abs((T.getPoint().getX() - q.getX()));
				if (q.getX() < T.getPoint().getX()) {
					nodoCercano = T.izq;
					nodoDistante = T.der;
				} else {
					nodoCercano = T.der;
					nodoDistante = T.izq;
				}
			}
			if (nodoCercano!= null)
				buscarMasCercano(nodoCercano, q); // cuando esta recursión termina, se habrá encontrado el punto que está en la misma región que Q
			if (distanciaAlEje < this.distanciaActual && nodoDistante != null)
				buscarMasCercano(nodoDistante, q);
		} else { // si es que es un punto
			double dist = distancia(T.getPoint(), q);
			if (dist < distanciaActual) {
				mejorActual = T.getPoint();
				distanciaActual = dist;
			}
		}

	}
}
