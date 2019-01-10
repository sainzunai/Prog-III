package examen.ord201701;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Clase de test unitario de la clase FarmaciaGuardia
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class FarmaciaGuardiaTest {

	@Test
	public void testConstructor3Strings() {
		// TAREA 1
		FarmaciaGuardia f1 = new FarmaciaGuardia("Getxo", "00:00-22:00", "(GetxoB) Avda. Universidades 24 | 944139000");
		FarmaciaGuardia f2 = new FarmaciaGuardia("", "00:01-22:00", "");
		FarmaciaGuardia f3 = new FarmaciaGuardia("", "23:59-00:00", "");
		FarmaciaGuardia f4 = new FarmaciaGuardia("", "", "hola xd");
		
		assertTrue(f1.getHoraHastaSt().equals("22:00"));
		assertTrue(f1.getHoraDesdeSt().equals("00:00"));
		assertTrue(f1.getLocalidad().equals("Getxo"));
		assertTrue(f1.getTelefono().equals("944139000"));
		assertTrue(f1.getZona().equals("GetxoB"));
	
		assertTrue(f1.getHoraDesde() < f2.getHoraDesde());
		assertTrue(f1.getHoraDesde() < f3.getHoraDesde());
		assertTrue(f4.getZona().equals("") && f4.getTelefono().equals(""));
	}
	
	@Test
	public void testCapicua() {
		// TAREA 7
		// TODO
	}

}
