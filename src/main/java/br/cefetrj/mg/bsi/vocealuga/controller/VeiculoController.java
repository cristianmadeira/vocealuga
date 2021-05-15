package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;

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

import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;
import br.cefetrj.mg.bsi.vocealuga.repository.VeiculoRepository;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
 
    @Autowired
    private VeiculoRepository repository;

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("veiculos/index");
        modelAndView.addObject("veiculos", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView, Veiculo veiculo){
        modelAndView.addObject("veiculo",  veiculo);
        modelAndView.addObject("method",          "POST");
        modelAndView.addObject("buttonName", "Cadastrar");
        modelAndView.addObject("action",    "/veiculos/");
        modelAndView.setViewName("veiculos/form");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(
        @Valid @ModelAttribute("veiculo")Veiculo veiculo,
        BindingResult result,
        ModelAndView modelAndView) {
        if(result.hasErrors()){
            return create(modelAndView,veiculo);
            
        }
        this.repository.save(veiculo);
        modelAndView.addObject("success", getSaveSuccessMessage("veiculo"));
        return index(modelAndView);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(
        @PathVariable("id") int id,
        ModelAndView modelAndView,
        Veiculo veiculo,
        Boolean hasErrors) {
        try {
            if( hasErrors == null)
                veiculo  = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            modelAndView.addObject("veiculo",      veiculo);
            modelAndView.addObject("method",          "POST");
            modelAndView.addObject("buttonName", "Atualizar");
            modelAndView.addObject("action", "/veiculos/" + id + "/update");
            modelAndView.setViewName("veiculos/form");
            return modelAndView;
        } catch (InvalidIdException e) {
            modelAndView.addObject("error", e.getMessage());
            return index(modelAndView);
        }
    }

    @PostMapping("/{id}/update")
    public ModelAndView update(
        @PathVariable("id") int id,
        @Valid @ModelAttribute("veiculo") Veiculo veiculo,
        BindingResult result,
        ModelAndView modelAndView) {
        if(result.hasErrors()) {
            return edit(id,modelAndView,veiculo,true);
        }
        this.repository.save(veiculo);
        modelAndView.addObject("success", getUpdateSuccessMessage("veiculo"));
        return index(modelAndView);
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelAndView modelAndView) {
        try {
            Veiculo veiculo = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            this.repository.delete(veiculo);
            modelAndView.addObject("success", getDeleteSuccessMessage("veiculo"));
        } catch (InvalidIdException e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return index(modelAndView);
    }

}
