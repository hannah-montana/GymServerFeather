package io.swagger.service;

import io.swagger.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> getAll();
    Session getSessionByName(String name);
    Session getSessionById(String id);

    Integer createSession(Session ses);
    Session updateSession(Session ses);

    Integer deleteSession(String id);

    Integer duplicateSession(Session ses);
}
