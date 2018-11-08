
public class RecursividadEjemplo {
	private static int veces = 0;

	public static void main(String[] args) {
		fvisualizaHastaMil();
	}

	public static void fvisualizaHastaMil() {
		veces++;
		System.out.println(veces);
		if (veces < 1000) {
			fvisualizaHastaMil();
		}
	}
}
