package br.cefetrj.mg.bsi.vocealuga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.model.Motorista;

public class MotoristaDAO implements DAO<Motorista>{
	
	private Connection conn = null;
	
	public MotoristaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean save(Motorista o) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "INSERT INTO Motorista(nome) VALUES(?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, o.getNome());
			pst.executeUpdate();
			pst.close();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}

	@Override
	public boolean update(Motorista o) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Motorista SET nome = ? WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,o.getNome() );
			pst.setInt(2, o.getId());
			pst.executeUpdate();
			pst.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM Motorista WHERE id  = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			pst.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	@Override
	public List<Motorista> findAll() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM Motorista";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<Motorista> motoristas = new ArrayList<>();
			while(rs.next()) {
				Motorista m = new Motorista();
				m.setId(rs.getInt("id"));
				m.setNome(rs.getString("nome"));
				motoristas.add(m);
			}
			rs.close();
			pst.close();
			return motoristas;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	@Override
	public Motorista find(int id) {
		// TODO Auto-generated method stub
		Motorista m = null;
		try {
			String sql ="SELECT * FROM Motorista WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				m = new Motorista();
				m.setId(rs.getInt("id"));
				m.setNome(rs.getString("nome"));
				
			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return m;
		
	}


}
