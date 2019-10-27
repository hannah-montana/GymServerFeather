package io.swagger.api;

import io.swagger.model.Exercise;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.ExerciseSessionService;
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
public class ExercisesSessionsApiController implements ExercisesSessionsApi {

    private static final Logger log = LoggerFactory.getLogger(ExercisesSessionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @org.springframework.beans.factory.annotation.Autowired
    public ExercisesSessionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Exercise>> getExercisesOfSession(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("sessId") String sessId) {
        List<Exercise> lstEx = new ArrayList<>();

        lstEx = exerciseSessionService.getListExercisesBySessionId(sessId);
        return new ResponseEntity<List<Exercise>>(lstEx, HttpStatus.OK);
    }

}
