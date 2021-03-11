package br.cefetrj.mg.bsi.vocealuga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

@TestMethodOrder(OrderAnnotation.class)
class GrupoDAOTest {

	private  static Grupo grupo, grupo2 = null;
	private static DAO<Grupo> dao = null;
	
	private static Grupo create() {
		Grupo g = new Grupo();
		g.setId(0);
		g.setNome("A");
		return g;
	}
	@BeforeAll
	 static void setUp(){
		grupo = create();
		grupo2 = create();
		dao = new GrupoDAO();
		
	}
	
	@Test
	@Order(1)
	void testGetLastId() throws Exception{
		int result = dao.getLastId();
		assertTrue(result >= 0);
		grupo.setId(result);
	}
	@Test
	@Order(2)
	void testSave() throws Exception{
		Grupo  g = dao.save(grupo);
		assertTrue(g.getId() > 0);
		assertNotNull(g.getCreatedAt());
		grupo = g;
		
		Grupo g2 = dao.save(grupo2);
		assertNotNull(g2.getCreatedAt());
		grupo2 = g2;
	}
	
	@Test
	@Order(3)
	void testUpdate()throws Exception {
		grupo.setNome("ABC");
		Grupo g =dao.update(grupo);
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
		Grupo  g = dao.find(grupo.getId());
		assertEquals(g.getId(),grupo.getId());
	}
	
	@Test
	@Order(6)
	void testDelete() throws Exception{
		Grupo result =  dao.delete(grupo.getId());
		assertTrue(result.getId() > 0);
		assertNotNull(result.getDeletedAt());
		grupo = result;
	
	}
	@Test
	@Order(7)
	void testFindByFakerId() throws SQLException{
		int fakerId = -1;
		assertThrows(Exception.class, () ->{
			dao.find(fakerId);
		});
	}
	
	@Test
	@Order(8)
	void testUpdateWithNotFoundId() throws SQLException{
		int notFoundId = -1;
		Grupo g = new Grupo();
		g.setId(notFoundId);
		assertThrows(Exception.class, () ->{
			dao.update(g);
		});
	}
	@Test
	@Order(9)
	void testDeleteWithNotFoundId() throws Exception{
		int notFoundId = -1;
		assertThrows(Exception.class, () ->{
			dao.delete(notFoundId);
		});
	}
	
	
	@Test
	@Order(10)
	void testSaveEmptyName() throws Exception {
		assertThrows(Exception.class,()->{
			dao.save(new Grupo());
			
		});
	}
}
