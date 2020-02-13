package org.example.astra.service;


import org.example.astra.domain.PersonalMessage;
import org.example.astra.domain.User;
import org.example.astra.repos.PersonalMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonalMessageService {
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

        personalMessage.setDatetime(formatForDateNow.toString());


        personalMessageRepo.save(personalMessage);
    }

    public List<PersonalMessage> getMessages(User userFrom, User userTo){

        String currentDialogname;

        if (userFrom.getId() < userTo.getId()){
            currentDialogname = userFrom.getId().toString() + "a" + userTo.getId().toString();
        }
        else {
            currentDialogname = userTo.getId().toString() + "a" + userFrom.getId().toString();
        }



        return personalMessageRepo.findByDialogname(currentDialogname);
    }


}
