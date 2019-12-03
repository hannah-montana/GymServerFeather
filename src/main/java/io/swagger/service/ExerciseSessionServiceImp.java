package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.*;
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
    private HistoryRepository historyRepository;
    private UserRepository userRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private NotificationService notificationService;

    public ExerciseSessionServiceImp(ExerciseSessionRepository exerciseSessionRepository,
                                     ExerciseRepository exerciseRepository,
                                     HistoryRepository historyRepository,
                                     UserRepository userRepository) {
        this.exerciseSessionRepository = exerciseSessionRepository;
        this.exerciseRepository = exerciseRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
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

    public List<History> getExercisesOfSessionInHistory(String userId, String sessId, String parentId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                        .andOperator(Criteria.where("sessId").is(sessId)
                                .andOperator(Criteria.where("processing").ne("0")
                                        .andOperator(Criteria.where("parentId").is(parentId))
                                )));

        List<History> lstHistory = mongoTemplate.find(query, History.class);

        return lstHistory;
    }

    public List<Exercise> getCheckListExercise (String sessId){
        List<Exercise> lstExBySessId = getListExercisesBySessionId(sessId);
        List<Exercise> lstAllExs = exerciseService.getAll(); // exerciseRepository.findAll();

        for(Exercise ex: lstAllExs){
            for(Exercise exCheck: lstExBySessId){
                if(ex.getId() == exCheck.getId()){
                    ex.setIsChecked("1");
                }
            }
        }

        return lstAllExs;
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

    public Integer saveListExercisesBySessionId(String sessId, String[] listEx){
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
            //String[] splitListEx = listEx.split(",");
            for (String it : listEx) {
                maxid = maxid + 1;
                ExerciseSession es = new ExerciseSession();
                es.setSessId(sessId);
                es.setExId(it);
                //es.setCoachId(coachId);
                es.setId(String.valueOf(maxid));
                exerciseSessionRepository.save(es);
            }
            return 1;

        } catch (Exception e){
            return 0;
        }
    }

    public Integer updatePractical(List<History> lstHistory){
        try {
            if(lstHistory != null) {
                String userId = "";
                int checkFinish = 0;
                for (History history : lstHistory) {
                    History his = historyRepository.findById(history.getId());
                    if (his != null) {
                        his.setPraticalDuration(history.getPraticalDuration());
                        if(his.getPraticalDuration() > 0)
                            checkFinish++;

                        userId = his.getUserId();

                        historyRepository.save(his);
                    }
                }
                /*
                * Finish this session
                * if this session is a focus session -> validate
                * next session is not focus session
                * processing next session
                * If not finish -> update history
                * */
                if(checkFinish == lstHistory.size()){
                    //get current session
                    History history = historyRepository.findById(lstHistory.get(0).getId());

                    //if you just finish a focus session -> have to make validation
                    if(history.getFocusSession() == 1){
                        updatePointCalorieDurationForUser(userId);
                        return 2;
                    }
                    else {
                        Query query = new Query();
                        query.addCriteria(Criteria.where("userId").is(history.getUserId())
                                .andOperator(Criteria.where("processing").is("0")
                                        .andOperator(Criteria.where("order").is(history.getOrder() + 1)
                                        )
                                )
                        );
                        List<History> lstHistory2 = mongoTemplate.find(query, History.class);
                        if (lstHistory2.size() > 0) {
                            if (lstHistory2.get(0).getFocusSession() == 0) {
                                //next session is not focus session
                                //update previous session with processing = 2
                                //proceed new session
                                for (History item : lstHistory) {
                                    History his2 = historyRepository.findById(item.getId());
                                    his2.setProcessing("2");
                                    historyRepository.save(his2);
                                }

                                for (History item : lstHistory2) {
                                    item.setProcessing("1");
                                    historyRepository.save(item);
                                }
                            } else {
                                //next session is a focus session
                                //send notification to customer -> send report to coach
                                User user = userRepository.findById(userId);
                                if (user != null) {
                                    Notification notification = new Notification();
                                    notification.setId("0");
                                    notification.setNotifyContent("Next session is a Focus Session, please send REPORT to coach");
                                    notification.setFromUser("0"); //system notification
                                    notification.setToUser(user.getId());

                                    notificationService.createNew(notification);
                                }
                            }
                        }
                    }

                }

                //update point, calorie, duration to user
                updatePointCalorieDurationForUser(userId);

                return 1;
            }
        }catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public void updatePointCalorieDurationForUser(String userId){
        int point = 0;
        int calorie = 0;
        int duration = 0;

        User user = userRepository.findById(userId);

        if(user != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userId)
                    .andOperator(Criteria.where("processing").ne("0")
                            .andOperator(Criteria.where("praticalDuration").gt(0))));
            List<History> lstHistory = mongoTemplate.find(query, History.class);
            if(lstHistory != null){
                for(History history : lstHistory){
                    point += history.getPoint();
                    calorie += history.getCalorie();
                    duration += history.getPraticalDuration();
                }
            }

            user.setPoint(point);
            user.setCalorie(calorie);
            user.setDuration(duration);

            //update badge
            if(point <= 500)
                user.setBadge("assets/images/level_1.png");
            else if(point > 500 && point <= 1000)
                user.setBadge("assets/images/level_2.png");
            else if(point > 1000 && point <= 2000)
                user.setBadge("assets/images/level_3.png");
            else if(point > 2000 && point <= 3500)
                user.setBadge("assets/images/level_4.png");
            else
                user.setBadge("assets/images/level_5.png");

            userRepository.save(user);
        }
    }
}
