/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.*;
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

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Add a new user to the gym club", nickname = "createNewUser", notes = "", response = User.class, responseContainer = "List", tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Integer> createNewUser(@ApiParam(value = "User object that needs to be added to the gym" ,required=true )  @Valid @RequestBody User body);


    @ApiOperation(value = "Delete user", nickname = "deleteUserByUserName", notes = "Delete an user.", tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid username supplied"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{userName}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUserByUserName(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("userName") String userName);


    @ApiOperation(value = "Find users by lastName or firstName.", nickname = "getUsersByName", notes = "", response = User.class, responseContainer = "List", tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found."),
        @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsersByName(@ApiParam(value = "") @Valid @RequestParam(value = "lastName", required = false) String lastName,@ApiParam(value = "") @Valid @RequestParam(value = "firstName", required = false) String firstName);


    @ApiOperation(value = "Update an existing user", nickname = "updateUserByUserName", notes = "", tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/updateUsers",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Integer> updateUserByUserName(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body);

    /*
    * Check login
    * */
    @ApiOperation(value = "Check login", nickname = "CheckLogin", notes = "", response = User.class, tags={ "LoginModel", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LoginModel.class),
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/users/checkLogin",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<LoginModel> CheckLogin(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body);

    /*
    * get User by id
    * */

    @ApiOperation(value = "get a user by id", nickname = "getUserById", notes = "", response = User.class, tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Exercise was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/{uId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<User> getUserById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("uId") String uId);

    @ApiOperation(value = "Delete user", nickname = "deleteUserById", notes = "Delete an user.", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid userId supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{userId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Integer> deleteUserById(@ApiParam(value = "The id that needs to be deleted",required=true) @PathVariable("userId") String userId);

    @ApiOperation(value = "Assign programs to user", nickname = "assignUserPrograms", notes = "", tags={ "ProgramUser", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/users/programs",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Integer> assignUserPrograms(@ApiParam(value = "",required=true) @Valid @RequestBody User user,
                                               @NotNull @ApiParam(value = "", required = false)
                                               @Valid @RequestParam(value = "listProg", required = false) String[] listProg,
                                               @NotNull @ApiParam(value = "", required = false)
                                               @Valid @RequestParam(value = "coachId", required = false) String coachId);


    @ApiOperation(value = "get all customers with role=2.", nickname = "getAllCustomers", notes = "", response = User.class, responseContainer = "List", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/getAllCustomers",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<User>> getAllCustomers();

    @ApiOperation(value = "Check existed User name", nickname = "checkExistedUserName", notes = "", response = Integer.class, tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Exercise was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/checkExistedUserName/{userName}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Integer> checkExistedUserName(@ApiParam(value = "",required=true) @PathVariable("userName") String userName);

    @ApiOperation(value = "Update photo", nickname = "updatePhoto", notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/updatePhoto",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Integer> updatePhoto(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body);

    /*
     * get CustomerDashboard
     * */

    @ApiOperation(value = "get CustomerDashboard object", nickname = "getCustomerDashboard", response = CustomerDashboard.class, notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomerDashboard.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/customerDashboard/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CustomerDashboard> getCustomerDashboard(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);

    /*
     * get CustomerDashboard
     * */

    @ApiOperation(value = "get HealthStatus object", nickname = "getHeathStatus", response = Integer.class, notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/healthStatus/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Integer> getHeathStatus(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);

    /*
     * get CustomerDashboard
     * */

    @ApiOperation(value = "get CurrentCustomer object", nickname = "getCurrentCustomerDashboard", response = CurrentCustomer.class, notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CurrentCustomer.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/currentCustomerDashboard/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CurrentCustomer> getCurrentCustomerDashboard(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);


    /*
     * get History Points
     * */

    @ApiOperation(value = "get History Points", nickname = "getHisotrySession",  notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/historyPoint/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<DataPoint>> getHisotryPointOfSession(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);

    /*
     * get History Calorie
     * */

    @ApiOperation(value = "get History Calorie", nickname = "getHisotryCalorie",  notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/users/historyCalorie/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<DataPoint>> getHisotryCalorieOfSession(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);


}
