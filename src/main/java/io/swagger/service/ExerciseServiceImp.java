package io.swagger.service;

import io.swagger.model.Exercise;
import io.swagger.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("exerciseService")
public class ExerciseServiceImp implements ExerciseService {

    private ExerciseRepository exerciseRepo;

    public ExerciseServiceImp(ExerciseRepository exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Exercise> getAll() {
        List<Exercise> lst = new ArrayList<>();
        lst = exerciseRepo.findAll();
        return lst;
    }

    @Override
    public Exercise getExerciseByName(String name) {
        Exercise ex = new Exercise();
        ex = exerciseRepo.findByName(name);
        return ex;
    }

    @Override
    public Exercise getExerciseById(String id) {

        Exercise exercise = new Exercise();
        exercise = exerciseRepo.findById(id);

        //exercise = exerciseRepo.findOne(id);
        //Query query = new Query(Criteria.where("id").is(id));
        //exercise = mongoTemplate.find(query,Exercise.class);

        return exercise;
    }

    @Override
    public Exercise createExercise(Exercise ex) {
        Exercise newEx = new Exercise();

        if(exerciseRepo.findByName(ex.getName().toString()) == null){
            newEx = exerciseRepo.save(ex);
        }

        return newEx;
    }

    @Override
    public Exercise updateExercise(Exercise ex) {
        // Need update
        Exercise exercise = new Exercise();

        if(exerciseRepo.exists(ex.getName().toString())){
            exerciseRepo.save(ex);
            exercise = ex;
        }

        return exercise;
    }

    @Override
    public Integer deleteExerciseById(String id) {
        int result = 0;

        if(exerciseRepo.findById(id) != null){
            exerciseRepo.delete(id);
            result = 1;
        }
        return  result;
    }




}