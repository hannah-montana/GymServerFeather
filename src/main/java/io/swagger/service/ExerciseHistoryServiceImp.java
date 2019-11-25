package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.ExerciseRepository;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@Service("exerciseHistoryService")
public class ExerciseHistoryServiceImp implements ExerciseHistoryService {
    SessionRepository sessionRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public ExerciseHistoryServiceImp(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    //get All exercises by userId
    @Override
    public List<ExerciseHistory> getListExerciseHistoryByUserId(String userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<ExerciseHistory> lsExHis = mongoTemplate.find(query, ExerciseHistory.class);

        return lsExHis;
    }


    //get List Session of A User
    @Override
    public List<Session> getListSessionsByUserId(String userId) {

        List<ExerciseHistory> lsExHis = this.getListExerciseHistoryByUserId(userId);

        List<Session> lstSess = new ArrayList<>();

        for(ExerciseHistory exHis: lsExHis){
            Session sess = sessionRepository.findById(exHis.getSessId());
            if(!lstSess.contains(sess)){
                lstSess.add(sess);
            }
        }

        return lstSess;
    }

    //get List of Total Point of Sessions of A User
    /*@Override
    public List<Integer> getListPointOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        List<Integer> lstPoint = new ArrayList<>();

        lstSess = this.getListSessionsByUserId(userId);

        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<ExerciseHistory> lstExHis = mongoTemplate.find(query, ExerciseHistory.class);

            int totalPointOfSession = 0;

            for(ExerciseHistory exHis: lstExHis){
                int point= exHis.getPoint();
                totalPointOfSession+=point;
            }

            lstPoint.add(totalPointOfSession);
        }

        return lstPoint;
    }*/

    //get List of Total Calorie of Session of A User
    /*@Override
    public Map<String,Integer> getMapCalorieOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        Map<String, Integer> mapSessCalorie = new HashMap<>();

        lstSess = this.getListSessionsByUserId(userId);

        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<ExerciseHistory> lstExHis = mongoTemplate.find(query, ExerciseHistory.class);

            int totalCalorieOfSession = 0;

            for(ExerciseHistory exHis: lstExHis){
                int calorie= exHis.getCalorie();
                totalCalorieOfSession+=calorie;
            }

            mapSessCalorie.put(sess.getId(),totalCalorieOfSession);
        }

        return mapSessCalorie;
    }*/


//}
