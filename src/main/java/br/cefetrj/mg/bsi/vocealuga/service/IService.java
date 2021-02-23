package br.cefetrj.mg.bsi.vocealuga.service;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;

public interface IService<k>{

	public void save(k o) throws SQLException, ModelException;
	public void update(k o) throws SQLException, ModelException;
	public void delete(int id) throws SQLException;
	public k findById(int id) throws SQLException, ModelException;
	public List<k> findAll() throws SQLException, ModelException;
	
	
}
