package io.swagger.repository;

import io.swagger.model.Program;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends MongoRepository<Program, String> {
    Program findByName(String name);
    Program findById(String id);
}
