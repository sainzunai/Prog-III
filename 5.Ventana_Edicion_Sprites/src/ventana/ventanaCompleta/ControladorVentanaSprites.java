package ventana.ventanaCompleta;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * Clase controladora de la ventana de sprites
 * 
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ControladorVentanaSprites {

	private VentanaEdicionSprites miVentana; // Ventana controlada

	/**
	 * Constructor de controlador de ventana de edición de sprites
	 * 
	 * @param vent
	 *            Ventana a controlar
	 */
	public ControladorVentanaSprites(VentanaEdicionSprites vent) {
		miVentana = vent;
	}

	/** Click sobre el botón buscar */
	public void clickBBuscar() {

		// Creamos el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // para que el usuario solo pueda seleccionar una
																// crapeta

		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		int seleccion = fc.showOpenDialog(miVentana); // el cuadro de dialogo depende de la ventana

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			// Seleccionamos el fichero
			File fichero = fc.getSelectedFile();
			miVentana.mSprites.clear();
			for (File f : fichero.listFiles()) {
				if (f.getName().toLowerCase().endsWith("png")) {
					miVentana.mSprites.addElement(f);
				}
			}
		}
	}

	public void scrollAngulo() {
		float valor = miVentana.slAngulo.getValue();
		miVentana.tfAngulo.setText(String.valueOf(valor));
	}
	
	public void dobleClickSprites() {
		miVentana.mSecuencia.addElement(miVentana.lSprites.getSelectedValue());
	}
	
	public void dobleClickSecuencia() {
		miVentana.lFoto.setImagen(miVentana.lSecuencia.getSelectedValue().toString());
	}
}
