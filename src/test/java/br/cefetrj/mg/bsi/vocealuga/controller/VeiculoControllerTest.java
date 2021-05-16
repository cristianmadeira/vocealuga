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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.cefetrj.mg.bsi.vocealuga.faker.VeiculoFaker;
import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;
import br.cefetrj.mg.bsi.vocealuga.repository.VeiculoRepository;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

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


    private  Veiculo veiculo = new Veiculo();
    @Autowired
    private WebApplicationContext context;

    private Veiculo create(){
        return new VeiculoFaker().create();
    }
    @BeforeEach
    public void setUp(){
        veiculo = repository.save(create());
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    
    @Test
    @WithMockUser()
    @Order(1)
    void testVeiculoController() {
        assertNotNull(controller);
    }

    @Test
    @WithMockUser()
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
    @WithMockUser()
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
    @WithMockUser()
    @Order(5)
    void testIndex() throws Exception{
        this.mvc.perform(get("/veiculos"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("veiculos"))
        .andExpect(model().attributeDoesNotExist("error"));
    }

    @Test
    @WithMockUser()
    @Order(6)
    void testEdit() throws Exception {
        this.mvc.perform(get("/veiculos/{id}/edit", veiculo.getId()))
        .andExpect(status().isOk())
        .andExpect(view().name("veiculos/form"));
    }

    @Test
    @WithMockUser()
    @Order(7)
    void testUpdate() throws Exception {
        this.mvc.perform(post("/veiculos/{id}/update", veiculo.getId())
        .param("placa",         "spl0000")
        .param("cor",          "Vermelho Teste")
        .param("kmRodado",       "101000")
        .param("marca",         "Porsche")
        .param("estaRevisado",    Boolean.toString(false))
        .param("disponivel",    Boolean.toString(false))
        .param("agencia",veiculo.getAgencia().getId().toString())
        .param("grupo",veiculo.getGrupo().getId().toString())
        .param("created_at",veiculo.getCreatedAt().toString()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"))
        .andExpect(model().attribute("success", getUpdateSuccessMessage("veiculo")));
    }

    @Test
    @WithMockUser()
    @Order(8)
    void testDelete() throws Exception {
        this.mvc.perform(post("/veiculos/{id}/delete", veiculo.getId()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"))
        .andExpect(model().attribute("success", getDeleteSuccessMessage("veiculo")));
    }
}
