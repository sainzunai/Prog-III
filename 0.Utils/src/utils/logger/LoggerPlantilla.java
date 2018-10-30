package utils.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerPlantilla {
	static String tituloLogger = ""; 	//poner el nombre del logger
	private static Logger logger = Logger.getLogger( tituloLogger );
	
	
	private static void hacerAlgo() {
		String str = "que se ha hecho"; //poner mensaje del log, para bd se pone el comando usado
		logger.log( Level.INFO, "Se ha hecho: " + str );	//nivel INFO
	}
	public static void main(String[] args) {
		hacerAlgo();

	}

}
