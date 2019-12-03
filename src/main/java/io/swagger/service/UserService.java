package io.swagger.service;

import io.swagger.model.*;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;

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

    Integer updatePhoto(User user);

    CustomerDashboard getCustomerDashboard(String userId);
    CurrentCustomer getCurrentCustomer(String userId);
    Integer getHealthPercent(String userId);

    Map<String,Integer> getMapPointOfSessionByUserId(String userId);
    Map<String,Integer> getMapCalorieOfSessionByUserId(String userId);

    List<DataPoint> getListPointOfSessionByUserId(String userId);
    List<DataPoint> getListCalorieOfSessionByUserId(String userId);

    List<Ranking> getListAllRanking();
    List<Ranking> getListTopRanking(String userId);

    Integer createNewAccount(User user);
}
