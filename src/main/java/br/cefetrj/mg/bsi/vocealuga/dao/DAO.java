package br.cefetrj.mg.bsi.vocealuga.dao;

import java.util.List;

public interface DAO<k> {

	public k save(k o) throws Exception ;
	public k update(k o) throws Exception ;
	public k delete(int id) throws Exception;
	public k find(int id) throws   Exception ;
	public List<k> findAll() throws  Exception ;
	public int getLastId() throws Exception ;
	
}
