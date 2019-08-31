package utils.fechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fecha {

	/**Pattern	Example
dd-MM-yy	31-01-12
dd-MM-yyyy	31-01-2012
MM-dd-yyyy	01-31-2012
yyyy-MM-dd	2012-01-31
yyyy-MM-dd HH:mm:ss	2012-01-31 23:59:59
yyyy-MM-dd HH:mm:ss.SSS	2012-01-31 23:59:59.999
yyyy-MM-dd HH:mm:ss.SSSZ	2012-01-31 23:59:59.999+0100
EEEEE MMMMM yyyy HH:mm:ss.SSSZ	Saturday November 2012 10:45:42.720+0100
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringFechaConHora = "2014-09-15 15:03:23";
		try {
			Date fechaConHora = sdf.parse(stringFechaConHora);
			System.out.println(fechaConHora);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		
	}

}
