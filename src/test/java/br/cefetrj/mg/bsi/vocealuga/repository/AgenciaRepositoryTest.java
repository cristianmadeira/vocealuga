package br.cefetrj.mg.bsi.vocealuga.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import br.cefetrj.mg.bsi.vocealuga.model.Agencia;


@TestMethodOrder(OrderAnnotation.class)
public class AgenciaRepositoryTest {
    
    private static Agencia agencia =null, agencia2 = null;
    private static IAgenciaRepository repo = null;
    
    private static Agencia create(){
        Agencia a = new Agencia();
        a.setNome("CEFET/RJ");
        a.setBairro("QUINTINO BOCAIUVA");
        a.setCep("21655340");
        //a.setCnpj("31608763000145");
        a.setCnpj(getFakerCnpj());
        a.setLogradouro("R CLARIMUNDO DE MELO");
        a.setMunicipio("RIO DE JANEIRO");
        a.setNumero("847");
        a.setUf("RJ");
        return a ;
    }
    private static  String getFakerCnpj(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,14);
    }

    @BeforeAll
    static void setUp(){
        repo =  new  AgenciaRepositoryImpl();
        agencia = create();
        agencia2 =  create();
    }

    @Test
    @Order(1)
    @DisplayName("Teste para  salvar no repositório")
    public void testSave() throws Exception{
       Agencia expected =  repo.save(agencia);
       assertNotNull(expected);
       assertNotNull(expected.getCreatedAt());
       assertTrue(expected.getId() > 0);
       agencia = expected;

       expected =  repo.save(agencia2);
       assertNotNull(expected);
       assertNotNull(expected.getCreatedAt());
       assertTrue(expected.getId() > 0);
       agencia2 = expected;

    }

    @Test
    @Order(2)
    @DisplayName("Teste para atualizar a agência no repositório")
    public void testUpdate() throws Exception{
        String updatedBairro = "Maria da Graça";
        agencia.setBairro(updatedBairro);
        Agencia expected = repo.update(agencia);
        assertNotNull(expected);
        assertNotNull(expected.getUpdatedAt());
        assertTrue(expected.getId() > 0);
        assertTrue(expected.getBairro().equals(updatedBairro));
        agencia = expected;
    }

    @Test
    @Order(3)
    @DisplayName("Teste para procurar por Id no repositrio agência")
    public void testfindById() throws Exception{
        int id = agencia.getId();
        Agencia expected = repo.findById(id);
        assertNotNull(expected);
        assertNull(expected.getDeletedAt());

        
    }

    @Test
    @Order(4)
    @DisplayName("Teste para excluir a agência no repositório")
    public void testDelete( ) throws Exception{
        int id = agencia.getId();
        Agencia expected = repo.delete(id);
        assertNotNull(expected);
        assertNotNull(expected.getDeletedAt());
        agencia = expected;
    }
    @Test
    @Order(5)
    @DisplayName("teste para procurar por Id excluído no repositrio agência")
    public void testfindByIdAgenciaDeleted() throws Exception{
        int id = agencia.getId();
        assertThrows(Exception.class, ()->{
            repo.findById(id);
        });
    }
    @Test
    @Order(6)
    @DisplayName("Teste para procurar todos que não foram excluídos no repositório agência")
    public void testfindAll() throws Exception{
        List<Agencia> agencias = repo.findAll();
        assertFalse(agencias.isEmpty());
        for(int i = 0; i < agencias.size() ;i++){
            Agencia agencia = agencias.get(i);
            assertNull(agencia.getDeletedAt());

        }
    }


}
