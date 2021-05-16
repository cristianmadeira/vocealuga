package br.cefetrj.mg.bsi.vocealuga.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.cefetrj.mg.bsi.vocealuga.model.Cliente;
import br.cefetrj.mg.bsi.vocealuga.model.User;
import br.cefetrj.mg.bsi.vocealuga.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    

    @Autowired
    private ClienteRepository clienteRepository;
    
    
    
    @GetMapping("/account")
    public ModelAndView account(
        Authentication authentication,
        ModelAndView modelAndView
        ){
            Cliente cliente = new Cliente();
            Object principal = authentication.getPrincipal();
            if( principal instanceof User){
                User u = (User) principal;
                cliente = this.clienteRepository.findByCpf(u.getCpf()).get();
                
            }
            modelAndView.setViewName("clientes/account");
            modelAndView.addObject("cliente", cliente);
            modelAndView.addObject("buttonName", "Editar");
            
            return modelAndView;
    }
}
