import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class MiLogger {
	private static Logger loggerConsola;	//para consola
	
	
	private static PrintStream loggerFichero;//para fichero
	
	
	
	
	private static String str = "fruta";
	
		public static void imprimir(String string) {
			System.out.println(string);
			loggerConsola.log( Level.INFO, "Se ha imprimido: " + str );
			loggerConsola.log(Level.FINER, "se ha registrado el log anterior");	//tiene poca prioridad y no se registra en el fichero. FINER no se registra, solo se produce
			loggerFichero.println("Se ha imprimido: " + str + new Date());
		}
		
		public static void probarExcepcion() {
			try {
				System.out.println("no mas fruta");
				BufferedReader ent = new BufferedReader(new FileReader("jaja.txt"));	//genero error para probar
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println("error: " + e);
				loggerConsola.log(Level.SEVERE, "error a la hora de abrir fichero", e);	//mandamos la excepcion a guardar
			}
		}
	
	public static void main(String[] args) {
		try {
//			loggerFichero = new PrintStream("logfichero.log");	//de esta manera el log se sobreescribe
			loggerFichero = new PrintStream(new FileOutputStream("logfichero.log", true));	//hacemos esto si queremos que se anyada en el dichero en vez de sobreescribir el log
		
			loggerConsola = Logger.getLogger( "MiPrimerLogger" );	//para consola con opciones
			FileHandler h = new FileHandler("logficheroLog.xml");
			loggerConsola.addHandler(h);	//nos guarda mucha mas infomacion
//			loggerConsola.setUseParentHandlers(false);//para que no utilice la consola pero si el .xml
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MiLogger.imprimir(str);
		probarExcepcion();
		loggerFichero.close();//cerramos el fichero
	}

}
