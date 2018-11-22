

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/** Clase de ventana para muestra de datos de centros escolares y feedback de mentoras
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaDatos extends JFrame {
	
	private JTable tDatos;  // JTable de datos de la ventana
	
	/** Crea una nueva ventana
	 */
	public VentanaDatos() {
		// Configuración general
		setTitle( "Ventana de datos" );
		setSize( 800, 600 ); // Tamaño por defecto
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creación de componentes y contenedores
		JPanel pBotonera = new JPanel();
		tDatos = new JTable();
		JButton bCargaFeedback = new JButton( "Carga feedback" );
		JButton bGuardaBD = new JButton( "Guardar en BD" );
		JButton bBuscaMentora = new JButton( "Buscar mentora" );
		// Asignación de componentes
		pBotonera.add( bCargaFeedback );
		pBotonera.add( bGuardaBD );
		pBotonera.add( bBuscaMentora );
		getContentPane().add( new JScrollPane( tDatos ), BorderLayout.CENTER );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		// Eventos
		bCargaFeedback.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickCargaFeedback();
			}
		});
		bGuardaBD.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickGuardaBD();
			}
		});
		bBuscaMentora.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickBuscarMentora();
			}
		});
		// Cierre
		setLocationRelativeTo( null );  // Centra la ventana en el escritorio
	}
	
	/** Asigna una tabla de datos a la JTable principal de la ventana
	 * @param tabla	Tabla de datos a visualizar
	 */
	public void setTabla( Tabla tabla ) {
		tDatos.setModel( tabla.getTableModel() );
	}
	
	// ========================================
	// Eventos

	// Click botón de guardado en Base de Datos
	private void clickGuardaBD() {
		// T3
		Connection con = BD.initBD("BaseDatos_Centros");
		Statement st = BD.usarCrearTablasBD(con);
		
		//recorro el hashmap y saco valores, analizo y segun cumpla o no se envi a a la base de datos
		for (String clave : Datos.centros.keySet()) {
//	        System.out.println("Key = " + clave);
	        CentroEd centro = Datos.centros.get( clave );
	        int sesiones = centro.getContSesiones().get();
	        if (sesiones > 0) {
	        	BD.centroInsert(st, centro);
//	        	BD.sesionInsert(st, clave, sesiones, centro.);
	        }
	    }
		
		
		BD.cerrarBD(con, st);
		
		
	}
	
	// Click botón de búsqueda de mentora
	private void clickBuscarMentora() {
		String aBuscar = JOptionPane.showInputDialog( this, "Introduce email", "Búsqueda de mentora", JOptionPane.QUESTION_MESSAGE );
		if (aBuscar==null || aBuscar.isEmpty()) return;
		ArrayList<MentoraCentro> listaMentorasCentros = new ArrayList<>();
		try {
			Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
			int columnaCentro = mentoras.getColumnWithHeader( "COD", true );
			int columnaEmail = mentoras.getColumnWithHeader( "email", false );
			for (int fila=0; fila<mentoras.size(); fila++) {
				try {
					String codCentro = mentoras.get( fila, columnaCentro );
					String email = mentoras.get( fila,  columnaEmail );
					if (!email.isEmpty() && !codCentro.isEmpty()) {
						MentoraCentro mc = new MentoraCentro( email, codCentro );
						int yaEsta = listaMentorasCentros.indexOf( mc );
						if (yaEsta==-1)
							listaMentorasCentros.add( mc );  // Nueva mentora-centro: meterla en la lista
						else
							listaMentorasCentros.get( yaEsta ).getContSesiones().inc();  // Ya estaba: incrementar contador de sesiones
					}
				} catch (Exception e) {}
			}
			listaMentorasCentros.sort( new Comparator<MentoraCentro>() {  // Ordena la lista por emails y luego por centros
				@Override
				public int compare(MentoraCentro o1, MentoraCentro o2) {
					return (o1.email+o1.codCentro).compareTo( o2.email+o2.codCentro );
				}
			});
			for (MentoraCentro mc : listaMentorasCentros) System.out.println( mc );  // Visualiza las mentoras en consola (a efectos de entender la estructura en el examen)
			// T4 - Búsqueda recursiva
			
			//partimos ya de una lista de mentoras
			String mentABuscar = aBuscar; //lo que sale en el optionpane
			buscarMentora(listaMentorasCentros, 0, mentABuscar);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
		
		// T4 - Método(s) para recursividad
	private void buscarMentora(ArrayList<MentoraCentro> mc, int num, String mentABusc) {
		
		if(mc.get(num).getEmail() == mentABusc) {//CASO BASE, QUE EN ESA POSICION ESTE LA MENTROA
			JOptionPane.showMessageDialog(null, "Encontrado" + mc.get(num).getEmail());
		}else {
			buscarMentora(mc, num + 1, mentABusc);	//habria que anadir que no se salga de la lista
		}
	
}

	
		/** Clase interna de gestión de mentora-centro con contador de sesiones de esa mentora en ese centro */
		public static class MentoraCentro {
			public String email;
			public String codCentro;
			public Contador contSesiones;
			/** Crea un nuevo objeto mentora-centro con contador de sesiones a uno
			 * @param email	Email de la mentora
			 * @param codCentro	Código del centro
			 */
			public MentoraCentro(String email, String codCentro) {
				super();
				this.email = email;
				this.codCentro = codCentro;
				this.contSesiones = new Contador(1);
			}
			public Contador getContSesiones() { return contSesiones; }
			public String getEmail() { return email; }
			public String getCodCentro() { return codCentro; }
			@Override
			public boolean equals(Object obj) {
				if (!(obj instanceof MentoraCentro)) return false;
				MentoraCentro mc = (MentoraCentro) obj;
				return mc.codCentro.equals(codCentro) && mc.email.equals(email);
			}
			@Override
			public String toString() {
				return "{" + email + "-" + codCentro + " : " + contSesiones + "}";
			}
		}
	

	// Click botón de carga de feedback
	private void clickCargaFeedback() {
		// Cálculo de datos en función del seguimiento del mentoring
		seguimientoSesiones();
	}
	
	private static void seguimientoSesiones() {
		try {
			Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
			int columnaSesion = mentoras.getColumnWithHeader( "Número de sesión", false );
			int columnaCentro = mentoras.getColumnWithHeader( "COD", true );
			int columnaNumEsts = mentoras.getColumnWithHeader( "Nº de chicas/os", false );
			int columnaSatMent = mentoras.getColumnWithHeader( "Tu nivel de satisf", false );
			int columnaSatEst = mentoras.getColumnWithHeader( "satisfacción de los chicas/os", false );
			for (int fila=0; fila<mentoras.size(); fila++) {
				try {
					int numSesion = Integer.parseInt( mentoras.get( fila, columnaSesion ) );
					String codCentro = mentoras.get( fila, columnaCentro );
					int numEstuds = Integer.parseInt( mentoras.get( fila, columnaNumEsts ) );
					int satMentora = Integer.parseInt( mentoras.get( fila, columnaSatMent ) );
					int satEstuds = Integer.parseInt( mentoras.get( fila, columnaSatEst ) );
					CentroEd centro = Datos.centros.get( codCentro );
					if (centro!=null) {
						centro.getContSesiones().inc();    // Incrementa el contador de sesiones
						centro.addValMentor( satMentora ); // Añade satisfacción de mentora
						centro.addValEstud( satEstuds );   // Añade satisfacción de estudiantes
						centro.getEstudPorSesion()[ numSesion-1 ] += numEstuds;  // Añade número de estudiantes en la sesión correspondiente
					} else {
						// T2
//						ArrayList<String> hola = new ArrayList<>();
//						hola.add("");
//						procesaErrorLinea(, mentoras.getFila(fila));
						System.err.println( "Código de centro incorrecto en línea de seguimiento: " + mentoras.getFila( fila ) );
					}
				} catch (Exception e) {
					// T2
					System.err.println( "Error en línea de seguimiento: " + mentoras.getFila( fila ) );
				}
			}
			Tabla c = Tabla.createTablaFromColl( Datos.centros.values() );
			Datos.v.setTabla( c );
		} catch (Exception e) { e.printStackTrace(); }
	}
	
		// T2 - Inicialización de logger		comento porque da error algo de ; de forma que pueda seguir
//		String fichero = "errores-sesiones.xml";
//		private static Logger loggerVentana = Logger.getLogger("LoggerErroresEnVentana");
//		try {
//			FileHandler h = new FileHandler(fichero);
//			loggerVentana.addHandler(h);	//guarda la info en el fichero
//			loggerVentana.setUseParentHandlers(false);	//queremos que NO utilice la consola (pedido por el enunciado, interpreto), si comentamos la linea SI saldria en consola
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		private static void procesaErrorLinea( Consumer<ArrayList<String>> proceso, ArrayList<String> linea ) {
			// T2
//			if(proceso == syso){
//			System.out.println("Código de centro incorrecto en línea de seguimiento: " + linea.toString());
//			o sino se puede hacer con log(level.info)
//			} else{
//			loggerVentana.log(Level.WARNING	, linea.get(0));
//			}
			
			
		}
	
}
