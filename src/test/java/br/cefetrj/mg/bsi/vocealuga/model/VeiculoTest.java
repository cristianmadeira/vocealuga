package br.cefetrj.mg.bsi.vocealuga.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Veiculo Model Test")
public class VeiculoTest {
    
    private static Veiculo veiculo;

    private static PodamFactory factory = null; 
    @BeforeAll
    public static void setUp(){
        factory = new PodamFactoryImpl();
        veiculo = factory.manufacturePojo(Veiculo.class,Boolean.class);
        veiculo.setDeletedAt(LocalDateTime.now());
    }
    @Test
    public void testCreatedAt(){
        assertNotNull(veiculo.getCreatedAt());
    }
    @Test
    public void testUpdatedAt(){
        assertNotNull(veiculo.getUpdatedAt());
    }
    @Test
    public void testDeletedAt(){
        assertNotNull(veiculo.getDeletedAt());
    }
}
