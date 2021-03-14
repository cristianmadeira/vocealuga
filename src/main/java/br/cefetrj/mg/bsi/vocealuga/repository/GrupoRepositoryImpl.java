package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.dao.GrupoDAOImpl;
import br.cefetrj.mg.bsi.vocealuga.dao.IDAO;
import br.cefetrj.mg.bsi.vocealuga.exception.ResultNotFoundException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public class GrupoRepositoryImpl implements IGrupoRepository {

	private IDAO<Grupo> dao = null;

	public GrupoRepositoryImpl() {
		this.dao = new GrupoDAOImpl();

	}

	@Override
	public Grupo save(Grupo o) throws Exception {
		return this.dao.save(o);
	}

	@Override
	public Grupo update(Grupo o) throws Exception {
		return this.dao.update(o);

	}

	@Override
	public Grupo delete(int id) throws Exception {
		return this.dao.delete(id);
	}

	@Override
	public Grupo findById(int id) throws Exception {
		return this.dao.find(id);
	}

	@Override
	public List<Grupo> findAll() throws Exception {
		List<Grupo> groupsWithoutTrash = new ArrayList<>();
		List<Grupo> grupos = this.dao.findAll();
		for (Grupo g : grupos) {
			if (g.getDeletedAt() == null)
				groupsWithoutTrash.add(g);
		}
		return groupsWithoutTrash;
	}

	@Override
	public Grupo findByNome(String nome) throws Exception {
		List<Grupo> grupos = this.findAll();
		for (Grupo g : grupos) {
			if (g.getNome().equalsIgnoreCase(nome))
				return g;
		}
		throw new ResultNotFoundException(nome);
	}

}
