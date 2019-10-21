package io.swagger.api;

import io.swagger.model.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Controller
public class SessionsApiController implements SessionsApi {

    private static final Logger log = LoggerFactory.getLogger(SessionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    SessionService sessionService;

    @org.springframework.beans.factory.annotation.Autowired
    public SessionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }



    public ResponseEntity<Session> createNewSession(@ApiParam(value = "Session object that needs to be added to the gym" ,required=true )  @Valid @RequestBody Session sess) {
        Session session = new Session();

        try{
            session = sessionService.createSession(sess);
        } catch(Exception e){
            session = new Session();
        }

        return new ResponseEntity<Session>(session,HttpStatus.OK);
    }

    public ResponseEntity<Integer> deleteSessionById(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("sesId") String sesId) {
        int result;

        result = sessionService.deleteSession(sesId);

        return new ResponseEntity<Integer>(result,HttpStatus.OK);
    }

    public ResponseEntity<Session> getSessionsById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("sesId") String sesId) {
        Session session = new Session();
        try{
            session = sessionService.getSessionById(sesId);
        }
        catch(Exception e)
        {
            session = new Session();
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }

    public ResponseEntity<List<Session>> getSessionsByName(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "sesName", required = false) String sesName, @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "keyWords", required = false) String keyWords) {
        Session session = new Session();
        List<Session> lst = new ArrayList<>();

        if(sesName == null){
            lst = sessionService.getAll();
            return new ResponseEntity<List<Session>>(lst,HttpStatus.OK);
        } else {
            session = sessionService.getSessionByName(sesName);
            lst.add(session);
            return new ResponseEntity<List<Session>>(lst,HttpStatus.OK);
        }
    }

    public ResponseEntity<Session> updateSession(@ApiParam(value = "Session that need to be updated",required=true) @Valid @RequestBody Session sess) {
        Session session = new Session();

        try{
            session = sessionService.updateSession(sess);
        } catch(Exception e){
            session = new Session();
        }

        return new ResponseEntity<Session>(session,HttpStatus.OK);
    }

}
