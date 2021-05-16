package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.cefetrj.mg.bsi.vocealuga.model.Cliente;

public interface ClienteRepository  extends CrudRepository<Cliente,Integer>{
    
    public Optional<Cliente> findByCpf(String cpf);
}
