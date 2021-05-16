package br.cefetrj.mg.bsi.vocealuga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cefetrj.mg.bsi.vocealuga.model.Role;
import br.cefetrj.mg.bsi.vocealuga.model.User;
import br.cefetrj.mg.bsi.vocealuga.repository.UserRepository;

@SpringBootApplication
public class Main {

	static UserRepository userRepository;

	@Autowired
	public Main(UserRepository userRepository){
		Main.userRepository = userRepository;
	}

	public static void main(String[] args){
		SpringApplication.run(Main.class, args);
		Role role1 = new Role();
		Role role2 = new Role();
		Role role3 = new Role();
		role1.setRole("ROLE_ADMIN");
		role2.setRole("ROLE_EMPLOYEE");
		role3.setRole("ROLE_CUSTOMER");
		List<Role> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		User user = new User();
		user.setCpf("12345678910");
		user.setName("administrador");
		user.setPassword(new BCryptPasswordEncoder().encode("123456789"));
		user.setRoles(roles);
		user.setType("FUNCIONARIO");
		user.setUsername("admin@gmail.com");
		user.setEnabled(true);
		userRepository.save(user);
		
	}

}
