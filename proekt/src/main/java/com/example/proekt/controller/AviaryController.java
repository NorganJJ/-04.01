package com.example.proekt.controller;

import com.example.proekt.model.AviaryModel;
import com.example.proekt.repo.AviaryRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aviary")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
public class AviaryController {
    private final AviaryRepo Repo;

    @Autowired
    public AviaryController(AviaryRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listAviary(Model model) {
        Iterable<AviaryModel> aviaries = Repo.findAll();
        model.addAttribute("aviaries", aviaries);
        return "aviary/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        AviaryModel aviaryModel = new AviaryModel();
        //clas.setPublicationYear(0);
        model.addAttribute("aviary", aviaryModel);
        return "aviary/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("aviary") AviaryModel aviaryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aviary/add";
        }
        System.out.println("Name: " + aviaryModel.getName());
        Repo.save(aviaryModel);
        return "redirect:/aviary";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        AviaryModel aviaryModel = Repo.findById(id).orElse(null);
        if (aviaryModel == null) {
            return "redirect:/aviary";
        }
        model.addAttribute("aviary", aviaryModel);
        return "aviary/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("aviary") AviaryModel aviaryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "aviary/update";
        }
        aviaryModel.setId(id);
        Repo.save(aviaryModel);
        return "redirect:/aviary";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/aviary";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "code") String code, Model model) {
        Iterable<AviaryModel> aviaryModels = Repo.findByNameContainingIgnoreCase(code);
        model.addAttribute("aviaries", aviaryModels);
        return "aviary/all";
    }
}
