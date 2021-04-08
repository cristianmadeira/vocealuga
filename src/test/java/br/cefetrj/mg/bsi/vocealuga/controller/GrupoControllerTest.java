package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import br.cefetrj.mg.bsi.vocealuga.repository.GrupoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class GrupoControllerTest {

	@Autowired
	private GrupoController controller;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private GrupoRepository repository;

	private static Grupo grupo = new Grupo();

	@BeforeEach
	public  void setUp(){
		grupo.setNome("Nome Válido");
		grupo = repository.save(grupo);
	}
	@Test
	@Order(1)
	void testGrupoController() {
		assertNotNull(controller);
	}

	@Test
	@Order(2)
	void testCreate() throws Exception {
		this.mvc.perform(get("/grupos/create"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("grupo"))
			.andExpect(model().attributeExists("action"))
			.andExpect(model().attributeExists("method"))
			.andExpect(view().name("grupos/form"));
	}

	@Test
	@Order(3)
	void testStore() throws Exception {
		this.mvc.perform(post("/grupos")
			.param("nome", "Teste nome mock"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("success"))
			.andExpect(model().attribute("success", getSaveSuccessMessage("grupo")));
	}

	@Test
	@Order(4)
	void testStoreWithInvalidParams() throws Exception{
		this.mvc.perform(post("/grupos")
		.param("Nome","1234")
		)
		.andExpect(model().attributeHasFieldErrors("grupo", "nome"))
		.andExpect(status().isOk())
		;
	}

	@Test
	@Order(5)
	void testIndex() throws Exception {
		this.mvc.perform(get("/grupos"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("grupos"))
			.andExpect(model().attributeDoesNotExist("error"));
			
		
		
	}

	@Test
	@Order(6)
	void testEdit() throws Exception {
		this.mvc.perform(get("/grupos/{id}/edit", grupo.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("grupos/form"));

	}

	@Test
	@Order(7)
	void testUpdate() throws Exception {
		this.mvc.perform(post("/grupos/{id}/update", grupo.getId())
			.param("nome", "new Name"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("success"))
			.andExpect(model().attribute("success", getUpdateSuccessMessage("grupo")));
	}

	@Test
	@Order(8)
	void testDelete() throws Exception {
		this.mvc.perform(post("/grupos/{id}/delete", grupo.getId())).andExpect(status().isOk())
				.andExpect(model().attributeExists("success"))
				.andExpect(model().attribute("success", getDeleteSuccessMessage("grupo")));
	}

	@Test
	@Order(10)
	void testUpdateEmptyName() throws Exception {
		this.mvc.perform(post("/grupos/{id}/update", grupo.getId())
			.param("nome", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("grupo", "nome"));
			
	}

	@Test
	@Order(11)
	void testEditInvalidId() throws Exception {
		this.mvc.perform(get("/grupos/{id}/edit", -1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("error"));

	}

	@Test
	@Order(12)
	void testDeleteInvalidId() throws Exception {
		this.mvc.perform(post("/grupos/{id}/delete", -1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("error"));

	}

}
