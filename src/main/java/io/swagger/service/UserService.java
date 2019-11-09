package io.swagger.service;

import io.swagger.model.LoginModel;
import io.swagger.model.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserByUserName(String userName);
    User getUserByLastName(String lastName);
    User getUserByFirstName(String firstName);
    LoginModel checkUserLogin(User user);

    User getUserById(String id);

    Integer deleteUserById(String id);

    List<User> getAllCustomer();

    Integer updateUser(User user);

    void updatePointForCoach(String coachId, Integer point);

    int getEasyPoint();
    int getMediumPoint();
    int getDificultPoint();
    int getMaxiPoint();

    Integer checkExistedUserName(String userName);
}
