package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AgenciaControllerTest {

    @Autowired
    private MockMvc mvc;
    
    private  Agencia agencia, agenciaWithEmployee, unsavedAgencia;
    
    @Autowired
    private AgenciaRepository repository;
    
    @BeforeEach
    public void setUp(){
        unsavedAgencia =  createAgencia();
        agencia = this.repository.save(createAgencia());
        Cargo c1 = createCargo();

        agenciaWithEmployee = createAgencia();

        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario f1= createFuncionario();
        Funcionario f2= createFuncionario();
        
        f1.setAgencia(agenciaWithEmployee);
        f1.setCargo(c1);
        f2.setAgencia(agenciaWithEmployee);
        f2.setCargo(c1);
        
        funcionarios.add(f1);
        funcionarios.add(f2);
        
        agenciaWithEmployee.setFuncionarios(funcionarios);
        
        agenciaWithEmployee  = this.repository.save(agenciaWithEmployee);
        
    }
    private Agencia createAgencia(){
        return new AgenciaFaker().create();
    }
    private Funcionario createFuncionario(){
        return new FuncionarioFaker().create();
    }
    private Cargo createCargo(){
        return new CargoFaker().create();
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
            .param("cnpj", unsavedAgencia.getCnpj())
            .param("nome", unsavedAgencia.getNome())
            .param("cep", unsavedAgencia.getCep())
            .param("logradouro", unsavedAgencia.getLogradouro())
            .param("numero", unsavedAgencia.getNumero())
            .param("bairro", unsavedAgencia.getBairro())
            .param("municipio", unsavedAgencia.getMunicipio())
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
            .andExpect(view().name("agencias/index"));



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

        Agencia updatedAgencia = agencia;
        this.mvc.perform(
            post("/agencias/{id}/update", agencia.getId())
            .param("cnpj", updatedAgencia.getCnpj())
            .param("nome", updatedAgencia.getNome())
            .param("cep", updatedAgencia.getCep())
            .param("logradouro", updatedAgencia.getLogradouro())
            .param("numero", updatedAgencia.getNumero())
            .param("bairro", updatedAgencia.getBairro())
            .param("municipio", updatedAgencia.getMunicipio())
            .param("uf", "RJ"))
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

    @Test
    @Order(12)
    public void testDeleteAgenciaWithEmployee() throws Exception{
        this.mvc
        .perform(post("/agencias/{id}/delete",agenciaWithEmployee.getId()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("error"));
    }

}
