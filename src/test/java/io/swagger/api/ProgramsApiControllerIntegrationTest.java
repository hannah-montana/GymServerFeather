package io.swagger.api;

import io.swagger.model.Program;

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
public class ProgramsApiControllerIntegrationTest {

    @Autowired
    private ProgramsApi api;

    @Test
    public void createNewProgramTest() throws Exception {
        Program body = new Program();
        //ResponseEntity<List<Program>> responseEntity = api.createNewProgram(body);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteProgramByIdTest() throws Exception {
        String proId = "proId_example";
        //ResponseEntity<Void> responseEntity = api.deleteProgramById(proId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getProgramsByIdTest() throws Exception {
        String proId = "proId_example";
        //ResponseEntity<Program> responseEntity = api.getProgramsById(proId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getProgramsByNameTest() throws Exception {
        String proName = "proName_example";
        String keyWords = "keyWords_example";
        //ResponseEntity<List<Program>> responseEntity = api.getProgramsByName(proName, keyWords);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateProgramByIdTest() throws Exception {
        Program body = new Program();
        String proId = "proId_example";
        //ResponseEntity<Void> responseEntity = api.updateProgramById(body, proId);
        //assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
