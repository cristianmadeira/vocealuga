package br.cefetrj.mg.bsi.vocealuga.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.config.ConnectionType;
import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

class GrupoDAOTest {

	private Connection conn = ConnectionFactory.getInstance(ConnectionType.EXTERNAL.value).getConn();
	@Test
	void testSave() throws SQLException {
		Grupo m = new Grupo();
		GrupoDAO dao =new GrupoDAO(conn);
		m.setNome("Cristian");
		dao.save(m);
		
		
		
		
	}
	
	@Test
	void testFindAll() throws SQLException, ModelException {
		GrupoDAO dao =new GrupoDAO(conn);
		List<Grupo> m = dao.findAll();
		assertNotNull(m);
	}
	@Test
	void testFindById() throws SQLException, ModelException {
		GrupoDAO dao =new GrupoDAO(conn);
		Grupo m = dao.find(26);
		String expected="Cristian";
		String resulted = m.getNome();
		assertEquals(expected,resulted);
	
	}
	
	

}
