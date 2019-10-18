package io.swagger.service;

import io.swagger.model.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAll();
    Exercise getExerciseByName(String name);
    Exercise getExerciseById(String id);

    Exercise createExercise(Exercise ex);
    Exercise updateExercise(Exercise ex);

    Integer deleteExerciseById(String id);


}
