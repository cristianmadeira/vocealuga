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
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;

@Controller
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaRepository repository;

    

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("agencias/index");
        modelAndView.addObject("agencias", this.repository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView, Agencia agencia) {
        modelAndView.setViewName("agencias/form");
        modelAndView.addObject("agencia", agencia);
        modelAndView.addObject("method", "POST");
        modelAndView.addObject("buttonName", "Cadastrar");
        modelAndView.addObject("url", "/agencias/");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(
        @Valid @ModelAttribute("agencia") Agencia agencia,
        BindingResult result, ModelAndView modelAndView
        ) {
        if(result.hasErrors())
            return this.create(modelAndView, agencia);
        this.repository.save(agencia);
        modelAndView.addObject("success", getSaveSuccessMessage("agência"));
        return index(modelAndView);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id, ModelAndView modelAndView, Agencia agencia, Boolean hasErrors) {
        try {
            if(hasErrors == null)
                agencia = this.repository.findById(id).orElseThrow(()-> new InvalidIdException(id));
            
            modelAndView.setViewName("agencias/form");         
            modelAndView.addObject("agencia", agencia);
            modelAndView.addObject("method", "POST");
            modelAndView.addObject("buttonName", "Atualizar");
            modelAndView.addObject("url", "/agencias/" + id + "/update");
            return modelAndView;
        } catch (InvalidIdException e) {
            modelAndView.addObject("error", e.getMessage());
            return index(modelAndView);
        }

    }

    @PostMapping("/{id}/update")
    public ModelAndView update(
        @PathVariable("id") int id,
        @Valid @ModelAttribute("agencia")Agencia agencia,
        BindingResult result,  ModelAndView modelAndView) {
        if(result.hasErrors())
            return edit(id, modelAndView, agencia, true);
        this.repository.save(agencia);
        modelAndView.addObject("success", getUpdateSuccessMessage("agência"));
        return index(modelAndView);
        

    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelAndView modelAndView) {
        try {
            Agencia agencia = this.repository.findById(id).orElseThrow( ()-> new InvalidIdException(id));
            List<Funcionario> funcionarios = agencia.getFuncionarios();
            int size = funcionarios.size();
            if(size > 0)
                throw new DeleteException("agência",size,"funcionário");
            this.repository.delete(agencia);
            modelAndView.addObject("success", getDeleteSuccessMessage("agência"));
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return index(modelAndView);

    }
}