package br.cefetrj.mg.bsi.vocealuga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public interface IDAO<O> {
	public O save(O o) throws SQLException;

	public O update(O o) throws SQLException;

	public O delete(int id) throws SQLException;

	public O find(int id) throws SQLException;

	public List<O> findAll() throws SQLException;

	public int getLastId(PreparedStatement pst ) throws SQLException;

	public O getFilledObject(ResultSet rs) throws SQLException;

}
