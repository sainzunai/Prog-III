package fibonacci;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(fibbonaci( 50 ));
	}

	private static int fibbonaci(int i) {
		if(i==1) {
			return 1;
		}
		else if (i == 2){
			return 1;
		} else {
			return fibbonaci(i-1) + fibbonaci(i-2);
		}
	}

}
