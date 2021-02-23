package br.cefetrj.mg.bsi.vocealuga.service;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import br.cefetrj.mg.bsi.vocealuga.repository.GrupoRepositoryImpl;
import br.cefetrj.mg.bsi.vocealuga.repository.IGrupoRepository;

public class GrupoServiceImpl implements IService<Grupo>{

	private IGrupoRepository repository = null;
	
	
	public GrupoServiceImpl() {
		// TODO Auto-generated constructor stub
		this.repository = new GrupoRepositoryImpl();
	}

	@Override
	public void save(Grupo o) throws SQLException, ModelException {
		// TODO Auto-generated method stub
		if(o.getNome().isBlank())
			throw  new ModelException(format(FIELD_IS_BLANK,"nome"));
		this.repository.save(o);
	}

	@Override
	public void update(Grupo o) throws SQLException, ModelException {
		// TODO Auto-generated method stub
		if(o.getNome().isBlank())
			throw new ModelException(format(FIELD_IS_BLANK,"nome"));
		this.repository.update(o);
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		this.repository.delete(id);
	}

	@Override
	public Grupo findById(int id) throws SQLException, ModelException {
		// TODO Auto-generated method stub
		return this.repository.findById(id);
	}

	@Override
	public List<Grupo> findAll() throws SQLException, ModelException {
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}
	
	
}
