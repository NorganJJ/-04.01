package com.example.proekt.controller;

import com.example.proekt.model.UserModel;
import com.example.proekt.model.roleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @GetMapping("/access_denied")
    private String access()
    {
        return "access_denied";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private com.example.proekt.repo.UserRepo userRepo;
    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(UserModel user, Model model)
    {
        UserModel user_from_db = userRepo.findByEmail(user.getEmail());
        if (user_from_db != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "/login";
    }
}
