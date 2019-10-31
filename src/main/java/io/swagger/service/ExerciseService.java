package io.swagger.service;

import io.swagger.model.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAll();
    Exercise getExerciseByName(String name);
    Exercise getExerciseById(String id);

    Integer createExercise(Exercise ex);
    Integer updateExercise(Exercise ex);

    Integer deleteExerciseById(String id);
}
