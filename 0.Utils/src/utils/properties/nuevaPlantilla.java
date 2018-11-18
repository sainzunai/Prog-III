package utils.properties;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class nuevaPlantilla {
	Properties prop = new Properties();
	private void leerPropiedades() {
        try {
            
        	prop.loadFromXML(new FileInputStream(new File("ruta.xml")));
            setSize(Integer.valueOf(prop.getProperty("VentanaAncho")),
                    Integer.valueOf(prop.getProperty("VentanaAlto")));
            setLocation(Integer.valueOf(prop.getProperty("VentanaPosX")),
                    Integer.valueOf(prop.getProperty("VentanaPosY")));
            in.close();
        } catch(Exception e) {
        }
    }

    private void guardarPropiedades() {
        try {
            
            prop.setProperty("VentanaAncho", String.valueOf(getWidth()));
            prop.setProperty("VentanaAlto", String.valueOf(getHeight()));
            prop.setProperty("VentanaPosX", String.valueOf(getX()));
            prop.setProperty("VentanaPosY", String.valueOf(getY()));

        } catch(Exception e) {
        }
    }
    
    public void saveProps() {
		try {
			prop.storeToXML( new PrintStream( "ruta.xml" ), "titullo" );//comentaripo dentro del fichero
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//Double dx = Double.parseDouble(misProps.getProperty("anchoVentana"));
//int dIntX = (int) dx.doubleValue();
//Double dy = Double.parseDouble(misProps.getProperty("altoVentana"));
//int dIntY = (int) dy.doubleValue();
//
//
//Dimension ventanaDimension = new Dimension(dIntX, dIntY);