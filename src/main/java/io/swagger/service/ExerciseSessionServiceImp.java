package io.swagger.service;

import io.swagger.model.Exercise;
import io.swagger.model.ExerciseSession;
import io.swagger.repository.ExerciseRepository;
import io.swagger.repository.ExerciseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("exerciseSessionService")
public class ExerciseSessionServiceImp implements  ExerciseSessionService{
    private ExerciseSessionRepository exerciseSessionRepository;
    private ExerciseRepository exerciseRepository;

    public ExerciseSessionServiceImp(ExerciseSessionRepository exerciseSessionRepository, ExerciseRepository exerciseRepository) {
        this.exerciseSessionRepository = exerciseSessionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Exercise> getListExercisesBySessionId(String sessId){
        Query query = new Query();
        query.addCriteria(Criteria.where("sessId").is(sessId));
        List<ExerciseSession> lstExSess = mongoTemplate.find(query, ExerciseSession.class);

        List<Exercise> lstEx = new ArrayList<>();
        for( ExerciseSession exSess: lstExSess){
            Exercise ex = exerciseRepository.findById(exSess.getExId());
            lstEx.add(ex);
        }

        return lstEx;
    }

    public int getMaxId(){
        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<ExerciseSession> lst = exerciseSessionRepository.findAll();

        for (ExerciseSession item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId);
        return maxId;
    }

    public Integer saveListExercisesBySessionId(String sessId, String listEx, String coachId){
        Query query = new Query();
        query.addCriteria(Criteria.where("sessId").is(sessId));
        List<ExerciseSession> lstExSess = mongoTemplate.find(query, ExerciseSession.class);

        try {
            //Delete all row if matching
            for (ExerciseSession item : lstExSess) {
                exerciseSessionRepository.delete(exerciseSessionRepository.findById(item.getId()));
            }

            //add again
            int maxid = getMaxId();
            String[] splitListEx = listEx.split(",");
            for (String it : splitListEx) {
                maxid = maxid + 1;
                ExerciseSession es = new ExerciseSession();
                es.setSessId(sessId);
                es.setExId(it);
                es.setCoachId(coachId);
                es.setId(String.valueOf(maxid));
                exerciseSessionRepository.save(es);
            }
            return 1;

        } catch (Exception e){
            return 0;
        }
    }
}
