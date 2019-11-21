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

@Service("historyService")
public class HistoryServiceImp implements HistoryService{
    SessionRepository sessionRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public HistoryServiceImp(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    //get All exercises by userId
    @Override
    public List<History> getListHistoryByUserId(String userId) {
        List<History> lstProcessingHistory = new ArrayList<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<History> lsHistory = mongoTemplate.find(query, History.class);

        for(History history: lsHistory){
            if(history.getProcessing().equals("1")){
                lstProcessingHistory.add(history);
            }
        }

        return lstProcessingHistory;
    }

    //get List Session of A User
    @Override
    public List<Session> getListSessionsByUserId(String userId) {
        List<History> lsHistory = this.getListHistoryByUserId(userId);

        List<Session> lstSess = new ArrayList<>();

        for(History history: lsHistory){
            Session sess = sessionRepository.findById(history.getSessId());
            /*if(!lstSess.contains(sess)){
                lstSess.add(sess);
            }*/
            lstSess.add(sess);
        }

        return lstSess;
    }
}
