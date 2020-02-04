package org.example.astra.controller;

import org.example.astra.domain.Message;
import org.example.astra.domain.User;
import org.example.astra.repos.MessageRepo;
import org.example.astra.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired MessageRepo messageRepo;
    @Autowired UserRepo userRepo;

    @Value("${upload.path}")   // берёт значения из properties
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty() ) {
            messages = messageRepo.findByTag(filter);
        }
        else {
            messages = messageRepo.findAll();
        }

        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {
        Message message = new Message(text, tag, user);

        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){   //проверяем, существует ли uploadPath, если нет, то создаём
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString(); //генерим universe unique id
            String resultFileName = uuidFile + "." + file.getOriginalFilename(); //конкатенируем с исходным названием

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFilename(resultFileName); //и вот конечное имя файла
        }

        messageRepo.save(message);

        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);


        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        model.addAttribute("filter", filter);



        return "main";
    }



}