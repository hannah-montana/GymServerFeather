package io.swagger.service;

import io.swagger.model.Program;

import java.util.List;

public interface ProgramService {
    List<Program> getAll();
    Program getProgramById(String id);
    Program getProgramByName(String name);

    Program createProgram(Program prog);
    Program updateProgram(Program prog);


    Integer deleteProgramById(String id);
}
