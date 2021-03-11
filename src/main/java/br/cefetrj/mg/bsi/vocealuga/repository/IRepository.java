package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.List;

public interface IRepository<k> {

	public k save(k o) throws Exception;
	public k update(k o)throws Exception;
	public k delete(int id) throws Exception;
	public k findById(int id) throws Exception;
	public List<k> findAll() throws Exception;
	
	
}
