package io.swagger.service;

import io.swagger.model.Program;
import io.swagger.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("programService")
public class ProgramServiceImp implements  ProgramService {
    private ProgramRepository programRepository;

    public ProgramServiceImp(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Program> getAll() {
        List<Program> lst = new ArrayList<>();
        lst = programRepository.findAll();
        return lst;
    }

    @Override
    public Program getProgramById(String id) {
        Program prog = new Program();
        prog = programRepository.findById(id);
        return prog;
    }

    @Override
    public Program getProgramByName(String name) {
        Program prog = new Program();
        prog = programRepository.findByName(name);
        return prog;
    }

    @Override
    public Integer createProgram(Program prog) {
        Program newProg = new Program();

        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<Program> lst = programRepository.findAll();
        for (Program item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId) + 1;

        if(programRepository.findByName(prog.getName().toString()) == null){
            prog.setId(String.valueOf(maxId));
            newProg = programRepository.save(prog);
            return maxId;
        }

        return 0;
    }

    @Override
    public Program updateProgram(Program prog) {
        Program newProg = new Program();

        newProg = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(prog.getId())), Program.class);
        if(newProg != null) {
            newProg.setName(prog.getName());
            newProg.setDescription(prog.getDescription());
            newProg.setNumberOfSession(prog.getNumberOfSession());
            //haven't check duplicate name
            programRepository.save(newProg);
        }

        return newProg;
    }

    @Override
    public Integer deleteProgramById(String id) {
        int result = 0;

        if(programRepository.findById(id) != null){
            programRepository.delete(programRepository.findById(id));
            result = 1;
        }

        return  result;
    }
}
