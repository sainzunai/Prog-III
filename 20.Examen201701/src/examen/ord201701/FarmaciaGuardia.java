package examen.ord201701;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Clase para representar una farmacia de guardia
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class FarmaciaGuardia implements Serializable {
	private static final long serialVersionUID = 1L;

	private String localidad = "";
	private long horaDesde = -1;
	private long horaHasta = -1;
	private String zona = "";
	private String direccion = "";
	private String telefono = "";
	
	/** Constructor de farmacia de guardia usando cada atributo
	 * @param localidad	Localidad de la farmacia
	 * @param horaDesde	Hora de apertura de la farmacia (milisegundos desde las 00:00 AM)
	 * @param horaHasta	Hora de cierre de la farmacia (milisegundos desde las 00:00 AM)
	 * @param zona	Zona/barrio de la farmacia
	 * @param direccion	Direcci�n de la farmacia
	 * @param telefono	Tel�fono de la farmacia (en formato string)
	 */
	public FarmaciaGuardia(String localidad, long horaDesde, long horaHasta, String zona, String direccion, String telefono) {
		super();
		this.localidad = localidad;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.zona = zona;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	// Atributos utilitarios
	public static SimpleDateFormat sdfHM = new SimpleDateFormat( "HH:mm" );  // para formateo de hora y minutos
	public static long MSGS_DIA = 24L * 3600000L;  // para calcular milisegundos al d�a (independientes de la fecha)
	
	
	/** Constructor de farmacia de guardia con atributos integrados
	 * @param localidad	Localidad de la farmacia
	 * @param horario	Horario en formato "hh:mm - hh:mm" (primero la apertura y despu�s el cierre)
	 * @param zonaDirTelefono	Datos en formato "(zona) direcci�n | tel�fono"
	 */
	public FarmaciaGuardia(String localidad, String horario, String zonaDirTelefono) {
		super();
		this.localidad = localidad;
		int guion = horario.indexOf( "-" );
		if (guion > 0) {
			try {
				Date hora = sdfHM.parse( horario.substring(0, guion).trim() );
				setHoraDesde( hora.getTime() );
				hora = sdfHM.parse( horario.substring(guion+1).trim() );
				setHoraHasta( hora.getTime() );
			} catch (ParseException e) { }
		}
		int parentesis = zonaDirTelefono.indexOf( "(" );
		int parentesis2 = zonaDirTelefono.indexOf( ")" );
		if (parentesis >= 0 && parentesis2 > 0) {
			zona = zonaDirTelefono.substring( parentesis+1, parentesis2 ).trim();
			zonaDirTelefono = zonaDirTelefono.substring( parentesis2+1 );
		}
		int barra = zonaDirTelefono.indexOf( "|" );
		if (barra > 0) {
			telefono = zonaDirTelefono.substring( barra + 1 ).trim();
			if (telefono.toUpperCase().startsWith( "TFNO: " )) telefono = telefono.substring( 6 ).trim();
			zonaDirTelefono = zonaDirTelefono.substring( 0, barra ).trim();
		}
		direccion = zonaDirTelefono;
	}
	
	/** Chequea si los datos de la farmacia son completos
	 * @return	true si la farmacia tiene datos de hora de apertura y cierre, localidad y direcci�n. false en caso contrario
	 */
	public boolean estaCompleta() {
		return horaDesde >= 0 && horaHasta >= 0 && !localidad.isEmpty() && !direccion.isEmpty();
	}
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	/** Consulta la hora de apertura de la farmacia
	 * @return	Hora de apertura en msgs desde las 00:00 AM
	 */
	public long getHoraDesde() {
		return horaDesde;
	}
	/** Consulta la hora de apertura de la farmacia
	 * @return	Hora de apertura en formato string "hh:mm"
	 */
	public String getHoraDesdeSt() {
		return sdfHM.format( new Date(horaDesde) );
	}
	/** Modifica la hora de apertura de la farmacia
	 * @param horaDesde	Msgs transcurridos desde las 00:00 AM (se ignoran los d�as-fecha)
	 */
	public void setHoraDesde(long horaDesde) {
		this.horaDesde = horaDesde % MSGS_DIA;
	}
	
	/** Consulta la hora de cierre de la farmacia
	 * @return	Hora de cierre en msgs desde las 00:00 AM
	 */
	public long getHoraHasta() {
		return horaHasta;
	}
	/** Consulta la hora de cierre de la farmacia
	 * @return	Hora de cierre en formato string "hh:mm"
	 */
	public String getHoraHastaSt() {
		return sdfHM.format( new Date(horaHasta) );
	}
	/** Modifica la hora de cierre de la farmacia
	 * @param horaDesde	Msgs transcurridos desde las 00:00 AM (se ignoran los d�as-fecha)
	 */
	public void setHoraHasta(long horaHasta) {
		this.horaHasta = horaHasta;
	}
	
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/** Indica si la farmacia est� actualmente abierta o va a estar abierta en los siguientes minutos
	 * @param proxMinutos	N�mero de minutos a considerar (positivo o cero)
	 * @return true si est� abierta ahora, o estar� abierta en los minutos indicados. false en caso contrario
	 */
	public boolean estaAbiertaAhora( int proxMinutos ) {
		long horaAhora = System.currentTimeMillis() % MSGS_DIA;
		// Test de horas distintas a la actual desde la aplicaci�n
		horaAhora = (horaAhora + VentanaFarmacias.horaTest*3600000L + VentanaFarmacias.minsTest*60000L) % MSGS_DIA;  // Modificaci�n de hora actual para testeo de la aplicaci�n ( normalmente hraTest y minsTest deber�a ser 0)
		//
		long horaInic = horaDesde % MSGS_DIA;
		long horaFin = horaHasta % MSGS_DIA;
		long minutosMargen = proxMinutos * 60000L;
		boolean ret = (horaAhora >= horaInic && horaAhora <= horaFin);
		if (!ret) {
			if (horaInic < horaFin) {   // Horario que no cambia de d�a (ej: 9 a 22)
				ret = (horaAhora >= horaInic-minutosMargen && horaAhora <= horaFin);
			} else {   // Horario que cambia de d�a  (ej: 22 a 9)
				ret = (horaAhora >= horaInic-minutosMargen || horaAhora <= horaFin);
			}
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return localidad + "  " + sdfHM.format( new Date(horaDesde) ) + "-" + sdfHM.format( new Date(horaHasta)) +
				"  (" + zona + ")  " + direccion + "  " + telefono;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FarmaciaGuardia) {
			FarmaciaGuardia f2 = (FarmaciaGuardia) obj;
			return localidad!=null && localidad.equals(f2.localidad) && horaDesde==f2.horaDesde && horaHasta==f2.horaHasta && direccion!=null && direccion.equals(f2.direccion);
		}
		return false;
	}

	
	// TAREA 6
	// TODO

	
	// TAREA 7
	/** Devuelve la propiedad del tel�fono de la farmacia de ser capic�a. 
	 * Para ello se comparan los d�gitos inicial y final y se devuelve su diferencia, sumada a la 
	 * diferencia de los d�gitos siguiente y anterior, y as� sucesivamente. Cuando un n�mero es capic�a,
	 * su diferencia total ser� cero. Si es cercano al capic�a, ser� un n�mero peque�o. Por ejemplo:
	 * telefono = "94 4139000" --> calcCapicua() grande
	 * telefono = "94 413 14 49" --> calcCapicua() = 0
	 * telefono = "944 132 439" --> calcCapicua() = 2
	 * @return	C�lculo de propiedad de capicua del tel�fono de la farmacia (0 si es perfectamente capic�a)
	 */
	public int calcCapicua() {
		// TAREA 7 - Llamadas a los dos m�todos siguientes
		quitaSimbolos( null, 0); // TODO
		calcCapicua( null, 0, 0); // TODO
		return 0;  // TODO
	}
		// TAREA 7 - Quita los s�mbolos del string dejando solo los d�gitos
		private String quitaSimbolos( String stringOriginal, int carIni ) {
			return null;  // TODO
		}
		// TAREA 7 - Calcula si es capic�a un string de d�gitos (0 = capic�a. Cuanto m�s cerca del capic�a, valor m�s bajo)
		private int calcCapicua( String telefono, int carIni, int carFin ) {
			return 0;  // TODO
		}
	
}

