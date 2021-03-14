package br.cefetrj.mg.bsi.vocealuga.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

@TestMethodOrder(OrderAnnotation.class)
public class CargoServiceTest {

    private static ICargoService service = null;

    private static Cargo cargo = null;

    private static Cargo create() {
        Cargo c = new Cargo();
        c.setId(0);
        c.setNome("NAV");

        return c;
    }

    @BeforeAll
    public static void setUp() throws Exception {
        service = new CargoServiceImpl();
        cargo = create();
    }

    @Test
    @Order(1)
    final void testSave() throws Exception {
        Cargo c = service.save(cargo);
        assertNotNull(c.getCreatedAt());
        cargo = c;
    }

    @Test
    @Order(2)
    public void testSaveWithEmptyName() throws Exception {

        Cargo c = new Cargo();
        c.setNome("");
        assertThrows(Exception.class, () -> {
            service.save(c);
        });

    }

    @Test
    @Order(3)
    public void testUpdate() throws Exception {
        cargo.setNome("Cargo Service Test");
        Cargo c = service.update(cargo);
        assertNotNull(c.getUpdatedAt());
        cargo = c;
    }

    @Test
    @Order(4)
    public void testUpdateInvalidsNameandId() throws Exception {

        Cargo c = new Cargo();
        c.setNome("");
        c.setId(-1);
        assertThrows(Exception.class, () -> {
            service.update(c);
        });
        c.setNome("abcdefg");
        c.setId(0);
        assertThrows(Exception.class, () -> {
            service.update(c);
        });
        c.setNome("");
        c.setId(1);
        assertThrows(Exception.class, () -> {
            service.update(c);
        });

    }

    void testUpdateIdLessThanZero() throws Exception {
        Cargo c = new Cargo();
        c.setId(-1);
        c.setNome("abcd");
        assertThrows(Exception.class, () -> {
            service.update(c);
        });
    }

    @Test
    @Order(5)
    public void testFindById() throws Exception {
        Cargo c = service.findById(cargo.getId());
        assertNull(c.getDeletedAt());
    }

    @Test
    @Order(6)
    void testFindByIdLessThanZero() throws Exception {
        int id = -1;
        assertThrows(Exception.class, () -> {
            service.findById(id);
        });
    }

    @Test
    @Order(7)
    public void testFindAll() throws Exception {
        List<Cargo> cargos = service.findAll();
        for (Cargo c : cargos) {
            assertNull(c.getDeletedAt());
        }
    }

    @Test
    @Order(8)
    public void testDelete() throws Exception {

        Cargo c = service.delete(cargo.getId());
        assertNotNull(c.getDeletedAt());

    }

    @Test
    @Order(9)
    void testDeleteIdLessThanZero() {
        int id = -1;
        assertThrows(Exception.class, () -> {
            service.delete(id);
        });

    }

}
