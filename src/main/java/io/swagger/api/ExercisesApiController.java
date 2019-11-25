package io.swagger.api;

import io.swagger.model.Exercise;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.User;
import io.swagger.service.ExerciseService;
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
public class ExercisesApiController implements ExercisesApi {

    private static final Logger log = LoggerFactory.getLogger(ExercisesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ExerciseService exerciseService;

    @org.springframework.beans.factory.annotation.Autowired
    public ExercisesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Integer> createNewExercise(@ApiParam(value = "Exercises object that needs to be added to the gym" ,required=true )  @Valid @RequestBody Exercise ex) {
        Exercise exercise = new Exercise();

        if(ex!= null){
            int exid = exerciseService.createExercise(ex);
            if (exid > 0)
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }

        return new ResponseEntity<Integer>(0, HttpStatus.OK);
    }

    public ResponseEntity<List<Exercise>> getExercisesByName(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "exName", required = false) String exName, @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "keyWords", required = false) String keyWords) {
        Exercise exercise = new Exercise();
        List<Exercise> lst = new ArrayList<>();

        if(exName == null){
            lst = exerciseService.getAll();
            return new ResponseEntity<List<Exercise>>(lst,HttpStatus.OK);
        } else {
            exercise = exerciseService.getExerciseByName(exName);
            lst.add(exercise);
            return new ResponseEntity<List<Exercise>>(lst,HttpStatus.OK);
        }

    }

    public ResponseEntity<Integer> deleteExerciseById(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("exId") String exId) {
        int result;

        result = exerciseService.deleteExerciseById(exId);

        return new ResponseEntity<Integer>(result,HttpStatus.OK);
    }


    public ResponseEntity<Exercise> getExerciseById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("exId") String exId) {

        Exercise exercise = new Exercise();

        if(exId!=null){
            exercise = exerciseService.getExerciseById(exId);
        } 

        return new ResponseEntity<Exercise>(exercise,HttpStatus.OK);
    }

    public ResponseEntity<Integer> updateExercise(@ApiParam(value = "name that need to be updated",required=true) @Valid @RequestBody Exercise ex) {
        Exercise exercise = new Exercise();
        //String name = ex.getName();

        if(ex!=null) {
            int res = exerciseService.updateExercise(ex);
            return new ResponseEntity<Integer>(res, HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(0, HttpStatus.OK);

    }

}
