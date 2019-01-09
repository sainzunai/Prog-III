package examen.ord201801;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import examen.ord201801.item.Arbol;

public class UtilsGPSTest {

	@Test
	public void gpsDentroDePoligonoTest() {
		// TAREA 1
		ArrayList<Arbol> arboles = GrupoZonas.arbolesErandio;
		GrupoZonas gz = GrupoZonas.jardinesErandio;
		for(Arbol a : arboles) {
			assertTrue(ptoEnAlgunaZona(a.getPunto()));
		}
		
		//ningun pto en los extremos del mapa
		double lat1 = 43.323; double long1 = -2.990;
		double lat2 = 43.287; double long2 = -2.923;
		double incr = (lat2-lat1)/100;
		for (double lat = lat1; lat<lat2; lat += incr) {
			assertFalse( ptoEnAlgunaZona( new PuntoGPS( lat, long1 ) ) );
			assertFalse( ptoEnAlgunaZona( new PuntoGPS( lat, long2 ) ) );
		}
		incr = (long2-long1)/100;
		for (double lon = long1; lon<long2; lon += incr) {
			assertFalse( ptoEnAlgunaZona( new PuntoGPS( lat1, lon ) ) );
			assertFalse( ptoEnAlgunaZona( new PuntoGPS( lat2, lon ) ) );
		}
		
	}
	
	private boolean ptoEnAlgunaZona(PuntoGPS a) {
		Iterator<Zona> iterador = GrupoZonas.jardinesErandio.getIteradorZonas();
		while(iterador.hasNext()) {
			Zona z = iterador.next();
			for(ArrayList<PuntoGPS> array : z.getPuntosGPS())
				if(UtilsGPS.gpsDentroDePoligono(a, array))
					return true;
				
		}
		return false;
	}

}
