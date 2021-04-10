package br.cefetrj.mg.bsi.vocealuga.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@ExtendWith(MockitoExtension.class)
public class FuncionarioTest {
    
    private  static Funcionario funcionario;
    private static PodamFactory factory; 
    @BeforeAll
    public static void setUp(){
        factory = new PodamFactoryImpl();
        funcionario = factory.manufacturePojo(Funcionario.class);
        funcionario.setEmail("fakeremail@gmail.com");
        funcionario.setNome("fakername");
        funcionario.setCpf("12345678901");
    }
    @Test
    public void testCreatedAt(){
        assertNotNull(funcionario.getCreatedAt());
    }
    @Test
    public void testUpdatedAt(){
        assertNotNull(funcionario.getUpdatedAt());
    }
    @Test
    public void testDeletedAt(){
        assertNotNull(funcionario.getDeletedAt());
    }
    @Test
    public void testCargoIsNotNull(){
        assertNotNull(funcionario.getCargo());
    }
    
    @Test
    public void testAgenciaIsNotNull(){
        assertNotNull(funcionario.getAgencia());
    }

    @Test
    public void testIdIsNotNull(){
        assertNotNull(funcionario.getId());
    }
    @Test
    public void testEmailIsValid(){
        assertTrue(funcionario.getEmail().contains("@"));
    }
    @Test
    public void testNomeIsNotNull(){
        assertNotNull(funcionario.getNome());
    }
    @Test
    public void testCpfIsNotNull(){
        assertEquals(funcionario.getCpf().length(),11);
    }

}
