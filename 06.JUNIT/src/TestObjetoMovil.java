import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class TestObjetoMovil {

//	@Before	//lo que hace antes de empezar el test
//	public void setUp() throws Exception {
//		v = new ObjetoMovil();
//		v.setLocation(2000,0);
//	}

	@Test
	public void testMoverVertical() {
		testTiempo();
	}

	public void testTiempo() {	//por defecto java solo ejecuta los test, solo ejecutara el metodo cuando se le llame desde un test
		final long PRECISION_MSG = 1;
		ObjetoMovil movil = new ObjetoMovil();
		movil.setPosX(0);
		movil.setPosY(0);
		double velocidadInicial = 0;
		movil.setVelY(velocidadInicial);
		
		long tiempoSubida = 0;
		while ( movil.getVelX()<0.0) {
			System.out.println("Subida " + tiempoSubida + " vel " + movil.getVelY());
			movil.mueve(PRECISION_MSG, 0, 980);
			tiempoSubida += PRECISION_MSG;
		}
		
		long tiempoBajada = 0;
		while ( movil.getVelX()<0.0) {
			System.out.println("Bajada " + tiempoSubida + " vel " + movil.getVelY());
			movil.mueve(PRECISION_MSG, 0, 980);
			tiempoBajada += PRECISION_MSG;
		}
		
		assertEquals(tiempoBajada, tiempoSubida);
		assertEquals(-movil.getVelY(), velocidadInicial, 5.0);
//		assertEquals(1, tiempoBajada);
	}
}
