package br.cefetrj.mg.bsi.vocealuga.service;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;


public class GrupoServiceTest {

	private IService service = null;
	private Grupo grupo = null;
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		this.service = new GrupoServiceImpl();
		
	}

	@Test
	public void testSaveWithNameIsEmpty() throws SQLException, ModelException {
		exceptionRule.expect(ModelException.class);
		grupo = new Grupo();
		grupo.setNome(new String());
		this.service.save(grupo);
		exceptionRule.expectMessage("Nome do grupo não pode ser nulo");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
