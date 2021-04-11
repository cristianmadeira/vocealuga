package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.cefetrj.mg.bsi.vocealuga.exception.DeleteException;
import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepository;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoRepository repository;

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("cargos/index");
        modelAndView.addObject("cargos", this.repository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(Cargo cargo, ModelAndView modelAndView) {
        modelAndView.setViewName("cargos/form");
        modelAndView.addObject("cargo", cargo);
        modelAndView.addObject("method", "POST");
        modelAndView.addObject("buttonName", "Cadastrar");
        modelAndView.addObject("url", "/cargos/");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(@Valid @ModelAttribute("cargo") Cargo cargo,BindingResult result, ModelAndView modelAndView) {
        if(result.hasErrors()){
            return create(cargo, modelAndView);
        }
        this.repository.save(cargo);
        modelAndView.addObject("success", getSaveSuccessMessage("cargo"));
        return index(modelAndView);
        

    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id,Cargo cargo, ModelAndView modelAndView, Boolean hasErrors) {
        try {
            if(hasErrors == null)
                cargo = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));    
            modelAndView.addObject("cargo", cargo);
            modelAndView.setViewName("cargos/form");
            modelAndView.addObject("method", "POST");
            modelAndView.addObject("buttonName", "Atualizar");
            modelAndView.addObject("url", "/cargos/" + cargo.getId() + "/update");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            return index(modelAndView);
        }

    }

    @PostMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id,
        @Valid @ModelAttribute("cargo")Cargo cargo, BindingResult result, ModelAndView modelAndView
    ) {
        if(result.hasErrors())
            return edit(id,cargo,modelAndView, true);
        this.repository.save(cargo);
        modelAndView.addObject("success", getUpdateSuccessMessage("cargo"));
        return index(modelAndView);
        

    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelAndView modelAndView) {
        try {
            Cargo cargo = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            List<Funcionario> funcionarios = cargo.getFuncionarios();
            int size = funcionarios.size();
            if(size > 0)
                throw new DeleteException("cargo",size,"funcionário");
            this.repository.delete(cargo);
            modelAndView.addObject("success", getDeleteSuccessMessage("cargo"));
        } catch (Exception  e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return index(modelAndView);

    }
}
