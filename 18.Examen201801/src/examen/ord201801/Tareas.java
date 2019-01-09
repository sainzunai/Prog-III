package examen.ord201801;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import examen.ord201801.item.Arbol;


/** Clase con métodos estáticos para implementar algunas de las tareas del examen
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tareas {
	
	// TAREA 4 - Atributos necesarios para la tarea 4
	static Connection con = null;
	static Statement st = null;
	public static void tarea4() {
		ventana = EdicionZonasGPS.getVentana();  // Atributo de acceso a la ventana
		// TAREA 4 - Código de conexión y de actualización de base de datos
		// 1.- Conexión de base de datos (que se conecte solo la primera vez)
		con = BD.initBD("BDArboles.db");
		st = BD.usarCrearTablasBD(con);
		
		// 2.- Actualización de árboles de zona seleccionada
		
		Zona zona;
		if(!ventana.lZonas.isSelectionEmpty()) {
			zona = ventana.lZonas.getSelectedValue();
			ArrayList<Arbol> listaArbolesEnMapa = new ArrayList<>();
			ArrayList<Arbol> arbolesDeZonaEnBD = BD.arbolSelect( st, zona.getCodigoZona() );
			for(Arbol a : GrupoZonas.arbolesErandio) {
				for(ArrayList<PuntoGPS> subzona : zona.getPuntosGPS()) {
					if(UtilsGPS.gpsDentroDePoligono(a.getPunto(), subzona)) {
						listaArbolesEnMapa.add(a);
					}

				}
			}
			// Quitar las que estén en los dos sitios
			for(Arbol a : arbolesDeZonaEnBD) {
				int enMapa = listaArbolesEnMapa.indexOf( a );
				if (enMapa!=-1) {  // Si está se quita de los dos sitios
					arbolesDeZonaEnBD.remove( a );
					listaArbolesEnMapa.remove( a );
			}
		}
			for(Arbol a : arbolesDeZonaEnBD)//eliminar estos arboles que ya no estan
				BD.deleteArbol(st, a);
			for(Arbol a : listaArbolesEnMapa)
				BD.arbolInsert(st, zona.getCodigoZona(), a);
		}
		
		ventana.lMensaje.setText( "Finalizado proceso de BD." );
	}
	
	public static void finAplicacion() {
		// TAREA 4 - Cierre al final de la aplicación
		BD.cerrarBD(con, st);
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
		(new Thread() { public void run() { calculoCentroides( 0 , null, null); ventana.lMensaje.setText( "Finalizado algoritmo de centroides." ); }}).start();
	}
	// TAREA 5 - Codificar este método de forma recursiva completando los pasos sin codificar
	private static void calculoCentroides( int numPasoRecursivo, double[] sumalatAnterior, double[] sumalongAnterior ) {
		ventana.lMensaje.setText( "Inicio de paso " + numPasoRecursivo );
		Color[] color = { Color.RED, Color.BLUE, Color.GREEN };  // Array de colores asociados a cada centroide
		ArrayList<PuntoGPS> listaCentroide = new ArrayList<>();
		for(PuntoGPS p : centroide)
			listaCentroide.add(p);
		// 1.- Colorear los puntos dependiendo del centroide más cercano:
		//   centroide1 - Rojo, centroide2 - Azul, centroide3 - Verde
		for(Arbol a : GrupoZonas.arbolesErandio) {
			PuntoGPS pTemp = null;
			double distTemp = 0;
			
			for (PuntoGPS p : listaCentroide) {
				if (pTemp == null) {
					distTemp = UtilsGPS.distanciaEntrePuntos(a.getPunto(), p);
					pTemp = p;
				} else {
					if (distTemp > UtilsGPS.distanciaEntrePuntos(a.getPunto(), p)) {
						distTemp = UtilsGPS.distanciaEntrePuntos(a.getPunto(), p);
						pTemp = p;
					}
				}
				
			}
			System.out.println("El centroide mas corto para el arbol " + a.getNombre() + " es " + pTemp);
			System.out.println(listaCentroide.indexOf(pTemp));
			a.setColor(color[listaCentroide.indexOf(pTemp)]);
		}
		
		// 2.- Redibujar la pantalla con los árboles recoloreados
		ventana.calculaMapa();
		// 3.- Dibujar centroides actuales
		for (int i=0; i<3; i++) {
			ventana.dibujaCirculo( centroide[i].getLongitud(), centroide[i].getLatitud(), 8, color[i], EdicionZonasGPS.stroke4, true );
			ventana.dibujaCruz( centroide[i].getLongitud(), centroide[i].getLatitud(), 30, Color.BLACK, EdicionZonasGPS.stroke2m, true );
		}
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
		System.out.println(sumaLat + " = " + sumalatAnterior);
		System.out.println(sumaLong + " = " + sumalongAnterior);
		
		if(sumalatAnterior == sumaLat && sumaLong == sumalongAnterior)
			return;
		calculoCentroides(numPasoRecursivo ++, sumaLat, sumaLong);
	}
}
