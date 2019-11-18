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

    @Autowired
    UserService userService;

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
        List<Exercise> lt = exerciseRepo.findAll();
        exercise = exerciseRepo.findById(id);

        return exercise;
    }

    @Override
    public Integer createExercise(Exercise ex) {
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
            exerciseRepo.save(ex);

            //update point for coach
            if(ex.getLevel().equals("Easy"))
                userService.updatePointForCoach(ex.getCoachId(), userService.getEasyPoint());
            else if(ex.getLevel().equals("Medium"))
                userService.updatePointForCoach(ex.getCoachId(), userService.getMediumPoint());
            else
                userService.updatePointForCoach(ex.getCoachId(), userService.getDificultPoint());

            return maxId;
        }
        return 0;
    }

    @Override
    public Integer updateExercise(Exercise ex) {
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

            //update point for user
            userService.updatePointForCoach(ex.getCoachId(), userService.getEasyPoint());

            return 1;
        }
        return 0;
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