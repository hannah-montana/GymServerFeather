package io.swagger.service;

import io.swagger.model.Exercise;
import io.swagger.model.History;

import java.util.List;

public interface ExerciseSessionService {
    List<Exercise> getListExercisesBySessionId(String sessId);
    Integer saveListExercisesBySessionId(String sessId, String[] listEx);
    List<Exercise> getCheckListExercise (String sessId);
    List<History> getExercisesOfSessionInHistory(String userId, String sessId, String parentId);

    Integer updatePractical(List<History> lstHistory);
}
