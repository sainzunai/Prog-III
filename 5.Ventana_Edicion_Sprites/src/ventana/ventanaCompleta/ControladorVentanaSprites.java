package ventana.ventanaCompleta;

/** Clase controladora de la ventana de sprites
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ControladorVentanaSprites {
	
	private VentanaEdicionSprites miVentana;  // Ventana controlada
	
	/** Constructor de controlador de ventana de edici�n de sprites
	 * @param vent	Ventana a controlar
	 */
	public ControladorVentanaSprites( VentanaEdicionSprites vent ) {
		miVentana = vent;
	}
	
	/** Click sobre el bot�n buscar */
	public void clickBBuscar() {
		// TODO Sacar un di�logo de b�squeda de fichero con JFileChooser
		// y cargar la lista de ficheros lSprites a trav�s de su modelo
		// Por ejemplo:
		miVentana.mSprites.clear();
		miVentana.mSprites.addElement( new java.io.File( "c:/datos/ejemplo/ejemplo.png" ) );
		miVentana.mSprites.addElement( new java.io.File( "c:/datos/ejemplo/ejemplo2.png" ) );
		miVentana.lCarpetaSel.setText( "c:/datos/ejemplo/" );
	}
	
	// TODO Resto de controladores
	
	
}
