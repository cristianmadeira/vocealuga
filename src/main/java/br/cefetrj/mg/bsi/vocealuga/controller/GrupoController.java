package br.cefetrj.mg.bsi.vocealuga.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

import br.cefetrj.mg.bsi.vocealuga.repository.GrupoRepository;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.util.Optional;

@Controller
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository repository;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("grupos", repository.findAll());
		return "grupos/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("grupo", new Grupo());
		model.addAttribute("method", "POST");
		model.addAttribute("buttonName", "Cadastrar");
		model.addAttribute("action", "/grupos/");
		return "grupos/form";
	}

	@PostMapping
	public String store(@Valid @ModelAttribute("grupo")Grupo grupo, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("method", "POST");
			model.addAttribute("buttonName", "Cadastrar");
			model.addAttribute("action", "/grupos/");
			return "grupos/form";
		}
		this.repository.save(grupo);
		model.addAttribute("success", getSaveSuccessMessage("grupo"));
		return index(model);
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		try {
			Optional<Grupo> opt = this.repository.findById(id);
			if(opt.isEmpty())
				throw new InvalidIdException(id);
			model.addAttribute("grupo",opt.get());
			model.addAttribute("method", "POST");
			model.addAttribute("buttonName", "Atualizar");
			model.addAttribute("action", "/grupos/" + id + "/update");
			return "grupos/form";
		} catch (InvalidIdException e) {
			model.addAttribute("error", e.getMessage());
			return index(model);
		}

	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") int id, @Valid @ModelAttribute("grupo") Grupo grupo, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("method", "POST");
			model.addAttribute("buttonName", "Atualizar");
			model.addAttribute("action", "/grupos/" + id + "/update");
			return "grupos/form";
		}
		this.repository.save(grupo);
		model.addAttribute("success", getUpdateSuccessMessage("grupo"));
		return index(model);
		

	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, Model model) {
		try {
			Optional<Grupo> opt = this.repository.findById(id);
			if(opt.isEmpty())
				throw new InvalidIdException(id);
			this.repository.delete(opt.get());
			model.addAttribute("success", getDeleteSuccessMessage("grupo"));
		} catch (InvalidIdException e) {
			model.addAttribute("error", e.getMessage());
		}
		return index(model);

	}

	

}
