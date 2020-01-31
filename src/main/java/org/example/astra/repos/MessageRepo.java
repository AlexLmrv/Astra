package org.example.astra.repos;

import org.springframework.data.repository.CrudRepository;
import org.example.astra.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}
