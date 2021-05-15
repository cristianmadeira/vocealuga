package br.cefetrj.mg.bsi.vocealuga.repository;

import org.springframework.data.repository.CrudRepository;

import br.cefetrj.mg.bsi.vocealuga.model.Cliente;

public interface ClienteRepository  extends CrudRepository<Cliente,Integer>{
    
}
