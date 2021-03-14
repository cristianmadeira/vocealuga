package br.cefetrj.mg.bsi.vocealuga.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;
import br.cefetrj.mg.bsi.vocealuga.service.CargoServiceImpl;
import br.cefetrj.mg.bsi.vocealuga.service.ICargoService;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.*;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    private ICargoService service = null;

    public CargoController() {

        this.service = new CargoServiceImpl();
    }

    @GetMapping
    public String index(Model model) {
        try {
            List<Cargo> cargos = service.findAll();
            model.addAttribute("cargos", cargos);
            if (cargos.isEmpty())
                throw new Exception("Não há cargos cadastrados.");
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());
            // System.out.println(e.getClass().getName().startsWith("java.sql"));
        }

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
    public String store(@ModelAttribute Cargo cargo, Model model) {
        try {
            this.service.save(cargo);
            model.addAttribute("success", getSaveSuccessMessage("cargo"));

            return index(model);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());

            return this.create(model);
        }

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("cargo", this.service.findById(id));
            model.addAttribute("method", "POST");
            model.addAttribute("buttonName", "Atualizar");
            model.addAttribute("url", "/cargos/" + id + "/update");

            return "cargos/form";
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());
            return index(model);
        }

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute Cargo cargo, Model model) {
        try {
            this.service.update(cargo);
            model.addAttribute("success", getUpdateSuccessMessage("cargo"));

            return index(model);
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

            return edit(id, model);

        }

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        try {
            this.service.delete(id);
            model.addAttribute("success", getDeleteSuccessMessage("cargo"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return index(model);

    }
}
