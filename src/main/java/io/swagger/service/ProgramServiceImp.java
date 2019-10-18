package io.swagger.service;

import io.swagger.model.Program;
import io.swagger.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("programService")
public class ProgramServiceImp implements  ProgramService {
    private ProgramRepository programRepository;

    public ProgramServiceImp(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> getAll() {
        List<Program> lst = new ArrayList<>();
        lst = programRepository.findAll();
        return lst;
    }

    @Override
    public Program getProgramById(String id) {
        Program prog = new Program();
        prog = programRepository.findByName(id);
        return prog;
    }

    @Override
    public Program getProgramByName(String name) {
        Program prog = new Program();
        prog = programRepository.findByName(name);
        return prog;
    }

    @Override
    public Program createProgram(Program prog) {
        Program newProg = new Program();

        if(programRepository.findByName(prog.getName().toString()) == null){
            newProg = programRepository.save(prog);
        }

        return newProg;
    }

    @Override
    public Program updateProgram(Program prog) {
        /*Need to modify*/
        Program updateProg= programRepository.findByName(prog.getName().toString());
        List<Program> lst = programRepository.findAll();

        if(programRepository.findByName(prog.getName().toString()) != null) {
            for(Program exercise:lst){
                if (updateProg.getName().equals(exercise.getName())) {
                    updateProg = programRepository.save(prog);
                    return updateProg;
                }
            }
        }

        return updateProg;
    }

    @Override
    public Integer deleteProgramById(String id) {
        int result = 0;
        String name= programRepository.findByName(id).getName();

        if(name!= null && !name.isEmpty()){
            programRepository.delete(id);
            result = 1;
        }
        return  result;
    }
}
