package br.cefetrj.mg.bsi.vocealuga.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.dao.MotoristaDAO;
import br.cefetrj.mg.bsi.vocealuga.model.Motorista;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

	private  MotoristaDAO dao = null;
	private Connection conn = null;
	
	public MotoristaController() {
		conn = ConnectionFactory.getInstance().getConn();
		dao = new MotoristaDAO(conn);
	}
	
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("motoristas",dao.findAll());
		return "motorista/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("motorista",new Motorista());
		model.addAttribute("method","POST");
		model.addAttribute("buttonName","Cadastrar");
		model.addAttribute("url","/motoristas/");
		return "motorista/form";
	}
	
	@PostMapping
	public String store(@ModelAttribute Motorista motorista) {
		dao.save(motorista);
		return "redirect:/motoristas";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("motorista",dao.find(id));
		model.addAttribute("method","PUT");
		model.addAttribute("buttonName","Atualizar");
		model.addAttribute("url","/motoristas/"+id+"/update");
		return "motorista/form";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") int id,@ModelAttribute Motorista motorista) {
		dao.update(motorista);
		return "redirect:/motoristas";
		
	}
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		dao.delete(id);
		return "redirect:/motoristas";
	}
	
}
