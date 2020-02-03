package org.example.astra.controller;


import org.example.astra.domain.Message;
import org.example.astra.domain.User;
import org.example.astra.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProfileController {
    //@Autowired UserRepo userRepo;

    @GetMapping("/profile")
    public String profile(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("user", user);

        return "profile";
    }



}
