package br.cefetrj.mg.bsi.vocealuga.exception;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EmptyListExceptionTest {

	private static EmptyListException exception = null;

	@BeforeAll
	static void setUp() {
		exception = new EmptyListException();
	}

	@Test
	void testInstance() {
		assertNotNull(exception);
	}

	@Test
	void testException() {
		String expected = "Não há grupos cadastrado(a)(s)";
		EmptyListException actual = assertThrows(EmptyListException.class, () -> {
			throw new EmptyListException("grupos");
		});

		assertEquals(expected, actual.getMessage());
	}

}
