package org.example.astra.controller;


import org.example.astra.domain.Message;
import org.example.astra.domain.User;
import org.example.astra.repos.UserRepo;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getProfile(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ){
        userService.updateProfile(user, password, email);
        return "redirect:/profile";
    }



}
