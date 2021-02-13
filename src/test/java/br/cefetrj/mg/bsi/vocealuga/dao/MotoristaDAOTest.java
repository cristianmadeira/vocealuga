package br.cefetrj.mg.bsi.vocealuga.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.config.ConnectionType;
import br.cefetrj.mg.bsi.vocealuga.model.Motorista;

class MotoristaDAOTest {

	private Connection conn = ConnectionFactory.getInstance(ConnectionType.EXTERNAL.value).getConn();
	@Test
	void testSave() {
		Motorista m = new Motorista();
		MotoristaDAO dao =new MotoristaDAO(conn);
		m.setNome("Cristian");
		boolean result = dao.save(m);
		assertTrue(result);
		
		
		
	}
	
	@Test
	void testFindAll() {
		MotoristaDAO dao =new MotoristaDAO(conn);
		List<Motorista> m = dao.findAll();
		assertNotNull(m);
	}
	@Test
	void testFindById() {
		MotoristaDAO dao =new MotoristaDAO(conn);
		Motorista m = dao.find(1);
		String expected="Cristian";
		String resulted = m.getNome();
		assertEquals(expected,resulted);
	
	}
	
	

}
