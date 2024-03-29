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
import java.util.*;

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
            res.setCoachId(u.getCoachId());
            res.setBadge(u.getBadge());
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
        query.fields().include("badge");

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
        query.fields().include("badge");
        query.fields().include("calorie");
        query.fields().include("duration");
        query.fields().include("gender");

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

        //lsHis = this.historyService.getListHistoryByUserId(userId);
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                .andOperator(Criteria.where("processing").ne("0")
                        .andOperator(Criteria.where("praticalDuration").gt(0))));
        lsHis = mongoTemplate.find(query, History.class);

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

    @Override
    public List<DataPoint> getListPointOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        List<Integer> lstPoint = new ArrayList<>();
        List<DataPoint> lstDataPoint = new ArrayList<>();

        lstSess = this.historyService.getListSessionsByUserId(userId);

        int i=1;
        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<History> lstHistory = mongoTemplate.find(query, History.class);

            int totalPointOfSession = 0;

            for(History history: lstHistory){
                int point= history.getPoint();
                totalPointOfSession+=point;
            }
            lstPoint.add(totalPointOfSession);

            DataPoint dataPoint = new DataPoint();
            dataPoint.setLabel("S" + String.valueOf(i));
            dataPoint.setY(totalPointOfSession);
            i++;

            lstDataPoint.add(dataPoint);
        }

        return lstDataPoint;
    }

    @Override
    public List<DataPoint> getListCalorieOfSessionByUserId(String userId) {
        List<Session> lstSess = new ArrayList<>();
        List<Integer> lstCalorie = new ArrayList<>();
        List<DataPoint> lstDataPoint = new ArrayList<>();

        lstSess = this.historyService.getListSessionsByUserId(userId);

        int i=1;
        int totalCalorieOfSession = 0;
        for(Session sess: lstSess){
            Query query = new Query();
            query.addCriteria(Criteria.where("sessId").is(sess.getId()));
            List<History> lstHistory = mongoTemplate.find(query, History.class);

            for(History history: lstHistory){
                int calorie= history.getCalorie();
                totalCalorieOfSession+=calorie;
            }

            lstCalorie.add(totalCalorieOfSession);

            DataPoint dataPoint = new DataPoint();
            dataPoint.setLabel("S" + String.valueOf(i));
            dataPoint.setY(totalCalorieOfSession);
            dataPoint.setX(i);
            i++;

            lstDataPoint.add(dataPoint);
        }

        return lstDataPoint;
    }

    @Override
    public List<Ranking> getListAllRanking() {
        List<User> lstAllUsers = this.getAllCustomer();
        List<Ranking> lstAllRanking = new ArrayList<>();


        Collections.sort(lstAllUsers, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u2.getPoint().compareTo(u1.getPoint());
            }
        });

        for(int i=0; i <lstAllUsers.size();i++){
            User user = lstAllUsers.get(i);
            Ranking ranking = new Ranking();
            ranking.setId(user.getId());
            ranking.setFirstName(user.getFirstName());
            ranking.setLastName(user.getLastName());
            ranking.setPoint(user.getPoint());
            ranking.setBadge(user.getBadge());
            ranking.setDuration(user.getDuration());
            ranking.setCalorie(user.getCalorie());

            lstAllRanking.add(ranking);
        }

        lstAllRanking.get(0).setRank(1);
        for(int i=1; i <lstAllUsers.size();i++) {
            if(lstAllRanking.get(i).getPoint()==lstAllRanking.get(i-1).getPoint()){
                lstAllRanking.get(i).setRank(lstAllRanking.get(i-1).getRank());
            }
            else{
                lstAllRanking.get(i).setRank(lstAllRanking.get(i-1).getRank()+1);
            }

        }
        return lstAllRanking;
    }

    @Override
    public List<Ranking> getListTopRanking(String userId) {

        List<Ranking> lstAllRanking = this.getListAllRanking();
        List<Ranking> lstTopRanking = new ArrayList<>();

        User user = this.getUserById(userId);
        if(lstAllRanking.size()>10){
            for(int i=0; i<10; i++){
                lstTopRanking.add(lstAllRanking.get(i));
            }
        }else{
            lstTopRanking = lstAllRanking;
        }

        int check = 0;
        for(Ranking r: lstTopRanking){
            if(r.getId().equals(userId)){
                check = 1;
                break;
            }
        }

        if(check == 0){
            lstTopRanking.remove(9);

            for(Ranking currentRanking:lstAllRanking ){
                if(user.getId().equals(currentRanking.getId())){
                    lstTopRanking.add(currentRanking);
                }
            }
        }

        for(Ranking item: lstTopRanking){
            if(item.getRank().equals(1)){
                item.setMedal("assets/images/winner.png");
            }
            else if(item.getRank().equals(2)){
                item.setMedal("assets/images/gold.png");
            }
            else if(item.getRank().equals(3)){
                item.setMedal("assets/images/silver.png");
            }
            else if(item.getRank().equals(4)){
                item.setMedal("assets/images/bronze.png");
            }
        }

        return lstTopRanking;
    }

    public Integer createNewAccount(User user){
        try {
            //get maxId
            List<Integer> lstId = new ArrayList<>();
            List<User> lst = userRepo.findAll();
            for (User item : lst) {
                lstId.add(Integer.parseInt(item.getId()));
            }
            int maxId = Collections.max(lstId) + 1;

            user.setId(String.valueOf(maxId));
            user.setPhoto("assets/images/customers/no-image.png");
            user.setRole("2");
            user.setCoachId("1");
            user.setPoint(0);
            user.setCalorie(0);
            user.setDuration(0);
            user.setGender("u");
            user.setNote(user.getPurpose());
            user.setLevel("Beginer");
            user.setBadge("assets/images/level_1.png");

            userRepo.save(user);

            //assign default program
            ProgramUser programUser = new ProgramUser();
            programUser.setId("0");
            programUser.setUserId(String.valueOf(maxId));
            programUser.setCoachId("1");
            programUser.setPoint(0);
            programUser.setIsFinished("Not yet");
            if(user.getPurpose().equals("Increase Muscle"))
                programUser.setProgId("1");
            else if(user.getPurpose().equals("Loose Weight"))
                programUser.setProgId("3");
            else
                programUser.setProgId("2");

            programUserService.assignProgramToUser(programUser);

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

}
