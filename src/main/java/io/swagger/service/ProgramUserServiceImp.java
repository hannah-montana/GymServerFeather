package io.swagger.service;

import io.swagger.model.Program;
import io.swagger.model.ProgramUser;
import io.swagger.model.SessionProgram;
import io.swagger.repository.ProgramRepository;
import io.swagger.repository.ProgramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("programUserService")
public class ProgramUserServiceImp implements ProgramUserService {
    ProgramRepository programRepository;
    ProgramUserRepository programUserRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public ProgramUserServiceImp(ProgramRepository programRepository, ProgramUserRepository programUserRepository) {
        this.programRepository = programRepository;
        this.programUserRepository = programUserRepository;
    }

    public List<Program> getListProgramByProgramId(String userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<ProgramUser> lstProgUser = mongoTemplate.find(query, ProgramUser.class);

        List<Program> lstProg = new ArrayList<>();
        for(ProgramUser progUser: lstProgUser){
            Program prog = programRepository.findById(progUser.getProgId());
            prog.setIsFinished(progUser.getIsFinished());
            lstProg.add(prog);
        }

        return lstProg;
    }

    public Integer checkUserAvailableForAssign(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("isFinished").is("Not yet")));
        List<ProgramUser> lstProgUser = mongoTemplate.find(query, ProgramUser.class);

        if(lstProgUser.size() > 0){
            //This user cannot be assign any more
            return 0;
        }
        else{
            return 1;
        }
    }

    public int getMaxId(){
        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<ProgramUser> lst = programUserRepository.findAll();

        for (ProgramUser item: lst)
        {
            lstId.add(Integer.parseInt(item.getId()));
        }
        int maxId = Collections.max(lstId);
        return maxId;
    }


    public Integer saveListProgramsByUserId(String userId, String[] listProg, String coachId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<ProgramUser> lstProgUser = mongoTemplate.find(query, ProgramUser.class);

        try{

            //Delete all row if matching
            for (ProgramUser item : lstProgUser) {
                programUserRepository.delete(programUserRepository.findById(item.getId()));
            }

            //add again
            int maxid = getMaxId();

            for (String it : listProg) {
                maxid = maxid + 1;
                ProgramUser progUser = new ProgramUser();
                progUser.setUserId(userId);
                //progUser.sessId(it);
                progUser.setCoachId(coachId);
                progUser.setProgId(it);
                progUser.setId(String.valueOf(maxid));
                programUserRepository.save(progUser);
            }
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public Integer assignProgramToUser(ProgramUser proUser){
        try {
            //get maxId
            int maxId = getMaxId() + 1;

            proUser.setId(String.valueOf(maxId));
            programUserRepository.save(proUser);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
