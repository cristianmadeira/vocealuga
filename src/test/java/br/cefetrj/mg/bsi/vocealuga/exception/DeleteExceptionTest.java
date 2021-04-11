package br.cefetrj.mg.bsi.vocealuga.exception;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




public class DeleteExceptionTest {

    
    @Test
    public void testConstructor(){
       assertThrows(DeleteException.class,()->{throw new DeleteException();});
    }

    @Test
    public void testConstructorWithArgs(){
        String expected = "Você não pode excluir este(a)(s) agência, pois encontra(m)-se 2 funcionários(s) .";
        Exception e = assertThrows(DeleteException.class,()->{
           throw new DeleteException("agência",2,"funcionários");
        });
        String actual = e.getMessage();
        
        assertEquals(expected,actual);
    }
}
