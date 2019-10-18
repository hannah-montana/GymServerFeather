package io.swagger.repository;


import io.swagger.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository <Session, String> {
    Session findByName(String name);
    Session findById(String id);

}

