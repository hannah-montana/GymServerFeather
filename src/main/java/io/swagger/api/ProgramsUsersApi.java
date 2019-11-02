
package io.swagger.api;

import io.swagger.model.Program;
import io.swagger.model.ProgramUser;
import io.swagger.model.Session;
import io.swagger.annotations.*;
import io.swagger.model.SessionProgram;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-19T11:02:13.245Z[GMT]")
@Api(value = "programsUsers", description = "the programsUsers API")
public interface ProgramsUsersApi {

    @ApiOperation(value = "get programs in a User", nickname = "getProgramsOfUser", notes = "", response = ProgramUser.class, tags={ "ProgramUser", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProgramUser.class), // reponse = Session.class
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User  was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/programsUsers/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Program>> getProgramsOfUser(@ApiParam(value = "Parameter description in CommonMark or HTML.", required = true) @PathVariable("userId") String userId);

    @ApiOperation(value = "Assign 1 program to 1 user", nickname = "assignProgramToUser", notes = "", response = ProgramUser.class, responseContainer = "ProgramUser", tags={ "ProgramUser", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProgramUser.class, responseContainer = "List"),
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/programsUsers",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Integer> assignProgramToUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProgramUser proUser);

    @ApiOperation(value = "Check Assign", nickname = "checkAssign", notes = "", response = ProgramUser.class, tags={ "ProgramUser", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProgramUser.class), // reponse = Session.class
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User  was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/checkAssign/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Integer> checkAssign(@ApiParam(value = "", required = true) @PathVariable("userId") String userId);

}
