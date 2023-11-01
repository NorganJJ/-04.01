package com.example.proekt.controller;

import com.example.proekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.proekt.model.roleEnum;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private com.example.proekt.repo.UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String listUser(Model model) {
        Iterable<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/all";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", roleEnum.values());
        return "user/update";
    }


    @PostMapping("/{id}/update")
    public String update_user(@RequestParam String email,
                              @RequestParam(name="roles[]", required = false) String[] roles,
                              @PathVariable Long id)
    {
        UserModel user = userRepository.findById(id).orElseThrow();
        user.setEmail(email);

        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(roleEnum.valueOf(role));
            }
        }



        userRepository.save(user);
        return "/home";
    }

}