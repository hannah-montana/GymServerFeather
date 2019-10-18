package io.swagger.service;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {
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


}
