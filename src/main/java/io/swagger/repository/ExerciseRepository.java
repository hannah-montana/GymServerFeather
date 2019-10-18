package io.swagger.repository;

import io.swagger.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository <Exercise, String> {
    Exercise findByName(String name);
    Exercise findById(String id);
}

