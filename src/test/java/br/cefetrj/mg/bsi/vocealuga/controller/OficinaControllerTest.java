package br.cefetrj.mg.bsi.vocealuga.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.faker.OficinaFaker;
import br.cefetrj.mg.bsi.vocealuga.model.Oficina;
import br.cefetrj.mg.bsi.vocealuga.repository.OficinaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class OficinaControllerTest {
    
    private MockMvc mvc;
    private OficinaRepository repository;
    private Oficina oficina;
    
    @Autowired
    public OficinaControllerTest(MockMvc mvc, OficinaRepository repository){
        this.mvc = mvc;
        this.repository = repository;
        this.oficina = new OficinaFaker().create();
        oficina = this.repository.save(oficina);
    }

    @Test
    @Order(1)
    public void testIndex() throws Exception{
        this.mvc.perform(get("/oficinas"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("oficinas"))
            .andExpect(view().name("oficinas/index"));
        ;
    }

    @Test
    @Order(2)
    public void testSave() throws Exception{
        this.mvc.perform(post("/oficinas")
        .param("nome","Nome Oficina Teste")
        .param("email","testeemail@gmail.com")
        .param("telefone","2112345678"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"));
    }
    @Test
    @Order(3)
    public void testsaveInvalidOficina() throws Exception{
        this.mvc.perform(post("/oficinas").param("nome",""))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("oficina", "nome"));
    }


}
