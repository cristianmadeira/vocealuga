package br.cefetrj.mg.bsi.vocealuga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    

    
    @GetMapping("{id}/pedidos")
    public ModelAndView pedidos(ModelAndView modelAndView){
        Cliente = 
        return modelAndView;
    }
}
