package br.cefetrj.mg.bsi.vocealuga.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.cefetrj.mg.bsi.vocealuga.exception.ResultNotFoundException;
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

@TestMethodOrder(OrderAnnotation.class)
class CargoRepositoryTest {

    private static ICargoRepository repository = null;

    private static Cargo cargo = null;

    private static Cargo create() {
        Cargo c = new Cargo();
        c.setId(0);
        c.setNome("ABC");

        return c;
    }

    @BeforeAll
    static void setUp() {
        repository = new CargoRepositoryImpl();
        cargo = create();
    }

    @Test
    @Order(1)
    void testInstante() {
        assertNotNull(repository);
    }

    @Test
    @Order(2)
    void testSave() throws Exception {
        Cargo result = repository.save(cargo);

        assertNotNull(result.getCreatedAt());

        cargo = result;
    }

    @Test
    @Order(3)
    void testUpdate() throws Exception {
        cargo.setNome("DVA");
        Cargo result = repository.update(cargo);

        assertNotNull(result.getUpdatedAt());

        cargo = result;
    }

    @Test
    @Order(4)
    void testFindById() throws Exception {
        Cargo c = repository.findById(cargo.getId());

        assertEquals(c.getId(), cargo.getId());
    }

    @Test
    @Order(5)
    void testFindAll() throws Exception {
        List<Cargo> cargos = repository.findAll();
        for (Cargo c : cargos) {
            assertNull(c.getDeletedAt());
        }
    }

    @Test
    @Order(6)
    void testFindByNome() throws Exception {
        String nome = "DVA";
        Cargo c = repository.findByNome(nome);
        assertNotNull(c.getCreatedAt());

    }

    @Test
    @Order(7)
    void testFindFakeName() {
        String name = "xyz";
        ResultNotFoundException exception = assertThrows(ResultNotFoundException.class, () -> {
            repository.findByNome(name);
        });
        String actual = exception.getMessage();
        String expected = "O(a) xyz não foi encontrado";
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    void testDelete() throws Exception {
        Cargo result = repository.delete(cargo.getId());
        assertNotNull(result.getDeletedAt());
    }

}
