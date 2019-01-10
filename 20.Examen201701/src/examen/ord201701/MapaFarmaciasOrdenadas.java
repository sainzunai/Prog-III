package examen.ord201701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class MapaFarmaciasOrdenadas {
	// TAREA 6 - Definir atributo, codificar constructor y método consultor de atributo
	TreeMap<String, FarmaciaGuardia> mapaTree;
	
	/** Crea un mapa de farmacias ordenadas por localidad, horario, zona y dirección partiendo de un mapa desordenado
	 * @param mapa	Mapa de farmacias por población, desordenado
	 */
	public MapaFarmaciasOrdenadas( MapaFarmacias mapa ) {
		mapaTree = new TreeMap<>();
		
		volcarDatos(mapa);
	}
	
	private void volcarDatos(MapaFarmacias mapa) {
		Iterator<ArrayList<FarmaciaGuardia>> iterador = mapa.getMapaFarmacias().values().iterator();
		while (iterador.hasNext()) {
			for(FarmaciaGuardia farmacia : iterador.next()) {
				mapaTree.put(farmacia.getLocalidad(), farmacia);
			}
		}
//		while (mapa.) {
//			
//		}
	}

	@Override
	public String toString() {
		Iterator it = mapaTree.values().iterator();
		String ret = "";
		while (it.hasNext()) {
			ret = ret + it.next().toString() + "\n";
		}
		return ret;
	}
	
	// TODO
	
}
