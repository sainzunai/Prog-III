package utils.bd.plantilla;

public class BD2 {

	public BD2() {
	}
	
	private static String secu( String string ) {
		// Implementaci�n (1)
		// return string.replaceAll( "'",  "''" ).replaceAll( "\\n", "" );
		// Implementaci�n (2)
		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ�?.,:;-_(){}[]-+*=<>'\"�!&%$@#/\\0123456789".indexOf(c)>=0) ret.append(c);
		}
		return ret.toString();
	}

	public static void main(String[] args) {
		System.out.println(secu("hoola"));
		System.out.println(secu("hoola ��"));


	}

}
