package org.example.astra.repos;

import org.springframework.data.repository.CrudRepository;
import org.example.astra.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String text);


}
