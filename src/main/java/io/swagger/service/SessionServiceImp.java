package io.swagger.service;

import io.swagger.model.ExerciseSession;
import io.swagger.model.Session;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("sessionService")
public class SessionServiceImp implements SessionService{
    private SessionRepository sessionRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public SessionServiceImp(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
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
    public List<Session> searchSessionByKeyword(String keyword){
        List<Session> lst = new ArrayList<>();
        //Query query = new Query();
        //query.addCriteria(Criteria.where("name").regex("^Matthew"));
        //List<Session> users = mongoTemplate.find(query, Session.class);
        lst = sessionRepository.findSessionByRegexString("/^((?!Matthew).)*$/s");

        return lst;
    }

    @Override
    public Session getSessionById(String id) {
        Session sess = new Session();

        sess = sessionRepository.findById(id);

        return sess;
    }

    @Override
    public Integer createSession(Session ses) {
        Session newSes = new Session();

        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<Session> lst = sessionRepository.findAll();
        for (Session item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId) + 1;

        if(sessionRepository.findByName(ses.getName().toString()) == null){
            ses.setId(String.valueOf(maxId));
            newSes = sessionRepository.save(ses);

            //add exercises

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
        }
        return newSes;
    }

    @Override
    public Integer deleteSession(String id) {
        int result = 0;

        if(sessionRepository.findById(id) != null){
            sessionRepository.delete(sessionRepository.findById(id));
            result = 1;
        }

        return  result;
    }

}
