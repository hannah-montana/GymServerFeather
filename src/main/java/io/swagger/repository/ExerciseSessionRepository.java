package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSessionRepository extends MongoRepository<ExerciseSessionRepository, String> {
}
