package br.cefetrj.mg.bsi.vocealuga.repository;

import java.sql.SQLException;

import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public interface IGrupoRepository extends IRepository<Grupo> {
	
	public Grupo findByNome(String nome) throws SQLException, ModelException;

}
