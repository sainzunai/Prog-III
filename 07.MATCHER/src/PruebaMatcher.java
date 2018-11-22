import java.util.regex.Pattern;

public class PruebaMatcher {


	
	public static boolean comprobarFecha( String fecha ) {
		Pattern patFecha = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d");
		return patFecha.matcher(fecha).matches();
	}
	
	
	public static void main(String[] args) {
		
				System.out.println(comprobarFecha("hola"));
	}

}
