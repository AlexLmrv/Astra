package org.example.astra.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("{user}")
    public String dialog(
            @AuthenticationPrincipal User userFrom,
            @PathVariable User user,
            Model model){
        model.addAttribute("messages", personalMessageService.getMessages(userFrom, user));

        return "dialogPage";

    }


}
