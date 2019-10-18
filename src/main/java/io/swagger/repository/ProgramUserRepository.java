package io.swagger.repository;

import io.swagger.model.ProgramUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramUserRepository extends MongoRepository<ProgramUser, String> {
}
