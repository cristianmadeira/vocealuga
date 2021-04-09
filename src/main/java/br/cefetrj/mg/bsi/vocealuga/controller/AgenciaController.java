package br.cefetrj.mg.bsi.vocealuga.controller;

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
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;

import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;


import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaRepository repository;

    

    @GetMapping
    public String index(Model model) {
        model.addAttribute("agencias", this.repository.findAll());
        return "agencias/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("agencia", new Agencia());
        model.addAttribute("method", "POST");
        model.addAttribute("buttonName", "Cadastrar");
        model.addAttribute("url", "/agencias/");
        return "agencias/form";
    }

    @PostMapping
    public String store(@Valid @ModelAttribute("agencia") Agencia agencia, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Cadastrar");
            model.addAttribute("url", "/agencias/");
            return "agencias/form";
        }

        this.repository.save(agencia);
        model.addAttribute("success", getSaveSuccessMessage("agência"));
        return index(model);
        

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        try {
            Optional<Agencia> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);;
            model.addAttribute("agencia", opt.get());
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("url", "/agencias/" + id + "/update");
            return "agencias/form";
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
            return index(model);
        }

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id,@Valid @ModelAttribute("agencia")Agencia agencia,Errors errors,  Model model) {
        
        if(errors.hasErrors()){
            model.addAttribute("agencia", agencia);
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("url", "/agencias/" + id + "/update");
            return "agencias/form";
        }
        this.repository.save(agencia);
        model.addAttribute("success", getUpdateSuccessMessage("agência"));
        return index(model);
        

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        try {
            Optional<Agencia> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);
            this.repository.delete(opt.get());
            model.addAttribute("success", getDeleteSuccessMessage("agência"));
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
        }
        return index(model);

    }
}
