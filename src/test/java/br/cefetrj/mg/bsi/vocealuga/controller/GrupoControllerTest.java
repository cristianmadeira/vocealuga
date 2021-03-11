package br.cefetrj.mg.bsi.vocealuga.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class GrupoControllerTest {

	@Autowired
	private GrupoController controller;

	@Autowired
	private MockMvc mvc;
	private static List<Grupo> grupos = new ArrayList();
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
		.andExpect(view().name("grupos/form"));
	}

	
	@Test
	@Order(3)
	void testStore() throws Exception {
		this.mvc.perform(post("/grupos").param("nome","Teste nome mock"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("success"))
		.andExpect(model().attribute("success", getSaveSuccessMessage("grupo")));
	}
	@Test
	@Order(4)
	void testIndex() throws Exception {
		this.mvc.perform(get("/grupos"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("grupos"))
		.andExpect(model().attributeDoesNotExist("error"));

		final Model model = new ExtendedModelMap();
		controller.index(model);

		grupos = (List<Grupo>) model.asMap().get("grupos");
		for(Grupo g : grupos) {
			assertNotNull(g.getCreatedAt());
			assertNull(g.getDeletedAt());
		}



	}
	@Test
	@Order(5)
	void testEdit() throws Exception {
		Grupo g = grupos.get(grupos.size() - 1);
		assertNotNull(g);
		this.mvc.perform(get("/grupos/{id}/edit",g.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("grupos/form"));


	}

	@Test
	@Order(6)
	void testUpdate() throws Exception {
		Grupo g = grupos.get(grupos.size() - 1);
		assertNotNull(g);
		this.mvc.perform(
				post("/grupos/{id}/update",g.getId())
				.param("nome", "new Name")
				)
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("success"))
		.andExpect(model().attribute("success", getUpdateSuccessMessage("grupo")));
	}

	@Test
	@Order(7)
	void testDelete() throws Exception {
		Grupo g = grupos.get(grupos.size() - 1);
		assertNotNull(g);
		this.mvc.perform(post("/grupos/{id}/delete",g.getId()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("success"))
		.andExpect(model().attribute("success",getDeleteSuccessMessage("grupo")));
	}
	
	@Test
	@Order(8)
	void testSaveEmptyName() throws Exception{
		this.mvc.perform(post("/grupos").param("nome", ""))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"))
		.andExpect(model().attribute("error",getBlankFieldMessage("nome")))
		;
	}
	@Test
	@Order(9)
	void testUpdateEmptyName() throws Exception{
		Grupo g = grupos.get(grupos.size()-  1);
		assertNotNull(g);
		this.mvc.perform(post("/grupos/{id}/update",g.getId()).param("nome", ""))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"))
		.andExpect(model().attribute("error",getBlankFieldMessage("nome")))
		;
	}
	@Test
	@Order(10)
	void testEditInvalidId() throws Exception {
		this.mvc.perform(get("/grupos/{id}/edit",-1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"));
		
	}
	@Test
	@Order(11)
	void testDeleteInvalidId() throws Exception {
		this.mvc.perform(post("/grupos/{id}/delete",-1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"));
		
	}

}
