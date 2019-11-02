package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Program;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.ProgramUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T11:02:13.245Z[GMT]")
@Controller
public class ProgramsUsersApiController implements ProgramsUsersApi {

    private static final Logger log = LoggerFactory.getLogger(SessionsProgramsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ProgramUserService programUserService;

    public ProgramsUsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<List<Program>> getProgramsOfUser(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userId") String userId) {
        List<Program> lstProg = new ArrayList<>();

        lstProg = programUserService.getListSessionsByProgramId(userId);

        return new ResponseEntity<List<Program>>(lstProg, HttpStatus.OK);
    }
}
