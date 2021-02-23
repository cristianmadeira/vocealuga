package br.cefetrj.mg.bsi.vocealuga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public class GrupoDAO implements DAO<Grupo>{

	private Connection conn = null;
	private ConnectionFactory factory =  null;
	
	public GrupoDAO(Connection conn) {
		this.conn = conn;
	}
	public GrupoDAO() {
		this.factory = ConnectionFactory.getInstance();
		
	}

	@Override
	public void save(Grupo o) throws SQLException {
		// TODO Auto-generated method stub
		this.conn  = this.factory.getConn();
		String sql = "INSERT INTO grupos(nome) VALUES(?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, o.getNome());
		pst.executeUpdate();
		pst.close();
		this.conn.close();

	}

	@Override
	public void update(Grupo o) throws SQLException{
		// TODO Auto-generated method stub
		this.conn =  this.factory.getConn();
		String sql = "UPDATE grupos SET nome = ? WHERE id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,o.getNome() );
		pst.setInt(2, o.getId());
		pst.executeUpdate();
		pst.close();
		this.conn.close();

	}

	@Override
	public void delete(int id) throws SQLException{
		// TODO Auto-generated method stub
		this.conn = this.factory.getConn();
		String sql = "DELETE FROM grupos WHERE id  = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		pst.close();
		this.conn.close();
		

	}

	@Override
	public List<Grupo> findAll() throws SQLException, ModelException{
		// TODO Auto-generated method stub
		this.conn  = this.factory.getConn();
		String sql = "SELECT * FROM grupos";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Grupo> grupos = new ArrayList<>();
		while(rs.next()) {
			Grupo m = new Grupo();
			m.setId(rs.getInt("id"));
			m.setNome(rs.getString("nome"));
			grupos.add(m);
		}
		rs.close();
		pst.close();
		this.conn.close();
		return grupos;


	}

	@Override
	public Grupo find(int id) throws SQLException, ModelException{
		// TODO Auto-generated method stub
		this.conn = this.factory.getConn();
		Grupo g = null;
		String sql ="SELECT * FROM grupos WHERE id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1,id);
		ResultSet rs = pst.executeQuery();
		if(!rs.next())
			throw new ModelException(String.format("Grupo com id %d não encontrado",id));
		else{
			g = new Grupo();
			g.setId(rs.getInt("id"));
			g.setNome(rs.getString("nome"));

		}
		rs.close();
		pst.close();
		this.conn.close();
		return g;

	}



}
