package org.example.astra.controller;

import org.example.astra.domain.User;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("message","");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){

        if (!userService.addUser(user)){ //проверяем наличие пользователя в БД
            model.addAttribute("message", "Такой пользователь уже существует!");
         return "/registration";
        }

        return "redirect:/main";
    }


    @GetMapping("/activate/{code}")
    public String activate (Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated){
            model.addAttribute("message", "Профиль успешно активирован!");
        }
        else {
            model.addAttribute("message", "При активации профиля возникла ошибка!");
        }
        return "login";
    }

}
