
public class RecursividadEjemplo2 {
	private static int veces = 0;

	public static void main(String[] args) {
		visualizaHastaMil( 1 );	//mandamos un parametro
		visualizaParesAscHasta2(1000);
	}

	/**
	 * Visualiza hasta 1000
	 */
	public static void visualizaHastaMil( int num ) {
		//SENTENCIAS PREVIAS A LA LLAMADA
		System.out.println( num );
		if (num < 1000) {
			visualizaHastaMil( num + 1);
		}
		//SENTENCIAS POSTERIORES A LA LLAMADA
	}
	private static void visualizaParesAscHasta2( int num ) {
		if (num == 2) {	//caso base. Ocurre una vez                                                                                                                                                                                                                                                                    
			System.out.println( 2 );
		} else {	//parte recursiva
			visualizaParesAscHasta2(num - 2);
			System.out.println(num);
			
		}
	}
}
