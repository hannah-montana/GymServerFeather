package io.swagger.service;

import io.swagger.model.Session;
import io.swagger.model.SessionProgram;
import io.swagger.repository.SessionProgramRepository;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("sessionProgramService")
public class SessionProgramServiceImp implements SessionProgramService  {

    SessionRepository sessionRepository;
    SessionProgramRepository sessionProgramRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SessionService sessionService;

    public SessionProgramServiceImp(SessionRepository sessionRepository, SessionProgramRepository sessionProgramRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionProgramRepository = sessionProgramRepository;
    }

    public List<Session> getListSessionsByProgramId(String progId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("progId").is(progId));
        List<SessionProgram> lstSessProg = mongoTemplate.find(query, SessionProgram.class);

        List<Session> lstSess = new ArrayList<>();
        for(SessionProgram sessProg: lstSessProg){
            Session sess = sessionRepository.findById(sessProg.getSessId());
            lstSess.add(sess);
        }

        return lstSess;
    }

    public int getMaxId(){
        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<SessionProgram> lst = sessionProgramRepository.findAll();

        for (SessionProgram item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId);
        return maxId;
    }

    public Integer saveListSesionsByProgramId(String progId, String[] listSes, String coachId){
        Query query = new Query();
        query.addCriteria(Criteria.where("progId").is(progId));
        List<SessionProgram> lstSessPro = mongoTemplate.find(query, SessionProgram.class);

        try {
            //Delete all row if matching
            for (SessionProgram item : lstSessPro) {
                sessionProgramRepository.delete(sessionProgramRepository.findById(item.getId()));
            }

            //add again
            int maxid = getMaxId();
            //String[] splitListEx = listSes.split(",");
            for (String it : listSes) {
                maxid = maxid + 1;
                SessionProgram es = new SessionProgram();
                es.setSessId(progId);
                es.sessId(it);
                es.setCoachId(coachId);
                es.setProgId(progId);
                es.setId(String.valueOf(maxid));
                sessionProgramRepository.save(es);
            }
            return 1;

        } catch (Exception e){
            return 0;
        }
    }

    public List<Session> getCheckListSession(String progId) {

        List<Session> lstExBySessId = getListSessionsByProgramId(progId);
        List<Session> lstAllExs = sessionService.getAll();

        for(Session ex: lstAllExs){
            for(Session exCheck: lstExBySessId){
                if(ex.getId() == exCheck.getId()){
                    ex.setIsChecked("1");
                }
            }
        }

        return lstAllExs;
    }
}
