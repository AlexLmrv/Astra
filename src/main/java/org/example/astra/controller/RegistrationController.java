package org.example.astra.controller;

import org.example.astra.domain.User;
import org.example.astra.domain.dto.CaptchaResponseDto;
import org.example.astra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Value("${recaptcha.secret}")
    private String secret;
    @Autowired
    private UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("message","");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordConf") String passwordConf,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        String urlForCaptcha = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(urlForCaptcha, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()){
            model.addAttribute("captchaError", "Заполните капчу!");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConf);
        if (isConfirmEmpty){
            model.addAttribute("passwordConfError","Введите верный повторный пароль!");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConf)){
            model.addAttribute("message","Пароли не совпадают!");
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()){
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
