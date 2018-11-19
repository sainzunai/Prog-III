package utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

public class PrpoertiesPlantilla {
	Properties misProps;

	public PrpoertiesPlantilla() { 
		misProps = new Properties();
	}

	private void initProps() {

		try {
			misProps.loadFromXML(new FileInputStream(new File(stringruta)));
		} catch (Exception e) { 
			e.printStackTrace();
			}
		}
	
	/** Devuelve el valor de la propiedad indicada. Funciona como una llamada a getProperty sobre getProps().
	 * @param propName	Nombre de la propiedad que se busca
	 * @return	Valor de la propiedad, null si esta propiedad no existe
	 */
	public String getProp( String propName ) {
		return misProps.getProperty( propName );
	}
	
	public void anyadirProp(String nombrePropiedad, String valorProp) {
		misProps.setProperty(nombrePropiedad, valorProp); //una inea por cada propiedad
	}
	
	
	/** Guarda el fichero de propiedades con los valores que estén actualmente definidos
	 */
	public void saveProps() {
		try {
			misProps.storeToXML( new PrintStream( nomFic ), "Propiedades de AEFicConfiguracion" );//comentaripo dentro del fichero
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
