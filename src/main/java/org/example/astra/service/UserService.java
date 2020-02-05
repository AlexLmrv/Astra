package org.example.astra.service;

import org.example.astra.domain.Role;
import org.example.astra.domain.User;
import org.example.astra.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private MailSender mailSender;


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
        userRepo.save(user); // сохраняем пользователя

        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Здравствуйте, %s! \n" + "Приветствуем вас в сервисе Astra! Для завершения регистрации проследуйте, пожалуйста, по данной ссылке: \n"
                    + "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()

            );

            mailSender.send(user.getEmail(), "Регистрация пользователя", message);


        }



        return true;
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
}
