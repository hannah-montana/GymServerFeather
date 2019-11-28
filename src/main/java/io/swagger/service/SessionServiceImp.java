package io.swagger.service;

import io.swagger.model.ExerciseSession;
import io.swagger.model.History;
import io.swagger.model.Session;
import io.swagger.repository.ExerciseSessionRepository;
import io.swagger.repository.HistoryRepository;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("sessionService")
public class SessionServiceImp implements SessionService{
    private SessionRepository sessionRepository;
    private ExerciseSessionRepository exerciseSessionRepository;
    private HistoryRepository historyRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserService userService;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Autowired
    SessionProgramService sessionProgramService;

    public SessionServiceImp(SessionRepository sessionRepository,
                             ExerciseSessionRepository exerciseSessionRepository,
                             HistoryRepository historyRepository) {
        this.sessionRepository = sessionRepository;
        this.exerciseSessionRepository = exerciseSessionRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public List<Session> getAll() {
        List<Session> lst= new ArrayList<>();

        lst = sessionRepository.findAll();

        return lst;
    }

    @Override
    public Session getSessionByName(String name) {
        Session sess = new Session();

        sess = sessionRepository.findByName(name);

        return sess;
    }

    @Override
    public Session getSessionById(String id) {
        Session sess = new Session();

        sess = sessionRepository.findById(id);

        return sess;
    }

    public int getMaxId(){
        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<Session> lst = sessionRepository.findAll();
        for (Session item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId) + 1;

        return maxId;
    }

    @Override
    public Integer createSession(Session ses) {
        Session newSes = new Session();

        int maxId = getMaxId();

        if(sessionRepository.findByName(ses.getName().toString()) == null){
            ses.setId(String.valueOf(maxId));
            newSes = sessionRepository.save(ses);

            //update point for coach
            if(ses.getLevel().equals("Easy"))
                userService.updatePointForCoach(ses.getCoachId(), userService.getEasyPoint());
            else if(ses.getLevel().equals("Medium"))
                userService.updatePointForCoach(ses.getCoachId(), userService.getMediumPoint());
            else
                userService.updatePointForCoach(ses.getCoachId(), userService.getDificultPoint());

            return maxId;
        }
        return 0;
    }

    @Override
    public Session updateSession(Session ses) {
        Session newSes = new Session();

        newSes = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(ses.getId())), Session.class);
        if(newSes != null) {
            newSes.setName(ses.getName());
            newSes.setDescription(ses.getDescription());
            newSes.setDuration(ses.getDuration());
            newSes.setLevel(ses.getLevel());
            //haven't check duplicate name
            sessionRepository.save(newSes);

            //update point for user
            userService.updatePointForCoach(ses.getCoachId(), userService.getEasyPoint());
        }
        return newSes;
    }

    @Override
    public Integer deleteSession(String id) {
        int result = 0;

        //delete in ExerciseSession
        Query query = new Query();
        query.addCriteria(Criteria.where("sessId").is(id));

        List<ExerciseSession> lstExSes = mongoTemplate.find(query, ExerciseSession.class);
        if(lstExSes != null){
            for(ExerciseSession item : lstExSes){
                exerciseSessionRepository.delete(exerciseSessionRepository.findById(item.getId()));
            }
        }

        //delete in Session
        if(sessionRepository.findById(id) != null){
            sessionRepository.delete(sessionRepository.findById(id));
            result = 1;
        }

        return  result;
    }

    public Integer duplicateSession(Session ses){
        /*
        * 1. insert new session
        * 2. insert exercise for new session
        * */
        try {
            Session duplicate = new Session();
            int maxId = getMaxId();

            int x = 0;
            String newName = ses.getName();
            while (sessionRepository.findByName(newName) != null) {
                Random random = new Random();
                x = random.nextInt(100);
                newName = newName + " - " + String.valueOf(x);
            }
            duplicate.setId(String.valueOf(maxId));
            duplicate.setName(newName);
            duplicate.setDescription(ses.getDescription());
            duplicate.setDuration(ses.getDuration());
            duplicate.setLevel(ses.getLevel());
            duplicate.setFocusSession(ses.getFocusSession());
            duplicate.setCoachId(ses.getCoachId()); //wrong

            sessionRepository.save(duplicate);

            //get list exercise by session
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(String.valueOf(ses.getId())));
            List<ExerciseSession> lstExSes = mongoTemplate.find(query, ExerciseSession.class);
            String[] lstEx = new String[lstExSes.size()];
            int i = 0;
            for(ExerciseSession item: lstExSes){
                lstEx[i] = item.getExId();
                i++;
            }
            exerciseSessionService.saveListExercisesBySessionId(String.valueOf(maxId), lstEx);

            //update point for user
            userService.updatePointForCoach(ses.getCoachId(), userService.getEasyPoint());

            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public List<Session> getListCurrentSessionByUserId(String userId){
        /*
        * Find by userid in History
        * get current processing
        * order by order field
        * */
        List<Session> lstSession = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("processing").ne("0")));
        Sort sort = new Sort(Sort.Direction.ASC, "order");
        query.with(sort);

        List<History> lstHistory = mongoTemplate.find(query, History.class);

        if(lstHistory.size() > 0){
            //get unique session id
            ArrayList<String> lstSessionId = new ArrayList<>();
            ArrayList<Integer> lstOrder = new ArrayList<>();

            String previousSession = "0";
            for(History item: lstHistory){
                if(previousSession == "0"){
                    lstSessionId.add(item.getSessId());
                    previousSession = item.getSessId();

                    Session session = sessionRepository.findById(item.getSessId());
                    if(session != null) {
                        Session newSession = session;
                        newSession.setParentId(item.getParentId());
                        lstSession.add(newSession);
                    }
                }
                else{
                    if(item.getSessId() != previousSession){
                        lstSessionId.add(item.getSessId());
                        previousSession = item.getSessId();

                        Session session = sessionRepository.findById(item.getSessId());
                        if(session != null) {
                            Session newSession = session;
                            newSession.setParentId(item.getParentId());
                            lstSession.add(newSession);
                        }
                    }
                }
                //get current session
                /*Session session = sessionRepository.findById(item.getSessId());
                if(session != null)
                    lstSession.add(session);*/
            }

            //get current session
            /*for(String item:lstSessionId){
                Session session = sessionRepository.findById(item);
                if(session != null)
                    lstSession.add(session);
            }*/
        }
        return lstSession;
    }

    public List<Session> getFurtherSessionByUserId(String userId){
        /*
         * Find by userid in History
         * get further processing
         * order by order field
         * */
        List<Session> lstSession = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("processing").is("0")));
        Sort sort = new Sort(Sort.Direction.ASC, "order");
        query.with(sort);

        List<History> lstHistory = mongoTemplate.find(query, History.class);

        if(lstHistory.size() > 0){
            //get unique session id
            ArrayList<String> lstSessionId = new ArrayList<>();
            String previousSession = "0";
            for(History item: lstHistory){
                if(previousSession == "0"){
                    lstSessionId.add(item.getSessId());
                    previousSession = item.getSessId();
                }
                else{
                    if(item.getSessId() != previousSession){
                        lstSessionId.add(item.getSessId());
                        previousSession = item.getSessId();
                    }
                }
            }
            //LinkedHashSet<String> unique = new LinkedHashSet<String>(lstSessionId);
            //get current session
            int order = 1;
            for(String item:lstSessionId){
                Session session = sessionRepository.findById(item);
                session.setOrder(order);
                if(session != null)
                    lstSession.add(session);
                order++;
            }
        }
        return lstSession;
    }
}
