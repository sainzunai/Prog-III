package utils.logger;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerPlantillaFichero {
	private static PrintStream loggerFichero;	//para fichero
	
	
	private static void hacerAlgo() {
		String str = "que se ha hecho"; //poner mensaje del log, para bd se pone el comando usado
		System.out.println(str);
		loggerFichero.println("Se ha imprimido: " + str + new Date());
	}
	public static void main(String[] args) {
		try {
//			loggerFichero = new PrintStream("logfichero.log");	//de esta manera el log se sobreescribe
			loggerFichero = new PrintStream(new FileOutputStream("logfichero.log", true));	//hacemos esto si queremos que se anyada en el dichero en vez de sobreescribir el log
		} catch (Exception e) {
			e.printStackTrace();
		}
		hacerAlgo();
		loggerFichero.close();//importante cerrar el fichero

	}

}
