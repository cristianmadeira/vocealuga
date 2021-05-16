package br.cefetrj.mg.bsi.vocealuga.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.cefetrj.mg.bsi.vocealuga.model.User;
import br.cefetrj.mg.bsi.vocealuga.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository
            .findByUsername(username)
            .orElseThrow(()->new UsernameNotFoundException(username));
        return user;
    }
    
}
