package br.cefetrj.mg.bsi.vocealuga.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
public class GrupoTest {
    
    private static Grupo grupo;

    private static PodamFactory factory = null;
    
    @BeforeAll
    public static void setUp(){
        factory = new PodamFactoryImpl();
        grupo = factory.manufacturePojo(Grupo.class);
        grupo.setDeletedAt(LocalDateTime.now());
    }
    @Test
    public void testCreatedAt(){
        assertNotNull(grupo.getCreatedAt());
    }
    @Test
    public void testUpdatedAt(){
        assertNotNull(grupo.getUpdatedAt());
    }
    @Test
    public void testDeletedAt(){
        assertNotNull(grupo.getDeletedAt());
    }
}
