package io.swagger.service;

import io.swagger.model.Session;

import java.util.List;

public interface SessionProgramService {
    List<Session> getListSessionsByProgramId(String proId);
    int getMaxId();
    Integer saveListSesionsByProgramId(String sessId, String[] listEx, String coachId);
}
