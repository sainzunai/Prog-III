package ejercicioPropuestoFactorial;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		hacerFactorial(5, 0, 1);

	}

	/**
	 * FACTORIAL ORDENADO
	 * 
	 * @param numBase
	 *            Numero al que se le quiere hacer factorial
	 * @param seguida
	 *            ronda en la que va contando, poner 0 para empezar
	 * @param respuesta
	 *            respuesta sucesiva, poner 1 para empezar
	 */
	private static void hacerFactorial(int numBase, int seguida, int respuesta) {
		if (numBase == seguida) { // caso base
			System.out.println(numBase + "! = " + respuesta);
		} else { // recursividad
			seguida++;
			System.out.println(respuesta + "*" + seguida);
			respuesta = respuesta * seguida;
			hacerFactorial(numBase, seguida, respuesta);

		}

	}

}
