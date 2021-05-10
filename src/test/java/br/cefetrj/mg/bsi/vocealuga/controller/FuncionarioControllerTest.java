package br.cefetrj.mg.bsi.vocealuga.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.faker.AgenciaFaker;
import br.cefetrj.mg.bsi.vocealuga.faker.CargoFaker;
import br.cefetrj.mg.bsi.vocealuga.faker.FuncionarioFaker;
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.repository.FuncionarioRepository;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class FuncionarioControllerTest {
    
    
    private MockMvc mvc ;
    private FuncionarioRepository repository;
    private static Funcionario funcionario;

    

    @Autowired
    public FuncionarioControllerTest(FuncionarioRepository repository, MockMvc mvc){
        this.repository = repository;
        this.mvc = mvc;
        funcionario = createFuncionario();
        funcionario = saveFuncionario(funcionario);
    }

    private  Funcionario createFuncionario(){
        FuncionarioFaker faker = new FuncionarioFaker();
        Funcionario f = faker.create();
        f.setAgencia(createAgencia());
        f.setCargo(createCargo());
        return f;
    }
    private Funcionario saveFuncionario(Funcionario f){
        return repository.save(f);
    }
    private Cargo createCargo(){
        return new CargoFaker().create();
    }
    private Agencia createAgencia(){
        return new AgenciaFaker().create();
    }
    @Test
    @Order(1)
    public void testIndex() throws Exception{
        this.mvc.perform(get("/funcionarios"))
        .andExpect(model().attribute("funcionarios", allOf(hasItem(hasProperty("nome",equalTo(funcionario.getNome()))))))
        .andExpect(view().name("funcionarios/index"));    
    }
    @Test
    @Order(2)
    public void testStoreWithValidEmployee() throws Exception {
        Funcionario f = createFuncionario();
        f.setAgencia(funcionario.getAgencia());
        f.setCargo(funcionario.getCargo());
        
        this.mvc.
            perform(post("/funcionarios")
            .param("nome",f.getNome())
            .param("cpf",f.getCpf())
            .param("email",f.getEmail())
            .param("agencia",f.getAgencia().getId().toString())
            .param("cargo",f.getCargo().getId().toString()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"));
    }
    @Test
    @Order(3)
    public void testStoreWithInvalidEmployee() throws Exception{
        this.mvc
        .perform(post("/funcionarios").param("email",""))
        .andExpect(model().attributeHasFieldErrors("funcionario","email"));
    }

    @Test
    @Order(4)
    public void testCreate() throws Exception{
        this.mvc
        .perform(get("/funcionarios/create"))
        .andExpect(status().isOk())
        .andExpect(view().name("funcionarios/form"))
        .andExpect(model().attributeExists("funcionario"))
        .andExpect(model().attributeExists("agencias"))
        .andExpect(model().attributeExists("cargos"));
    }

    @Test
    @Order(5)
    public void testEditWithValidId() throws Exception{
        this.mvc
        .perform(get("/funcionarios/{id}/edit",funcionario.getId()))
        .andExpect(status().isOk())
        .andExpect(view().name("funcionarios/form"))
        .andExpect(model().attributeExists("funcionario"))
        .andExpect(model().attributeExists("agencias"))
        .andExpect(model().attributeExists("cargos"));
    }

    @Test
    @Order(6)
    public void testEditWithIndvalidId() throws Exception{
        int invalidId = -1;
        this.mvc
        .perform(get("/funcionarios/{id}/edit",invalidId))
        .andExpect(model().attributeExists("error"));
        
    }
    @Test
    @Order(7)
    public void testupdateWithValidEmployee() throws Exception {
        Funcionario f = funcionario;
        String updatedName = "Novo Funcionário atualizado";
        f.setNome(updatedName);
        f.setCpf(UUID.randomUUID().toString().substring(0,11));
        this.mvc.
        perform(post("/funcionarios/{id}/update",f.getId())
            .param("nome",f.getNome())
            .param("cpf",f.getCpf())
            .param("email",f.getEmail())
            .param("agencia",String.valueOf(f.getAgencia().getId()))
            .param("cargo",f.getCargo().getId().toString()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("success"));
            
    }
    @Test
    @Order(8)
    public void testupdateWithIndalidEmployee() throws Exception {
        Funcionario f = funcionario;
        f.setNome("");
        f.setCpf(UUID.randomUUID().toString().substring(0,11));
        this.mvc.
            perform(post("/funcionarios/{id}/update",f.getId())
            .param("nome",f.getNome())
            .param("cpf",f.getCpf())
            .param("email",f.getEmail())
            .param("agencia",String.valueOf(f.getAgencia().getId()))
            .param("cargo",f.getCargo().getId().toString()))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("funcionario", "nome"));
    }

    @Test
    @Order(9)
    public void testDeleteWithValidId() throws Exception{
        this.mvc
        .perform(post("/funcionarios/{id}/delete", funcionario.getId()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("success"));
    }
    @Test
    @Order(10)
    public void testDeleteWithInvalidId() throws Exception{
        this.mvc
        .perform(post("/funcionarios/{id}/delete", -1))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("error"));
    }


}

