package io.swagger.api;

import io.swagger.model.Session;

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
public class SessionsApiControllerIntegrationTest {

    @Autowired
    private SessionsApi api;

    @Test
    public void createNewSessionTest() throws Exception {
        Session body = new Session();
        //ResponseEntity<List<Session>> responseEntity = api.createNewSession(body);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteSessionByIdTest() throws Exception {
        String sesId = "sesId_example";
        //ResponseEntity<Void> responseEntity = api.deleteSessionById(sesId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getSessionsByIdTest() throws Exception {
        String sesId = "sesId_example";
        //ResponseEntity<Session> responseEntity = api.getSessionsById(sesId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getSessionsByNameTest() throws Exception {
        String sesName = "sesName_example";
        String keyWords = "keyWords_example";
        //ResponseEntity<List<Session>> responseEntity = api.getSessionsByName(sesName, keyWords);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateSessionByIdTest() throws Exception {
        Session body = new Session();
        String sesId = "sesId_example";
        //ResponseEntity<Void> responseEntity = api.updateSessionById(body, sesId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
