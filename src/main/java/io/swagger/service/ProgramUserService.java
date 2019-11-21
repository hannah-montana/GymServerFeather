package io.swagger.service;

import io.swagger.model.Program;
import io.swagger.model.ProgramUser;

import java.util.List;

public interface ProgramUserService {
    List<Program> getListProgramByProgramId(String userId);
    int getMaxId();
    Integer saveListProgramsByUserId(String userId, String[] listProg, String coachId);

    Integer assignProgramToUser(ProgramUser proUser);

    Integer checkUserAvailableForAssign(String userId);

    List<Program> getListProgramByUserId(String userId);
}
