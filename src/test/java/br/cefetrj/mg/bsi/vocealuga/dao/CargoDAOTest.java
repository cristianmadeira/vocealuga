package br.cefetrj.mg.bsi.vocealuga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

@TestMethodOrder(OrderAnnotation.class)
class CargoDAOTest {

    private static Cargo cargo, cargo2 = null;
    private static IDAO<Cargo> dao = null;

    private static Cargo create() {
        Cargo c = new Cargo();
        c.setId(0);
        c.setNome("ABC");

        return c;
    }

    @BeforeAll
    static void setUp() {
        cargo = create();
        cargo2 = create();
        dao = new CargoDAOImpl();

    }

	@Test
	@Order(1)
	void testGetLastId() throws SQLException{
		String wrongSql = "INSERT INTO cargos (VALUES)";
		Connection conn = ConnectionFactory.getInstance().getConn();
		PreparedStatement pst = conn.prepareStatement(wrongSql, PreparedStatement.RETURN_GENERATED_KEYS);
		assertThrows(SQLException.class,()->{
			dao.getLastId(pst);
		});
	}
    @Test
    @Order(2)
    void testSave() throws Exception {
        Cargo c = dao.save(cargo);
        assertTrue(c.getId() > 0);
        assertNotNull(c.getCreatedAt());
        cargo = c;

        Cargo c2 = dao.save(cargo2);
        assertNotNull(c2.getCreatedAt());
        cargo2 = c2;
    }

    @Test
    @Order(3)
    void testUpdate() throws Exception {
        cargo.setNome("CBA");
        Cargo c = dao.update(cargo);
        assertNotNull(c.getUpdatedAt());
        cargo = c;
    }

    @Test
	@Order(4)
	void testFindAll() throws Exception {
		List<Cargo> cargos = dao.findAll();
		assertTrue(cargos.size() > 0);

	}

    @Test
	@Order(5)
	void testFindById() throws Exception {
		Cargo c = dao.find(cargo.getId());
		assertEquals(c.getId(), cargo.getId());
	}

    @Test
	@Order(6)
	void testDelete() throws Exception {
		Cargo result = dao.delete(cargo.getId());
		assertTrue(result.getId() > 0);
		assertNotNull(result.getDeletedAt());
		cargo = result;

	}
    
    @Test
	@Order(7)
	void testInvalidId() throws SQLException {
		int invalidId = -1;
		assertThrows(SQLException.class, () -> {
			dao.find(invalidId);
		});
		
	}

    
	@Test
	@Order(8)
	void testUpdateWithNotFoundId() throws SQLException {
		int notFoundId = -1;
		Cargo c = new Cargo();
		c.setId(notFoundId);
		assertThrows(Exception.class, () -> {
			dao.update(c);
		});
	}

    @Test
	@Order(9)
	void testDeleteWithNotFoundId() throws Exception {
		int notFoundId = -1;
		assertThrows(Exception.class, () -> {
			dao.delete(notFoundId);
		});
	}

    @Test
	@Order(10)
	void testSaveEmptyName() throws Exception {
		assertThrows(Exception.class, () -> {
			dao.save(new Cargo());

		});
	}

    @Test
	@Order(11)
	void testGetFilledObject() throws SQLException {
		String sql = "SELECT * FROM  cargos";
		try (Connection conn = ConnectionFactory.getInstance().getConn();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			rs.next();
			assertNotNull(dao.getFilledObject(rs));

		}

	}

}
