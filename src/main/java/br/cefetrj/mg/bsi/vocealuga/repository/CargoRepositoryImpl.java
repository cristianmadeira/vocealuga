package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.exception.ResultNotFoundException;
import br.cefetrj.mg.bsi.vocealuga.dao.IDAO;
import br.cefetrj.mg.bsi.vocealuga.dao.CargoDAOImpl;
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

public class CargoRepositoryImpl implements ICargoRepository {

    private IDAO<Cargo> dao = null;

    public CargoRepositoryImpl() {
        this.dao = new CargoDAOImpl();
    }

    @Override
    public Cargo save(Cargo o) throws Exception {

        return this.dao.save(o);
    }

    @Override
    public Cargo update(Cargo o) throws Exception {

        return this.dao.update(o);
    }

    @Override
    public Cargo delete(int id) throws Exception {

        return this.dao.delete(id);
    }

    @Override
    public Cargo findById(int id) throws Exception {

        return this.dao.find(id);
    }

    @Override
    public List<Cargo> findAll() throws Exception {
        List<Cargo> cargosWithoutTrash = new ArrayList<>();
        List<Cargo> cargos = this.dao.findAll();
        for (Cargo c : cargos) {
            if (c.getDeletedAt() == null)
                cargosWithoutTrash.add(c);
        }
        return cargosWithoutTrash;
    }

    @Override
    public Cargo findByNome(String nome) throws Exception {
        List<Cargo> cargos = this.findAll();
        for (Cargo c : cargos) {
            if (c.getNome().equalsIgnoreCase(nome))
                return c;
        }
        throw new ResultNotFoundException(nome);
    }

}