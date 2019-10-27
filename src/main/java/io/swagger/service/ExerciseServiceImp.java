package io.swagger.service;

import io.swagger.model.Exercise;
import io.swagger.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<Exercise> lst = exerciseRepo.findAll();
        for (Exercise item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId) + 1;

        if(exerciseRepo.findByName(ex.getName().toString()) == null){
            ex.setId(String.valueOf(maxId));
            newEx = exerciseRepo.save(ex);
            return newEx;
        }
        return newEx;
    }

    @Override
    public Exercise updateExercise(Exercise ex) {
        Exercise newEx = new Exercise();

        newEx = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(ex.getId())), Exercise.class);
        if(newEx != null) {
            newEx.setName(ex.getName());
            newEx.setDescription(ex.getDescription());
            newEx.setInstruction(ex.getInstruction());
            newEx.setDuration(ex.getDuration());
            newEx.setLevel(ex.getLevel());
            newEx.setType(ex.getType());
            newEx.setTarget(ex.getTarget());
            newEx.setCalorie(ex.getCalorie());
            newEx.setPhoto(ex.getPhoto());
            newEx.setBenefit(ex.getBenefit());
            newEx.setFrequency(ex.getFrequency());
            newEx.setPoint(ex.getPoint());
            //haven't check duplicate name
            exerciseRepo.save(newEx);
        }
        return newEx;
    }

    @Override
    public Integer deleteExerciseById(String id) {
        int result = 0;

        if(exerciseRepo.findById(id) != null){
            exerciseRepo.delete(exerciseRepo.findById(id));
            result = 1;
        }
        return  result;
    }

}