package ejercicioPropuestoFactorial;

public class FactorialResol {

	public static void main(String[] args) {
		factorial(14, 1); // podemos hacer un metodo a parte para que solo tengamos que mandar un
							// parametro y que no se vean los patametros funcionales
		factorial2(5);

	}

	private static int factorial2(int i) {
		if (i == 0) {
			return 1;
		} else {
			return i * factorial2(i - 1);
		}
	}

	private static void factorial(int objetivo, int valor) {
		if (objetivo == 0) {
			System.out.println(valor);
		} else {
			int siguiente = objetivo * valor;
			factorial(objetivo - 1, siguiente);
		}
	}

}
