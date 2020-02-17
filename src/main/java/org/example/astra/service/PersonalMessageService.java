package org.example.astra.service;


import org.example.astra.domain.PersonalMessage;
import org.example.astra.domain.User;
import org.example.astra.repos.PersonalMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonalMessageService {

    @Value("${upload.path}")   // берёт значения из properties
    private String uploadPath;

    @Autowired
    PersonalMessageRepo personalMessageRepo;


    public void sendMessage(User userFrom, User userTo, PersonalMessage personalMessage){
        if (userFrom.getId() < userTo.getId()){
            personalMessage.setDialogname(userFrom.getId().toString()+"a"+userTo.getId().toString());
        }
        else {
            personalMessage.setDialogname(userTo.getId().toString()+"a"+userFrom.getId().toString());
        }

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("'Отправлено 'E yyyy.MM.dd ' в ' hh:mm:ss a zzz");

        personalMessage.setDatetime(formatForDateNow.format(dateNow));
        personalMessage.setAuthor(userFrom);


        personalMessageRepo.save(personalMessage);
    }

    public void sendFilename(PersonalMessage message , MultipartFile file) throws IOException {

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

    }

    public List<PersonalMessage> getMessages(User userFrom, User userTo){

        String currentDialogname;

        if (userFrom.getId() < userTo.getId()){
            currentDialogname = userFrom.getId().toString() + "a" + userTo.getId().toString();
        }
        else {
            currentDialogname = userTo.getId().toString() + "a" + userFrom.getId().toString();
        }


        List<PersonalMessage> resultMessages = personalMessageRepo.findByDialogname(currentDialogname);
        Collections.reverse(resultMessages);
        return resultMessages;
    }


}
