package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.ExerciseRepository;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

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
            if(!lstSess.contains(sess)){
                lstSess.add(sess);
            }
            //lstSess.add(sess);
        }

        return lstSess;
    }

    public Integer checkFinishedSession(String sessId, String userId){
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userId)
                    .andOperator(Criteria.where("sessId").is(sessId)
                            .andOperator(Criteria.where("processing").is("1")
                            )
                    )
            );
            List<History> lstHistory = mongoTemplate.find(query, History.class);
            int nextOrder = 0;
            if(lstHistory != null){
                for(History his: lstHistory){
                    nextOrder = his.getOrder();
                    if(his.getPraticalDuration() == 0)
                        return 1; //haven't finish
                }

                nextOrder++;
                /*
                * Check next session
                * If focus session -> send report
                * Else -> cannot sent
                * */
                Query query2 = new Query();
                query2.addCriteria(Criteria.where("userId").is(userId)
                        .andOperator(Criteria.where("processing").is("0")
                                .andOperator(Criteria.where("order").is(nextOrder)
                                )
                        )
                );
                List<History> lstHistory2 = mongoTemplate.find(query2, History.class);
                if(lstHistory2 != null){
                    if(lstHistory2.get(0).getFocusSession() == 1)
                        return 2; //next session is focus session -> send report
                }
                return 3; //next session is not fs
            }
            return 0;
        }catch (Exception ex){
            return 0;
        }
    }

    public List<Evoluation> getListEvolution(String userId){
        List<Evoluation> lstEvoluation = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
            .andOperator(Criteria.where("focusSession").is("1")
                    .andOperator(Criteria.where("processing").ne("0"))));

        List<History> lstHistory = mongoTemplate.find(query, History.class);
        if(lstHistory.size() > 0){
            ArrayList<String> lstParentId = new ArrayList<>();
            String parentId = "";
            for(History history: lstHistory){
                parentId = history.getParentId();
                lstParentId.add(parentId);
            }
            LinkedHashSet<String> unique = new LinkedHashSet<String>(lstParentId);


            int fsNo = 1;
            for(String item: unique){
                Query query2 = new Query();
                query2.addCriteria(Criteria.where("parentId").is(item));
                List<History> subHistorys = new ArrayList<>();
                if(subHistorys.size() > 0) {
                    Evoluation evoluation = new Evoluation();
                    evoluation.setType("column");
                    evoluation.setName("FC" + String.valueOf(fsNo));
                    evoluation.setLegendText("FC" + String.valueOf(fsNo));
                    evoluation.setShowInLegend(true);

                    List<DataPoint> lstDataPoint = new ArrayList<>();
                    for (History subHistory : subHistorys) {
                        DataPoint dataPoint = new DataPoint();
                        dataPoint.setLabel(subHistory.getName());
                        dataPoint.setY(String.valueOf(subHistory.getPraticalDuration()));
                        lstDataPoint.add(dataPoint);
                    }
                    evoluation.setDataPoints(lstDataPoint);
                    lstEvoluation.add(evoluation);
                }
                fsNo++;
            }
        }

        return lstEvoluation;
    }
}
