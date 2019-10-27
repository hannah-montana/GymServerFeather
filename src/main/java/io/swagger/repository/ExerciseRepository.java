package io.swagger.repository;

import io.swagger.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends MongoRepository <Exercise, String> {
    Exercise findByName(String name);
    Exercise findById(String id);
    Exercise findFirstByOrderByIdDesc();
}

