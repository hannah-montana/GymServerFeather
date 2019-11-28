package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.*;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("programUserService")
public class ProgramUserServiceImp implements ProgramUserService {
    ProgramRepository programRepository;
    ProgramUserRepository programUserRepository;
    HistoryRepository historyRepository;
    ParentHistoryRepository parentHistoryRepository;

    @Autowired
    SessionProgramService sessionProgramService;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Autowired
    MongoTemplate mongoTemplate;

    public ProgramUserServiceImp(ProgramRepository programRepository,
                                 ProgramUserRepository programUserRepository,
                                 HistoryRepository historyRepository,
                                 ParentHistoryRepository parentHistoryRepository) {
        this.programRepository = programRepository;
        this.programUserRepository = programUserRepository;
        this.historyRepository = historyRepository;
        this.parentHistoryRepository = parentHistoryRepository;
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
        int maxId = 0;
        if(lst.size() > 0) {
            for (ProgramUser item : lst) {
                lstId.add(Integer.parseInt(item.getId()));
            }
            maxId = Collections.max(lstId);
        }
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

    public int getHistoryMaxId(){
        //get maxId
        List<Integer> lstId = new ArrayList<>();
        List<History> lst = historyRepository.findAll();

        int maxId = 0;
        if(lst.size() > 0) {
            for (History item : lst) {
                lstId.add(Integer.parseInt(item.getId()));
            }
            maxId = Collections.max(lstId);
        }
        return maxId;
    }
    public Integer assignProgramToUser(ProgramUser proUser){
        try {
            //get maxId
            int maxId = getMaxId() + 1;

            proUser.setId(String.valueOf(maxId));
            programUserRepository.save(proUser);

            //save to History
            /*
            * 1. get all session in this program
            * 2. insert to Parent History
            * 3. for each session -> get all exercise
            * 4. insert each row to table History
            * */
            int order = 1;
            String processing = "1";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateAction = formatter.format(date);

            //1. get all session in this program
            List<Session> lstSession = sessionProgramService.getListSessionsByProgramId(String.valueOf(proUser.getProgId()));

            for (Session sess: lstSession){
                //2. insert to Parent History
                ParentHistory parentHistory = new ParentHistory();
                //get maxId
                List<Integer> lstId = new ArrayList<>();
                int maxIdParentHistory = 0;
                List<ParentHistory> lstParentHistory = parentHistoryRepository.findAll();
                if(lstParentHistory.size() > 0) {
                    for (ParentHistory item : lstParentHistory) {
                        lstId.add(Integer.parseInt(item.getId()));
                    }
                    maxIdParentHistory = Collections.max(lstId) + 1;
                }
                else {
                    maxIdParentHistory = 1;
                }
                parentHistory.setId(String.valueOf(maxIdParentHistory));
                parentHistory.setProUsId(String.valueOf(maxId));
                parentHistory.setProgId(proUser.getProgId());
                parentHistory.setSessId(sess.getId());
                parentHistory.setUserId(proUser.getUserId());
                parentHistoryRepository.save(parentHistory);

                //3. for each session -> get all exercise
                List<Exercise> lstExercise = exerciseSessionService.getListExercisesBySessionId(sess.getId());

                //4. insert each row to table History
                for(Exercise ex: lstExercise) {
                    History history = new History();
                    int hisId = getHistoryMaxId();
                    history.setId(String.valueOf(hisId + 1));
                    history.setParentId(String.valueOf(maxIdParentHistory));
                    history.setProUsId(String.valueOf(maxId));
                    history.setProgId(proUser.getProgId());
                    history.setUserId(proUser.getUserId());
                    history.setSessId(sess.getId());
                    history.setExId(ex.getId());
                    history.setFocusSession(sess.getFocusSession());
                    history.setDuration(ex.getDuration());
                    history.setPraticalDuration(0);
                    history.setPoint(ex.getPoint());
                    history.setCalorie(ex.getCalorie());
                    history.setOrder(order);
                    history.setProcessing(processing);
                    history.setDateAction(dateAction);
                    history.setLevel(ex.getLevel());
                    history.setName(ex.getName());
                    history.setDescription(ex.getDescription());
                    history.setInstruction(ex.getInstruction());
                    history.setType(ex.getType());
                    history.setPhoto(ex.getPhoto());
                    history.setCalorie(ex.getCalorie());
                    history.setTarget(ex.getTarget());
                    history.setCalorie(ex.getCalorie());
                    history.setSendValidateFS("0");
                    history.setValidatedByCoach("0");

                    historyRepository.save(history);
                }
                processing = "0";
                order ++;
            }
            //update already assign to program
            Program program = programRepository.findById(proUser.getProgId());
            if(program != null){
                program.setAlreadyAssign("1");
                programRepository.save(program);
            }

            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public List<Program> getListProgramByUserId(String userId) {

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
}
