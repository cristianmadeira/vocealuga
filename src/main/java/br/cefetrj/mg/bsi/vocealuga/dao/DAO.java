package br.cefetrj.mg.bsi.vocealuga.dao;

import java.util.List;

public interface DAO<k> {

	public boolean save(k o);
	public boolean update(k o) ;
	public boolean delete(int id);
	public k find(int id);
	public List<k> findAll();
}
