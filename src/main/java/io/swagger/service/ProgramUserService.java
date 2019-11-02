package io.swagger.service;

import io.swagger.model.Program;

import java.util.List;

public interface ProgramUserService {

    public List<Program> getListSessionsByProgramId(String userId);
    int getMaxId();
    Integer saveListProgramsByUserId(String userId, String[] listProg, String coachId);
}
