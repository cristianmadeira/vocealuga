package br.cefetrj.mg.bsi.vocealuga.repository;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public interface IGrupoRepository extends IRepository<Grupo> {
	
	public Grupo findByNome(String nome) throws  Exception;
	

}
