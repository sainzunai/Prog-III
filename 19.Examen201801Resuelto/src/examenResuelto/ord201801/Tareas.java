package examenResuelto.ord201801;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import examenResuelto.ord201801.item.Arbol;

/** Clase con métodos estáticos para implementar algunas de las tareas del examen
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tareas {

	// TAREA 4 - Atributos necesarios para la tarea 4
	private static Connection conn = null;
	private static Statement stat = null;
	public static void tarea4() {
		ventana = EdicionZonasGPS.getVentana();  // Atributo de acceso a la ventana
		// TAREA 4 - Código de conexión y de actualización de base de datos
		// 1.- Conexión de base de datos (que se conecte solo la primera vez)
		if (conn==null) {
			conn = BD.initBD( "arboles.bd" );
			stat = BD.usarCrearTablasBD( conn );
		}
		// 2.- Actualización de árboles de zona seleccionada
		if (ventana.lZonas.getSelectedIndex()!=-1) {
			Zona zona = ventana.lZonas.getSelectedValue();
			// Cargar las dos listas - base de datos y mapa
			ArrayList<Arbol> arbolesDeZonaEnBD = BD.arbolSelect( stat, zona.getCodigoZona() );
			ArrayList<Arbol> arbolesDeZonaEnMapa = new ArrayList<>();
			for (Arbol arbol : GrupoZonas.arbolesErandio) {
				for (ArrayList<PuntoGPS> subzona : zona.getPuntosGPS()) {
					if (UtilsGPS.gpsDentroDePoligono( arbol.getPunto(), subzona )) {
						arbolesDeZonaEnMapa.add( arbol );
						break;
					}
				}
			}
			// Quitar las que estén en los dos sitios
			for (int i=arbolesDeZonaEnBD.size()-1; i>=0; i--) {
				Arbol arbolEnBD = arbolesDeZonaEnBD.get( i );
				int enMapa = arbolesDeZonaEnMapa.indexOf( arbolEnBD );
				if (enMapa!=-1) {  // Si está se quita de los dos sitios
					arbolesDeZonaEnBD.remove( i );
					arbolesDeZonaEnMapa.remove( enMapa );
				}
			}
			// Borrar los que estén en BD, insertar los que estén en mapa
			for (Arbol a : arbolesDeZonaEnBD) {
				BD.arbolDelete( stat, zona.getCodigoZona(), a.getPunto().getLatitud(), a.getPunto().getLongitud() );
			}
			for (Arbol a : arbolesDeZonaEnMapa) {
				BD.arbolInsert( stat, zona.getCodigoZona(), a );
			}
		}
		ventana.lMensaje.setText( "Finalizado proceso de BD." );
	}
	
	public static void finAplicacion() {
		// TAREA 4 - Cierre al final de la aplicación
		if (conn!=null) {
			BD.cerrarBD( conn, stat );
		}
	}
	
	
	// Atributos y métodos de la tarea 5
	private static EdicionZonasGPS ventana;
	private static PuntoGPS[] centroide;
	public static void tarea5() {
		// 1.- Reajusta la ventana para que se vean las zonas completas y para que se vean los árboles
		ventana = EdicionZonasGPS.getVentana();
		if (!ventana.cbArboles.isSelected()) {
			ventana.cbArboles.doClick();  // Activa la selección de árboles para que se vean
		}
		ventana.calcMoverACentroZonas();
		ventana.calculaMapa();
		// 2.- Plantea 3 centroides (en lugar de aleatorios -que es lo habitual- se ponen de forma arbitraria para que se pueda probar el algoritmo siempre con los mismos valores
		centroide = new PuntoGPS[] { new PuntoGPS( 43.319, -2.964 ), new PuntoGPS( 43.304, -2.979 ), new PuntoGPS( 43.298, -2.958 ) };
		// 3.- Llama al algoritmo propio de la tarea 5 para que esos centroides se vayan recalculando
		(new Thread() { public void run() { calculoCentroides( 0 ); ventana.lMensaje.setText( "Finalizado algoritmo de centroides." ); }}).start();
	}
	// TAREA 5 - Codificar este método de forma recursiva completando los pasos sin codificar
	private static void calculoCentroides( int numPasoRecursivo ) {
		ventana.lMensaje.setText( "Inicio de paso " + numPasoRecursivo );
		Color[] color = { Color.RED, Color.BLUE, Color.GREEN };  // Array de colores asociados a cada centroide
		// 1.- Colorear los puntos dependiendo del centroide más cercano:
		//   centroide1 - Rojo, centroide2 - Azul, centroide3 - Verde
		int numCambios = 0;
		for (Arbol arbol : GrupoZonas.arbolesErandio) {
			double[] dist = new double[3];
			for (int i=0; i<3; i++) dist[i] = UtilsGPS.distanciaEntrePuntos( arbol.getPunto(), centroide[i] );
			if (dist[0]<=dist[1] && dist[0]<=dist[2]) {
				if (arbol.getColor() != color[0]) {
					numCambios++; arbol.setColor( color[0] );
				}
			} else if (dist[1]<=dist[0] && dist[1]<=dist[2]) {
				if (arbol.getColor() != color[1]) {
					numCambios++; arbol.setColor( color[1] );
				}
			} else {  // Es el centroide[2] -->  if (dist[2]<=dist[0] && dist[2]<=dist[1]) {
				if (arbol.getColor() != color[2]) {
					numCambios++; arbol.setColor( color[2] );
				}
			}
		}
		// 2.- Redibujar la pantalla con los árboles recoloreados
		ventana.calculaMapa();
		// 3.- Dibujar centroides actuales
		for (int i=0; i<3; i++) {
			ventana.dibujaCirculo( centroide[i].getLongitud(), centroide[i].getLatitud(), 8, color[i], EdicionZonasGPS.stroke4, true );
			ventana.dibujaCruz( centroide[i].getLongitud(), centroide[i].getLatitud(), 30, Color.BLACK, EdicionZonasGPS.stroke2m, true );
		}
		if (numCambios==0) return; // Caso base
		// 4.- Esperar un par de segundos
		try { Thread.sleep( 2000 ); } catch (Exception e) {}
		// 5.- Calcular medias de cada uno de los grupos (colores de árboles)
		double[] sumaLat = new double[3];
		double[] sumaLong = new double[3];
		int[] num = new int[3];
		for (Arbol arbol : GrupoZonas.arbolesErandio) {
			int numGrupo = Arrays.asList( color ).indexOf( arbol.getColor() );
			sumaLat[numGrupo] += arbol.getPunto().getLatitud(); 
			sumaLong[numGrupo] += arbol.getPunto().getLongitud();
			num[numGrupo]++;
		}
		// 6.- Recalcular los centroides con las medias
		for (int i=0; i<3; i++) {
			centroide[i].setLatitud( sumaLat[i]/num[i] );
			centroide[i].setLongitud( sumaLong[i]/num[i] );
		}
		// 7.- Completar recursividad...
		calculoCentroides( numPasoRecursivo + 1);
	}
}
