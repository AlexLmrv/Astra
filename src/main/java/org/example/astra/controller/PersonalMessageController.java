package org.example.astra.controller;


import org.example.astra.domain.PersonalMessage;
import org.example.astra.domain.User;
import org.example.astra.repos.PersonalMessageRepo;
import org.example.astra.repos.UserRepo;
import org.example.astra.service.PersonalMessageService;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/dialogs")
public class PersonalMessageController {

    @Autowired
    PersonalMessageRepo personalMessageRepo;
    @Autowired
    PersonalMessageService personalMessageService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Value("${upload.path}")
    private String uploadPath;



    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "dialogList";
        /*здесь просто возвращаем список пользователей, которым можно написать сообщение*/
    }

    @GetMapping("{userTo}")
    public String dialog(
            @AuthenticationPrincipal User userFrom,
            @PathVariable User userTo,
            Model model){
        model.addAttribute("messages", personalMessageService.getMessages(userFrom, userTo));
        model.addAttribute("userTo", userTo);


        return "dialogPage";

    }

    @PostMapping("{userTo}")
    public String sendmessage(
            @AuthenticationPrincipal User userFrom,
            @Valid PersonalMessage personalMessage,
            Model model,
            @PathVariable User userTo,
            @RequestParam String text){
        //PersonalMessage personalMessage = new PersonalMessage(); //пока не нужно
        personalMessage.setText(text);
        personalMessageService.sendMessage(userFrom, userTo, personalMessage);
        //и обратно возвращаем инфу
        model.addAttribute("messages", personalMessageService.getMessages(userFrom, userTo));
        model.addAttribute("userTo", userTo);
        model.addAttribute("user", userFrom);

        //return "dialogPage";
        return "redirect:/dialogs/{userTo}";
    }

}
