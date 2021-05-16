package br.cefetrj.mg.bsi.vocealuga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.cefetrj.mg.bsi.vocealuga.repository.VeiculoRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private VeiculoRepository veiculoRepository;
	@Autowired
	public IndexController(VeiculoRepository veiculoRepository){
		this.veiculoRepository = veiculoRepository;
	}
	@GetMapping
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		modelAndView.addObject("veiculos",this.veiculoRepository.findByVeiculoDisponivel());
		return modelAndView;
	}

}
