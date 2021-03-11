package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.dao.DAO;
import br.cefetrj.mg.bsi.vocealuga.dao.GrupoDAO;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public class GrupoRepositoryImpl implements IGrupoRepository{

	
	private DAO<Grupo> dao = null;
	
	public GrupoRepositoryImpl() {
		this.dao = new GrupoDAO();
		
	}
	@Override
	public Grupo save(Grupo o) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.save(o);
	}

	@Override
	public Grupo update(Grupo o) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.update(o);
		
	}

	@Override
	public Grupo delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.delete(id);
	}

	@Override
	public Grupo findById(int id) throws Exception{
		// TODO Auto-generated method stub
		return this.dao.find(id);
	}

	@Override
	public List<Grupo> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Grupo> gruposWithoutTrash = new ArrayList<>();
		List<Grupo> grupos  =this.dao.findAll();
		for(Grupo g :grupos) {
			if(g.getDeletedAt() == null)
				gruposWithoutTrash.add(g);
		}
		return gruposWithoutTrash;
	}
	@Override
	public Grupo findByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		List<Grupo> grupos = this.findAll();
		for(Grupo g : grupos) {
			if(g.getNome().equalsIgnoreCase(nome))
				return g;
		}
		throw new Exception(String.format("%s não existe.",nome));
	}
	

	
}
