package br.cefetrj.mg.bsi.vocealuga.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
public class AgenciaTest {
    
    private static Agencia agencia;

    private static PodamFactory factory = null;
    
    @BeforeEach
    public  void setUp(){
        factory = new PodamFactoryImpl();
        agencia = factory.manufacturePojo(Agencia.class);
    }
    @Test
    public void testCreatedAt(){
        assertNotNull(agencia.getCreatedAt());
    }
    @Test
    public void testUpdatedAt(){
        assertNotNull(agencia.getUpdatedAt());
    }
    @Test
    public void testDeletedAt(){
        assertNotNull(agencia.getDeletedAt());
    }
}
