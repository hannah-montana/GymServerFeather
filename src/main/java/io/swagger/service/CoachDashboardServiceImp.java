package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("coachDashboard")
public class CoachDashboardServiceImp implements CoachDashboardService {
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProgramService programService;

    @Autowired
    SessionService sessionService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Autowired
    UserService userService;

    public CoachDashboardServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getListUserByCoachId(String coachId) {
        List<User> lstUser = new ArrayList<>();

        /*Query query = new Query();
        query.addCriteria(Criteria.where("coachId").is(coachId));
        List<ProgramUser> lsProgUser = mongoTemplate.find(query, ProgramUser.class);

        for(ProgramUser progUser: lsProgUser){
            User user = userRepository.findById(progUser.getUserId());
            if(!lstUser.contains(user)){
                lstUser.add(user);
            }
        }*/
        lstUser = userService.getAllCustomer();

        return lstUser;
    }


    public List<User> getListMaleByCoachId(String coachId) {
        List<User> lstMale = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getGender().equals("m")){
                lstMale.add(user);
            }
        }

        return lstMale;
    }

    public List<User> getListFemaleByCoachId(String coachId) {
        List<User> lstFemale = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getGender().equals("f")){
                lstFemale.add(user);
            }
        }

        return lstFemale;
    }

    public List<User> getListUserLevel1(String coachId) {
        List<User> lstLevel1 = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getLevel().equals("1")){
                lstLevel1.add(user);
            }
        }
        return lstLevel1;
    }

    public List<User> getListUserLevel2(String coachId) {
        List<User> lstLevel2 = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getLevel().equals("2")){
                lstLevel2.add(user);
            }
        }
        return lstLevel2;
    }

    public List<User> getListUserLevel3(String coachId) {
        List<User> lstLevel3 = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getLevel().equals("3")){
                lstLevel3.add(user);
            }
        }
        return lstLevel3;
    }

    public List<User> getListUserLevel4(String coachId) {
        List<User> lstLevel4 = new ArrayList<>();
        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);

        for(User user: lstUserByCoachId){
            if(user.getLevel().equals("4")){
                lstLevel4.add(user);
            }
        }
        return lstLevel4;
    }

    public List<Exercise> getListEasyExercise() {
        List<Exercise> lstEasyEx = new ArrayList<>();
        List<Exercise>  lstEx = this.exerciseService.getAll();

        for(Exercise ex: lstEx){
            if(ex.getLevel().equals("Easy")){
                lstEasyEx.add(ex);
            }
        }
        return lstEasyEx;
    }

    public List<Exercise> getListMediumExercise() {
        List<Exercise> lstMedEx = new ArrayList<>();
        List<Exercise>  lstEx = this.exerciseService.getAll();

        for(Exercise ex: lstEx){
            if(ex.getLevel().equals("Medium")){
                lstMedEx.add(ex);
            }
        }
        return lstMedEx;
    }

    public List<Session> getListEasySession() {
        List<Session> lstEasySess = new ArrayList<>();
        List<Session>  lstSess = this.sessionService.getAll();

        for(Session sess: lstSess){
            if(sess.getLevel().equals("Easy")){
                lstEasySess.add(sess);
            }
        }
        return lstEasySess;
    }

    public List<Session> getListMediumSession() {
        List<Session> lstMedSess = new ArrayList<>();
        List<Session>  lstSess = this.sessionService.getAll();

        for(Session sess: lstSess){
            if(sess.getLevel().equals("Medium")){
                lstMedSess.add(sess);
            }
        }
        return lstMedSess;
    }

    @Override
    public CoachDashboard getCoachDashboardStatistics(String coachId) {
        CoachDashboard coachDashboard = new CoachDashboard();
        List<User> lstUser = this.getListUserByCoachId(coachId);
        List<User> lstMale = this.getListMaleByCoachId(coachId);
        List<User> lstFemale = this.getListFemaleByCoachId(coachId);

        int noCustomer = lstUser.size();
        int noMale = lstMale.size();
        int noFemale = lstFemale.size();
        int noUndefined = noCustomer - noMale - noFemale;
        int noProgram = this.programService.getAll().size();
        int noSession = this.sessionService.getAll().size();
        int noExercise = this.exerciseService.getAll().size();
        int noEasyEx = this.getListEasyExercise().size();
        int noMedEx = this.getListMediumExercise().size();
        int noDifEx = noExercise - noEasyEx - noMedEx;
        int noEasySess = this.getListEasySession().size();
        int noMedSess = this.getListMediumSession().size();
        int noDifSess = noSession - noEasySess - noMedSess;


        coachDashboard.setNoCustomer(noCustomer);
        coachDashboard.setNoMale(noMale);
        coachDashboard.setNoFemale(noFemale);
        coachDashboard.setNoUndefined(noUndefined);
        coachDashboard.setNoProgram(noProgram);
        coachDashboard.setNoSession(noSession);
        coachDashboard.setNoExercise(noExercise);
        coachDashboard.setNoEasyEx(noEasyEx);
        coachDashboard.setNoMedEx(noMedEx);
        coachDashboard.setNoDifEx(noDifEx);
        coachDashboard.setNoEasySess(noEasySess);
        coachDashboard.setNoMedSess(noMedSess);
        coachDashboard.setNoDifSess(noDifSess);

        return coachDashboard;
    }

    @Override
    public List<AreaChart> getListExerciseOfFocusSession(String sessId) {
        List<AreaChart> lstAreaChart = new ArrayList<>();
        List<Exercise> lstEx = this.exerciseSessionService.getListExercisesBySessionId(sessId);

        for(int i=0;i<lstEx.size();i++){
            AreaChart areaChart = new AreaChart();
            areaChart.setId(i+1);
            areaChart.setDuration(lstEx.get(i).getCalorie());
            areaChart.setPoint(lstEx.get(i).getPoint());
            areaChart.setDuration(lstEx.get(i).getDuration());
            areaChart.setCalorie(lstEx.get(i).getCalorie());

            lstAreaChart.add(areaChart);
        }
        return lstAreaChart;
    }

    @Override
    public BarChart getBarChart(String coachId) {
        BarChart barChart = new BarChart();

        List<User> lstUserByCoachId = this.getListUserByCoachId(coachId);
        List<User> lstLvel1 = this.getListUserLevel1(coachId);
        List<User> lstLvel2 = this.getListUserLevel2(coachId);
        List<User> lstLvel3 = this.getListUserLevel3(coachId);
        List<User> lstLvel4 = this.getListUserLevel4(coachId);

        float noCustomer = lstUserByCoachId.size();
        float noLevel1 = lstLvel1.size();
        float noLevel2 = lstLvel2.size();
        float noLevel3 = lstLvel3.size();
        float noLevel4 = lstLvel4.size();
        float noLevel5 = noCustomer - noLevel1 - noLevel2 - noLevel3 - noLevel4;

        barChart.setNoLevel1(100*noLevel1/noCustomer);
        barChart.setNoLevel2(100*noLevel2/noCustomer);
        barChart.setNoLevel3(100*noLevel3/noCustomer);
        barChart.setNoLevel4(100*noLevel4/noCustomer);
        barChart.setNoLevel5(100*noLevel5/noCustomer);

        return barChart;
    }
}
