package br.cefetrj.mg.bsi.vocealuga.dao;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
public class GrupoDAO implements DAO<Grupo>{

	final static Logger logger = LogManager.getLogger(GrupoDAO.class);
	private ConnectionFactory factory =  null;

	public GrupoDAO() {
		this.factory = ConnectionFactory.getInstance();
	}

	@Override
	public Grupo save(Grupo o) throws Exception  {
		// TODO Auto-generated method stub
		Connection conn = this.factory.getConn();
		String sql = "INSERT INTO grupos(nome, created_at) VALUES(?,?)";
		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, o.getNome());
			pst.setTimestamp(2,converterLocalDateTimeToTimestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
			pst.executeUpdate();
			conn.commit();
			pst.close();
			int id = this.getLastId();
			o  =  this.find(id);
			logger.info(getSaveSuccessMessage("grupo "+o.getNome()));
			return o;
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
			String errorMsg = getSaveErrorMessage("grupo",e.getMessage());
			logger.error(errorMsg);
			throw new Exception(errorMsg);
		}



	}



	@Override
	public Grupo update(Grupo o) throws Exception{
		// TODO Auto-generated method stub
		String sql = "UPDATE grupos SET nome = ?, updated_at = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = this.factory.getConn();
			conn.setAutoCommit(false);
			Grupo oldGrupo  = this.find(o.getId());
			pst = conn.prepareStatement(sql);
			pst.setString(1,o.getNome());
			pst.setTimestamp(2, converterLocalDateTimeToTimestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
			pst.setInt(3, oldGrupo.getId());
			pst.executeUpdate();
			conn.commit();
			pst.close();
			logger.info(getUpdateSuccessMessage("grupo "+o.getNome()));
			return this.find(o.getId());
		}catch(Exception e) {
			conn.rollback();
			logger.error(getUpdateErrorMessage("grupo",e.getMessage()));
			throw new Exception(getUpdateErrorMessage("grupo",e.getMessage()));
		}

	}

	@Override
	public Grupo delete(int id) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "UPDATE grupos SET deleted_at = ? WHERE id = ?";
		try {
			conn = this.factory.getConn();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,converterLocalDateTimeToTimestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
			pst.setInt(2, id);
			pst.executeUpdate();
			pst.close();
			conn.commit();
			logger.info(getDeleteSuccessMessage("grupo"));
			return this.find(id);
		}catch(Exception e) {
			conn.rollback();
			logger.error(getDeleteErrorMessage("grupo", e.getMessage()));
			throw new Exception(getDeleteErrorMessage("grupo", e.getMessage()));
		}


	}

	@Override
	public List<Grupo> findAll() throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM grupos";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Grupo> grupos = new ArrayList<>();
		try {
			conn = this.factory.getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Grupo g = new Grupo();
				g.setId(rs.getInt("id"));
				g.setNome(rs.getString("nome"));
				LocalDateTime date = null;
				date = rs.getTimestamp("created_at").toLocalDateTime();
				g.setCreatedAt(date);
				if(rs.getTimestamp("updated_at") != null) {
					date = rs.getTimestamp("updated_at").toLocalDateTime();
					g.setUpdatedAt(date);
				}
				if(rs.getTimestamp("deleted_at") != null) {
					date = rs.getTimestamp("deleted_at").toLocalDateTime();
					g.setDeletedAt(date);
				}
				grupos.add(g);
			}
			rs.close();
			pst.close();

		}catch(SQLException e) {
			logger.error("findAll:"+e.getMessage());
			throw new Exception(e.getMessage());
		}
		return grupos;


	}

	@Override
	public Grupo find(int id) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = this.factory.getConn();
		Grupo g = null;
		String sql ="SELECT * FROM grupos WHERE id = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,id);

			ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new Exception(String.format("Grupo com id %d não foi encontrado",id));
			else{
				g = new Grupo();
				g.setId(rs.getInt("id"));
				g.setNome(rs.getString("nome"));
				LocalDateTime date = null;
				date = rs.getTimestamp("created_at").toLocalDateTime();
				g.setCreatedAt(date);
				if(rs.getTimestamp("updated_at") != null) {
					date = rs.getTimestamp("updated_at").toLocalDateTime();
					g.setUpdatedAt(date);
				}
				if(rs.getTimestamp("deleted_at") != null) {
					date = rs.getTimestamp("deleted_at").toLocalDateTime();
					g.setDeletedAt(date);
				}


			}
			rs.close();
			pst.close();
			return g;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("findById:"+e.getMessage());
			throw new Exception(e.getMessage());
		}



	}


	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		int id = 0;
		String sql = "SELECT max(id) as id FROM grupos ;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = this.factory.getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next())
				id = rs.getInt("id");
			rs.close();
			pst.close();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}

	}




}
