package com.example.proekt.controller;

import com.example.proekt.model.UserModel;
import com.example.proekt.model.roleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYEE')")
public class HomeController {
    @GetMapping("/")
    public String login() {
        return "home";
    }

}