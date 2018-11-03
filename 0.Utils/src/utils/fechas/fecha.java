package utils.fechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fecha {

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
