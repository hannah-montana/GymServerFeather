/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Exercise;
import io.swagger.annotations.*;
import io.swagger.model.ExerciseSession;
import io.swagger.model.History;
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
@Api(value = "exercisesSessions", description = "the exercisesSessions API")
public interface ExercisesSessionsApi {
                                                                                                // reponse = ExerciseSession.class
    @ApiOperation(value = "get exercises in a session", nickname = "getExercisesOfSession", notes = "", response = Exercise.class, tags={ "ExercisesSessions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Exercise.class), // reponse = ExerciseSession.class
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Session was not found"),
        @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/exercisesSessions/{sessId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Exercise>> getExercisesOfSession(@ApiParam(value = "Parameter description in CommonMark or HTML.", required = true) @PathVariable("sessId") String sessId);

    @ApiOperation(value = "get exercises by session id, user id in table history", nickname = "getExercisesOfSessionInHistory", notes = "", response = Exercise.class, tags={ "ExercisesSessions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Exercise.class), // reponse = ExerciseSession.class
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Session was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/exercisesSessions/history/{userId}/{sessId}/{parentId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<History>> getExercisesOfSessionInHistory(@ApiParam(value = "", required = true) @PathVariable("userId") String userId,
                                                                 @ApiParam(value = "", required = true) @PathVariable("sessId") String sessId,
                                                                 @ApiParam(value = "", required = true) @PathVariable("parentId") String parentId);

    @ApiOperation(value = "get check list exercise by session", nickname = "getCheckListExercisesOfSession", notes = "", response = Exercise.class, tags={ "ExercisesSessions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Exercise.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Session was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/checkListExercisesSessions/{sessId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Exercise>> getCheckListExercisesOfSession(@ApiParam(value = "", required = true) @PathVariable("sessId") String sessId);

    @ApiOperation(value = "Update practical to History", nickname = "updatePractical", notes = "", response = History.class, responseContainer = "List", tags={ "ExercisesSessions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = History.class, responseContainer = "List"),
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/history/updatePractical",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Integer> updatePractical(@ApiParam(value = "" ,required=true )  @Valid @RequestBody List<History> lstHistory);

}
