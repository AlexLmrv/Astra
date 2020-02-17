package org.example.astra.service;


import org.example.astra.domain.Message;
import org.example.astra.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class MainMessageService {
    @Value("${upload.path}")   // берёт значения из properties
    private String uploadPath;

    @Autowired
    MessageRepo messageRepo;

    public List<Message> getMessages(String filter){
        if (filter != null && !filter.isEmpty() ) {
            List<Message> resultMessages = messageRepo.findByTag(filter);
            Collections.reverse(resultMessages);
           return resultMessages;
        }
        else {
            List<Message> resultMessages = messageRepo.findAll();
            Collections.reverse(resultMessages);
            return resultMessages;
        }
    }


    public void sendFilename(Message message , MultipartFile file) throws IOException {

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

    public void sendMessage(Message message) {
        messageRepo.save(message);
    }
}
