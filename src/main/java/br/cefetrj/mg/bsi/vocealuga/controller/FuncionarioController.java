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
import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;
import br.cefetrj.mg.bsi.vocealuga.repository.AgenciaRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.CargoRepository;
import br.cefetrj.mg.bsi.vocealuga.repository.FuncionarioRepository;
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private FuncionarioRepository repository;
    private CargoRepository cargoRepository;
    private AgenciaRepository agenciaRepository;
    @Autowired
    public FuncionarioController(
        FuncionarioRepository repository,
        AgenciaRepository agenciaRepository,
        CargoRepository cargoRepository){
            this.repository = repository;
            this.agenciaRepository = agenciaRepository;
            this.cargoRepository = cargoRepository;
        
    }
    
    

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("funcionarios",this.repository.findAll());
        modelAndView.setViewName("funcionarios/index");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(Funcionario funcionario, ModelAndView modelAndView){
        modelAndView.setViewName("funcionarios/form");
        modelAndView.addObject("funcionario",funcionario);
        modelAndView.addObject("agencias", agenciaRepository.findAll());
        modelAndView.addObject("cargos", cargoRepository.findAll());
        modelAndView.addObject("method", "POST");
        modelAndView.addObject("buttonName", "Cadastrar");
        modelAndView.addObject("url", "/funcionarios/");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(@Valid @ModelAttribute("funcionario") Funcionario funcionario,BindingResult result, ModelAndView modelAndView){
        if(result.hasErrors()){
            return create(funcionario,modelAndView);
        }
        this.repository.save(funcionario);
        modelAndView.addObject("success", getSaveSuccessMessage("funcionário"));
        return index(modelAndView);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id")int id, ModelAndView modelAndView, Funcionario funcionario, Boolean hasErrors){
        try{
            if(hasErrors == null)
                funcionario = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            modelAndView.setViewName("funcionarios/form");
            modelAndView.addObject("funcionario",funcionario);
            modelAndView.addObject("agencias", agenciaRepository.findAll());
            modelAndView.addObject("cargos", cargoRepository.findAll());
            modelAndView.addObject("method", "POST");
            modelAndView.addObject("buttonName", "Atualizar");
            modelAndView.addObject("url", "/funcionarios/"+id+"/update");
            return modelAndView;
        }catch(InvalidIdException e){
            modelAndView.addObject("error", e.getMessage());
            return index(modelAndView);
        }
        

    }
    @PostMapping("/{id}/update")
    public ModelAndView update(
        @PathVariable ("id") int id, 
        @Valid @ModelAttribute("funcionario") Funcionario funcionario,
        BindingResult result,
        ModelAndView modelAndView){
        if(result.hasErrors())
            return edit(id,modelAndView,funcionario, true);
        this.repository.save(funcionario);
        modelAndView.addObject("success", getUpdateSuccessMessage("funcionário"));
        return index(modelAndView);
        
    }
    @PostMapping("{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelAndView modelAndView){
        try{
            Funcionario funcionario = this.repository.findById(id).orElseThrow(()->new InvalidIdException(id));
            this.repository.delete(funcionario);
            modelAndView.addObject("success", getDeleteSuccessMessage("funcionário"));
        }catch(InvalidIdException e ){
            modelAndView.addObject("error", e.getMessage());
        }
        return index(modelAndView);
    }


    
}
