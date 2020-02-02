package org.example.astra.controller;

import org.example.astra.domain.Role;
import org.example.astra.domain.User;
import org.example.astra.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){ //проверяем наличие пользователя в БД
            model.put("message", "Такой пользователь уже существует!");
         return "/registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); //создаём сет с единственным значением
        userRepo.save(user); // сохраняем пользователя
        return "redirect:/main";
    }
}
