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
import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;
import br.cefetrj.mg.bsi.vocealuga.repository.VeiculoRepository;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

import java.util.Optional;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
 
    @Autowired
    private VeiculoRepository repository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("veiculos", repository.findAll());
        return "veiculos/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("veiculo",  new Veiculo());
        model.addAttribute("method",          "POST");
        model.addAttribute("buttonName", "Cadastrar");
        model.addAttribute("action",    "/veiculos/");
        return "veiculos/form";
    }

    @PostMapping
    public String store(@Valid @ModelAttribute("veiculo")Veiculo veiculo, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("method",          "POST");
            model.addAttribute("buttonName", "Cadastrar");
            model.addAttribute("action",    "/veiculos/");
            return "veiculos/form";
        }
        this.repository.save(veiculo);
        model.addAttribute("success", getSaveSuccessMessage("veiculo"));
        return index(model);
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        try {
            Optional<Veiculo> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);
            model.addAttribute("veiculo",      opt.get());
            model.addAttribute("method",          "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("action", "/veiculos/" + id + "/update");
            return "veiculos/form";
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
            return index(model);
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("veiculo") Veiculo veiculo, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("method",          "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("action", "/veiculos/" + id + "/update");
            return "veiculos/form";
        }
        this.repository.save(veiculo);
        model.addAttribute("success", getUpdateSuccessMessage("veiculo"));
        return index(model);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        try {
            Optional<Veiculo> opt = this.repository.findById(id);
            if(opt.isEmpty())
                throw new InvalidIdException(id);
            this.repository.delete(opt.get());
            model.addAttribute("success", getDeleteSuccessMessage("veiculo"));
        } catch (InvalidIdException e) {
            model.addAttribute("error", e.getMessage());
        }
        return index(model);
    }

}
