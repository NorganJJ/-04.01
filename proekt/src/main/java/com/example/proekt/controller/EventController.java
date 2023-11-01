package com.example.proekt.controller;

import com.example.proekt.model.*;
import com.example.proekt.repo.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class EventController {

    @Autowired
    public EventRepo Repo;
    @Autowired
    public CoachesRepo coachesRepo;
    @Autowired
    public CategoryRepo categoryRepo;
    @PersistenceContext // Внедрение EntityManager
    private EntityManager entityManager;


    @GetMapping()
    public String listTickets(Model model) {
        Iterable<EventModel> eventModels = Repo.findAll();
        model.addAttribute("events", eventModels);
        return "event/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        EventModel eventModel = new EventModel();
        model.addAttribute("event", eventModel);

        // Получите список классов из репозитория и добавьте его в модель
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
        model.addAttribute("categories", categoryModels);

        return "event/add";
    }


    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("event") EventModel eventModel,
            BindingResult bindingResult) {
        // Сохранение HeroModel с выбранным оружием
        Repo.save(eventModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/event";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        EventModel eventModel = Repo.findById(id).orElse(null);
        if (eventModel == null) {
            return "redirect:/event";
        }
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
        model.addAttribute("categories", categoryModels);

        model.addAttribute("event", eventModel);
        return "event/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("event") EventModel eventModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
            model.addAttribute("coachess", coachesModels);

            Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
            model.addAttribute("categories", categoryModels);

            return "event/update";
        }
        eventModel.setId(id);

        Repo.save(eventModel);
        return "redirect:/event";
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/event";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<EventModel> eventModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("events", eventModels);
        return "event/all";
    }
}
