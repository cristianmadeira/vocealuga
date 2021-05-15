package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
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
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class CargoControllerTest {

    private MockMvc mvc;
    private Cargo cargo, cargoWithEmployee;
    private CargoRepository repository;
    
    

    @Autowired
    public CargoControllerTest(CargoRepository repository, MockMvc mvc ){
        this.repository = repository;
        this.mvc = mvc;
        
        
    }

    @BeforeEach
    public void setUp(){
        cargo =  this.repository.save(createCargo());
        
        cargoWithEmployee = createCargo();
        
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario f1 = createFuncionario();
        f1.setAgencia(createAgencia());
        f1.setCargo(cargoWithEmployee);
        funcionarios.add(f1);
        
        cargoWithEmployee.setFuncionarios(funcionarios);
        cargoWithEmployee  = this.repository.save(cargoWithEmployee);
    }
    private Cargo createCargo(){
        return new CargoFaker().create();
    }
    
    private Funcionario createFuncionario(){
        return new FuncionarioFaker().create();
    }
    private Agencia createAgencia(){
        return new AgenciaFaker().create();
    }
    @Test
    @Order(2)
    void testCreate() throws Exception {
        this.mvc.perform(get("/cargos/create"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("cargo"))
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



    }

    @Test
    @Order(5)
    void testEdit() throws Exception {
        this.mvc.perform(get("/cargos/{id}/edit", cargo.getId())).andExpect(status().isOk())
                .andExpect(view().name("cargos/form"));

    }

    @Test
    @Order(6)
    void testUpdate() throws Exception {
        this.mvc.perform(
            post("/cargos/{id}/update", cargo.getId()).param("nome", "new Name")).
            andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", getUpdateSuccessMessage("cargo")));
    }

    @Test
    @Order(7)
    void testDelete() throws Exception {
        this.mvc.perform(post("/cargos/{id}/delete", cargo.getId())).andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", getDeleteSuccessMessage("cargo")));
    }

    @Test
    @Order(8)
    void testSaveEmptyName() throws Exception {
        this.mvc.perform(
            post("/cargos").param("nome", ""))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("cargo", "nome"));
            
    }

    @Test
    @Order(9)
    void testUpdateEmptyName() throws Exception {
        this.mvc.perform(post("/cargos/{id}/update", cargo.getId()).param("nome", ""))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("cargo", "nome"));
                
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
    @Test
    @Order(12)
    void testDeleteCargoWithEmployee() throws Exception{
        this.mvc
            .perform(post("/cargos/{id}/delete",cargoWithEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"));
    }

}
