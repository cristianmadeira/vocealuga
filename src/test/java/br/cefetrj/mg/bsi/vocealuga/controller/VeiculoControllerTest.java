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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;
import br.cefetrj.mg.bsi.vocealuga.repository.VeiculoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class VeiculoControllerTest {
    
    @Autowired
    private VeiculoController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VeiculoRepository repository;

    private static Veiculo veiculo = new Veiculo();

    // @BeforeEach
    // public void setUp(){
    //     veiculo.setMarca("marca");
    //     veiculo = repository.save(veiculo);
    // }

    @Test
    @Order(1)
    void testVeiculoController() {
        assertNotNull(controller);
    }

    @Test
    @Order(2)
    void testCreate() throws Exception{
        this.mvc.perform(get("/veiculos/create"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("veiculo"))
        .andExpect(model().attributeExists("action"))
        .andExpect(model().attributeExists("method"))
        .andExpect(view().name("veiculos/form"));
    }

    @Test
    @Order(3)
    void testStore() throws Exception{ 
        this.mvc.perform(post("/veiculos")
        .param("placa",         "ABC1234")
        .param("cor",          "Vermelho")
        .param("kmRodado",       "101000")
        .param("marca",         "Porsche")
        .param("estaRevisado",    Boolean.toString(true))
        .param("disponivel",    Boolean.toString(false)))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"))
        .andExpect(model().attribute("success", getSaveSuccessMessage("veiculo")));
    }

    @Test
    @Order(5)
    void testIndex() throws Exception{
        this.mvc.perform(get("/veiculos"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("veiculos"))
        .andExpect(model().attributeDoesNotExist("error"));
    }

    @Test
    @Order(6)
    void testEdit() throws Exception {
        this.mvc.perform(get("/veiculos/{id}/edit", veiculo.getId()))
        .andExpect(status().isOk())
        .andExpect(view().name("veiculos/form"));
    }

    @Test
    @Order(7)
    void testUpdate() throws Exception {
        this.mvc.perform(post("/veiculos/{id}/update", veiculo.getId())
        .param("marca", "Marca nova"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"))
        .andExpect(model().attribute("success", getUpdateSuccessMessage("veiculo")));
    }

    @Test
    @Order(8)
    void testDelete() throws Exception {
        this.mvc.perform(post("/veiculos/{id}/delete", veiculo.getId()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"))
        .andExpect(model().attribute("success", getDeleteSuccessMessage("veiculo")));
    }
}
