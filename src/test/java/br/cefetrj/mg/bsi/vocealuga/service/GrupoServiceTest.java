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

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

@TestMethodOrder(OrderAnnotation.class)
public class GrupoServiceTest {

	private static IGrupoService service = null;
	private static Grupo grupo = null;

	private static Grupo create() {
		Grupo g = new Grupo();
		g.setId(0);
		g.setNome("AB");
		return g;
	}

	@BeforeAll
	public static void setUp() throws Exception {
		service = new GrupoServiceImpl();
		grupo = create();

	}

	@Test
	@Order(1)
	final void testSave() throws Exception {
		Grupo g = service.save(grupo);
		assertNotNull(g.getCreatedAt());
		grupo = g;
	}

	@Test
	@Order(2)
	public void testSaveWithEmptyName() throws Exception {
		Grupo g = new Grupo();
		g.setNome("");
		assertThrows(Exception.class, () -> {
			service.save(g);
		});

	}

	@Test
	@Order(3)
	public void testUpdate() throws Exception {
		grupo.setNome("Grupo Serivce Test");
		Grupo g = service.update(grupo);
		assertNotNull(g.getUpdatedAt());
		grupo = g;
	}

	@Test
	@Order(4)
	public void testUpdateInvalidsNameandId() throws Exception {
		/**
		 * p = Name is blank q = Id less or equal than zero p v q = s v v v v f v f v v
		 * f f f
		 */
		Grupo g = new Grupo();
		g.setNome("");
		g.setId(-1);
		assertThrows(Exception.class, () -> {
			service.update(g);
		});
		g.setNome("asdasda");
		g.setId(0);
		assertThrows(Exception.class, () -> {
			service.update(g);
		});
		g.setNome("");
		g.setId(1);
		assertThrows(Exception.class, () -> {
			service.update(g);
		});

	}

	void testUpdateIdLessThanZero() throws Exception {
		Grupo g = new Grupo();
		g.setId(-1);
		g.setNome("abcd");
		assertThrows(Exception.class, () -> {
			service.update(g);
		});
	}

	@Test
	@Order(5)
	public void testFindById() throws Exception {
		Grupo g = service.findById(grupo.getId());
		assertNull(g.getDeletedAt());
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
		List<Grupo> grupos = service.findAll();
		for (Grupo g : grupos) {
			assertNull(g.getDeletedAt());
		}
	}

	@Test
	@Order(8)
	public void testDelete() throws Exception {
		Grupo g = service.delete(grupo.getId());
		assertNotNull(g.getDeletedAt());

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
