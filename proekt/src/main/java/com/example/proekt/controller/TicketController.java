package com.example.proekt.controller;

import com.example.proekt.model.TicketModel;
import com.example.proekt.model.UserModel;
import com.example.proekt.repo.TicketRepo;
import com.example.proekt.repo.UserRepo;
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
@RequestMapping("/ticket")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class TicketController {

    @Autowired
    public TicketRepo Repo; //hero
    @Autowired
    public UserRepo userRepo; //class
    @PersistenceContext // Внедрение EntityManager
    private EntityManager entityManager;


    @GetMapping()
    public String listTickets(Model model) {
        Iterable<TicketModel> ticketModels = Repo.findAll();
        model.addAttribute("tickets", ticketModels);
        return "ticket/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        TicketModel ticketModel = new TicketModel();
        model.addAttribute("ticket", ticketModel);

        // Получите список классов из репозитория и добавьте его в модель
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);

        return "ticket/add";
    }



    @PostMapping("/add")
    public String addHero(
            @Valid @ModelAttribute("ticket") TicketModel ticketModel,
            BindingResult bindingResult) {
        // Сохранение HeroModel с выбранным оружием
        Repo.save(ticketModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/ticket";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        TicketModel ticketModel = Repo.findById(id).orElse(null);
        if (ticketModel == null) {
            return "redirect:/ticket";
        }
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);

        model.addAttribute("ticket", ticketModel);
        return "ticket/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("ticket") TicketModel ticketModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<UserModel> userModels = userRepo.findAll();
            model.addAttribute("users", userModels);

            return "ticket/update";
        }
        ticketModel.setId(id);

        Repo.save(ticketModel);
        return "redirect:/ticket";
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/ticket";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<TicketModel> ticketModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("tickets", ticketModels);
        return "ticket/all";
    }

}
