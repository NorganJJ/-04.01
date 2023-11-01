package com.example.proekt.controller;

import com.example.proekt.model.AnimalModel;
import com.example.proekt.model.AviaryModel;
import com.example.proekt.model.UserModel;
import com.example.proekt.repo.AnimalRepo;
import com.example.proekt.repo.AviaryRepo;
import com.example.proekt.repo.UserRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animal")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
public class AnimalController {
    @Autowired
    private AnimalRepo Repo;
    @Autowired
    private AviaryRepo aviaryRepo;

    @Autowired
    public AnimalController(AnimalRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listAnimal(Model model) {
        Iterable<AnimalModel> animalModels = Repo.findAll();
        model.addAttribute("animals", animalModels);
        return "animal/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Iterable<AviaryModel> aviaryModels = aviaryRepo.findAll();
        model.addAttribute("aviary", aviaryModels);
        return "animal/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam long id, @RequestParam String name, @RequestParam int age, @RequestParam String color) {
        AnimalModel animalModel = new AnimalModel(id, name, age, color);
        Repo.save(animalModel);
        return "redirect:/animal";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        AnimalModel animalModel = Repo.findById(id).orElse(null);
        if (animalModel == null) {
            return "redirect:/animal";
        }
        model.addAttribute("animal", animalModel);
        return "animal/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @Valid @ModelAttribute("animal") AnimalModel animalModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "animal/update";
        }
        animalModel.setId(id);
        Repo.save(animalModel);
        return "redirect:/animal";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/animal";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "name") String name, Model model) {
        Iterable<AnimalModel> animalModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("animals", animalModels);
        return "animal/all";
    }
}
