
public class RecursividadEjemplo2Inverso {
	private static int veces = 0;

	public static void main(String[] args) {
		visualizaHastaMil( 1 );	//mandamos un parametro
		visualizaParesDescHasta2( 1000 );
	}

	/**
	 * Visualiza hasta 1000 inverso
	 */
	private static void visualizaHastaMil( int num ) {
		//SENTENCIAS PREVIAS A LA LLAMADA
		if (num < 1000) {
			visualizaHastaMil( num + 1);
		}
		//SENTENCIAS POSTERIORES A LA LLAMADA
		System.out.println(num);
	}
	
	private static void visualizaParesDescHasta2( int num ) {
		if (num == 2) {	//caso base. Ocurre 1 vez.
			System.out.println( 2 );
		} else {	//parte recursiva. Si cambiamos de orden estos elementos se invierte el orden
			System.out.println(num);	
			visualizaParesDescHasta2(num - 2);
		}
	}
}
