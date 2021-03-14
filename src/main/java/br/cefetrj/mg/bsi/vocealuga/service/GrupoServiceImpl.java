package br.cefetrj.mg.bsi.vocealuga.service;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.security.InvalidParameterException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import br.cefetrj.mg.bsi.vocealuga.repository.GrupoRepositoryImpl;
import br.cefetrj.mg.bsi.vocealuga.repository.IGrupoRepository;

public class GrupoServiceImpl implements IGrupoService {

	private IGrupoRepository repository = null;

	public GrupoServiceImpl() {
		this.repository = new GrupoRepositoryImpl();
	}

	@Override
	public Grupo save(Grupo o) throws Exception {
		if (o.getNome().isBlank())
			throw new InvalidParameterException(getBlankFieldMessage("nome"));
		return this.repository.save(o);

	}

	@Override
	public Grupo update(Grupo o) throws Exception {
		if (o.getNome().isBlank() || o.getId() <= 0)
			throw new InvalidParameterException(getBlankFieldMessage("nome or id"));
		return this.repository.update(o);

	}

	@Override
	public Grupo delete(int id) throws Exception {
		if (id <= 0)
			throw new InvalidIdException(id);
		return this.repository.delete(id);

	}

	@Override
	public Grupo findById(int id) throws Exception {
		if (id <= 0)
			throw new InvalidIdException(id);
		return this.repository.findById(id);

	}

	@Override
	public List<Grupo> findAll() throws Exception {
		return this.repository.findAll();
	}

}
