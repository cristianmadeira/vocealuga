package br.cefetrj.mg.bsi.vocealuga.repository;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

public interface ICargoRepository extends IRepository<Cargo> {

    public Cargo findByNome(String nome) throws Exception;

}
