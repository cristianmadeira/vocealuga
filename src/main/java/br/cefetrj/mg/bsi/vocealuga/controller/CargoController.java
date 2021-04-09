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
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepository;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoRepository repository;

    

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cargos", this.repository.findAll());
        return "cargos/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cargo", new Cargo());
        model.addAttribute("method", "POST");
        model.addAttribute("buttonName", "Cadastrar");
        model.addAttribute("url", "/cargos/");
        return "cargos/form";
    }

    @PostMapping
    public String store(@Valid @ModelAttribute("cargo") Cargo cargo, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Cadastrar");
            model.addAttribute("url", "/cargos/");
            return "cargos/form";
        }

        this.repository.save(cargo);
        model.addAttribute("success", getSaveSuccessMessage("cargo"));
        return index(model);
        

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        try {
            Optional<Cargo> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);;
            model.addAttribute("cargo", opt.get());
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("url", "/cargos/" + id + "/update");
            return "cargos/form";
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
            return index(model);
        }

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id,@Valid @ModelAttribute("cargo")Cargo cargo,Errors errors,  Model model) {
        
        if(errors.hasErrors()){
            model.addAttribute("cargo", cargo);
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("url", "/cargos/" + id + "/update");
            return "cargos/form";
        }
        this.repository.save(cargo);
        model.addAttribute("success", getUpdateSuccessMessage("cargo"));
        return index(model);
        

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        try {
            Optional<Cargo> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);
            this.repository.delete(opt.get());
            model.addAttribute("success", getDeleteSuccessMessage("cargo"));
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
        }
        return index(model);

    }
}
