package org.example.astra.controller;


import org.example.astra.domain.User;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserService userService;
    @Value("${upload.path}")   // берёт значения из properties
    private String uploadPath;
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
            @RequestParam String email,
            @RequestParam("file") MultipartFile file

    ) throws IOException {


            if(file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()){   //проверяем, существует ли uploadPath, если нет, то создаём
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString(); //генерим universe unique id
                String resultFileName = uuidFile + "." + file.getOriginalFilename(); //конкатенируем с исходным названием

                file.transferTo(new File(uploadPath + "/" + resultFileName));
                user.setAvatarpath(resultFileName); //и вот конечное имя файла
            }




        userService.updateProfile(user, password, email);
        return "redirect:/profile";
    }



}
