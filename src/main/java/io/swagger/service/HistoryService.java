package io.swagger.service;

import io.swagger.model.Evoluation;
import io.swagger.model.History;
import io.swagger.model.Session;

import java.util.List;

public interface HistoryService {
    List<History> getListHistoryByUserId(String userId);
    List<Session> getListSessionsByUserId(String userId);
    Integer checkFinishedSession(String sessId, String userId);
    List<Evoluation> getListEvolution(String userId);
}
