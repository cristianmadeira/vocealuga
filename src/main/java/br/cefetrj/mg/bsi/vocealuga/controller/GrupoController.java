package br.cefetrj.mg.bsi.vocealuga.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import br.cefetrj.mg.bsi.vocealuga.service.GrupoServiceImpl;
import br.cefetrj.mg.bsi.vocealuga.service.IGrupoService;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

@Controller
@RequestMapping("/grupos")
public class GrupoController {

	
	private IGrupoService service = null;
	
	public GrupoController() {
		// TODO Auto-generated constructor stub
		this.service = new GrupoServiceImpl();
		
	}
	@GetMapping
	public String index(Model model) {
		try {
			List<Grupo> grupos = service.findAll();
			model.addAttribute("grupos",grupos);
			if(grupos.isEmpty())
				throw new Exception("Não há grupos cadastrados.");
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			model.addAttribute("error",e.getMessage());
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
			model.addAttribute("success",getSaveSuccessMessage("grupo"));
			return index(model);
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			return this.create(model);
		}
		
		
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("grupo",this.service.findById(id));
			model.addAttribute("method","POST");
			model.addAttribute("buttonName","Atualizar");
			model.addAttribute("url","/grupos/"+id+"/update");
			return "grupos/form";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("error",e.getMessage());
			return index(model);
		}
		
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") int id,@ModelAttribute Grupo grupo, Model model) {
		try {
			this.service.update(grupo);
			model.addAttribute("success",getUpdateSuccessMessage("grupo"));
			return index(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("error",e.getMessage());
			return edit(id,model);
			
		} 
		
		
		
	}
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, Model model) {
		try {
			this.service.delete(id);
			model.addAttribute("success",getDeleteSuccessMessage("grupo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("error",e.getMessage()); 
		}
		return index(model);
		
	}
	

		
	
}
