package ejercicioPropuestoFactorial;

public class FactorialResol {

	public static void main(String[] args) {
		factorial(14,1);

	}

	private static void factorial(int objetivo, int valor) {
		if (objetivo == 0) {
			System.out.println( valor );
		}else {
			int siguiente = objetivo * valor;
			factorial( objetivo - 1, siguiente);
		}
	}

}
