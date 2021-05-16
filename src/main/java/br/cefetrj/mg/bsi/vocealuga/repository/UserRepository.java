package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.cefetrj.mg.bsi.vocealuga.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    
    public Optional<User> findByUsername(String username);
    
}
