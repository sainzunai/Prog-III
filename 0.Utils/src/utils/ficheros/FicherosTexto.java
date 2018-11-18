package utils.ficheros;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FicherosTexto {
	
	private void leerFichero() {
		File fichero = new File("rutafichero.txt");
		Scanner s;
		
		try {
			//lectura del contenido de fuchero
			System.out.println("leyendo contenido");
			s = new Scanner(fichero);
			
			//lectura linea a linea
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				System.out.println(linea);
			}
			
			//cuando acabe de leer el fichero lo cierra
			s.close();
			
		}catch (Exception e) {
			System.out.println("ERROR excepcion: " + e.toString());
		}
	}
	

	private void escribirEnFichero() {
		String[] lineas = {"Uno", "Dos", "Tres", "Cuyatro"};
		FileWriter fichero;
		
		try {
			fichero = new FileWriter("rutaEscritura.txt");
			
			//escritura linea a linea del arraylist
			for (String linea: lineas) {
				fichero.write(linea + "\n");
			}
			//cuando acaba hace close
			fichero.close();
		}catch(Exception e2) {
			System.out.println("ERROR 2 excepcion " + e2.toString());
		}
	}
	
	
	public static void main(String[] args) {
		

	}
}
