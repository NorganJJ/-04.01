package com.example.proekt.controller;

import com.example.proekt.model.*;
import com.example.proekt.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/eventmark")
//@PreAuthorize("hasAnyAuthority('USER', 'EMPLOYEE', 'ADMIN')")
public class EventMarkController {

    @Autowired
    public EventRepo eventRepo;
    @Autowired
    public UserRepo userRepo;

    @PersistenceContext // Внедрение EntityManager
    private EntityManager entityManager;


    @GetMapping()
    public String listEventMark(Model model) {
        Iterable<EventModel> eventModels = eventRepo.findAll();
        model.addAttribute("events", eventModels);
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);
        return "eventmark/all";
    }

    @GetMapping("/add")
    private String MainEventMark(Model model){
        Iterable<EventModel> eventModels = eventRepo.findAll();
        model.addAttribute("events", eventModels);
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);

        return "eventmark/add";
    }

    @PostMapping("/add")
    public String blogPostAdd(@RequestParam String event, @RequestParam String user, RedirectAttributes redirectAttributes)
    {
        EventModel eventModel = eventRepo.findByName(event);
        UserModel userModel = userRepo.findByEmail(user);
        userModel.getEvent().add(eventModel);
        eventModel.getUser().add(userModel);
        eventRepo.save(eventModel);
        userRepo.save(userModel);

        // Добавьте атрибут "success" для сообщения об успешном добавлении
        redirectAttributes.addFlashAttribute("success", "Успешно");

        // Перенаправьте пользователя на страницу "allSC"
        return "redirect:/eventmark";
    }
}
