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
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

@TestMethodOrder(OrderAnnotation.class)
class GrupoRepositoryTest {

	private static IGrupoRepository repository = null;
	private static Grupo grupo = null;

	private static Grupo create() {
		Grupo g = new Grupo();
		g.setId(0);
		g.setNome("ABC");
		return g;
	}

	@BeforeAll
	static void setUp() {
		repository = new GrupoRepositoryImpl();
		grupo = create();
	}

	@Test
	@Order(1)
	void testInstance() {
		assertNotNull(repository);
	}

	@Test
	@Order(2)
	void testSave() throws Exception {
		Grupo result = repository.save(grupo);
		assertNotNull(result.getCreatedAt());
		grupo = result;
	}

	@Test
	@Order(3)
	void testUpdate() throws Exception {
		grupo.setNome("CBA");
		Grupo result = repository.update(grupo);
		assertNotNull(result.getUpdatedAt());
		grupo = result;
	}

	@Test
	@Order(4)
	void testFindById() throws Exception {
		Grupo g = repository.findById(grupo.getId());
		assertEquals(g.getId(), grupo.getId());

	}

	@Test
	@Order(5)
	void testFindAll() throws Exception {
		List<Grupo> grupos = repository.findAll();
		for (Grupo g : grupos) {
			assertNull(g.getDeletedAt());
		}
	}

	@Test
	@Order(6)
	void testFindByNome() throws Exception {
		String nome = "CBA";
		Grupo g = repository.findByNome(nome);
		assertNotNull(g.getCreatedAt());

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
		Grupo result = repository.delete(grupo.getId());
		assertNotNull(result.getDeletedAt());
	}
}
