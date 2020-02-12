package org.example.astra.controller;

import org.example.astra.domain.User;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

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
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConf())){
            model.addAttribute("message","Пароли не совпадают!");
        }

        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)){ //проверяем наличие пользователя в БД
            model.addAttribute("usernameError", "Такой пользователь уже существует!");
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
