package br.cefetrj.mg.bsi.vocealuga.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
public class CargoTest {
    
    private static Cargo cargo;

    private static PodamFactory factory = null;
    
    @BeforeAll
    public static void setUp(){
        factory = new PodamFactoryImpl();
        cargo = factory.manufacturePojo(Cargo.class);
        cargo.setDeleteAt(LocalDateTime.now());
    }
    @Test
    public void testCreatedAt(){
        assertNotNull(cargo.getCreatedAt());
    }
    @Test
    public void testUpdatedAt(){
        assertNotNull(cargo.getUpdatedAt());
    }
    @Test
    public void testDeletedAt(){
        assertNotNull(cargo.getDeletedAt());
    }
}
