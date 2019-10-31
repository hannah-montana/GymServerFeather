package io.swagger.service;

import io.swagger.model.LoginModel;
import io.swagger.model.User;
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

    public UserServiceImp(UserRepository userRepo) {
        this.userRepo = userRepo;
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
        List<User> lst = userRepo.findAll();
        user = userRepo.findById(id);
        return user;
    }
}
