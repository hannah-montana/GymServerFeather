/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Session;
import io.swagger.annotations.*;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Api(value = "sessions", description = "the sessions API")
public interface SessionsApi {


    @ApiOperation(value = "Add a new session", nickname = "createNewSession", notes = "", response = Session.class, responseContainer = "List", tags={ "Session", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Session.class, responseContainer = "List"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/sessions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Session> createNewSession(@ApiParam(value = "Session object that needs to be added to the gym" ,required=true )  @Valid @RequestBody Session sess);


    @ApiOperation(value = "Delete session", nickname = "deleteSessionById", notes = "Delete an session.", tags={ "Session", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid sesId supplied"),
        @ApiResponse(code = 404, message = "Program not found") })
    @RequestMapping(value = "/sessions/{sesId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Integer> deleteSessionById(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("sesId") String sesId);


    @ApiOperation(value = "get session by its ID", nickname = "getSessionsById", notes = "", response = Session.class, tags={ "Session", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Session.class),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Session was not found"),
        @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/sessions/{sesId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Session> getSessionsById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("sesId") String sesId);


    @ApiOperation(value = "Find session by its name and its keywords.", nickname = "getSessionsByName", notes = "", response = Session.class, responseContainer = "List", tags={ "Session", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Session.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found."),
        @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/sessions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Session>> getSessionsByName(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "sesName", required = false) String sesName,@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "keyWords", required = false) String keyWords);


    @ApiOperation(value = "Update an existing session", nickname = "updateSessionById", notes = "", tags={ "Session", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Session not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/sessions/{sesId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Session> updateSession(@ApiParam(value = "Session that need to be updated",required=true) @PathVariable("session") Session sess);

}
