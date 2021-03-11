package br.cefetrj.mg.bsi.vocealuga.service;

import java.util.List;

public interface IService<k>{

	public k save(k o) throws  Exception;
	public k update(k o) throws  Exception;
	public k delete(int id)throws  Exception;
	public k findById(int id) throws Exception;
	public List<k> findAll() throws Exception;
	
	
}
