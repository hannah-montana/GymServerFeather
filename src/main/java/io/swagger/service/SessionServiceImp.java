package io.swagger.service;

import io.swagger.model.Session;
import io.swagger.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Session getSessionById(String id) {
        Session sess = new Session();
        sess = sessionRepository.findByName(id);
        return sess;
    }

    @Override
    public Session createSession(Session ses) {
        Session newSes = new Session();

        if(sessionRepository.findByName(ses.getName().toString()) == null){
            newSes = sessionRepository.save(ses);
        }

        return newSes;
    }

    @Override
    public Session updateSession(Session ses) {
        /*Need to modify*/
        Session updateSes= sessionRepository.findByName(ses.getName().toString());
        List<Session> lst = sessionRepository.findAll();

        if(sessionRepository.findByName(ses.getName().toString()) != null) {
            for(Session exercise:lst){
                if (updateSes.getName().equals(exercise.getName())) {
                    updateSes = sessionRepository.save(ses);
                    return updateSes;
                }
            }
        }

        return updateSes;
    }

    @Override
    public Integer deleteSession(String id) {
        int result = 0;

        if(sessionRepository.findByName(id) != null){
            sessionRepository.delete(id);
            result = 1;
        }

        return  result;
    }
}
