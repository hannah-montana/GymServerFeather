package io.swagger.service;

import io.swagger.model.History;
import io.swagger.model.Session;

import java.util.List;

public interface HistoryService {
    List<History> getListHistoryByUserId(String userId);
    List<Session> getListSessionsByUserId(String userId);
}
