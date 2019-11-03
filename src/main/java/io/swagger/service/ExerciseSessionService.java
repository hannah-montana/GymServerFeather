package io.swagger.service;

import io.swagger.model.Exercise;

import java.util.List;

public interface ExerciseSessionService {
    List<Exercise> getListExercisesBySessionId(String sessId);
    Integer saveListExercisesBySessionId(String sessId, String[] listEx, String coachId);
    List<Exercise> getCheckListExercise (String sessId);
}
