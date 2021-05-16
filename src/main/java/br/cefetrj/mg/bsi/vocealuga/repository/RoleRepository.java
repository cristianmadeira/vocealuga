package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.cefetrj.mg.bsi.vocealuga.model.Role;

public interface RoleRepository  extends CrudRepository<Role,Integer>{
    public Optional<Role> findByRole(String role);
}
