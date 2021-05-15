package br.cefetrj.mg.bsi.vocealuga.controller;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;

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
import br.cefetrj.mg.bsi.vocealuga.model.Oficina;
import br.cefetrj.mg.bsi.vocealuga.repository.OficinaRepository;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {
    
    @Autowired
    private OficinaRepository repository;
    
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("oficinas/index");
        modelAndView.addObject("oficinas", this.repository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView, Oficina oficina){
        modelAndView.setViewName("oficinas/form");
        modelAndView.addObject("oficina",oficina);
        modelAndView.addObject("buttonName", "Cadastrar");
        modelAndView.addObject("action", "/oficinas/");
        modelAndView.addObject("method", "POST");
        return modelAndView;
    }
    @PostMapping
    public ModelAndView store(@Valid @ModelAttribute("oficina") Oficina oficina, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            return create(modelAndView,oficina);
        }
        this.repository.save(oficina);
        modelAndView.addObject("success", getSaveSuccessMessage("oficina"));
        return index(modelAndView);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id,Oficina oficina, ModelAndView modelAndView, Boolean hasErrors ) throws Exception{
        try{
            if(hasErrors == null)
                oficina = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            modelAndView.setViewName("oficinas/form");
            modelAndView.addObject("oficina",oficina);
            modelAndView.addObject("buttonName", "Atualizar");
            modelAndView.addObject("action", "/oficinas/"+oficina.getId()+"/update");
            modelAndView.addObject("method", "POST");
            return modelAndView;
        }catch(Exception e){
            modelAndView.addObject("error", e.getMessage());
            return index(modelAndView);
        }
        
        
    }

    @PostMapping("/{id}/update")
    public ModelAndView update(
         @PathVariable("id") int id,
        @Valid @ModelAttribute("oficina")Oficina oficina,
        BindingResult result,  ModelAndView modelAndView) throws Exception{
            if(result.hasErrors()){
                return edit(id, oficina, modelAndView, true);
            }
            this.repository.save(oficina);
            modelAndView.addObject("success", getUpdateSuccessMessage("oficina"));
        return index(modelAndView);
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelAndView modelAndView) {
        
        try{
            Oficina oficina = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            this.repository.delete(oficina);
            modelAndView.addObject("success", getDeleteSuccessMessage("oficina"));
        }catch(InvalidIdException e){
            modelAndView.addObject("error", e.getMessage());
        }
        
        return index(modelAndView);

    }
}
