package br.cefetrj.mg.bsi.vocealuga.service;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.security.InvalidParameterException;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepositoryImpl;
import br.cefetrj.mg.bsi.vocealuga.repository.ICargoRepository;

public class CargoServiceImpl implements ICargoService {

    private ICargoRepository repository = null;

    public CargoServiceImpl() {
        this.repository = new CargoRepositoryImpl();
    }

    @Override
    public Cargo save(Cargo o) throws Exception {

        if (o.getNome().isBlank())
            throw new InvalidParameterException(getBlankFieldMessage("nome"));

        return this.repository.save(o);
    }

    @Override
    public Cargo update(Cargo o) throws Exception {

        if (o.getNome().isBlank() || o.getId() <= 0)
            throw new InvalidParameterException(getBlankFieldMessage("nome or id"));

        return this.repository.update(o);
    }

    @Override
    public Cargo delete(int id) throws Exception {

        if (id <= 0) {
            throw new InvalidIdException(id);
        }

        return this.repository.delete(id);
    }

    @Override
    public Cargo findById(int id) throws Exception {
        if (id <= 0)
            throw new InvalidIdException(id);
        return this.repository.findById(id);

    }

    @Override
    public List<Cargo> findAll() throws Exception {
        return this.repository.findAll();
    }

}