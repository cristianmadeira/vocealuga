package br.cefetrj.mg.bsi.vocealuga.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.cefetrj.mg.bsi.vocealuga.enums.MessageEnums;
import br.cefetrj.mg.bsi.vocealuga.exception.ModelException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import br.cefetrj.mg.bsi.vocealuga.service.GrupoServiceImpl;
import br.cefetrj.mg.bsi.vocealuga.service.IService;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.format;
@Controller
@RequestMapping("/grupos")
public class GrupoController {

	
	private IService<Grupo> service = null;
	
	public GrupoController() {
		// TODO Auto-generated constructor stub
		this.service = new GrupoServiceImpl();
		
	}
	@GetMapping
	public String index(Model model) {
		try {
			List<Grupo> grupos = service.findAll();
			
			model.addAttribute("grupos",grupos);
		} catch (SQLException | ModelException e) {
			// TODO Auto-generated catch block
			model.addAttribute("erros",e.getMessage());
			//System.out.println(e.getClass().getName().startsWith("java.sql"));
		}
		return "grupos/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("grupo",new Grupo());
		model.addAttribute("method","POST");
		model.addAttribute("buttonName","Cadastrar");
		model.addAttribute("url","/grupos/");
		return "grupos/form";
	}
	
	@PostMapping
	public String store(@ModelAttribute Grupo grupo, Model model) {
		try {
			this.service.save(grupo);
			model.addAttribute("success",format(MessageEnums.INSERT_SUCCESS,"Grupo"));
		} catch (SQLException | ModelException e) {
			// TODO Auto-generated catch block
			model.addAttribute("erros",e.getMessage());
		}
		return index(model);
		
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("grupo",this.service.findById(id));
			model.addAttribute("method","POST");
			model.addAttribute("buttonName","Atualizar");
			model.addAttribute("url","/grupos/"+id+"/update");
			return "grupos/form";
		} catch (SQLException | ModelException e) {
			// TODO Auto-generated catch block
			model.addAttribute("erros",e.getMessage());
			return index(model);
		}
		
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") int id,@ModelAttribute Grupo grupo, Model model) {
		try {
			this.service.update(grupo);
			model.addAttribute("success","Grupo atualizado com sucesso.");
			return index(model);
		} catch (SQLException | ModelException e) {
			// TODO Auto-generated catch block
			model.addAttribute("erros",e.getMessage());
			return edit(id,model);
			
		} 
		
		
		
	}
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, Model model) {
		try {
			this.service.delete(id);
			model.addAttribute("success","Grupo removido com sucesso.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			model.addAttribute("erros",e.getMessage()); 
		}
		return index(model);
		
	}
	
}
