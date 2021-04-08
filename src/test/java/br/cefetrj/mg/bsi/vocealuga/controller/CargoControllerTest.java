package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getBlankFieldMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class CargoControllerTest {

    @Autowired
    private CargoController controller;

    @Autowired
    private MockMvc mvc;
    private static List<Cargo> cargos = new ArrayList<>();

    @Test
    @Order(1)
    void testCargoController() {
        assertNotNull(controller);
    }

    @Test
    @Order(2)
    void testCreate() throws Exception {

        this.mvc.perform(get("/cargos/create")).andExpect(status().isOk()).andExpect(model().attributeExists("cargo"))
                .andExpect(view().name("cargos/form"));
    }

    @Test
    @Order(3)
    void testStore() throws Exception {
        this.mvc.perform(post("/cargos").param("nome", "Teste nome mock")).andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", getSaveSuccessMessage("cargo")));
    }

    @Test
    @Order(4)
    void testIndex() throws Exception {
        this.mvc.perform(get("/cargos")).andExpect(status().isOk()).andExpect(model().attributeExists("cargos"))
                .andExpect(model().attributeDoesNotExist("error"));

        final Model model = new ExtendedModelMap();
        controller.index(model);


    }

    @Test
    @Order(5)
    void testEdit() throws Exception {
        Cargo c = cargos.get(cargos.size() - 1);
        assertNotNull(c);
        this.mvc.perform(get("/cargos/{id}/edit", c.getId())).andExpect(status().isOk())
                .andExpect(view().name("cargos/form"));

    }

    @Test
    @Order(6)
    void testUpdate() throws Exception {
        Cargo c = cargos.get(cargos.size() - 1);
        assertNotNull(c);
        this.mvc.perform(post("/cargos/{id}/update", c.getId()).param("nome", "new Name")).andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", getUpdateSuccessMessage("cargo")));
    }

    @Test
    @Order(7)
    void testDelete() throws Exception {
        Cargo c = cargos.get(cargos.size() - 1);
        assertNotNull(c);
        this.mvc.perform(post("/cargos/{id}/delete", c.getId())).andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", getDeleteSuccessMessage("cargo")));
    }

    @Test
    @Order(8)
    void testSaveEmptyName() throws Exception {
        this.mvc.perform(post("/cargos").param("nome", "")).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", getBlankFieldMessage("nome")));
    }

    @Test
    @Order(9)
    void testUpdateEmptyName() throws Exception {
        Cargo c = cargos.get(cargos.size() - 1);
        assertNotNull(c);
        this.mvc.perform(post("/cargos/{id}/update", c.getId()).param("nome", "")).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", getBlankFieldMessage("nome or id")));
    }

    @Test
    @Order(10)
    void testEditInvalidId() throws Exception {
        this.mvc.perform(get("/cargos/{id}/edit", -1)).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));

    }

    @Test
    @Order(11)
    void testDeleteInvalidId() throws Exception {
        this.mvc.perform(post("/cargos/{id}/delete", -1)).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));

    }

}
