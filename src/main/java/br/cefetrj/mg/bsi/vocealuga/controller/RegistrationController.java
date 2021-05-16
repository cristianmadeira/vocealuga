package br.cefetrj.mg.bsi.vocealuga.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.cefetrj.mg.bsi.vocealuga.exception.ResultNotFoundException;
import br.cefetrj.mg.bsi.vocealuga.model.Cliente;
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.model.Role;
import br.cefetrj.mg.bsi.vocealuga.model.User;
import br.cefetrj.mg.bsi.vocealuga.repository.ClienteRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.FuncionarioRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.RoleRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.UserRepository;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ModelAndView show(ModelAndView modelAndView, User user){
        modelAndView.setViewName("security/register");
        modelAndView.addObject("buttonName", "Cadastrar-se");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView register(
       @Valid @ModelAttribute("user") User user,
        BindingResult result,
        ModelAndView modelAndView
       ){
            
            Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
            if(result.hasErrors()){
                return this.show(modelAndView, user);
            }
            
            else if(userOpt.isPresent()){
                result.rejectValue("username", "error.user","There is already a user registered with the user name provided");
                return this.show(modelAndView,user);
            }
            else{
                if(user.getType().equalsIgnoreCase("funcionario")){
                    Funcionario f = new Funcionario();
                    f.setCpf(user.getCpf());
                    f.setCreatedAt(LocalDateTime.now());
                    f.setEmail(user.getUsername());
                    f.setNome(user.getName());
                    this.funcionarioRepository.save(f);
                    Role role  = roleRepository.findByRole("ROLE_EMPLOYEE").get();
                    List<Role> roles = new ArrayList<>();
                    roles.add(role);
                    user.setRoles(roles);
                    modelAndView.setViewName("index");    
                    modelAndView.addObject("success",getSaveSuccessMessage("dados"));
                }
                else{
                    Cliente c= new Cliente();
                    c.setCpf(user.getCpf());
                    c.setCreatedAt(LocalDateTime.now());
                    c.setEmail(user.getUsername());
                    c.setNome(user.getName());
                    this.clienteRepository.save(c);
                    user.setEnabled(true);
                    Role role  = roleRepository.findByRole("ROLE_CUSTOMER").get();
                    List<Role> roles = new ArrayList<>();
                    roles.add(role);
                    user.setRoles(roles);
                    modelAndView.setViewName("redirect:/clientes/account");
                }
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                userRepository.save(user);
                
                
                
                return modelAndView;
            }
            
            
    }

   
}
