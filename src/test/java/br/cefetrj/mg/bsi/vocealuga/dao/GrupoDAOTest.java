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

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

@TestMethodOrder(OrderAnnotation.class)
class GrupoDAOTest {

	private static Grupo grupo, grupo2 = null;
	private static IDAO<Grupo> dao = null;

	private static Grupo create() {
		Grupo g = new Grupo();
		g.setId(0);
		g.setNome("A");
		return g;
	}

	@BeforeAll
	static void setUp() {
		grupo = create();
		grupo2 = create();
		dao = new GrupoDAOImpl();

	}
	@Test
	@Order(1)
	void testGetLastId() throws SQLException{
		
		String wrongSql = "INSERT INTO grupos (VALUES)";
		Connection conn = ConnectionFactory.getInstance().getConn();
		PreparedStatement pst = conn.prepareStatement(wrongSql, PreparedStatement.RETURN_GENERATED_KEYS);
		assertThrows(SQLException.class,()->{
			dao.getLastId(pst);
		});

		
				

	}
	@Test
	@Order(2)
	void testSave() throws SQLException {
		Grupo g = dao.save(grupo);
		assertTrue(g.getId() > 0);
		assertNotNull(g.getCreatedAt());
		grupo = g;

		Grupo g2 = dao.save(grupo2);
		assertNotNull(g2.getCreatedAt());
		grupo2 = g2;
	}

	@Test
	@Order(3)
	void testUpdate() throws Exception {
		grupo.setNome("ABC");
		Grupo g = dao.update(grupo);
		assertNotNull(g.getUpdatedAt());
		grupo = g;
	}

	@Test
	@Order(4)
	void testFindAll() throws Exception {
		List<Grupo> grupos = dao.findAll();
		assertTrue(grupos.size() > 0);

	}

	@Test
	@Order(5)
	void testFindById() throws Exception {
		Grupo g = dao.find(grupo.getId());
		assertEquals(g.getId(), grupo.getId());
	}

	@Test
	@Order(6)
	void testDelete() throws Exception {
		Grupo result = dao.delete(grupo.getId());
		assertTrue(result.getId() > 0);
		assertNotNull(result.getDeletedAt());
		grupo = result;

	}

	@Test
	@Order(7)
	void testInvalidId() throws SQLException {
		int invalidId = -1;
		String expected = "Id -1 é inválido";
		SQLException e = assertThrows(SQLException.class, () -> {
			dao.find(invalidId);
		});
		assertEquals(expected, e.getMessage());
	}

	@Test
	@Order(8)
	void testUpdateWithNotFoundId() throws SQLException {
		int notFoundId = -1;
		Grupo g = new Grupo();
		g.setId(notFoundId);
		assertThrows(SQLException.class, () -> {
			dao.update(g);
		});
	}

	@Test
	@Order(9)
	void testDeleteWithNotFoundId() throws SQLException {
		int notFoundId = -1;
		assertThrows(SQLException.class, () -> {
			dao.delete(notFoundId);
		});
	}

	@Test
	@Order(10)
	void testSaveEmptyName() throws Exception {
		assertThrows(Exception.class, () -> {
			dao.save(new Grupo());

		});
	}

	@Test
	@Order(11)
	void testGetFilledObject() throws SQLException {
		String sql = "SELECT * FROM  grupos";
		try (Connection conn = ConnectionFactory.getInstance().getConn();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			rs.next();
			assertNotNull(dao.getFilledObject(rs));

		}

	}
	
}
