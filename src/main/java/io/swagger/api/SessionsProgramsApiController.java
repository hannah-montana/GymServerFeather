package io.swagger.api;

import io.swagger.model.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.SessionProgramService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T11:02:13.245Z[GMT]")
@Controller
public class SessionsProgramsApiController implements SessionsProgramsApi {

    private static final Logger log = LoggerFactory.getLogger(SessionsProgramsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    SessionProgramService sessionProgramService;

    @org.springframework.beans.factory.annotation.Autowired
    public SessionsProgramsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Session>> getSessionsOfProgram(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("progId") String progId) {
        List<Session> lstSess = new ArrayList<>();

        lstSess = sessionProgramService.getListSessionsByProgramId(progId);

        return new ResponseEntity<List<Session>>(lstSess, HttpStatus.OK);

    }

    public ResponseEntity<List<Session>> getCheckListSessionOfProgram(@ApiParam(value = "", required = true) @PathVariable("progId") String progId){
        List<Session> lstEx = new ArrayList<>();

        lstEx = sessionProgramService.getCheckListSession(progId);

        return new ResponseEntity<List<Session>>(lstEx, HttpStatus.OK);
    }

}
