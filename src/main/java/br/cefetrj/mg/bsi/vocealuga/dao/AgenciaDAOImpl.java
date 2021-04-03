package br.cefetrj.mg.bsi.vocealuga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.exception.EmptyListException;
import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;
import java.sql.Timestamp;

import static br.cefetrj.mg.bsi.vocealuga.utils.Utils.converterLocalDateTimeToTimestamp;
import static br.cefetrj.mg.bsi.vocealuga.utils.Utils.converterTimestampToLocalDateTime;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

public class AgenciaDAOImpl implements IDAO<Agencia> {

    private static ConnectionFactory factory = ConnectionFactory.getInstance();
    private static final ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private static final Logger logger = LogManager.getLogger(AgenciaDAOImpl.class);
    private static final String TITLE = "Agência";

    @Override
    public Agencia save(Agencia o) throws SQLException {
        Connection conn = factory.getConn();
        String sql = "INSERT INTO agencias(nome,cnpj,cep,logradouro,numero,bairro,municipio,uf,created_at) VALUES(?,?,?,?,?,?,?,?,?);";
        conn.setAutoCommit(false);
        try (PreparedStatement pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, o.getNome());
            pst.setString(2, o.getCnpj());
            pst.setString(3, o.getCep());
            pst.setString(4, o.getLogradouro());
            pst.setString(5, o.getNumero());
            pst.setString(6, o.getBairro());
            pst.setString(7, o.getMunicipio());
            pst.setString(8, o.getUf());
            o.setCreatedAt((LocalDateTime.now(ZONE_ID)));
            pst.setTimestamp(9, converterLocalDateTimeToTimestamp(o.getCreatedAt()));
            pst.executeUpdate();
            conn.commit();
            o.setId(this.getLastId(pst));
            logger.debug(getSaveSuccessMessage(TITLE));
            return o;
        } catch (SQLException e) {
            conn.rollback();
            logger.error(getSaveErrorMessage(TITLE, e.getMessage()));
            throw new SQLException(e);

        }

    }

    @Override
    public Agencia update(Agencia o) throws SQLException {
        Connection conn = factory.getConn();
        String sql = "UPDATE agencias SET nome = ?, cnpj = ?, cep = ?, logradouro = ?, numero = ?, bairro = ?, municipio = ?, uf = ?, updated_at = ? WHERE id = ? ";
        conn.setAutoCommit(false);
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            Agencia oldAgencia = this.find(o.getId());
            pst.setString(1, o.getNome());
            pst.setString(2, o.getCnpj());
            pst.setString(3, o.getCep());
            pst.setString(4, o.getLogradouro());
            pst.setString(5, o.getNumero());
            pst.setString(6, o.getBairro());
            pst.setString(7, o.getMunicipio());
            pst.setString(8, o.getUf());
            o.setUpdatedAt((LocalDateTime.now(ZONE_ID)));
            pst.setTimestamp(9, converterLocalDateTimeToTimestamp(o.getUpdatedAt()));
            pst.setInt(10,oldAgencia.getId());
            pst.executeUpdate();
            conn.commit();
            logger.debug(getUpdateSuccessMessage(TITLE));
            return o;
        } catch (SQLException | InvalidIdException e) {
            conn.rollback();
            logger.error(getUpdateErrorMessage(TITLE, e.getMessage()));
            throw new SQLException(e);

        }
    }

    @Override
    public Agencia delete(int id) throws SQLException {
        String sql = "UPDATE agencias SET  deleted_at = ? WHERE id = ?";
        Connection conn = factory.getConn();
        conn.setAutoCommit(false);
        try(PreparedStatement pst = conn.prepareStatement(sql);) {
            Agencia agencia = this.find(id);
            agencia.setDeletedAt(LocalDateTime.now());
            pst.setTimestamp(1,converterLocalDateTimeToTimestamp(agencia.getDeletedAt()));
            pst.setInt(2,agencia.getId());
            pst.executeUpdate();
            conn.commit();
            return agencia;
        } catch (SQLException | InvalidIdException e) {
            conn.rollback();
            throw new SQLException(e);
        }
        
    }

    @Override
	public Agencia find(int id) throws SQLException, InvalidIdException {
		Connection conn = factory.getConn();
		Agencia a = null;
		String sql = "SELECT * FROM agencias WHERE id = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (!rs.next())
					throw new InvalidIdException(id);
				else
					a = this.getFilledObject(rs);
			}
			return a;
		} catch (SQLException e) {
			logger.error("findById:".concat(e.getMessage()));
			throw new SQLException(e.getMessage());
		}
    }

    @Override
    public List<Agencia> findAll() throws SQLException {
        String sql  = "SELECT * FROM agencias";
        Connection conn = factory.getConn();
        conn.setAutoCommit(false);
        List<Agencia> agencias  = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
        ){
            while(rs.next()){
                agencias.add(this.getFilledObject(rs));
            }
            if(agencias.isEmpty())
                throw new EmptyListException(getEmptyListMessage("agências"));
            
            return agencias;
        }catch(SQLException e){
            throw new SQLException(e);
        }
        catch(EmptyListException e ){
            throw new SQLException(e);
        }
        
    }

    @Override
    public int getLastId() throws SQLException {
        return -1;
        
    }

    public int getLastId(PreparedStatement pst ) throws SQLException {
        int id = -1;
        try(ResultSet rs = pst.getGeneratedKeys()){
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch(SQLException e){
            throw new SQLException(e);
        }
        return id;
        
    }
    @Override
    public Agencia getFilledObject(ResultSet rs) throws SQLException {
        Timestamp aux = null;
        Agencia a = new Agencia();
        a.setId(rs.getInt("id"));
        a.setNome(rs.getString("nome"));
        a.setBairro(rs.getString("bairro"));
        a.setCep(rs.getString("cep"));
        a.setCnpj(rs.getString("cnpj"));
        a.setCreatedAt(converterTimestampToLocalDateTime(rs.getTimestamp("created_at")));
        aux = rs.getTimestamp("deleted_at");
        if( aux != null)
            a.setDeletedAt(converterTimestampToLocalDateTime(aux));
        aux = rs.getTimestamp("updated_at");
        if(aux != null)
            a.setUpdatedAt(converterTimestampToLocalDateTime(aux));
        a.setLogradouro(rs.getString("logradouro"));
        a.setMunicipio(rs.getString("municipio"));
        a.setNumero(rs.getString("numero"));
        a.setUf(rs.getString("uf"));
        
        return a;
        
    }

}
