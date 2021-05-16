package br.cefetrj.mg.bsi.vocealuga.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.cefetrj.mg.bsi.vocealuga.faker.OficinaFaker;
import br.cefetrj.mg.bsi.vocealuga.model.Oficina;
import br.cefetrj.mg.bsi.vocealuga.repository.OficinaRepository;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class OficinaControllerTest {
    
    private MockMvc mvc;
    private OficinaRepository repository;
    private Oficina oficina;
    private WebApplicationContext context;

    @Autowired
    public OficinaControllerTest(
        MockMvc mvc,
        OficinaRepository repository,
        WebApplicationContext context
        ){
        this.mvc = mvc;
        this.repository = repository;
        this.context = context;
        this.oficina = new OficinaFaker().create();
        oficina = this.repository.save(oficina);
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }
    @WithMockUser()
    @Test
    @Order(1)
    public void testIndex() throws Exception{
        this.mvc.perform(get("/oficinas"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("oficinas"))
            .andExpect(view().name("oficinas/index"));
        ;
    }
@WithMockUser()
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
@WithMockUser()
    @Test
    @Order(3)
    public void testsaveInvalidOficina() throws Exception{
        this.mvc.perform(post("/oficinas").param("nome",""))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("oficina", "nome"));
    }
@WithMockUser()
    @Test
    @Order(4)
    public void testeEditar() throws Exception{
        this.mvc.perform(get("/oficinas/{id}/edit",this.oficina.getId()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("oficina"))
            .andExpect(view().name("oficinas/form"));
    }
    @WithMockUser()
    @Test
    @Order(5)
    public void testeEditarComIdInvalido() throws Exception{
        this.mvc.perform(get("/oficinas/{id}/edit",-1))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"))
            .andExpect(view().name("oficinas/index"));
    }
@WithMockUser()
    @Test
    @Order(6)
    public void testUpdate() throws Exception{
        this.oficina.setEmail("cristianmadeira1106@gmail.com");
        this.mvc.perform(post("/oficinas/{id}/update",this.oficina.getId())
        .param("nome","Nome Atualizado")
        .param("email","emailatualizado@gmail.com")
        .param("telefone","12345678910"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"))
            .andExpect(view().name("oficinas/index"));
    }
@WithMockUser()
    @Test
    @Order(7)
    public void testUpdateWithInvalidId() throws Exception{
        this.mvc.perform(post("/oficinas/{id}/update",this.oficina.getId())
        .param("nome","")
        .param("email","emailatualizado@gmail.com")
        .param("telefone","12345678910"))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("oficina","nome"))
            .andExpect(view().name("oficinas/form"));
    }
@WithMockUser()
    @Test
    @Order(8)
    public void testDeleteWithValidId() throws Exception{
        this.mvc
        .perform(post("/oficinas/{id}/delete", oficina.getId()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"));
    }
@WithMockUser()
    @Test
    @Order(9)
    public void testDeleteWithInvalidId() throws Exception{
        this.mvc
        .perform(post("/oficinas/{id}/delete", -1))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("error"));
    }

}
