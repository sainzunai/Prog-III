package utils.JUnit;

import static org.junit.Assert.fail;

import org.junit.*;


class EjemploTest {

	@Before
	public void setUp(){
		fail("");
	}

	@After
	public void tearDown() {
		fail("");
	}


	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_Exc1() {
		fail("");
	}

}
