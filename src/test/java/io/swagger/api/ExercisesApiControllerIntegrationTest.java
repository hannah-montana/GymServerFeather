package io.swagger.api;

import io.swagger.model.Exercise;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExercisesApiControllerIntegrationTest {

    @Autowired
    private ExercisesApi api;

    @Test
    public void deleteExerciseByIdTest() throws Exception {
        String exId = "exId_example";
        //ResponseEntity<Void> responseEntity = api.deleteExerciseById(exId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getExerciseByIdTest() throws Exception {
        String exId = "exId_example";
        //ResponseEntity<Exercise> responseEntity = api.getExerciseById(exId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateExerciseByIdTest() throws Exception {
        Exercise body = new Exercise();
        String exId = "exId_example";
        //ResponseEntity<Void> responseEntity = api.updateExerciseById(body, exId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
