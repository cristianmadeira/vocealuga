package br.cefetrj.mg.bsi.vocealuga.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ResultNotFoundExceptionTest {

    @Test
    void testDefaultException() {
        assertThrows(ResultNotFoundException.class, () -> {
            throw new ResultNotFoundException();
        });
    }

    @Test
    void testCustomMessageException() {
        String fieldName = "nome";
        ResultNotFoundException exception = assertThrows(ResultNotFoundException.class, () -> {
            throw new ResultNotFoundException(fieldName);
        });
        String expected = "O(a) nome não foi encontrado";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }
}
