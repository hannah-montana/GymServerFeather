package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.ProgramUserRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    private UserRepository userRepo;
    private ProgramUserRepository programUserRepository;

    @Autowired
    ProgramUserService programUserService;

    @Autowired
    SessionProgramService sessionProgramService;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Autowired
    HistoryService historyService;

    public int getEasyPoint() {
        return 10;
    }

    public int getMediumPoint(){
        return 20;
    }
    public int getDificultPoint(){
        return 30;
    }
    public int getMaxiPoint(){
        return 50;
    }

    public UserServiceImp(UserRepository userRepo,
                          ProgramUserRepository programUserRepository) {
        this.userRepo = userRepo;
        this.programUserRepository = programUserRepository;
    }

    public List<User> getAll(){
        List<User> lst = new ArrayList<>();
        lst =  userRepo.findAll();
        return lst;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        user = userRepo.findOne(userName);
        return user;
    }
    @Override
    public User getUserByLastName(String lastName){
        User user = new User();
        user = userRepo.findOne(lastName);
        return user;
    };

    @Override
    public User getUserByFirstName(String firstName){
        User user = new User();
        user = userRepo.findOne(firstName);
        return user;
    };

    public LoginModel checkUserLogin(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user.getUserName()).andOperator(Criteria.where("password").is(user.getPassword())));
        //List<String> ress = mongoTemplate.find(query, String.class);
        List<User> users = mongoTemplate.find(query, User.class);

        LoginModel res = new LoginModel();
        if(users != null) {
            User u = users.get(0);
            res.setId(u.getId());
            res.setFirstName(u.getFirstName());
            res.setLastName(u.getLastName());
            res.setBirthDate(u.getBirthDate());
            res.setPoint(u.getPoint());
            res.setLevel(u.getLevel());
            res.setRole(u.getRole());
            res.setStatus("login");
        }
        return res;
    }

    public User getUserById(String id){
        User user = new User();
        Query query = new Query();
        query.fields().include("id");
        query.fields().include("firstName");
        query.fields().include("lastName");
        query.fields().include("userName");
        query.fields().include("birthDate");
        query.fields().include("role");
        query.fields().include("point");
        query.fields().include("photo");
        query.fields().include("email");
        query.fields().include("note");
        query.fields().include("level");
        query.fields().include("coachId");

        query.addCriteria(Criteria.where("id").is(id));
        user = mongoTemplate.findOne(query, User.class);

        return user;
    }

    public Integer deleteUserById(String id) {
        int result = 0;

        //delete in SessionProgram
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));

        List<ProgramUser> lstProgUser = mongoTemplate.find(query, ProgramUser.class);
        if(lstProgUser != null){
            for(ProgramUser item : lstProgUser){
                programUserRepository.delete(programUserRepository.findById(item.getId()));
            }
        }

        if(programUserRepository.findById(id) != null){
            programUserRepository.delete(programUserRepository.findById(id));
            result = 1;
        }

        return  result;
    }

    public List<User> getAllCustomer(){
        Query query = new Query();
        query.fields().include("id");
        query.fields().include("firstName");
        query.fields().include("lastName");
        query.fields().include("userName");
        query.fields().include("birthDate");
        query.fields().include("role");
        query.fields().include("point");
        query.fields().include("photo");
        query.fields().include("email");
        query.fields().include("note");
        query.fields().include("level");
        query.fields().include("coachId");

        query.addCriteria(Criteria.where("role").is("2"));

        List<User> lst = mongoTemplate.find(query, User.class);

        return lst;
    }

    public Integer updateUser(User user){
        User u = new User();

        u = userRepo.findById(user.getId());
        if(u != null){
            u.setEmail(user.getEmail());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            if(user.getPassword() != "" && user.getPassword() != null)
                u.setPassword(user.getPassword());
            u.setNote(user.getNote());
            u.setBirthDate(user.getBirthDate());
            if(user.getPhoto() != "" && user.getPhoto() != null)
                u.setPhoto(user.getPhoto());

            userRepo.save(u);

            return 1;
        }

        return 0;
    }

    public void updatePointForCoach(String coachId, Integer point){
        User coach = userRepo.findById(coachId);
        if(coach != null) {
            int newPoint = coach.getPoint() + point;
            coach.setPoint(newPoint);
            userRepo.save(coach);
        }
    }

    public Integer checkExistedUserName(String userName){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(user != null)
            return 1;
        return 0;
    }

    public Integer updatePhoto(User user){
        User u = userRepo.findById(user.getId());
        if(u != null)
        {
            u.setPhoto(user.getPhoto());
            userRepo.save(u);
            return 1;
        }
        return 0;
    }

    @Override
    public CustomerDashboard getCustomerDashboard(String userId) {

        List<Program> lstProg = new ArrayList<>();

        int calorie = 0;
        int point = 0;
        int duration = 0;

        int totalCalories = 0;
        int totalDuration = 0;
        int totalPoint = 0;
        int totalNumberEx = 0;

        lstProg = this.programUserService.getListProgramByUserId(userId);

        for(Program prog: lstProg){
            List<Session> lstSess = new ArrayList<>();
            lstSess = this.sessionProgramService.getListSessionsByProgramId(prog.getId());

            for(Session sess: lstSess){
                List<Exercise> lstEx = new ArrayList<>();
                lstEx = this.exerciseSessionService.getListExercisesBySessionId(sess.getId());

                for(Exercise ex: lstEx){
                    point = ex.getPoint();
                    calorie = ex.getCalorie();
                    duration = ex.getDuration();

                    totalCalories+=calorie;
                    totalPoint+=point;
                    totalDuration+=duration;
                    totalNumberEx++;
                }
            }
        }

        CustomerDashboard customerDashboard = new CustomerDashboard();
        customerDashboard.setPoint(totalPoint);
        customerDashboard.setCalorie(totalCalories);
        customerDashboard.setDuration(totalDuration);
        customerDashboard.setNumEx(totalNumberEx);

        return customerDashboard;
    }

    @Override
    public CurrentCustomer getCurrentCustomer(String userId) {

        List<History> lsHis = new ArrayList<>();

        int currentCalorie = 0;
        int currentDuration = 0;
        int currentPoint = 0;
        int currentNumberEx = 0;
        int currentNumberExEasy = 0;
        int currentNumberExMedium = 0;
        int currentNumberExDifficult = 0;



        lsHis = this.historyService.getListHistoryByUserId(userId);

        for(History history: lsHis){
            currentCalorie += history.getCalorie();
            currentDuration += history.getPraticalDuration();
            currentPoint += history.getPoint();
            currentNumberEx ++;

            String level = history.getLevel();

            switch(level) {
                case "Easy":
                    currentNumberExEasy++;
                    break;
                case "Medium":
                    currentNumberExMedium++;
                    break;
                case "Difficult":
                    currentNumberExDifficult++;
                    break;
            }

        }

        CurrentCustomer currentCustomer = new CurrentCustomer();
        currentCustomer.setCalorie(currentCalorie);
        currentCustomer.setPracDuration(currentDuration);
        currentCustomer.setPoint(currentPoint);
        currentCustomer.setNumEx(currentNumberEx);
        currentCustomer.setNumExEasy(currentNumberExEasy);
        currentCustomer.setNumExMedium(currentNumberExMedium);
        currentCustomer.setNumExDifficult(currentNumberExDifficult);

        return currentCustomer;
    }

    @Override
    public Integer getHealthPercent(String userId) {
        CurrentCustomer currentCustomer = new CurrentCustomer();
        CustomerDashboard customerDashboard = new CustomerDashboard();

        int totalPoints = 0;
        int currentPoint = 0;
        //HealthStatus healthStatus = new HealthStatus();

        currentPoint = this.getCurrentCustomer(userId).getPoint();
        //currentPoint = currentCustomer.getPoint();
        totalPoints = this.getCustomerDashboard(userId).getPoint();

        int healthPercent = 0;
        healthPercent =  (int)(((float)currentPoint/(float)totalPoints)*100);

        return healthPercent;
    }

    @Override
    public Map<String,Integer> getMapPointOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        Map<String, Integer> mapSessPoint = new HashMap<>();

        lstSess = this.historyService.getListSessionsByUserId(userId);

        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<History> lstHistory = mongoTemplate.find(query, History.class);

            int totalPointOfSession = 0;

            for(History history: lstHistory){
                int point= history.getPoint();
                totalPointOfSession+=point;
            }

            mapSessPoint.put(sess.getId(),totalPointOfSession);
        }

        return mapSessPoint;
    }

    @Override
    public Map<String, Integer> getMapCalorieOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        Map<String, Integer> mapSessCalorie = new HashMap<>();

        lstSess = this.historyService.getListSessionsByUserId(userId);

        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<History> lstHistory = mongoTemplate.find(query, History.class);

            int totalCalorieOfSession = 0;

            for(History history: lstHistory){
                int calorie= history.getCalorie();
                totalCalorieOfSession+=calorie;
            }

            mapSessCalorie.put(sess.getId(),totalCalorieOfSession);
        }

        return mapSessCalorie;
    }
}
