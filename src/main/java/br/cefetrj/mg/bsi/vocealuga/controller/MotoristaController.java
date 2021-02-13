package br.cefetrj.mg.bsi.vocealuga.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.cefetrj.mg.bsi.vocealuga.model.Motorista;
import br.cefetrj.mg.bsi.vocealuga.repository.MotoristaRepository;



@Controller()
@RequestMapping("/motoristas")
public class MotoristaController {
	
	@Autowired()
	private MotoristaRepository repository;
	private final String PREFIX="/motorista";
	
	@GetMapping
	public ModelAndView index(Model model) {
		model.addAttribute("motoristas", repository.findAll());
		return new ModelAndView(this.PREFIX+"/index");
	}
	
	@GetMapping("/create")
	public ModelAndView create(Model model){
		model.addAttribute("motorista", new Motorista());
		
		return new ModelAndView(this.PREFIX.concat("/create"));
		
	}
	
	@PostMapping
	public String store(@ModelAttribute Motorista motorista) {
		repository.save(motorista);
		return "redirect:/motoristas";
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable(value = "id") int id, Model model) {
		System.out.println("id:");
		model.addAttribute("motorista",repository.findById(id));
		return new ModelAndView(this.PREFIX.concat("/create"));
	}
	
	
	
	
}
