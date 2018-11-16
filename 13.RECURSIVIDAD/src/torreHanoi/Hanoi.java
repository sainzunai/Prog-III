package torreHanoi;

public class Hanoi {

	public static void main(String[] args) {
		hanoi(3, 'a', 'b', 'c');
	}

	private static void hanoi(int n, char origen, char destino, char auxiliar) {
		if (n == 1) {
			System.out.println("Mover 1 de " + origen + " a " + destino);
		}else {
			hanoi(n-1, origen, auxiliar, destino);
			System.out.println("Mover " + n + " de " + origen + " a " + destino);
			hanoi(n-1, auxiliar, destino, origen);
		}
		
	}
}
