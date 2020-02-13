package org.example.astra.service;

import org.example.astra.domain.Role;
import org.example.astra.domain.User;
import org.example.astra.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.findByUsername(username);
    }


    /*возвращает false. если пользователь уже есть в БД, возвращает true, и регистрирует пользователя, если он новый*/
    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); //создаём сет с единственным значением
        user.setActivationCode(UUID.randomUUID().toString()); //генерим проверочный код, с которым перейдёт по ссылке
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user); // сохраняем пользователя


        sendActivateMessage(user);


        return true;
    }

    private void sendActivateMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Здравствуйте, %s! \n" + "Приветствуем вас в сервисе Astra! Для завершения регистрации проследуйте, пожалуйста, по данной ссылке: \n"
                    + "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()

            );

            mailSender.send(user.getEmail(), "Регистрация пользователя", message);


        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);  // устанавливаем имя пользователя

        //запрашиваем роли пользователя и переводим их из енама в сет
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); // предварительно очищаем роли пользователя
        //получаем список ролей из формы и итерируем по нему
        for (String key : form.keySet()) {
            //т.к. в форме есть и иные поля, проверяем их на соответствие с ролями
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged){
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {

            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
        if (isEmailChanged) {
            sendActivateMessage(user);}
    }
}
