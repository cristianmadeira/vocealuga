package br.cefetrj.mg.bsi.vocealuga.exception;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class InvalidIdExceptionTest {

    @Test
    void testDefaultMessageException() {
        assertThrows(InvalidIdException.class, () -> {
            throw new InvalidIdException();
        });
    }

    @Test
    void testCustomMessageException() {
        int invalidId = -1;
        String expected = "Id -1 é inválido";
        InvalidIdException exception = assertThrows(InvalidIdException.class, () -> {
            throw new InvalidIdException(invalidId);
        });
        assertTrue(exception.getMessage().contains(expected));
    }

}
