package io.swagger.api;

import io.swagger.model.LoginModel;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.ProgramUserService;
import io.swagger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    ProgramUserService programUserService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<User>> createNewUser(@ApiParam(value = "User object that needs to be added to the gym" ,required=true )  @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"password\" : \"password\",\n  \"role\" : 6,\n  \"_id\" : 0,\n  \"userName\" : \"userName\",\n  \"points\" : 1\n}, {\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"password\" : \"password\",\n  \"role\" : 6,\n  \"_id\" : 0,\n  \"userName\" : \"userName\",\n  \"points\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUserByUserName(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("userName") String userName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*public ResponseEntity<User> getUserByUserName(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userName") String userName) {

        User user = userService.getUserByUserName(userName);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }*/

    public ResponseEntity<List<User>> getUsersByName(@ApiParam(value = "") @Valid @RequestParam(value = "lastName", required = false) String lastName,@ApiParam(value = "") @Valid @RequestParam(value = "firstName", required = false) String firstName) {
        //need to rewrite again, it's just an example
        List<User> lst = new ArrayList<User>();
        //if (!firstName.isEmpty())
        //{
            //lst = userService.getUserByLastName(lastName);
        //}

        String res = new String();
        try {
            lst = userService.getAll();

        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<User>>(lst, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUserByUserName(@ApiParam(value = "User object that needs to be update to the gym" ,required=true )  @Valid @RequestBody User body,@ApiParam(value = "name that need to be updated",required=true) @PathVariable("userName") String userName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*
    * Check Login
    * */
    public ResponseEntity<LoginModel> CheckLogin(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        LoginModel user = new LoginModel();
        if (accept != null && accept.contains("application/json")) {
            try {
                user = userService.checkUserLogin(body);
                return new ResponseEntity<LoginModel>(user, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<LoginModel>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<LoginModel>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*
    * Get user by Id
    * */

    public ResponseEntity<User> getUserById(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("uId") String uId) {

        User user = new User();

        if(uId!=null){
            user = userService.getUserById(uId);
        }

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    public ResponseEntity<Integer> deleteUserById(@ApiParam(value = "The id that needs to be deleted",required=true) @PathVariable("userId") String userId) {
        int result;

        result = userService.deleteUserById(userId);

        return new ResponseEntity<Integer>(result,HttpStatus.OK);
    }

    public ResponseEntity<Integer> assignUserPrograms(@ApiParam(value = "",required=true) @Valid @RequestBody User user,
                                                      @NotNull @ApiParam(value = "", required = false)
                                                      @Valid @RequestParam(value = "listProg", required = false) String[] listProg,
                                                      @NotNull @ApiParam(value = "", required = false)
                                                      @Valid @RequestParam(value = "coachId", required = false) String coachId) {
        if(listProg != null){

            //update list sessions to program
            programUserService.saveListProgramsByUserId(user.getId(), listProg, coachId);
            return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }

        return new ResponseEntity<Integer>(0, HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAllCustomers(){
        try {
            List<User> lst = userService.getAllCustomer();
            return new ResponseEntity<List<User>>(lst, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
