package examenResuelto.ord201801;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import examenResuelto.ord201801.item.Arbol;

public class UtilsGPSTest {

	@Test
	public void gpsDentroDePoligonoTest() {
		// TAREA 1
		// Todos los árboles están en zonas
		for (Arbol arbol : GrupoZonas.arbolesErandio) {
			assertTrue( puntoEstaEnAlgunaZona( arbol.getPunto() ) );
		}
		// Todos los bordes no están en zonas
		double lat1 = 43.323; double long1 = -2.990;
		double lat2 = 43.287; double long2 = -2.923;
		double incr = (lat2-lat1)/100;
		for (double lat = lat1; lat<lat2; lat += incr) {
			assertFalse( puntoEstaEnAlgunaZona( new PuntoGPS( lat, long1 ) ) );
			assertFalse( puntoEstaEnAlgunaZona( new PuntoGPS( lat, long2 ) ) );
		}
		incr = (long2-long1)/100;
		for (double lon = long1; lon<long2; lon += incr) {
			assertFalse( puntoEstaEnAlgunaZona( new PuntoGPS( lat1, lon ) ) );
			assertFalse( puntoEstaEnAlgunaZona( new PuntoGPS( lat2, lon ) ) );
		}
	}
		// Método para comprobar todas las zonas
		private boolean puntoEstaEnAlgunaZona( PuntoGPS punto ) {
			Iterator<Zona> itZona = GrupoZonas.jardinesErandio.getIteradorZonas();
			while (itZona.hasNext()) {
				Zona zona = itZona.next();
				for (ArrayList<PuntoGPS> subzona : zona.getPuntosGPS()) {
					if (UtilsGPS.gpsDentroDePoligono( punto, subzona )) return true;
				}
			}
			return false;
		}

}
