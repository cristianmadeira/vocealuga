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

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.cefetrj.mg.bsi.vocealuga.model.Agencia;
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.FuncionarioRepository;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class CargoControllerTest {

    private MockMvc mvc;
    private Cargo cargo;
    private CargoRepository repository;
    private AgenciaRepository agenciaRepository;
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    public CargoControllerTest(CargoRepository repository, MockMvc mvc, AgenciaRepository agenciaRepository, FuncionarioRepository funcionarioRepository){
        this.mvc = mvc;
        this.repository = repository;
        this.agenciaRepository = agenciaRepository;
        this.funcionarioRepository= funcionarioRepository;
        this.cargo = new Cargo();
        this.cargo.setNome("Cargo Válido");
        this.cargo = this.repository.save(cargo);
    }

    private Cargo attachFuncionario(){
        Agencia a = new Agencia();
        Funcionario f = new Funcionario();
        
        a = new PodamFactoryImpl().manufacturePojo(Agencia.class);
        f = new PodamFactoryImpl().manufacturePojo(Funcionario.class);
        
        a.setDeletedAt(null);
        a = agenciaRepository.save(a);
        
        f.setEmail("fakeremail@gmail.com");
        f.setCargo(cargo);
        f.setAgencia(a);
        f.setDeletedAt(null);
        f = this.funcionarioRepository.save(f);
        
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(f);
        cargo.setFuncionarios(funcionarios);
        
        return cargo;

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
        Cargo cargo = this.attachFuncionario();
        this.mvc
            .perform(post("/cargos/{id}/delete",cargo.getId()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"));
    }

}
