package br.cefetrj.mg.bsi.vocealuga.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;

public interface IDAO<O> {
	public O save(O o) throws SQLException;

	public O update(O o) throws SQLException;

	public O delete(int id) throws SQLException;

	public O find(int id) throws SQLException, InvalidIdException;

	public List<O> findAll() throws SQLException;

	public int getLastId() throws SQLException;

	public O getFilledObject(ResultSet rs) throws SQLException;

}
