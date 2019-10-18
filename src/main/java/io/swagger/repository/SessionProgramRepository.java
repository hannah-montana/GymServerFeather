package io.swagger.repository;

import io.swagger.model.SessionProgram;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionProgramRepository extends MongoRepository<SessionProgram, String> {
}
