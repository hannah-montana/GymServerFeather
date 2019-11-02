package io.swagger.service;

import io.swagger.model.LoginModel;
import io.swagger.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserByUserName(String userName);
    User getUserByLastName(String lastName);
    User getUserByFirstName(String firstName);
    LoginModel checkUserLogin(User user);

    User getUserById(String id);

    Integer deleteUserById(String id);
}
