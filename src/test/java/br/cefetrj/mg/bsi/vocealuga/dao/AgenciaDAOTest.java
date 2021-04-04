package br.cefetrj.mg.bsi.vocealuga.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



@TestMethodOrder(OrderAnnotation.class)
public class AgenciaDAOTest {

    private static Agencia agencia = null;
    private static IDAO<Agencia> dao = null;

    private static Agencia create() {
        Agencia a = new Agencia();
        a.setNome("FUNDACAO DE APOIO A ESCOLA TECNICA DO EST.RIO DE JANEIRO");
        a.setBairro("QUINTINO BOCAIUVA");
        a.setCep("21655340");
        //a.setCnpj("31608763000152");
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

    @BeforeAll
    static void setUp() throws Exception {
        agencia = create();
        dao = new AgenciaDAOImpl();

    }

    @Test
    @Order(1)
    public void testSave() throws Exception {
        Agencia expected = dao.save(agencia);
        assertNotNull(expected);
        assertNotNull(expected.getCreatedAt());
        System.out.println(expected.getId());
        assertTrue(expected.getId() > 0);
        agencia = expected;
        
    }
    @Test
    @Order(2)
    public void testIfCanToSaveSameTheObject() throws Exception{
        assertThrows(SQLException.class, ()->{
            dao.save(agencia);
        });
    }


    @Test
    @Order(3)
    public void testFindById() throws Exception {
        Agencia expected  = dao.find(agencia.getId());
        assertTrue(expected.getNome().equals(agencia.getNome()));
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception{
        String updatedName =  "FAETEC";
        agencia.setNome(updatedName);
        Agencia expected = dao.update(agencia);
        assertNotNull(expected);
        assertNotNull(agencia.getUpdatedAt());
        assertTrue(expected.getNome().equals(updatedName));
        agencia = expected;
    }




    @Test
    @Order(5)
    public void testUpdateWithInvalidId() throws Exception{
        int invalidId = -1 ;
        Agencia invalidAgencia = new Agencia();
        invalidAgencia.setId(invalidId);
        assertThrows(SQLException.class, ()->{
            dao.update(invalidAgencia);
        });



    }

    @Test
    @Order(6)
    public void testDeleteWithInvalidId(){
        int invalidId = -1 ;
        assertThrows(SQLException.class,()->{
            dao.delete(invalidId);
        });
    }
    @Test
    @Order(7)
    public void testDelete() throws Exception{
        int validId = agencia.getId();
        Agencia expected = dao.delete(validId);
        assertNotNull(expected.getDeletedAt());
        agencia = expected;
    }

    @Test
    @Order(8)
    public void testFindByInvalidId(){
        int invalidId = -1;
        assertThrows(SQLException.class,()->{
            dao.find(invalidId);
        }) ;
    }

    @Test
    @Order(9)
    public void testFindAll() throws SQLException{
        int actual = dao.findAll().size();
        assertTrue(actual > 0);
    }
    @Test
    @Order(10)
    public void testGetLastId() throws SQLException{
        String wrongSql = "INSERT INTO agencias (VALUES)";
		Connection conn = ConnectionFactory.getInstance().getConn();
		PreparedStatement pst = conn.prepareStatement(wrongSql, PreparedStatement.RETURN_GENERATED_KEYS);
		assertThrows(SQLException.class,()->{
			dao.getLastId(pst);
		});
    }


}
