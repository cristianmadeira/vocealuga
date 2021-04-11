package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.UUID;

import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.model.Agencia;
import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AgenciaControllerTest {

    @Autowired
    private MockMvc mvc;
    
    private Agencia agencia;
    
    @Autowired
    private AgenciaRepository repository;
    
    private Agencia create() {
        Agencia a = new Agencia();
        a.setNome("FUNDACAO DE APOIO A ESCOLA TECNICA DO EST.RIO DE JANEIRO");
        a.setBairro("QUINTINO BOCAIUVA");
        a.setCep("21655340");
        a.setCnpj(getFakerCnpj());
        a.setLogradouro("R CLARIMUNDO DE MELO");
        a.setMunicipio("RIO DE JANEIRO");
        a.setNumero("847");
        a.setUf("RJ");
        return a;
    }
    private static  String getFakerCnpj(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,14);
    }

    @BeforeEach
    void testAgenciaController() {
        agencia = this.create();
        agencia = this.repository.save(this.agencia);
        
    }

    @Test
    @Order(2)
    void testCreate() throws Exception {
        this.mvc.perform(get("/agencias/create"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("agencia"))
            .andExpect(view().name("agencias/form"));
    }

    @Test
    @Order(3)
    void testStore() throws Exception {
        this.mvc.perform(post("/agencias")
            .param("cnpj", getFakerCnpj())
            .param("nome", "Teste nome mock")
            .param("cep", "12345678")
            .param("logradouro", "Teste nome mock")
            .param("numero", "Teste nome mock")
            .param("bairro", "Teste nome mock")
            .param("municipio", "Teste nome mock")
            .param("uf", "RJ")
            ).andExpect(status().isOk())
            .andExpect(model().attributeExists("success"))
            .andExpect(model().attribute("success", getSaveSuccessMessage("agência")))
            .andExpect(model().attribute("agencia", hasProperty("createdAt")));
            
    }

    @Test
    @Order(4)
    void testIndex() throws Exception {
        this.mvc.perform(get("/agencias"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("agencias"))
            .andExpect(model().attributeDoesNotExist("error"));



    }

    @Test
    @Order(5)
    void testEdit() throws Exception {
        this.mvc.perform(get("/agencias/{id}/edit", agencia.getId()))
            .andExpect(status().isOk())
            .andExpect(view().name("agencias/form"));

    }

    @Test
    @Order(6)
    void testUpdate() throws Exception {
        this.mvc.perform(
            post("/agencias/{id}/update", agencia.getId())
            .param("cnpj", getFakerCnpj())
            .param("nome", "Teste nome mock Update")
            .param("cep", "87654321")
            .param("logradouro", "Teste nome mock")
            .param("numero", "Teste nome mock")
            .param("bairro", "Teste nome mock")
            .param("municipio", "Teste nome mock")
            .param("uf", "ES"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"))
            .andExpect(model().attribute("success", getUpdateSuccessMessage("agência")));
    }

    @Test
    @Order(7)
    void testDelete() throws Exception {
        this.mvc.perform(post("/agencias/{id}/delete", agencia.getId()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"))
            .andExpect(model().attribute("success", getDeleteSuccessMessage("agência")));
    }

    @Test
    @Order(8)
    void testSaveEmptyName() throws Exception {
        this.mvc.perform(
            post("/agencias").param("nome", ""))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("agencia", "nome"));
            
    }

    @Test
    @Order(9)
    void testUpdateEmptyName() throws Exception {
        this.mvc.perform(post("/agencias/{id}/update", agencia.getId()).param("nome", ""))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("agencia", "nome"));
                
    }

    @Test
    @Order(10)
    void testEditInvalidId() throws Exception {
        this.mvc.perform(get("/agencias/{id}/edit", -1))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"));

    }

    @Test
    @Order(11)
    void testDeleteInvalidId() throws Exception {
        this.mvc.perform(post("/agencias/{id}/delete", -1))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"));

    }

}
