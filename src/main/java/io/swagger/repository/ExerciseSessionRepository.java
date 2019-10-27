package io.swagger.repository;

import io.swagger.model.ExerciseSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseSessionRepository extends MongoRepository<ExerciseSession, String> {
    ExerciseSession findById(String id);
    boolean deleteByIdIn(List<String> lstId);
}
