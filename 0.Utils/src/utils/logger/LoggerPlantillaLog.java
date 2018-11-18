package utils.logger;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class LoggerPlantillaLog {
	private static Logger loggerConsola = Logger.getLogger("MiPrimerLogger"); // logger completo
	

	private static void hacerAlgo() {
		String str = "que se ha hecho"; // poner mensaje del log, para bd se pone el comando usado
		System.out.println(str);
		loggerConsola.log( Level.INFO, "Se ha imprimido: " + str );	//nivel INFO
																	//nivel SEVERE
																	//nivel WARNING
	}
	
	private static void error() {
		try {
			//lo que sea que puede generar error
		}catch(Exception e) {
			String mensajeError = "";	//el mensaje de error
			loggerConsola.log(Level.SEVERE, mensajeError , e);	//va a almacenar los datos de error
//			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);	//opcional que salga un joptionpane
		}
	}

	public static void main(String[] args) {
		String fichero = ".xml";	//nombre fichero, en este fichero se guardan los logs importantes
		try {
			FileHandler h = new FileHandler(fichero);
			loggerConsola.addHandler(h);	//guarda la info en el fichero
//			loggerConsola.setUseParentHandlers(false);	//SOLO si queremos que NO utilice la consola
//			loggerConsola.setLevel(Level.FINEST);		//filtra lo que se va a escribir o no en fichero, es decir, puedes poner nivel muy bajo pero que te lo escriba en fichero
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		hacerAlgo();
	}

}
