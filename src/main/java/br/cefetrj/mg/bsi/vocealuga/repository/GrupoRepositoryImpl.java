package br.cefetrj.mg.bsi.vocealuga.repository;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.dao.DAO;
import br.cefetrj.mg.bsi.vocealuga.dao.GrupoDAO;
import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public class GrupoRepositoryImpl implements IGrupoRepository{

	
	private DAO<Grupo> dao = null;
	
	
	public GrupoRepositoryImpl() {
		this.dao = new GrupoDAO();
		
	}
	@Override
	public void save(Grupo o) throws SQLException {
		// TODO Auto-generated method stub
		this.dao.save(o);
	}

	@Override
	public void update(Grupo o) throws SQLException {
		// TODO Auto-generated method stub
		this.dao.update(o);
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		this.dao.delete(id);
		
	}

	@Override
	public Grupo findById(int id) throws SQLException, ModelException {
		// TODO Auto-generated method stub
		return this.dao.find(id);
	}

	@Override
	public List<Grupo> findAll() throws SQLException, ModelException {
		// TODO Auto-generated method stub
		return this.dao.findAll();
	}
	@Override
	public Grupo findByNome(String nome) throws SQLException, ModelException {
		// TODO Auto-generated method stub
		List<Grupo> grupos = this.findAll();
		for(Grupo g : grupos) {
			if(g.getNome().equalsIgnoreCase(nome))
				return g;
		}
		throw new ModelException(String.format("%s não existe.",nome));
	}

	
}
