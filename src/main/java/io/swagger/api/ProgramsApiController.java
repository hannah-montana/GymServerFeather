package io.swagger.api;

import io.swagger.model.Program;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import io.swagger.service.ProgramService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Controller
public class ProgramsApiController implements ProgramsApi {

    private static final Logger log = LoggerFactory.getLogger(ProgramsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ProgramService programService;

    @Autowired
    SessionProgramService sessionProgramService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProgramsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Integer> createNewProgram(@ApiParam(value = "Program object that needs to be added to the gym" ,required=true )  @Valid @RequestBody Program prog,
                                                    @NotNull @ApiParam(value = "", required = false)
                                                    @Valid @RequestParam(value = "listSes", required = false) String listSes,
                                                    @NotNull @ApiParam(value = "", required = false)
                                                    @Valid @RequestParam(value = "coachId", required = false) String coachId) {
        Program program = new Program();

        try{
            int progId = programService.createProgram(prog);
            if(progId > 0){
                if(listSes != null) {
                    //update list sessions to program
                    sessionProgramService.saveListSesionsByProgramId(prog.getId(), listSes, coachId);
                }
            }
            return new ResponseEntity<Integer>(1, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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


    public ResponseEntity<Program> updateProgram(@ApiParam(value = "name that need to be updated",required=true) @Valid @RequestBody Program prog){
        Program program = new Program();
<<<<<<< HEAD

        try{
            program = programService.updateProgram(prog);
        } catch(Exception e){
            program = new Program();
        }

        return new ResponseEntity<Program>(program,HttpStatus.OK);

    }

    public ResponseEntity<Integer> updateProgramSessions(@ApiParam(value = "",required=true) @Valid @RequestBody Program prog,
                                                         @NotNull @ApiParam(value = "", required = false)
                                                         @Valid @RequestParam(value = "listSes", required = false) String listSes,
                                                         @NotNull @ApiParam(value = "", required = false)
                                                         @Valid @RequestParam(value = "coachId", required = false) String coachId){
        if(listSes != null){
            //update program
            Program program = new Program();
            program = programService.updateProgram(prog);
            //update list sessions to program
            sessionProgramService.saveListSesionsByProgramId(prog.getId(), listSes, coachId);
            return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }

        return new ResponseEntity<Integer>(0, HttpStatus.OK);
=======

        try{
            program = programService.createProgram(prog);
        } catch(Exception e){
            program = new Program();
        }

        return new ResponseEntity<Program>(program,HttpStatus.OK);

>>>>>>> 1bf1e6bc61d8e084ba50a4333ca00a75fb7fe0fd
    }

}
