package org.example.astra.repos;

import org.example.astra.domain.PersonalMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalMessageRepo extends CrudRepository<PersonalMessage, Integer> {

    List<PersonalMessage> findByDialogname(String text);
}
