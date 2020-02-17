package org.example.astra.controller;

import org.example.astra.domain.Message;
import org.example.astra.domain.User;
import org.example.astra.repos.MessageRepo;
import org.example.astra.repos.UserRepo;
import org.example.astra.service.MainMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired MessageRepo messageRepo;
    @Autowired UserRepo userRepo;
    @Autowired MainMessageService mainMessageService;


    @GetMapping("/")
    public String greeting() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){

        model.addAttribute("messages", mainMessageService.getMessages(filter));
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult, //список ошибок и аргументов валидации, должен быть перед model
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam("file") MultipartFile file)
            throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);
        }
        else{
            mainMessageService.sendFilename(message, file);
            mainMessageService.sendMessage(message);
        }

        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("messages", messageRepo.findAll());
        model.addAttribute("filter", filter);

        return "redirect:/main";
    }




}