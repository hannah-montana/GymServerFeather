package io.swagger.api;

import io.swagger.model.User;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersApiControllerIntegrationTest {

    @Autowired
    private UsersApi api;

    @Test
    public void createNewUserTest() throws Exception {
        User body = new User();
        //ResponseEntity<List<User>> responseEntity = api.createNewUser(body);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteUserByUserNameTest() throws Exception {
        String userName = "userName_example";
        //ResponseEntity<Void> responseEntity = api.deleteUserByUserName(userName);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByUserNameTest() throws Exception {
        String userName = "userName_example";
        //ResponseEntity<User> responseEntity = api.getUserByUserName(userName);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getUsersByNameTest() throws Exception {
        String lastName = "lastName_example";
        String firstName = "firstName_example";
        //ResponseEntity<List<User>> responseEntity = api.getUsersByName(lastName, firstName);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateUserByUserNameTest() throws Exception {
        User body = new User();
        String userName = "userName_example";
        //ResponseEntity<Void> responseEntity = api.updateUserByUserName(body, userName);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
