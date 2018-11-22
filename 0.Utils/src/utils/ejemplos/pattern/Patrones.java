package utils.ejemplos.pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patrones {
	
	public static void patron() {
	
	// TODO Implementar esto con expresiones regulares y clase Pattern
			Pattern p = Pattern.compile("a*b");
			Matcher m = p.matcher("aaaaab");
			
			System.out.println(m.matches());
			
	}
	public static void main(String[] args) {
		patron();
	}
			

}
