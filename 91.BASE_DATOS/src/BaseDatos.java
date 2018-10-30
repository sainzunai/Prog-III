import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

public class BaseDatos {

	
	public BaseDatos() {
		
	}
	
	
	
	public void establecerConexion(String nombreBD) {
		Class.forName("org.sqlite.JDBC");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
		    return con;
		}
	}
	
	public static void main(String[] args) {

	}

}
