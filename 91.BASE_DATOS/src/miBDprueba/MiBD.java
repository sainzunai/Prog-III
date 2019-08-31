package miBDprueba;

import java.sql.*;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MiBD {
	private static final String NOMBRETABLA = "Usuario";
	private static final String COLUMNAS_TABLA = " (nombre string primary key not null, contrasenya string)";
	
	/** Inicializa una BD SQLITE y devuelve una conexion con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexion con la base de datos indicada. Si hay algun error, se devuelve null
	 */
	
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			log( Level.SEVERE, "Error en conexion de base de datos " + nombreBD, e );
			e.printStackTrace();
			return null;
		}
	}
	
	/** Devuelve statement para usar la base de datos
	 * @param con	Conexion ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	
	public static Statement usarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en uso de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	
	/** Crea las tablas de la base de datos. Si ya existen, las deja tal cual
	 * @param con	Conexion ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			try {
				statement.executeUpdate("create table "+NOMBRETABLA+COLUMNAS_TABLA);
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			log( Level.INFO, "Creada base de datos", null );
			return statement;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en creacion de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	/** Anyade una analitica a la tabla abierta de BD, usando la sentencia INSERT de SQL
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a la analitica)
	 * @param nomtabla	nombre de la tabla a la que qeuremos acceder
	 * @param Nombre/contraseña	campos a introducir
	 * @return	true si la insercion es correcta, false en caso contrario
	 */
	public static boolean insert( Statement st, String nomTabla, String nombre, String  contraseña ) {
		String sentSQL = "";
		try {
			// primero habria que probar si existe el valor para que no se repita
			sentSQL = "select * from " + nomTabla + " where nombre = '" + nombre + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			
			if (!rs.next()) {	//si no existe, inserta
				System.out.println("El elemento no existe, se inserta en la tabla");
				sentSQL = "insert into " + nomTabla + " values('" + secu(nombre) + "', '" + contraseña + "')";
				int val = st.executeUpdate( sentSQL );
				log( Level.INFO, "BD fila añadida " + val + " fila\t" + sentSQL, null );
				if (val!=1) {  // Se tiene que añadir 1 - error si no
					log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
					return false;  
				}
			} else {
				System.out.println("El elemento EXISTE, NO se insertara en la tabla");
			}
			
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return false;
		}
	}

	
	
	/** Realiza una consulta a la tabla abierta de la BD, usando la sentencia SELECT de SQL
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a la analitica)
	 * @param nomTabla nombre de la tabla
	 * @param nombreUsu	Codigo a buscar en la tabla
	 * @return	contador cargado desde la base de datos para ese cï¿½digo, Integer.MAX_VALUE si hay cualquier error
	 */
	public static String select( Statement st, String nomTabla, String nombreUsu ) {
		String sentSQL = "";
		try {
			String ret = "";
			sentSQL = "select * from " + nomTabla +" where nombre='" + nombreUsu + "'";
			ResultSet rs = st.executeQuery( sentSQL );
			if (rs.next()) {
				ret = rs.getString( "contrasenya" );
			}
			rs.close();
			log( Level.INFO, "BD\t" + sentSQL, null );
			return ret;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return "Error";
		}
	}
	
	
	
	
/////////////////////////////////////////////////////////////////////
//                      Logging                                    //
/////////////////////////////////////////////////////////////////////

	private static Logger logger = null;

	// Mï¿½todo pï¿½blico para asignar un logger externo
	/**
	 * Asigna un logger ya creado para que se haga log de las operaciones de base de
	 * datos
	 * 
	 * @param logger
	 *            Logger ya creado
	 */
	public static void setLogger(Logger logger) {
		MiBD.logger = logger;
	}

	// Mï¿½todo local para loggear (si no se asigna un logger externo, se asigna uno
	// local)
	private static void log(Level level, String msg, Throwable excepcion) {
		if (logger == null) { // Logger por defecto local:
			logger = Logger.getLogger("Logger BD"); // Nombre del logger - el de la clase
			logger.setLevel(Level.ALL); // Loguea todos los niveles
			try {
				// logger.addHandler( new FileHandler( "bd-" + System.currentTimeMillis() +
				// ".log.xml" ) ); // Y saca el log a fichero xml
				logger.addHandler(new FileHandler("bd.log.xml", true)); // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log(Level.SEVERE, "No se pudo crear fichero de log", e);
			}
		}
		if (excepcion == null)
			logger.log(level, msg);
		else
			logger.log(level, msg, excepcion);
	}
	
	
/////////////////////////////////////////////////////////////////////
//                      Metodos privados                           //
/////////////////////////////////////////////////////////////////////


	private static String secu(String string) {

		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZï¿?.,:;-_(){}[]-+*=<>'\"¡!&%$@#/\\0123456789"
					.indexOf(c) >= 0)
				ret.append(c);
		}
		return ret.toString();
	}
	
	public static void main(String[] args) {
<<<<<<< HEAD
		System.out.println(secu("hoola"));
	} 
=======
		Connection con = null;
		Statement st = null;
		
		con = initBD("BD gestion usuarios");
		usarCrearTablasBD(con);
		st = usarBD(con);
		insert(st, "Usuario", "Uni", "pass1");
		insert(st, "Usuario", "Banano", "pass2");
		insert(st, "Usuario", "platano", "pass3");
		
		System.out.println(select(st, "Usuario", "Uni"));
		System.out.println(select(st, "Usuario", "Banano"));
		System.out.println(select(st, "Usuario", "platano"));
		System.out.println(select(st, "Usuario", "jajajajajaaj"));

	}
>>>>>>> branch 'master' of https://github.com/sainzunai/Prog-III
}

