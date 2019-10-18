package io.swagger.api;

import io.swagger.model.Program;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.ProgramService;
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
public class ProgramsApiController implements ProgramsApi {

    private static final Logger log = LoggerFactory.getLogger(ProgramsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ProgramService programService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProgramsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Program> createNewProgram(@ApiParam(value = "Program object that needs to be added to the gym" ,required=true )  @Valid @RequestBody Program prog) {
        Program program = new Program();

        try{
            program = programService.createProgram(prog);
        } catch(Exception e){
            program = new Program();
        }

        return new ResponseEntity<Program>(program,HttpStatus.OK);
    }

    public ResponseEntity<Integer> deleteProgramById(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("proId") String proId) {
        int result;

        result = programService.deleteProgramById(proId);

        return new ResponseEntity<Integer>(result,HttpStatus.OK);
    }

    public ResponseEntity<Program> getProgramsById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("proId") String proId) {
        Program program = new Program();

        try{
            program = programService.getProgramById(proId);
        } catch(Exception e){
            program = new Program();
        }

        return new ResponseEntity<Program>(program,HttpStatus.OK);
    }

    public ResponseEntity<List<Program>> getProgramsByName(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "proName", required = false) String proName,@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "keyWords", required = false) String keyWords) {
        Program program = new Program();
        List<Program> lst = new ArrayList<>();

        if(proName == null){
            lst = programService.getAll();
            return new ResponseEntity<List<Program>>(lst,HttpStatus.OK);
        } else {
            program = programService.getProgramByName(proName);
            lst.add(program);
            return new ResponseEntity<List<Program>>(lst,HttpStatus.OK);
        }
    }


    public ResponseEntity<Program> updateProgram(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("program") Program prog){
            Program program = new Program();

            try{
                program = programService.updateProgram(prog);
            } catch(Exception e){
                program = new Program();
            }

            return new ResponseEntity<Program>(program,HttpStatus.OK);
    }

}
