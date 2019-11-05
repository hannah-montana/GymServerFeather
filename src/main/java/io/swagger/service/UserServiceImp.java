package io.swagger.service;

import io.swagger.model.LoginModel;
import io.swagger.model.ProgramUser;
import io.swagger.model.User;
import io.swagger.repository.ProgramUserRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    private UserRepository userRepo;
    private ProgramUserRepository programUserRepository;

    public UserServiceImp(UserRepository userRepo, ProgramUserRepository programUserRepository) {
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
}
