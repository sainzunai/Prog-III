import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class TablaTest {
	Tabla tableUnderTest;
	Tabla tableUnderTest2;

	@Before
	public void setUp() { // antes de iniciar el test ejecutamos el metodo
		try {
			tableUnderTest = Tabla.processCSV(VentanaDatos.class.getResource("testTabla1.csv").toURI().toURL());
			tableUnderTest2 = Tabla.processCSV(VentanaDatos.class.getResource("testTabla2.csv").toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {

		testear1();
		testear2();

	}

	private void testear1() {
		assertEquals(4, tableUnderTest.size()); // se por el enunciado y viendo el fichero que son 4
		assertEquals(4, tableUnderTest.getWidth());
		// codifico que la tabla sea igual a la esperada
		for (int col = 0; col < 4; col++) {
			assertEquals("1", tableUnderTest.get(0, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("2", tableUnderTest.get(1, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("4", tableUnderTest.get(2, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("6", tableUnderTest.get(3, col));
		}
	}

	private void testear2() {
		assertEquals(4, tableUnderTest2.size()); // se por el enunciado y viendo el fichero que son 4
		assertEquals(4, tableUnderTest2.getWidth());
		// codifico que la tabla sea igual a la esperada
		for (int col = 0; col < 4; col++) {
			assertEquals("1", tableUnderTest2.get(0, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("2", tableUnderTest2.get(1, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("4", tableUnderTest2.get(2, col));
		}
		for (int col = 0; col < 4; col++) {
			assertEquals("6", tableUnderTest2.get(3, col));
		}
	}
}
